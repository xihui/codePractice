package com.rti.xihui.fromscratch.idl;

import com.rti.dds.domain.DomainParticipant;
import com.rti.dds.domain.DomainParticipantFactory;
import com.rti.dds.infrastructure.ResourceLimitsQosPolicy;
import com.rti.dds.infrastructure.StatusKind;
import com.rti.dds.infrastructure.StringSeq;
import com.rti.dds.publication.Publisher;
import com.rti.dds.publication.PublisherQos;
import com.rti.dds.subscription.DataReader;
import com.rti.dds.subscription.DataReaderAdapter;
import com.rti.dds.subscription.DataReaderListener;
import com.rti.dds.subscription.InstanceStateKind;
import com.rti.dds.subscription.SampleInfo;
import com.rti.dds.subscription.SampleInfoSeq;
import com.rti.dds.subscription.SampleStateKind;
import com.rti.dds.subscription.Subscriber;
import com.rti.dds.subscription.ViewStateKind;
import com.rti.dds.topic.Topic;
import com.rti.dds.type.builtin.StringDataReader;
import com.rti.dds.type.builtin.StringTypeSupport;

public class HelloMsgSubscriber {

	public static void main(String[] args) {

		DomainParticipant participant = null;
		try {
			participant = DomainParticipantFactory.get_instance()
					.create_participant(HelloMsgPublisher.DOMAIN_ID,
							DomainParticipantFactory.PARTICIPANT_QOS_DEFAULT,
							null, StatusKind.STATUS_MASK_NONE);
			HelloMsgTypeSupport.register_type(participant, HelloMsgTypeSupport.get_type_name());
			
			Subscriber subscriber = participant.create_subscriber(
					DomainParticipant.SUBSCRIBER_QOS_DEFAULT, null,
					StatusKind.STATUS_MASK_NONE);
			Topic topic = participant.create_topic(
					HelloMsgPublisher.TOPIC_NAME,
					HelloMsgTypeSupport.get_type_name(),
					DomainParticipant.TOPIC_QOS_DEFAULT, null,
					StatusKind.STATUS_MASK_NONE);
			DataReaderListener listener = new DataReaderAdapter() {
				private HelloMsgSeq msgSeq = new HelloMsgSeq();
				private SampleInfoSeq infoSeq=  new SampleInfoSeq();
				
				@Override
				public void on_data_available(DataReader reader) {
					try {
						((HelloMsgDataReader) reader).take(msgSeq , infoSeq,
								ResourceLimitsQosPolicy.LENGTH_UNLIMITED,
								SampleStateKind.ANY_SAMPLE_STATE,
								ViewStateKind.ANY_VIEW_STATE,
								InstanceStateKind.ANY_INSTANCE_STATE);
						for(int i=0; i<msgSeq.size(); i++){
							SampleInfo info = (SampleInfo)infoSeq.get(i);
							if(info.valid_data)
								System.out.println("Received: " + (HelloMsg)msgSeq.get(i));
							else
								System.out.println("Invalidate Data!");					
						}
					} catch (Exception e) {
						e.printStackTrace();
					}finally{
						((HelloMsgDataReader)reader).return_loan(msgSeq, infoSeq);
					}
				}
			};
			subscriber.create_datareader(topic,
					Subscriber.DATAREADER_QOS_DEFAULT, listener,
					StatusKind.STATUS_MASK_ALL);
			
			Thread.sleep(12000);

		}catch(Exception exception){
			exception.printStackTrace();
		}finally{
			if(participant !=null){
				participant.delete_contained_entities();
				DomainParticipantFactory.TheParticipantFactory.delete_participant(participant);
			}
		}

	}
}

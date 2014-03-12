package com.rti.xihui.case25434.sparselyerror;

import com.rti.dds.domain.DomainParticipant;
import com.rti.dds.domain.DomainParticipantFactory;
import com.rti.dds.domain.DomainParticipantQos;
import com.rti.dds.infrastructure.Property_t;
import com.rti.dds.infrastructure.ResourceLimitsQosPolicy;
import com.rti.dds.infrastructure.StatusKind;
import com.rti.dds.infrastructure.StringSeq;
import com.rti.dds.publication.Publisher;
import com.rti.dds.publication.PublisherQos;
import com.rti.dds.subscription.DataReader;
import com.rti.dds.subscription.DataReaderAdapter;
import com.rti.dds.subscription.DataReaderListener;
import com.rti.dds.subscription.DataReaderQos;
import com.rti.dds.subscription.InstanceStateKind;
import com.rti.dds.subscription.SampleInfo;
import com.rti.dds.subscription.SampleInfoSeq;
import com.rti.dds.subscription.SampleStateKind;
import com.rti.dds.subscription.Subscriber;
import com.rti.dds.subscription.ViewStateKind;
import com.rti.dds.topic.Topic;
import com.rti.dds.type.builtin.Bytes;
import com.rti.dds.type.builtin.BytesDataReader;
import com.rti.dds.type.builtin.BytesSeq;
import com.rti.dds.type.builtin.BytesTypeSupport;
import com.rti.dds.type.builtin.StringDataReader;
import com.rti.dds.type.builtin.StringTypeSupport;

public class BuiltinBytesSubscriber {

	public static void main(String[] args) {

		DomainParticipant participant = null;
		try {
			DomainParticipantQos participantQos = new DomainParticipantQos();
			DomainParticipantFactory.get_instance().get_default_participant_qos(participantQos);
//			participantQos.property.value.add(new Property_t(
//					"dds.builtin_type.octets.max_size", 
//					""+HelloDynamicWorldType.HELLO_MAX_PAYLOAD_SIZE, true));
//			participantQos.resource_limits.local_reader_allocation.initial_count=4;
//			participantQos.resource_limits.local_reader_allocation.max_count=4;
//			participantQos.resource_limits.local_reader_allocation.incremental_count=0;
//			participantQos.discovery_config.subscription_writer.heartbeats_per_max_samples=1;
			participant = DomainParticipantFactory.get_instance()
					.create_participant(BuiltinBytesPublisher.DOMAIN_ID,
							participantQos, 
							null, StatusKind.STATUS_MASK_NONE);
//			participant = DomainParticipantFactory.get_instance()
//					.create_participant(BuiltinBytesPublisher.DOMAIN_ID,
//							participantQos,
//							null, StatusKind.STATUS_MASK_NONE);
			
			Subscriber subscriber = participant.create_subscriber(
					DomainParticipant.SUBSCRIBER_QOS_DEFAULT, null,
					StatusKind.STATUS_MASK_NONE);
			Topic topic = participant.create_topic(
					BuiltinBytesPublisher.TOPIC_NAME,
					BytesTypeSupport.get_type_name(),
					DomainParticipant.TOPIC_QOS_DEFAULT, null,
					StatusKind.STATUS_MASK_NONE);
			DataReaderListener listener = new DataReaderAdapter() {
				private BytesSeq bytesSeq = new BytesSeq();
				private SampleInfoSeq infoSeq=  new SampleInfoSeq();
				
				@Override
				public void on_data_available(DataReader reader) {
					try {
						((BytesDataReader) reader).take(bytesSeq , infoSeq,
								ResourceLimitsQosPolicy.LENGTH_UNLIMITED,
								SampleStateKind.ANY_SAMPLE_STATE,
								ViewStateKind.ANY_VIEW_STATE,
								InstanceStateKind.ANY_INSTANCE_STATE);
						for(int i=0; i<bytesSeq.size(); i++){
							SampleInfo info = (SampleInfo)infoSeq.get(i);
							if(info.valid_data)
								System.out.println("Received: " + ((Bytes) bytesSeq.get(i)).length);
							else
								System.out.println("Invalidate Data!");					
						}
					} catch (Exception e) {
						e.printStackTrace();
					}finally{
						((BytesDataReader)reader).return_loan(bytesSeq, infoSeq);
					}
					
				}
			};
			DataReaderQos readerQos = new DataReaderQos();
			subscriber.get_default_datareader_qos(readerQos);
			subscriber.create_datareader(topic,
					readerQos, listener,
					StatusKind.STATUS_MASK_ALL);
			
			Thread.sleep(120000);

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

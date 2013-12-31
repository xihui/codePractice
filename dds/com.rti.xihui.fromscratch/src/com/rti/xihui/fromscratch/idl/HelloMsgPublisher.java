package com.rti.xihui.fromscratch.idl;

import com.rti.dds.domain.DomainParticipant;
import com.rti.dds.domain.DomainParticipantFactory;
import com.rti.dds.infrastructure.InstanceHandle_t;
import com.rti.dds.infrastructure.IntSeq;
import com.rti.dds.infrastructure.StatusKind;
import com.rti.dds.publication.DataWriter;
import com.rti.dds.publication.Publisher;
import com.rti.dds.topic.Topic;

public class HelloMsgPublisher {
	static final String TOPIC_NAME = "XihuiFirstScratch.HelloMsg";
	final static int DOMAIN_ID = 1;
	
	public static void main(String[] args) throws InterruptedException {
		
		DomainParticipant participant = null;
		try{
			participant = DomainParticipantFactory.get_instance().create_participant(
					DOMAIN_ID, DomainParticipantFactory.PARTICIPANT_QOS_DEFAULT, null, StatusKind.STATUS_MASK_NONE);
		
			HelloMsgTypeSupport.register_type(participant, HelloMsgTypeSupport.get_type_name());
			Topic topic = participant.create_topic(TOPIC_NAME, HelloMsgTypeSupport.get_type_name(), DomainParticipant.TOPIC_QOS_DEFAULT, null, StatusKind.STATUS_MASK_NONE);
			Publisher publisher = participant.create_publisher(DomainParticipant.PUBLISHER_QOS_DEFAULT, null, StatusKind.STATUS_MASK_NONE);
			
			HelloMsgDataWriter dataWriter = (HelloMsgDataWriter) publisher.create_datawriter(topic, Publisher.DATAWRITER_QOS_DEFAULT, null, StatusKind.STATUS_MASK_NONE);
			HelloMsg instance_data = new HelloMsg();
			for(int i=0; i<10; i++){
				instance_data.id = i;
				instance_data.msg = "hello " + i;
				instance_data.mySeq = new IntSeq(new int[]{12,12,23,34,45,456,12312});
				dataWriter.write(instance_data, InstanceHandle_t.HANDLE_NIL);
				System.out.println("write " + instance_data);
				Thread.sleep(1000);
			}
			
		}finally{
			if(participant !=null){
				participant.delete_contained_entities();
				DomainParticipantFactory.TheParticipantFactory.delete_participant(participant);
			}
		}
	}
}

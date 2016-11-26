package com.rti.xihui.case25434.sparselyerror;

import com.rti.dds.domain.DomainParticipant;
import com.rti.dds.domain.DomainParticipantFactory;
import com.rti.dds.domain.DomainParticipantQos;
import com.rti.dds.dynamicdata.DynamicData;
import com.rti.dds.infrastructure.InstanceHandle_t;
import com.rti.dds.infrastructure.Property_t;
import com.rti.dds.infrastructure.StatusKind;
import com.rti.dds.publication.DataWriter;
import com.rti.dds.publication.DataWriterQos;
import com.rti.dds.publication.Publisher;
import com.rti.dds.topic.Topic;
import com.rti.dds.type.builtin.Bytes;
import com.rti.dds.type.builtin.BytesDataWriter;
import com.rti.dds.type.builtin.BytesTypeSupport;
import com.rti.dds.type.builtin.StringDataWriter;
import com.rti.dds.type.builtin.StringTypeSupport;

public class BuiltinBytesPublisher {
	static final String TOPIC_NAME = "XihuiFirstScratch.BuiltinBytes";
	final static int DOMAIN_ID = 21;

	public static void main(String[] args) throws InterruptedException {
		DomainParticipant participant = null;
		try {

			DomainParticipantQos domainParticipantQos = new DomainParticipantQos();
			DomainParticipantFactory.get_instance()
					.get_default_participant_qos(domainParticipantQos);
//			domainParticipantQos.property.value.add(new Property_t(
//					"dds.builtin_type.octets.max_size", 
//					""+HelloDynamicWorldType.HELLO_MAX_PAYLOAD_SIZE, true));
			participant = DomainParticipantFactory.get_instance()
					.create_participant(DOMAIN_ID,domainParticipantQos,
							null, StatusKind.STATUS_MASK_NONE);

			Publisher publisher = participant.create_publisher(
					DomainParticipant.PUBLISHER_QOS_DEFAULT, null,
					StatusKind.STATUS_MASK_NONE);

			Topic topic = participant.create_topic(TOPIC_NAME,
					BytesTypeSupport.get_type_name(),
					DomainParticipant.TOPIC_QOS_DEFAULT, null,
					StatusKind.STATUS_MASK_NONE);

			DataWriterQos dataWriterQos = new DataWriterQos();
			publisher.get_default_datawriter_qos(dataWriterQos);
//			dataWriterQos.property.value.add(new Property_t(
//					"dds.builtin_type.octets.alloc_size", "100000", true));
//			
			BytesDataWriter dataWriter = (BytesDataWriter) publisher
					.create_datawriter(topic, dataWriterQos, null,
							StatusKind.STATUS_MASK_NONE);
			Bytes instance = new Bytes();
			InstanceHandle_t instanceHandle = dataWriter.register_instance(instance);
			for (int i = 0; i < 10000; i++) {
				if(i==5)
					Thread.sleep(1000000);
				byte[] payload = new byte[(int) (HelloDynamicWorldType.HELLO_MAX_PAYLOAD_SIZE)];
				System.out.println("payload Length: " + payload.length);

				for (int j = 0; j < payload.length; j++) {
					payload[j] = (byte) ((Math.random() * 255 * j) % 0xff);
				}
				instance.length=payload.length;
				instance.value = payload;
				dataWriter.write(instance, instanceHandle);
//				dataWriter.write(payload, 0, payload.length,
//						InstanceHandle_t.HANDLE_NIL);
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (participant != null) {
				participant.delete_contained_entities();
				DomainParticipantFactory.TheParticipantFactory
						.delete_participant(participant);
			}
		}

	}
}

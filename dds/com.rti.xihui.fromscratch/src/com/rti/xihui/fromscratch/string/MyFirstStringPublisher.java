package com.rti.xihui.fromscratch.string;

import com.rti.dds.domain.DomainParticipant;
import com.rti.dds.domain.DomainParticipantFactory;
import com.rti.dds.domain.DomainParticipantQos;
import com.rti.dds.infrastructure.InstanceHandle_t;
import com.rti.dds.infrastructure.StatusKind;
import com.rti.dds.publication.DataWriter;
import com.rti.dds.publication.Publisher;
import com.rti.dds.topic.Topic;
import com.rti.dds.type.builtin.StringDataWriter;
import com.rti.dds.type.builtin.StringTypeSupport;

public class MyFirstStringPublisher {
	static final String TOPIC_NAME = "XihuiFirstScratch.String";
	final static int DOMAIN_ID = 21;

	public static void main(String[] args) throws InterruptedException {
		DomainParticipant participant = null;
		try {

			participant = DomainParticipantFactory.get_instance()
					.create_participant(DOMAIN_ID,
							DomainParticipantFactory.PARTICIPANT_QOS_DEFAULT,
							null, StatusKind.STATUS_MASK_NONE);

			Publisher publisher = participant.create_publisher(
					DomainParticipant.PUBLISHER_QOS_DEFAULT, null,
					StatusKind.STATUS_MASK_NONE);

			Topic topic = participant.create_topic(TOPIC_NAME,
					StringTypeSupport.get_type_name(),
					DomainParticipant.TOPIC_QOS_DEFAULT, null,
					StatusKind.STATUS_MASK_NONE);

			StringDataWriter dataWriter = (StringDataWriter) publisher
					.create_datawriter(topic, Publisher.DATAWRITER_QOS_DEFAULT,
							null, StatusKind.STATUS_MASK_NONE);

			for (int i = 0; i < 100; i++) {

				String s = "hello " + i;
				dataWriter.write(s, InstanceHandle_t.HANDLE_NIL);
				System.out.println(s);
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

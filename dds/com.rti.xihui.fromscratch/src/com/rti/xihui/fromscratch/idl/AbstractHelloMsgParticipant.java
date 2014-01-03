package com.rti.xihui.fromscratch.idl;

import java.util.concurrent.atomic.AtomicBoolean;

import com.rti.dds.domain.DomainParticipant;
import com.rti.dds.domain.DomainParticipantAdapter;
import com.rti.dds.domain.DomainParticipantFactory;
import com.rti.dds.domain.DomainParticipantQos;
import com.rti.dds.infrastructure.Property_t;
import com.rti.dds.infrastructure.StatusKind;
import com.rti.dds.infrastructure.TransportBuiltinKind;
import com.rti.dds.publication.DataWriter;
import com.rti.dds.publication.PublicationMatchedStatus;
import com.rti.dds.subscription.DataReader;
import com.rti.dds.subscription.Subscriber;
import com.rti.dds.topic.Topic;

public abstract class AbstractHelloMsgParticipant {
	protected DomainParticipant participant;
	protected Topic topic;
	protected AtomicBoolean isLive = new AtomicBoolean(true);
	public AbstractHelloMsgParticipant() {
		
		DomainParticipantQos participantQos = configParticipantQoS();
		
		
		
		participant = DomainParticipantFactory.get_instance()
				.create_participant(HelloMsgPublisher.DOMAIN_ID,
						participantQos, new HelloMsgParticipantListener(), StatusKind.STATUS_MASK_NONE);
		
	
		
		HelloMsgTypeSupport.register_type(participant,
				HelloMsgTypeSupport.get_type_name());
		
		topic = participant.create_topic(
				HelloMsgPublisher.TOPIC_NAME,
				HelloMsgTypeSupport.get_type_name(),
				DomainParticipant.TOPIC_QOS_DEFAULT, null,
				StatusKind.STATUS_MASK_NONE);	
	}

	protected DomainParticipantQos configParticipantQoS() {
		DomainParticipantQos participantQos = new DomainParticipantQos();
		
		DomainParticipantFactory.get_instance().get_default_participant_qos(
				participantQos);
//		participantQos.transport_builtin.mask = TransportBuiltinKind.SHMEM;
		//this seems doesn't affect the communication between 10.10.xx and 10.30.xx
//		participantQos.property.value.add(new Property_t("multicast_ttl", "0", true));

		participantQos.property.value.add(new Property_t("rti.monitor.library", "rtimonitoring", true));
		participantQos.property.value.add(new Property_t("rti.monitor.create_function", "RTIDefaultMonitor_create", true));
		return participantQos;
	}

	protected synchronized void dispose() {
		isLive.set(false);
		if (participant != null) {
			participant.delete_contained_entities();
			DomainParticipantFactory.TheParticipantFactory
					.delete_participant(participant);
			participant = null;
		}
	}
	
	
	private static class HelloMsgParticipantListener extends DomainParticipantAdapter{ 
		@Override
		public void on_publication_matched(DataWriter writer,
				PublicationMatchedStatus status) {
			System.out.println("Participant on_publication_matched: " + status);
		}
		
		@Override
		public void on_data_available(DataReader reader) {
			System.out.println("Participant on_data_available");
		}
		
		@Override
		public void on_data_on_readers(Subscriber subs) {
			System.out.println("Participant on_data_on_readers");
		}
	}
}

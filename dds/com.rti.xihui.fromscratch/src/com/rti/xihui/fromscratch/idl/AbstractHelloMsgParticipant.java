package com.rti.xihui.fromscratch.idl;

import java.util.concurrent.atomic.AtomicBoolean;

import com.rti.dds.domain.DomainParticipant;
import com.rti.dds.domain.DomainParticipantFactory;
import com.rti.dds.domain.DomainParticipantQos;
import com.rti.dds.infrastructure.StatusKind;
import com.rti.dds.infrastructure.TransportBuiltinKind;
import com.rti.dds.topic.Topic;

public abstract class AbstractHelloMsgParticipant {
	protected DomainParticipant participant;
	protected Topic topic;
	protected AtomicBoolean isLive = new AtomicBoolean(true);
	public AbstractHelloMsgParticipant() {
		
		DomainParticipantQos participantQos = new DomainParticipantQos();
		
		DomainParticipantFactory.get_instance().get_default_participant_qos(
				participantQos);
		participantQos.transport_builtin.mask = TransportBuiltinKind.UDPv4;
		
		participant = DomainParticipantFactory.get_instance()
				.create_participant(HelloMsgPublisher.DOMAIN_ID,
						participantQos, null, StatusKind.STATUS_MASK_NONE);
		
		HelloMsgTypeSupport.register_type(participant,
				HelloMsgTypeSupport.get_type_name());
		
		topic = participant.create_topic(
				HelloMsgPublisher.TOPIC_NAME,
				HelloMsgTypeSupport.get_type_name(),
				DomainParticipant.TOPIC_QOS_DEFAULT, null,
				StatusKind.STATUS_MASK_NONE);	
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
	
}

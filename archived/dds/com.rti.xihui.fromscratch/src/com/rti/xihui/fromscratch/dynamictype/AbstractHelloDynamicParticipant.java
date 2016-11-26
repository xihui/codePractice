package com.rti.xihui.fromscratch.dynamictype;

import java.util.concurrent.atomic.AtomicBoolean;

import com.rti.dds.domain.DomainParticipant;
import com.rti.dds.domain.DomainParticipantFactory;
import com.rti.dds.domain.DomainParticipantQos;
import com.rti.dds.dynamicdata.DynamicDataTypeSupport;
import com.rti.dds.infrastructure.Property_t;
import com.rti.dds.infrastructure.StatusKind;
import com.rti.dds.topic.Topic;
import com.rti.dds.typecode.TypeCode;

public class AbstractHelloDynamicParticipant {
	
	protected static final int DOMAIN_ID = 21;
	protected DomainParticipant participant;
	protected Topic topic;
	protected AtomicBoolean isLive = new AtomicBoolean(true);
	protected static final String TOPIC_NAME="HelloDynamicTopic";
	protected TypeCode helloDynamicType;
	
	protected AbstractHelloDynamicParticipant(){
		DomainParticipantQos participantQos = configParticipantQoS();

		participant = DomainParticipantFactory.get_instance()
				.create_participant(DOMAIN_ID, participantQos, null,
						StatusKind.STATUS_MASK_NONE);
		if (participant == null) {
			System.err.println("! Unable to create DDS domain participant");
			return;
		}
		helloDynamicType = HelloDynamicWorldType.create();

		if (helloDynamicType == null) {
			System.err.println("! Unable to create dynamic type code");
			return;
		}

		DynamicDataTypeSupport typeSupport = new DynamicDataTypeSupport(
				helloDynamicType, DynamicDataTypeSupport.TYPE_PROPERTY_DEFAULT);
		
		typeSupport.register_type(participant, HelloDynamicWorldType.getTypeName());
		
		topic = participant.create_topic(TOPIC_NAME, HelloDynamicWorldType.getTypeName(),
				DomainParticipant.TOPIC_QOS_DEFAULT, null,
				StatusKind.STATUS_MASK_NONE);
	
	}
	
	
	protected DomainParticipantQos configParticipantQoS() {
		DomainParticipantQos participantQos = new DomainParticipantQos();
		
		DomainParticipantFactory.get_instance().get_default_participant_qos(
				participantQos);
		
//		participantQos.resource_limits.type_code_max_serialized_length=0;
//		participantQos.resource_limits.type_object_max_serialized_length=20000;
////		
//		participantQos.property.value.add(new Property_t("dds.transport.UDPv4.builtin.recv_socket_buffer_size", "65500", true));
//		
//		participantQos.property.value.add(new Property_t("dds.transport.UDPv4.builtin.parent.message_size_max", "6500", true));
//		participantQos.property.value.add(new Property_t("dds.transport.UDPv4.builtin.send_socket_buffer_size", "65500", true));
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
	
}

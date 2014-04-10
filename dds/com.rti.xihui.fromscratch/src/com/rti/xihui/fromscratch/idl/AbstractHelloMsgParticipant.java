package com.rti.xihui.fromscratch.idl;

import java.util.concurrent.atomic.AtomicBoolean;

import com.rti.dds.domain.DomainParticipant;
import com.rti.dds.domain.DomainParticipantAdapter;
import com.rti.dds.domain.DomainParticipantFactory;
import com.rti.dds.domain.DomainParticipantQos;
import com.rti.dds.infrastructure.PropertyQosPolicyHelper;
import com.rti.dds.infrastructure.Property_t;
import com.rti.dds.infrastructure.StatusKind;
import com.rti.dds.infrastructure.TransportBuiltinKind;
import com.rti.dds.publication.DataWriter;
import com.rti.dds.publication.PublicationMatchedStatus;
import com.rti.dds.subscription.DataReader;
import com.rti.dds.subscription.Subscriber;
import com.rti.dds.topic.Topic;
import com.rti.ndds.config.Version;

public abstract class AbstractHelloMsgParticipant {
	protected static final int DOMAIN_ID = 21;
	protected DomainParticipant participant;
	protected Topic topic;
	protected AtomicBoolean isLive = new AtomicBoolean(true);
	public AbstractHelloMsgParticipant() {
		
		DomainParticipantQos participantQos = configParticipantQoS();
		
		Property_t targetProperty = PropertyQosPolicyHelper.lookup_property(participantQos.property, "dds.sys_info.target");
		System.out.println("Target Arch:" + targetProperty.value);
		
		participant = DomainParticipantFactory.get_instance()
				.create_participant(AbstractHelloMsgParticipant.DOMAIN_ID,
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

		participantQos.participant_name.name="HelloMsg_Participant";
		
//		qosDiscoveryUDPOnly(participantQos);
//
		qosEnableMonitoring(participantQos);
//		
//		qosTypeCodeMessageSize(participantQos);
//		qosWireProtoco(participantQos);
		return participantQos;
	}
	
	protected void qosWireProtoco(DomainParticipantQos participantQos){
		participantQos.wire_protocol.rtps_well_known_ports.domain_id_gain = 1210;
	}
	
	protected void qosTypeCodeMessageSize(DomainParticipantQos participantQos){
		participantQos.resource_limits.type_code_max_serialized_length=0;
		participantQos.resource_limits.type_object_max_serialized_length=20000;
//		
		participantQos.property.value.add(new Property_t("dds.transport.UDPv4.builtin.recv_socket_buffer_size", "65530", true));
		
		participantQos.property.value.add(new Property_t("dds.transport.UDPv4.builtin.parent.message_size_max", "65530", true));
		participantQos.property.value.add(new Property_t("dds.transport.UDPv4.builtin.send_socket_buffer_size", "65530", true));
	
	}
	
	protected void qosEnableMonitoring(DomainParticipantQos participantQos){
		participantQos.property.value.add(new Property_t("rti.monitor.library", "rtimonitoring", true));
		participantQos.property.value.add(new Property_t("rti.monitor.create_function", "RTIDefaultMonitor_create", true));
	}
	
	protected void qosLocalReaderAllocation(DomainParticipantQos participantQos){
		participantQos.resource_limits.local_reader_allocation.initial_count=7;
		participantQos.resource_limits.local_reader_allocation.max_count=7;
		participantQos.resource_limits.local_reader_allocation.incremental_count=0;
		participantQos.discovery_config.subscription_writer.heartbeats_per_max_samples=1;
	}
	
	protected void qosLocalWriterAllocation(DomainParticipantQos participantQos){
		participantQos.resource_limits.local_writer_allocation.initial_count=5;
		participantQos.resource_limits.local_writer_allocation.max_count=5;
		participantQos.resource_limits.local_writer_allocation.incremental_count=0;
		participantQos.discovery_config.publication_writer.heartbeats_per_max_samples=1;
	}

	
	protected void qosDiscoveryUDPOnly(DomainParticipantQos participantQoS){
		participantQoS.transport_builtin.mask = TransportBuiltinKind.UDPv4;
		
		participantQoS.discovery.initial_peers.clear();
		participantQoS.discovery.initial_peers.add("udpv4://239.255.0.5");

		participantQoS.discovery.multicast_receive_addresses.clear();
		participantQoS.discovery.multicast_receive_addresses
				.add("udpv4://239.255.0.5");
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

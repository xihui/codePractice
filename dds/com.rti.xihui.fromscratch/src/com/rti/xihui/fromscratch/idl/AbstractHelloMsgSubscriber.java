package com.rti.xihui.fromscratch.idl;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.rti.dds.domain.DomainParticipant;
import com.rti.dds.domain.DomainParticipantQos;
import com.rti.dds.infrastructure.DurabilityQosPolicyKind;
import com.rti.dds.infrastructure.Duration_t;
import com.rti.dds.infrastructure.HistoryQosPolicyKind;
import com.rti.dds.infrastructure.LivelinessQosPolicyKind;
import com.rti.dds.infrastructure.Property_t;
import com.rti.dds.infrastructure.ReliabilityQosPolicyKind;
import com.rti.dds.infrastructure.StatusKind;
import com.rti.dds.infrastructure.TransportBuiltinKind;
import com.rti.dds.infrastructure.TransportMulticastSettings_t;
import com.rti.dds.subscription.DataReaderQos;
import com.rti.dds.subscription.Subscriber;
import com.rti.xihui.fromscratch.ui.SimpleGUI;

public abstract class AbstractHelloMsgSubscriber extends
		AbstractHelloMsgParticipant {

	protected Subscriber subscriber;

	protected AbstractHelloMsgSubscriber(String guiName) {
		SimpleGUI.createGUI(guiName, new Runnable() {

			@Override
			public void run() {
				dispose();
			}
		});

		subscriber = participant.create_subscriber(
				DomainParticipant.SUBSCRIBER_QOS_DEFAULT, null,
				StatusKind.STATUS_MASK_NONE);

	}


	protected DataReaderQos createDataReaderQos() {
		DataReaderQos dataReaderQos = new DataReaderQos();
		subscriber.get_default_datareader_qos(dataReaderQos);
		
		dataReaderQos.subscription_name.name="HelloMsg_DataReader";

//		qosEnableMulticastTransport(dataReaderQos);
		qosReliabilityDemo(dataReaderQos);
		
		return dataReaderQos;
	}
	
	protected void qosReliabilityDemo(DataReaderQos dataReaderQos){
		//Reliability
		dataReaderQos.reliability.kind = ReliabilityQosPolicyKind.RELIABLE_RELIABILITY_QOS;
		
		//History
		dataReaderQos.history.kind = HistoryQosPolicyKind.KEEP_LAST_HISTORY_QOS;
		dataReaderQos.history.depth = 30;	
		
		//Resource Limit
		dataReaderQos.resource_limits.max_samples = 50;
		dataReaderQos.resource_limits.initial_samples = 50;
	}
	
	protected void qosEnableMulticastTransport(DataReaderQos dataReaderQos){
		//enable only UDPv4 transports, not necessary, which is enabled by default
		dataReaderQos.transport_selection.enabled_transports.clear();;
		dataReaderQos.transport_selection.enabled_transports
			.add(TransportBuiltinKind.UDPv4_ALIAS);

		
		//Use multicast transport
		// This is also the default value, so not necessary to set it
		// dataReaderQos.multicast.kind =
		// TransportMulticastQosPolicyKind.AUTOMATIC_TRANSPORT_MULTICAST_QOS;
		try {
			TransportMulticastSettings_t settings = new TransportMulticastSettings_t();
			settings.receive_address = InetAddress.getByName("239.255.0.6");
			dataReaderQos.multicast.value.add(settings);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected DomainParticipantQos configParticipantQoS() {
		DomainParticipantQos participantQos = super.configParticipantQoS();
//		participantQos.resource_limits.type_code_max_serialized_length=0;
//		participantQos.resource_limits.type_object_max_serialized_length=20000;
////		
//		participantQos.property.value.add(new Property_t("dds.transport.UDPv4.builtin.recv_socket_buffer_size", "6553", true));
//		
//		participantQos.property.value.add(new Property_t("dds.transport.UDPv4.builtin.parent.message_size_max", "6553", true));
//		participantQos.property.value.add(new Property_t("dds.transport.UDPv4.builtin.send_socket_buffer_size", "6553", true));
		return participantQos;
	
	}

}

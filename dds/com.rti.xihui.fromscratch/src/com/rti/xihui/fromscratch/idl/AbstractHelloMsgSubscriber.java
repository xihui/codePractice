package com.rti.xihui.fromscratch.idl;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.rti.dds.domain.DomainParticipant;
import com.rti.dds.domain.DomainParticipantQos;
import com.rti.dds.infrastructure.DurabilityQosPolicyKind;
import com.rti.dds.infrastructure.Duration_t;
import com.rti.dds.infrastructure.HistoryQosPolicyKind;
import com.rti.dds.infrastructure.LivelinessQosPolicyKind;
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

	@Override
	protected DomainParticipantQos configParticipantQoS() {
		DomainParticipantQos participantQoS = super.configParticipantQoS();

//		participantQoS.discovery.multicast_receive_addresses.clear();
//		participantQoS.discovery.multicast_receive_addresses
//				.add("udpv4://239.255.0.2");
//
//		participantQoS.discovery.initial_peers.clear();
//		participantQoS.discovery.initial_peers.add("udpv4://239.255.0.3");
		return participantQoS;

	}

	protected DataReaderQos createDataReaderQos() {
		DataReaderQos dataReaderQos = new DataReaderQos();
		subscriber.get_default_datareader_qos(dataReaderQos);
		
//		dataReaderQos.subscription_name.name="datareader_name";
//		dataReaderQos.liveliness.kind = LivelinessQosPolicyKind.AUTOMATIC_LIVELINESS_QOS;
//		dataReaderQos.liveliness.lease_duration.sec =100;
		
		
		//Reliability
		dataReaderQos.reliability.kind = ReliabilityQosPolicyKind.RELIABLE_RELIABILITY_QOS;
		
		//History
		dataReaderQos.history.kind = HistoryQosPolicyKind.KEEP_LAST_HISTORY_QOS;
		dataReaderQos.history.depth = 3;

		//Durability. This only work if reliability is RELIABLE.
//		dataReaderQos.durability.kind = DurabilityQosPolicyKind.PERSISTENT_DURABILITY_QOS;//TRANSIENT_LOCAL_DURABILITY_QOS;

		
		
		//Resource Limit
		dataReaderQos.resource_limits.max_samples = 20;
		dataReaderQos.resource_limits.initial_samples = 20;

		//enable UDPv4 transports
//		dataReaderQos.transport_selection.enabled_transports
//				.add(TransportBuiltinKind.UDPv4_ALIAS);
//
//		
//		//Use multicast transport
//		// This is also the default value, so not necessary to set it
//		// dataReaderQos.multicast.kind =
//		// TransportMulticastQosPolicyKind.AUTOMATIC_TRANSPORT_MULTICAST_QOS;
//		try {
//			TransportMulticastSettings_t settings = new TransportMulticastSettings_t();
//			settings.receive_address = InetAddress.getByName("239.255.0.2");
//			dataReaderQos.multicast.value.add(settings);
//		} catch (UnknownHostException e) {
//			e.printStackTrace();
//		}

		return dataReaderQos;
	}

}

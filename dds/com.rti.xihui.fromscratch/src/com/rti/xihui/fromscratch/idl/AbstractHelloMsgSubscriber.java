package com.rti.xihui.fromscratch.idl;

import com.rti.dds.domain.DomainParticipant;
import com.rti.dds.domain.DomainParticipantFactory;
import com.rti.dds.infrastructure.DurabilityQosPolicyKind;
import com.rti.dds.infrastructure.HistoryQosPolicyKind;
import com.rti.dds.infrastructure.ReliabilityQosPolicyKind;
import com.rti.dds.infrastructure.StatusKind;
import com.rti.dds.subscription.DataReaderQos;
import com.rti.dds.subscription.Subscriber;
import com.rti.xihui.fromscratch.ui.SimpleGUI;

public abstract class AbstractHelloMsgSubscriber extends AbstractHelloMsgParticipant{

	protected Subscriber subscriber;
	
	protected AbstractHelloMsgSubscriber(String guiName){
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
	
	protected DataReaderQos createDataReaderQos(){
		DataReaderQos dataReaderQos = new DataReaderQos();
		subscriber.get_default_datareader_qos(dataReaderQos);
		dataReaderQos.reliability.kind = ReliabilityQosPolicyKind.RELIABLE_RELIABILITY_QOS;
		dataReaderQos.history.kind = HistoryQosPolicyKind.KEEP_LAST_HISTORY_QOS;
		dataReaderQos.history.depth = 1;
		
		dataReaderQos.durability.kind=DurabilityQosPolicyKind.TRANSIENT_LOCAL_DURABILITY_QOS;
		
		dataReaderQos.resource_limits.max_samples = 20;
		dataReaderQos.resource_limits.initial_samples = 20;
		dataReaderQos.transport_selection.enabled_transports.add("udpv4");
		return dataReaderQos;
	}
	
}

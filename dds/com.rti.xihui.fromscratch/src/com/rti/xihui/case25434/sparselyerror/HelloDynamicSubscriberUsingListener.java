package com.rti.xihui.case25434.sparselyerror;

import com.rti.dds.domain.DomainParticipant;
import com.rti.dds.dynamicdata.DynamicData;
import com.rti.dds.dynamicdata.DynamicDataReader;
import com.rti.dds.dynamicdata.DynamicDataSeq;
import com.rti.dds.infrastructure.ByteSeq;
import com.rti.dds.infrastructure.ConditionSeq;
import com.rti.dds.infrastructure.DurabilityQosPolicyKind;
import com.rti.dds.infrastructure.Duration_t;
import com.rti.dds.infrastructure.GuardCondition;
import com.rti.dds.infrastructure.RETCODE_NO_DATA;
import com.rti.dds.infrastructure.RETCODE_TIMEOUT;
import com.rti.dds.infrastructure.ReliabilityQosPolicyKind;
import com.rti.dds.infrastructure.ResourceLimitsQosPolicy;
import com.rti.dds.infrastructure.StatusCondition;
import com.rti.dds.infrastructure.StatusKind;
import com.rti.dds.infrastructure.WaitSet;
import com.rti.dds.subscription.DataReader;
import com.rti.dds.subscription.DataReaderAdapter;
import com.rti.dds.subscription.DataReaderQos;
import com.rti.dds.subscription.InstanceStateKind;
import com.rti.dds.subscription.LivelinessChangedStatus;
import com.rti.dds.subscription.ReadCondition;
import com.rti.dds.subscription.SampleInfo;
import com.rti.dds.subscription.SampleInfoSeq;
import com.rti.dds.subscription.SampleStateKind;
import com.rti.dds.subscription.Subscriber;
import com.rti.dds.subscription.SubscriptionMatchedStatus;
import com.rti.dds.subscription.ViewStateKind;
import com.rti.xihui.fromscratch.ui.SimpleGUI;

public class HelloDynamicSubscriberUsingListener extends
		AbstractHelloDynamicParticipant {


	public HelloDynamicSubscriberUsingListener() {
		SimpleGUI.createGUI("Dynamic Subscriber", new Runnable() {

			@Override
			public void run() {
				dispose();
			}
		});

		Subscriber subscriber = participant.create_subscriber(
				DomainParticipant.SUBSCRIBER_QOS_DEFAULT, null,
				StatusKind.STATUS_MASK_NONE);

		DataReaderQos dataReaderQos = new DataReaderQos();
		subscriber.get_default_datareader_qos(dataReaderQos);

//		dataReaderQos.reliability.kind = ReliabilityQosPolicyKind.RELIABLE_RELIABILITY_QOS;
//		dataReaderQos.durability.kind = DurabilityQosPolicyKind.PERSISTENT_DURABILITY_QOS;
		
		DynamicDataReader reader = (DynamicDataReader) subscriber
				.create_datareader(topic, dataReaderQos, new DynamicTypeReaderListener(),
						StatusKind.STATUS_MASK_ALL);

		

	}


	public static void main(String[] args) {
		new HelloDynamicSubscriberUsingListener();

	}
	
	class DynamicTypeReaderListener extends DataReaderAdapter{
		DynamicDataSeq msgSeq = new DynamicDataSeq();
		SampleInfoSeq infoSeq = new SampleInfoSeq();
		@Override
		public void on_data_available(DataReader reader) {
			try {
				((DynamicDataReader)reader).take(msgSeq, infoSeq,
						ResourceLimitsQosPolicy.LENGTH_UNLIMITED,
						SampleStateKind.ANY_SAMPLE_STATE,
						ViewStateKind.ANY_VIEW_STATE,
						InstanceStateKind.ANY_INSTANCE_STATE);
			System.out.println("Received: ");
			for (int j = 0; j < msgSeq.size(); j++) {
				SampleInfo info = (SampleInfo) infoSeq.get(j);
				if (info.valid_data) {
					DynamicData data = (DynamicData) msgSeq
							.get(j);
//					System.out
//							.println("\tsampleId: "
//									+ data.get_int(
//											HelloDynamicWorldType.SAMPLE_ID_FIELD,
//											DynamicData.MEMBER_ID_UNSPECIFIED));
//					System.out
//							.println("\tname: "
//									+ data.get_string(
//											HelloDynamicWorldType.NAME_FIELD,
//											DynamicData.MEMBER_ID_UNSPECIFIED));
					ByteSeq payload = new ByteSeq(
							HelloDynamicWorldType.HELLO_MAX_PAYLOAD_SIZE);
					data.get_byte_seq(
							payload,
							HelloDynamicWorldType.PAYLOAD_FIELD,
							DynamicData.MEMBER_ID_UNSPECIFIED);
					System.out.println("\tpayload: " + payload.size() );//+ " max: " + payload.getMaximum());

					System.out.println((DynamicData) msgSeq
							.get(j));
				} else
					// 7.4.6.6 Valid Data Flag
					System.out.println("Invalidate Data! "
							+ info.sample_state);
			}

			// Thread.sleep(1000);
		} catch (RETCODE_NO_DATA noData) {
			System.out.println("No data.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			((DynamicDataReader) reader).return_loan(msgSeq,
					infoSeq);
		}
		
		}
	}

}

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
import com.rti.dds.infrastructure.HistoryQosPolicyKind;
import com.rti.dds.infrastructure.RETCODE_NO_DATA;
import com.rti.dds.infrastructure.RETCODE_TIMEOUT;
import com.rti.dds.infrastructure.ReliabilityQosPolicyKind;
import com.rti.dds.infrastructure.ResourceLimitsQosPolicy;
import com.rti.dds.infrastructure.StatusCondition;
import com.rti.dds.infrastructure.StatusKind;
import com.rti.dds.infrastructure.WaitSet;
import com.rti.dds.subscription.DataReaderQos;
import com.rti.dds.subscription.InstanceStateKind;
import com.rti.dds.subscription.LivelinessChangedStatus;
import com.rti.dds.subscription.ReadCondition;
import com.rti.dds.subscription.SampleInfo;
import com.rti.dds.subscription.SampleInfoSeq;
import com.rti.dds.subscription.SampleLostStatus;
import com.rti.dds.subscription.SampleRejectedStatus;
import com.rti.dds.subscription.SampleStateKind;
import com.rti.dds.subscription.Subscriber;
import com.rti.dds.subscription.SubscriptionMatchedStatus;
import com.rti.dds.subscription.ViewStateKind;
import com.rti.xihui.fromscratch.ui.SimpleGUI;

public class HelloDynamicSubscriberUsingWaitSet extends
		AbstractHelloDynamicParticipant {

	private GuardCondition guardCondition;

	public HelloDynamicSubscriberUsingWaitSet() {
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
//		dataReaderQos.history.kind = HistoryQosPolicyKind.KEEP_ALL_HISTORY_QOS;
//		dataReaderQos.history.depth = 5;
//		dataReaderQos.resource_limits.max_samples = 5;
//		dataReaderQos.resource_limits.initial_samples=5;
		DynamicDataReader reader = (DynamicDataReader) subscriber
				.create_datareader(topic, dataReaderQos, null,
						StatusKind.STATUS_MASK_ALL);

		ReadCondition readCondition = reader.create_readcondition(
				SampleStateKind.ANY_SAMPLE_STATE,
				ViewStateKind.ANY_VIEW_STATE,
				InstanceStateKind.ALIVE_INSTANCE_STATE);

		StatusCondition statusCondition = reader.get_statuscondition();

		statusCondition
				.set_enabled_statuses(StatusKind.STATUS_MASK_ALL & (~StatusKind.DATA_AVAILABLE_STATUS));
						//StatusKind.SUBSCRIPTION_MATCHED_STATUS
						//| StatusKind.LIVELINESS_CHANGED_STATUS);

		guardCondition = new GuardCondition();

		WaitSet waitSet = new WaitSet();
		waitSet.attach_condition(readCondition);
		waitSet.attach_condition(statusCondition);
		waitSet.attach_condition(guardCondition);

		DynamicDataSeq msgSeq = new DynamicDataSeq();
		SampleInfoSeq infoSeq = new SampleInfoSeq();

		Duration_t timeout = new Duration_t(2, 0);

		while (isLive.get()) {

			try {
				ConditionSeq activeConditionSeq = new ConditionSeq();

				waitSet.wait(activeConditionSeq, timeout);

				for (int i = 0; i < activeConditionSeq.size(); i++) {
					if (activeConditionSeq.get(i) == statusCondition) {
						int statusMask = reader.get_status_changes();
						System.out.println("status: " + statusMask);
						if ((statusMask & StatusKind.LIVELINESS_CHANGED_STATUS) != 0) {
							LivelinessChangedStatus st = new LivelinessChangedStatus();
							reader.get_liveliness_changed_status(st);
							System.out.println("Liveliness changed:" + st);
						}

						if ((statusMask & StatusKind.SUBSCRIPTION_MATCHED_STATUS) != 0) {
							SubscriptionMatchedStatus st = new SubscriptionMatchedStatus();
							reader.get_subscription_matched_status(st);
							System.out.println("Subscription matched:" + st);
						}
						if((statusMask & StatusKind.SAMPLE_REJECTED_STATUS)!=0){
							SampleRejectedStatus st = new SampleRejectedStatus();
							reader.get_sample_rejected_status(st);
							System.out.println("Sample rejected: " + st);
						}
						if((statusMask & StatusKind.SAMPLE_LOST_STATUS)!=0){
							SampleLostStatus st = new SampleLostStatus();
							reader.get_sample_lost_status(st);
							System.out.println("Sample lost: " + st);
						}
						
					} else if (activeConditionSeq.get(i) == readCondition) {
						try {
//							reader.take_w_condition(msgSeq, infoSeq, 1,
//									readCondition);
							reader.take(msgSeq, infoSeq, 
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
									System.out
											.println("\tsampleId: "
													+ data.get_int(
															HelloDynamicWorldType.SAMPLE_ID_FIELD,
															DynamicData.MEMBER_ID_UNSPECIFIED));
									System.out
											.println("\tname: "
													+ data.get_string(
															HelloDynamicWorldType.NAME_FIELD,
															DynamicData.MEMBER_ID_UNSPECIFIED));
									ByteSeq payload = new ByteSeq(
											HelloDynamicWorldType.HELLO_MAX_PAYLOAD_SIZE);
									data.get_byte_seq(
											payload,
											HelloDynamicWorldType.PAYLOAD_FIELD,
											DynamicData.MEMBER_ID_UNSPECIFIED);
									System.out.println("\tpayload: " + payload);

									System.out.println((DynamicData) msgSeq
											.get(j));
								} else
									// 7.4.6.6 Valid Data Flag
									System.out.println("Invalidate Data! "
											+ info.sample_state);
							}

//							 Thread.sleep(1000);
						} catch (RETCODE_NO_DATA noData) {
							System.out.println("No data.");
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							((DynamicDataReader) reader).return_loan(msgSeq,
									infoSeq);
						}
					} else if (activeConditionSeq.get(i) == guardCondition) {
						System.out.println("Exited."
								+ guardCondition.get_trigger_value());
						guardCondition.delete();
					}
				}

			} catch (RETCODE_TIMEOUT e) {
				System.out
						.println("Wait timed out. No condition was triggered.");
			}

		}

	}

	@Override
	protected synchronized void dispose() {
		if (guardCondition != null)
			guardCondition.set_trigger_value(true);
		super.dispose();
	}

	public static void main(String[] args) {
		new HelloDynamicSubscriberUsingWaitSet();

	}

}

package com.rti.xihui.fromscratch.idl;

import com.rti.dds.infrastructure.ConditionSeq;
import com.rti.dds.infrastructure.Duration_t;
import com.rti.dds.infrastructure.GuardCondition;
import com.rti.dds.infrastructure.RETCODE_NO_DATA;
import com.rti.dds.infrastructure.RETCODE_TIMEOUT;
import com.rti.dds.infrastructure.ResourceLimitsQosPolicy;
import com.rti.dds.infrastructure.StatusCondition;
import com.rti.dds.infrastructure.StatusKind;
import com.rti.dds.infrastructure.WaitSet;
import com.rti.dds.subscription.InstanceStateKind;
import com.rti.dds.subscription.LivelinessChangedStatus;
import com.rti.dds.subscription.ReadCondition;
import com.rti.dds.subscription.SampleInfo;
import com.rti.dds.subscription.SampleInfoSeq;
import com.rti.dds.subscription.SampleStateKind;
import com.rti.dds.subscription.SubscriptionMatchedStatus;
import com.rti.dds.subscription.ViewStateKind;

public class HelloMsgSubscriberUsingWaitSet extends AbstractHelloMsgSubscriber {

	private GuardCondition guardCondition;

	public HelloMsgSubscriberUsingWaitSet() {
		super(HelloMsgSubscriberUsingWaitSet.class.getSimpleName());

		HelloMsgDataReader reader = (HelloMsgDataReader) subscriber
				.create_datareader(topic, createDataReaderQos(), null,
						StatusKind.STATUS_MASK_ALL);

		ReadCondition readCondition = reader.create_readcondition(
				SampleStateKind.NOT_READ_SAMPLE_STATE,
				ViewStateKind.ANY_VIEW_STATE,
				InstanceStateKind.ALIVE_INSTANCE_STATE);

		StatusCondition statusCondition = reader.get_statuscondition();

		statusCondition
				.set_enabled_statuses(StatusKind.SUBSCRIPTION_MATCHED_STATUS
						| StatusKind.LIVELINESS_CHANGED_STATUS);

		guardCondition = new GuardCondition();

		WaitSet waitSet = new WaitSet();
		waitSet.attach_condition(readCondition);
		waitSet.attach_condition(statusCondition);
		waitSet.attach_condition(guardCondition);

		HelloMsgSeq msgSeq = new HelloMsgSeq();
		SampleInfoSeq infoSeq = new SampleInfoSeq();

		Duration_t timeout = new Duration_t(2, 0);

		while (isLive.get()) {

			try {
				ConditionSeq activeConditionSeq = new ConditionSeq();

				waitSet.wait(activeConditionSeq, timeout);

				for (int i = 0; i < activeConditionSeq.size(); i++) {
					if (activeConditionSeq.get(i) == statusCondition) {
						int statusMask = reader.get_status_changes();
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
					} else if (activeConditionSeq.get(i) == readCondition) {
						try {
							reader.take_w_condition(msgSeq, infoSeq,
									ResourceLimitsQosPolicy.LENGTH_UNLIMITED,
									readCondition);
							for (int j = 0; j < msgSeq.size(); j++) {
								SampleInfo info = (SampleInfo) infoSeq.get(j);
								if (info.valid_data)
									System.out.println("Received: "
											+ (HelloMsg) msgSeq.get(j));
								else
									// 7.4.6.6 Valid Data Flag
									System.out.println("Invalidate Data! "
											+ info);
							}
							
							Thread.sleep(1000);
						} catch (RETCODE_NO_DATA noData) {
							System.out.println("No data.");
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							((HelloMsgDataReader) reader).return_loan(msgSeq,
									infoSeq);
						}
					}else if(activeConditionSeq.get(i)== guardCondition){
						System.out.println("Exited." + guardCondition.get_trigger_value());
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
		new HelloMsgSubscriberUsingWaitSet();

	}

}

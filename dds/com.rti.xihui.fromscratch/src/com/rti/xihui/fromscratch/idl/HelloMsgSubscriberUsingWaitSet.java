package com.rti.xihui.fromscratch.idl;

import com.rti.dds.infrastructure.ConditionSeq;
import com.rti.dds.infrastructure.Duration_t;
import com.rti.dds.infrastructure.GuardCondition;
import com.rti.dds.infrastructure.InstanceHandleSeq;
import com.rti.dds.infrastructure.InstanceHandle_t;
import com.rti.dds.infrastructure.RETCODE_NO_DATA;
import com.rti.dds.infrastructure.RETCODE_TIMEOUT;
import com.rti.dds.infrastructure.ResourceLimitsQosPolicy;
import com.rti.dds.infrastructure.StatusCondition;
import com.rti.dds.infrastructure.StatusKind;
import com.rti.dds.infrastructure.WaitSet;
import com.rti.dds.subscription.InstanceStateKind;
import com.rti.dds.subscription.LivelinessChangedStatus;
import com.rti.dds.subscription.QueryCondition;
import com.rti.dds.subscription.ReadCondition;
import com.rti.dds.subscription.SampleInfo;
import com.rti.dds.subscription.SampleInfoSeq;
import com.rti.dds.subscription.SampleLostStatus;
import com.rti.dds.subscription.SampleRejectedStatus;
import com.rti.dds.subscription.SampleStateKind;
import com.rti.dds.subscription.SubscriptionMatchedStatus;
import com.rti.dds.subscription.ViewStateKind;
import com.rti.dds.topic.builtin.TopicBuiltinTopicData;

public class HelloMsgSubscriberUsingWaitSet extends AbstractHelloMsgSubscriber {

	private GuardCondition guardCondition;

	public HelloMsgSubscriberUsingWaitSet() {
		super(HelloMsgSubscriberUsingWaitSet.class.getSimpleName());

		
		HelloMsgDataReader reader = (HelloMsgDataReader) subscriber
				.create_datareader(topic, createDataReaderQos(), null,
						StatusKind.STATUS_MASK_ALL);

		//Don't use readCondition in simple cases, it uses more CPU than StatusCondition
		ReadCondition readCondition = reader.create_readcondition(
				SampleStateKind.ANY_SAMPLE_STATE,
				ViewStateKind.ANY_VIEW_STATE,
				InstanceStateKind.ALIVE_INSTANCE_STATE);
		
		
		StatusCondition statusCondition = reader.get_statuscondition();

		statusCondition
				.set_enabled_statuses(StatusKind.STATUS_MASK_ALL);// & (~StatusKind.DATA_AVAILABLE_STATUS));
//		StatusKind.SUBSCRIPTION_MATCHED_STATUS
//						| StatusKind.LIVELINESS_CHANGED_STATUS
//						|StatusKind.SAMPLE_LOST_STATUS
//						|StatusKind.SAMPLE_REJECTED_STATUS );

		guardCondition = new GuardCondition();

		WaitSet waitSet = new WaitSet();
//		waitSet.attach_condition(readCondition);
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
						System.out.println("statusMask = " + statusMask);
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
						if ((statusMask & StatusKind.SAMPLE_LOST_STATUS) != 0) {
							SampleLostStatus sampleLostStatus = new SampleLostStatus();
							reader.get_sample_lost_status(sampleLostStatus);
							System.out.println("Sample Lost:" + sampleLostStatus);
						}
						if ((statusMask & StatusKind.SAMPLE_REJECTED_STATUS) != 0) {
							SampleRejectedStatus sampleRejectedStatus = new SampleRejectedStatus();
							reader.get_sample_rejected_status(sampleRejectedStatus);
							System.out.println("Sample Rejected:" + sampleRejectedStatus);
						}
						if((statusMask & StatusKind.DATA_AVAILABLE_STATUS)!=0){
							System.out.println("data available");
							try {
								synchronized (this) {
										reader.read(msgSeq, infoSeq,
												ResourceLimitsQosPolicy.LENGTH_UNLIMITED,
												SampleStateKind.ANY_SAMPLE_STATE,
												ViewStateKind.ANY_VIEW_STATE,
												InstanceStateKind.ANY_INSTANCE_STATE
										);
								}
							
								System.out.println("StatusCondition Received: ");
								for (int j = 0; j < msgSeq.size(); j++) {								
									SampleInfo info = (SampleInfo) infoSeq.get(j);
									System.out.println(info.publication_virtual_guid + " " + info.original_publication_virtual_guid);
									if (info.valid_data)
										System.out.println(
												(HelloMsg) msgSeq.get(j));
									
										// 7.4.6.6 Valid Data Flag
										System.out.println("Invalidate Data! "
												+ info);
								}
								
								Thread.sleep(100);
							} catch (RETCODE_NO_DATA noData) {
								System.out.println("No data.");
							} catch (Exception e) {
								e.printStackTrace();
							} finally {
								((HelloMsgDataReader) reader).return_loan(msgSeq,
										infoSeq);
							}
						}
					} else if (activeConditionSeq.get(i) == readCondition) {
						try {
							synchronized (this) {
									reader.take_w_condition(msgSeq, infoSeq,
									100,
									readCondition);
							}
						
							System.out.println("Read Condition Received: ");
							for (int j = 0; j < msgSeq.size(); j++) {								
								SampleInfo info = (SampleInfo) infoSeq.get(j);
								if (info.valid_data)
									System.out.println(
											(HelloMsg) msgSeq.get(j));
								else
									// 7.4.6.6 Valid Data Flag
									System.out.println("Invalidate Data! "
											+ info);
							}
							
							Thread.sleep(100);
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
						waitSet.delete();
					}
				}

			} catch (RETCODE_TIMEOUT e) {
				System.out
						.println("Wait timed out. No condition was triggered.");
				InstanceHandleSeq instanceHandleSeq = new InstanceHandleSeq();
				participant.get_discovered_topics(instanceHandleSeq);
				int size = instanceHandleSeq.size();
				for(int i=0; i<size; i++){
					TopicBuiltinTopicData topicBuiltinTopicData = new TopicBuiltinTopicData();
					participant.get_discovered_topic_data(topicBuiltinTopicData,
							(InstanceHandle_t) instanceHandleSeq.get(i));
					System.out.println(topicBuiltinTopicData.name);
				}
				System.out.println(instanceHandleSeq.size());
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

package com.rti.xihui.fromscratch.idl;

import com.rti.dds.infrastructure.RETCODE_NO_DATA;
import com.rti.dds.infrastructure.ResourceLimitsQosPolicy;
import com.rti.dds.infrastructure.StatusKind;
import com.rti.dds.subscription.DataReader;
import com.rti.dds.subscription.DataReaderAdapter;
import com.rti.dds.subscription.InstanceStateKind;
import com.rti.dds.subscription.LivelinessChangedStatus;
import com.rti.dds.subscription.RequestedDeadlineMissedStatus;
import com.rti.dds.subscription.RequestedIncompatibleQosStatus;
import com.rti.dds.subscription.SampleInfo;
import com.rti.dds.subscription.SampleInfoSeq;
import com.rti.dds.subscription.SampleLostStatus;
import com.rti.dds.subscription.SampleRejectedStatus;
import com.rti.dds.subscription.SampleStateKind;
import com.rti.dds.subscription.SubscriptionMatchedStatus;
import com.rti.dds.subscription.ViewStateKind;

public class HelloMsgSubscriberUsingPolling extends AbstractHelloMsgSubscriber {

	public HelloMsgSubscriberUsingPolling() {
		super(HelloMsgSubscriberUsingPolling.class.getSimpleName());

		DataReader reader = subscriber.create_datareader(topic,
				createDataReaderQos(), null, StatusKind.STATUS_MASK_ALL);

		HelloMsgSeq msgSeq = new HelloMsgSeq();
		SampleInfoSeq infoSeq = new SampleInfoSeq();

		while (isLive.get()) {
			try {
				synchronized (this) {
					if(!isLive.get())
						continue;
					((HelloMsgDataReader) reader).take(msgSeq, infoSeq,
							ResourceLimitsQosPolicy.LENGTH_UNLIMITED,
							SampleStateKind.ANY_SAMPLE_STATE,
							ViewStateKind.ANY_VIEW_STATE,
							InstanceStateKind.ANY_INSTANCE_STATE);
				}
				for (int i = 0; i < msgSeq.size(); i++) {
					SampleInfo info = (SampleInfo) infoSeq.get(i);
					if (info.valid_data)
						System.out.println("Received: "
								+ (HelloMsg) msgSeq.get(i));
					else
						// 7.4.6.6 Valid Data Flag
						System.out.println("Invalidate Data! " + info);
				}

			} catch (RETCODE_NO_DATA noData) {
				System.out.println("No data.");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				((HelloMsgDataReader) reader).return_loan(msgSeq, infoSeq);
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		new HelloMsgSubscriberUsingPolling();

	}

	private static class HelloMsgReaderListener extends DataReaderAdapter {

		@Override
		public void on_data_available(DataReader reader) {

		}

		/**
		 * 7.3.7.4 LIVELINESS_CHANGED Status This status indicates that the
		 * liveliness of one or more matched DataWriters has changed (i.e., one
		 * or more DataWriters has become alive or not alive).
		 */
		@Override
		public void on_liveliness_changed(DataReader reader,
				LivelinessChangedStatus status) {
			System.out.println("reader: on_liveliness_changed " + status);

		}

		/**
		 * 7.3.7.7 SAMPLE_LOST Status For a DataReader, when there are
		 * insufficient resources to accept incoming samples of data, samples
		 * may be dropped by the receiving application. Those samples are
		 * considered to be REJECTED (see Section 7.3.7.8). But DataWriters are
		 * limited in the number of published data samples that they can store,
		 * so that if a DataWriter continues to publish data samples, new data
		 * may overwrite old data that have not yet been received by the
		 * DataReader. The samples that are overwritten can never be resent to
		 * the DataReader and thus are considered to be lost.
		 */
		@Override
		public void on_sample_lost(DataReader reader, SampleLostStatus status) {
			System.out.println("reader: on_sample_lost " + status);
		}

		/**
		 * For example, the reader's resource limit has been exceeded. the queue
		 * if full or reach the max_samples parameter
		 */
		@Override
		public void on_sample_rejected(DataReader reader,
				SampleRejectedStatus status) {
			System.out.println("reader: on_sample_rejected " + status);
		}

		@Override
		public void on_subscription_matched(DataReader reader,
				SubscriptionMatchedStatus status) {
			System.out.println("reader: on_subscription_matched " + status);
		}

		@Override
		public void on_requested_deadline_missed(DataReader reader,
				RequestedDeadlineMissedStatus status) {
			System.out
					.println("reader: on_requested_deadline_missed " + status);
		}

		@Override
		public void on_requested_incompatible_qos(DataReader reader,
				RequestedIncompatibleQosStatus status) {
			System.out.println("reader: on_requested_incompatible_qos "
					+ status);
		}
	}
}

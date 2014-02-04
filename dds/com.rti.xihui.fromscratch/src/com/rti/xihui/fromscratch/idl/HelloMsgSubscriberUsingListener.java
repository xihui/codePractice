package com.rti.xihui.fromscratch.idl;

import com.rti.dds.infrastructure.RETCODE_NO_DATA;
import com.rti.dds.infrastructure.ResourceLimitsQosPolicy;
import com.rti.dds.infrastructure.StatusKind;
import com.rti.dds.publication.builtin.PublicationBuiltinTopicDataDataReader;
import com.rti.dds.publication.builtin.PublicationBuiltinTopicDataTypeSupport;
import com.rti.dds.subscription.DataReader;
import com.rti.dds.subscription.DataReaderAdapter;
import com.rti.dds.subscription.DataReaderListener;
import com.rti.dds.subscription.DataReaderQos;
import com.rti.dds.subscription.InstanceStateKind;
import com.rti.dds.subscription.LivelinessChangedStatus;
import com.rti.dds.subscription.RequestedDeadlineMissedStatus;
import com.rti.dds.subscription.RequestedIncompatibleQosStatus;
import com.rti.dds.subscription.SampleInfo;
import com.rti.dds.subscription.SampleInfoSeq;
import com.rti.dds.subscription.SampleLostStatus;
import com.rti.dds.subscription.SampleRejectedStatus;
import com.rti.dds.subscription.SampleStateKind;
import com.rti.dds.subscription.Subscriber;
import com.rti.dds.subscription.SubscriptionMatchedStatus;
import com.rti.dds.subscription.ViewStateKind;
import com.rti.xihui.fromscratch.idl.BuiltinReaderExample.MyPublicationBuiltinReaderListener;

public class HelloMsgSubscriberUsingListener extends AbstractHelloMsgSubscriber {

	public HelloMsgSubscriberUsingListener() {
		super(HelloMsgSubscriberUsingListener.class.getSimpleName());
		
//		Subscriber builtin_subscriber = participant.get_builtin_subscriber();
//		PublicationBuiltinTopicDataDataReader publicationBuiltinReader = (PublicationBuiltinTopicDataDataReader) builtin_subscriber
//				.lookup_datareader(PublicationBuiltinTopicDataTypeSupport.PUBLICATION_TOPIC_NAME);
//		if (publicationBuiltinReader == null) {
//			System.err.println("Faield to get builtin dataReader");
//			return;
//		}
//		publicationBuiltinReader.set_listener(
//				new BuiltinReaderExample().new MyPublicationBuiltinReaderListener(),
//				StatusKind.STATUS_MASK_ALL);

		DataReaderListener listener = new HelloMsgReaderListener();

		DataReaderQos readerQosr = createDataReaderQos();
//		readerQosr.time_based_filter.minimum_separation.sec=1;
//		readerQosr.time_based_filter.minimum_separation.nanosec=0;
		
		subscriber.create_datareader(topic, readerQosr, listener,
				StatusKind.STATUS_MASK_ALL);
//		subscriber.create_datareader(topic, readerQosr, listener,
//				StatusKind.STATUS_MASK_ALL);
//		subscriber.create_datareader(topic, readerQosr, listener,
//				StatusKind.STATUS_MASK_ALL);
//		subscriber.create_datareader(topic, readerQosr, listener,
//				StatusKind.STATUS_MASK_ALL);


	}
	

	public static void main(String[] args) {
		new HelloMsgSubscriberUsingListener();

	}

	private static class HelloMsgReaderListener extends DataReaderAdapter {

		private HelloMsgSeq msgSeq = new HelloMsgSeq();
		private SampleInfoSeq infoSeq = new SampleInfoSeq();

		@Override
		public void on_data_available(DataReader reader) {
			try {
				((HelloMsgDataReader) reader).take(msgSeq, infoSeq,
						ResourceLimitsQosPolicy.LENGTH_UNLIMITED,
						SampleStateKind.ANY_SAMPLE_STATE,
						ViewStateKind.ANY_VIEW_STATE,
						InstanceStateKind.ANY_INSTANCE_STATE);
				System.out.println("Received: ");
				for (int i = 0; i < msgSeq.size(); i++) {					
					SampleInfo info = (SampleInfo) infoSeq.get(i);
					if (info.valid_data)
						System.out.println((HelloMsg) msgSeq.get(i));
					else{
						// 7.4.6.6 Valid Data Flag
						System.out.println("Invalidate Data! " + info.instance_state);
					}
				}
				//not good to sleep at here, because it will block the middleware thread!
//				Thread.sleep(1000);
			}catch (RETCODE_NO_DATA noData) {
                System.out.println("No data.");
            }catch (Exception e) {
				e.printStackTrace();
			} finally {
				((HelloMsgDataReader) reader).return_loan(msgSeq, infoSeq);
			}
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

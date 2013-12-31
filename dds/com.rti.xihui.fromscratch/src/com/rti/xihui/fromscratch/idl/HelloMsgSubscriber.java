package com.rti.xihui.fromscratch.idl;

import com.rti.dds.domain.DomainParticipant;
import com.rti.dds.domain.DomainParticipantFactory;
import com.rti.dds.domain.DomainParticipantQos;
import com.rti.dds.infrastructure.HistoryQosPolicyKind;
import com.rti.dds.infrastructure.ReliabilityQosPolicyKind;
import com.rti.dds.infrastructure.ResourceLimitsQosPolicy;
import com.rti.dds.infrastructure.StatusKind;
import com.rti.dds.infrastructure.TransportBuiltinKind;
import com.rti.dds.infrastructure.TransportBuiltinQosPolicy;
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
import com.rti.dds.topic.Topic;
import com.rti.xihui.fromscratch.ui.SimpleGUI;

public class HelloMsgSubscriber {
	
	private boolean running = true;
	private DomainParticipant participant;
	
	public HelloMsgSubscriber() {
		
		SimpleGUI.createGUI("subscriber", new Runnable() {

			@Override
			public void run() {
				dispose();
			}
		});


		participant = null;
		try {
			DomainParticipantQos participantQos = new DomainParticipantQos();
			DomainParticipantFactory.get_instance().get_default_participant_qos(participantQos);
			participantQos.transport_builtin.mask = TransportBuiltinKind.UDPv4;
			participant = DomainParticipantFactory.get_instance()
					.create_participant(HelloMsgPublisher.DOMAIN_ID,
							participantQos,
							null, StatusKind.STATUS_MASK_NONE);
			HelloMsgTypeSupport.register_type(participant,
					HelloMsgTypeSupport.get_type_name());

			Subscriber subscriber = participant.create_subscriber(
					DomainParticipant.SUBSCRIBER_QOS_DEFAULT, null,
					StatusKind.STATUS_MASK_NONE);

			Topic topic = participant.create_topic(
					HelloMsgPublisher.TOPIC_NAME,
					HelloMsgTypeSupport.get_type_name(),
					DomainParticipant.TOPIC_QOS_DEFAULT, null,
					StatusKind.STATUS_MASK_NONE);

			DataReaderListener listener = new HelloMsgReaderListener();

			DataReaderQos dataReaderQos = new DataReaderQos();
			subscriber.get_default_datareader_qos(dataReaderQos);
			dataReaderQos.reliability.kind = ReliabilityQosPolicyKind.RELIABLE_RELIABILITY_QOS;
			dataReaderQos.history.kind = HistoryQosPolicyKind.KEEP_LAST_HISTORY_QOS;
			dataReaderQos.history.depth = 1;
			dataReaderQos.resource_limits.max_samples = 20;
			dataReaderQos.resource_limits.initial_samples = 20;
			dataReaderQos.transport_selection.enabled_transports.add("udpv4");

			subscriber.create_datareader(topic, dataReaderQos, listener,
					StatusKind.STATUS_MASK_ALL);

		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
//			dispose();
		}
	}

	private synchronized void dispose() {
		running = false;
		if (participant != null) {
			participant.delete_contained_entities();
			DomainParticipantFactory.TheParticipantFactory
					.delete_participant(participant);
			participant = null;
		}
	}

	public static void main(String[] args) {
		new HelloMsgSubscriber();

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
				for (int i = 0; i < msgSeq.size(); i++) {
					SampleInfo info = (SampleInfo) infoSeq.get(i);
					if (info.valid_data)
						System.out.println("Received: "
								+ (HelloMsg) msgSeq.get(i));
					else
						//7.4.6.6 Valid Data Flag
						System.out.println("Invalidate Data! " + info);
				}
				Thread.sleep(1000);
			} catch (Exception e) {
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

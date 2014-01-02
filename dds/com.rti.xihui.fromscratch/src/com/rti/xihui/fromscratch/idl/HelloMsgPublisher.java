package com.rti.xihui.fromscratch.idl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.rti.dds.domain.DomainParticipant;
import com.rti.dds.domain.DomainParticipantFactory;
import com.rti.dds.infrastructure.Cookie_t;
import com.rti.dds.infrastructure.DurabilityQosPolicyKind;
import com.rti.dds.infrastructure.HistoryQosPolicyKind;
import com.rti.dds.infrastructure.InstanceHandle_t;
import com.rti.dds.infrastructure.IntSeq;
import com.rti.dds.infrastructure.Locator_t;
import com.rti.dds.infrastructure.ReliabilityQosPolicyKind;
import com.rti.dds.infrastructure.StatusKind;
import com.rti.dds.publication.DataWriter;
import com.rti.dds.publication.DataWriterAdapter;
import com.rti.dds.publication.DataWriterQos;
import com.rti.dds.publication.LivelinessLostStatus;
import com.rti.dds.publication.OfferedDeadlineMissedStatus;
import com.rti.dds.publication.OfferedIncompatibleQosStatus;
import com.rti.dds.publication.PublicationMatchedStatus;
import com.rti.dds.publication.Publisher;
import com.rti.dds.publication.ReliableReaderActivityChangedStatus;
import com.rti.dds.publication.ReliableWriterCacheChangedStatus;
import com.rti.xihui.fromscratch.ui.SimpleGUI;

public class HelloMsgPublisher extends AbstractHelloMsgParticipant {
	static final String TOPIC_NAME = "XihuiFirstScratch.HelloMsg";
	final static int DOMAIN_ID = 1;
	private boolean paused = false;

	public HelloMsgPublisher() throws InterruptedException {

		JPanel frame = SimpleGUI.createGUI("Publisher", new Runnable() {

			@Override
			public void run() {
				dispose();
			}
		});
		final JButton pauseButton = new JButton("Pause");
		pauseButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				paused = !paused;
				if (!paused)
					pauseButton.setText("Pause");
				else
					pauseButton.setText("Resume");
			}
		});
		frame.add(pauseButton);
		frame.setVisible(true);

		try {

			Publisher publisher = participant.create_publisher(
					DomainParticipant.PUBLISHER_QOS_DEFAULT, null,
					StatusKind.STATUS_MASK_NONE);

			// create DataWriter QoS
			DataWriterQos writerQos = new DataWriterQos();
			publisher.get_default_datawriter_qos(writerQos);
			writerQos.reliability.kind = ReliabilityQosPolicyKind.RELIABLE_RELIABILITY_QOS;
			writerQos.history.kind = HistoryQosPolicyKind.KEEP_LAST_HISTORY_QOS;
			writerQos.history.depth = 1;

			writerQos.durability.kind = DurabilityQosPolicyKind.TRANSIENT_LOCAL_DURABILITY_QOS;

			writerQos.resource_limits.initial_samples = 20;
			writerQos.resource_limits.max_samples = 20;
			// writerQos.protocol.rtps_reliable_writer.high_watermark = 12;
			writerQos.protocol.rtps_reliable_writer.fast_heartbeat_period.nanosec = 5000000;
			writerQos.protocol.rtps_reliable_writer.fast_heartbeat_period.sec = 0;
			writerQos.protocol.rtps_reliable_writer.heartbeats_per_max_samples = 1;
			writerQos.transport_selection.enabled_transports.add("udpv4");
			// writerQos.protocol.rtps_reliable_writer.heartbeat_period.

			HelloMsgDataWriter dataWriter = (HelloMsgDataWriter) publisher
					.create_datawriter(topic, writerQos,
							new HelloMsgWriterListener(),
							StatusKind.STATUS_MASK_ALL);
			HelloMsg instance_data = new HelloMsg();
			instance_data.id = 0;
			InstanceHandle_t instanceHandle0 = dataWriter
					.register_instance(instance_data);

			instance_data.id = 1;
			InstanceHandle_t instanceHandle1 = dataWriter
					.register_instance(instance_data);

			InstanceHandle_t[] instanceHandles = new InstanceHandle_t[] {
					instanceHandle0, instanceHandle1 };

			int i = 0;
			while (isLive.get()) {
				Thread.sleep(100);
				if(i==1000)
					paused = true;
				if (paused)
					continue;
				
				

				instance_data.id = i % 2;
				instance_data.msg = "hello " + i++;
				instance_data.mySeq = new IntSeq(new int[] {
						(int) (Math.random() * 100), 12, 23, 34, 45, 456,
						(int) (Math.random() * 100) });
				synchronized (HelloMsgPublisher.this) {
					if (isLive.get())
						dataWriter.write(instance_data, instanceHandles[i % 2]);
				}
				System.out.println("write " + instance_data);

			}

			// Thread.sleep(100000);

		} finally {
			// dispose();
		}
	}

	public static void main(String[] args) throws InterruptedException {

		new HelloMsgPublisher();
	}



	private static class HelloMsgWriterListener extends DataWriterAdapter {
		@Override
		public void on_publication_matched(DataWriter writer,
				PublicationMatchedStatus status) {
			System.out.println("writer: on_publication_matched " + status);
		}

		@Override
		public void on_liveliness_lost(DataWriter writer,
				LivelinessLostStatus status) {
			System.out.println("writer: on_liveliness_lost " + status);
		}

		@Override
		public void on_reliable_writer_cache_changed(DataWriter writer,
				ReliableWriterCacheChangedStatus status) {
			// System.out.println("writer: on_reliable_writer_cache_changed "
			// + status);
		}

		@Override
		public void on_sample_removed(DataWriter writer, Cookie_t cookie) {
			System.out.println("writer: on_sample_removed " + cookie);
		}

		@Override
		public Object on_data_request(DataWriter writer, Cookie_t cookie) {
			System.out.println("writer: on_data_request " + cookie);
			return cookie;

		}

		@Override
		public void on_data_return(DataWriter writer, Object instance_data,
				Cookie_t cookie) {
			System.out.println("writer: on_data_return " + cookie);

		}

		public void on_application_acknowledgment(DataWriter writer,
				com.rti.dds.publication.AcknowledgmentInfo ackInfo) {
		};

		@Override
		public void on_destination_unreachable(DataWriter writer,
				InstanceHandle_t handle, Locator_t destination) {
			System.out.println("writer: on_destination_unreachable "
					+ destination);

		}

		@Override
		public void on_instance_replaced(DataWriter writer,
				InstanceHandle_t handle) {
			System.out.println("writer: on_instance_replaced " + handle);

		}

		@Override
		public void on_offered_deadline_missed(DataWriter writer,
				OfferedDeadlineMissedStatus status) {
			System.out.println("writer: on_offered_deadline_missed " + status);

		}

		@Override
		public void on_reliable_reader_activity_changed(DataWriter writer,
				ReliableReaderActivityChangedStatus status) {
			System.out.println("writer: on_reliable_reader_activity_changed "
					+ status);

		}

		@Override
		public void on_offered_incompatible_qos(DataWriter writer,
				OfferedIncompatibleQosStatus status) {
			System.out.println("writer: on_offered_incompatible_qos " + status);

		}
	}
}

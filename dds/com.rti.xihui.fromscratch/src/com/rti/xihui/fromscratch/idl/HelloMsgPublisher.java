package com.rti.xihui.fromscratch.idl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.rti.dds.domain.DomainParticipant;
import com.rti.dds.domain.DomainParticipantQos;
import com.rti.dds.infrastructure.Cookie_t;
import com.rti.dds.infrastructure.DurabilityQosPolicyKind;
import com.rti.dds.infrastructure.HistoryQosPolicyKind;
import com.rti.dds.infrastructure.InstanceHandle_t;
import com.rti.dds.infrastructure.IntSeq;
import com.rti.dds.infrastructure.Locator_t;
import com.rti.dds.infrastructure.ReliabilityQosPolicyKind;
import com.rti.dds.infrastructure.StatusKind;
import com.rti.dds.infrastructure.TransportBuiltinKind;
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
	private boolean paused = false;
	private Map<HelloMsg, InstanceHandle_t> instanceHandlesMap = new HashMap<HelloMsg, InstanceHandle_t>();
	private HelloMsgDataWriter dataWriter;

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
			
			//reliability
			writerQos.reliability.kind = ReliabilityQosPolicyKind.RELIABLE_RELIABILITY_QOS;
			
			//History per instance
			writerQos.history.kind = HistoryQosPolicyKind.KEEP_LAST_HISTORY_QOS;
			writerQos.history.depth = 2;

			//Durability, only takes effect if reliability QoS is reliable
			writerQos.durability.kind = DurabilityQosPolicyKind.TRANSIENT_LOCAL_DURABILITY_QOS;

			//resource limits per instance
			writerQos.resource_limits.initial_samples = 20;
			writerQos.resource_limits.max_samples = 20;
			
			//The limit when it will switch to fast heartbeat
			// writerQos.protocol.rtps_reliable_writer.high_watermark = 12;
			
			//Fast heartbeat period, should not be too fast to flood the network
			writerQos.protocol.rtps_reliable_writer.fast_heartbeat_period.nanosec = 5000000;
			writerQos.protocol.rtps_reliable_writer.fast_heartbeat_period.sec = 0;
			
			//Attach heart beat to every how many samples
			writerQos.protocol.rtps_reliable_writer.heartbeats_per_max_samples = 1;
			
			//enable udpv4 transports
			writerQos.transport_selection.enabled_transports
					.add(TransportBuiltinKind.UDPv4_ALIAS);
			// writerQos.protocol.rtps_reliable_writer.heartbeat_period.

			dataWriter = (HelloMsgDataWriter) publisher
					.create_datawriter(topic, writerQos,
							new HelloMsgWriterListener(),
							StatusKind.STATUS_MASK_ALL);
			HelloMsg instance_data = new HelloMsg();
			instance_data.id = 0;
			InstanceHandle_t instanceHandle0 = dataWriter
					.register_instance(instance_data);
			instanceHandlesMap.put(instance_data, instanceHandle0);

			HelloMsg instance_data2 = new HelloMsg();
			
			instance_data2.id = 1;
			InstanceHandle_t instanceHandle1 = dataWriter
					.register_instance(instance_data2);

			HelloMsg[] instances = new HelloMsg[] {
					instance_data, instance_data2};
			instanceHandlesMap.put(instance_data2, instanceHandle1);


			int i = 0;
			while (isLive.get()) {
				Thread.sleep(10);
				if (paused)
					continue;
				
				HelloMsg instance = instances[i % 2];

				instance.msg = "hello " + i++;
				instance.mySeq = new IntSeq(new int[] {
						(int) (Math.random() * 100), 12, 23, 34, 45, 456,
						(int) (Math.random() * 100) });
				synchronized (HelloMsgPublisher.this) {
					if (isLive.get())
						dataWriter.write(instance, instanceHandlesMap.get(instance));
				}
				System.out.println("write " + instance);

			}

			// Thread.sleep(100000);

		} finally {
			// dispose();
		}
	}

	@Override
	protected DomainParticipantQos configParticipantQoS() {
		DomainParticipantQos participantQoS = super.configParticipantQoS();
		participantQoS.discovery.initial_peers.clear();
		participantQoS.discovery.initial_peers.add("udpv4://239.255.0.2");

		participantQoS.discovery.multicast_receive_addresses.clear();
		participantQoS.discovery.multicast_receive_addresses
				.add("udpv4://239.255.0.3");
		return participantQoS;

	}
	
	@Override
	protected synchronized void dispose() {
		if(participant!=null && dataWriter !=null){
			for(HelloMsg data : instanceHandlesMap.keySet())
			dataWriter.unregister_instance(data, instanceHandlesMap.get(data));
		}
		super.dispose();
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

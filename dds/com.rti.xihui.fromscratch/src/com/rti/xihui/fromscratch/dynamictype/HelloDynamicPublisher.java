package com.rti.xihui.fromscratch.dynamictype;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.WritableRaster;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.rti.dds.domain.DomainParticipant;
import com.rti.dds.dynamicdata.DynamicData;
import com.rti.dds.dynamicdata.DynamicDataWriter;
import com.rti.dds.infrastructure.ByteSeq;
import com.rti.dds.infrastructure.DurabilityQosPolicyKind;
import com.rti.dds.infrastructure.HistoryQosPolicyKind;
import com.rti.dds.infrastructure.InstanceHandle_t;
import com.rti.dds.infrastructure.ReliabilityQosPolicyKind;
import com.rti.dds.infrastructure.StatusKind;
import com.rti.dds.publication.DataWriter;
import com.rti.dds.publication.DataWriterAdapter;
import com.rti.dds.publication.DataWriterQos;
import com.rti.dds.publication.Publisher;
import com.rti.dds.publication.ReliableReaderActivityChangedStatus;
import com.rti.xihui.fromscratch.ui.SimpleGUI;

public class HelloDynamicPublisher extends AbstractHelloDynamicParticipant {

	private boolean paused;
	private DynamicData instance;
	private DynamicDataWriter dataWriter;
	private InstanceHandle_t instanceHandle;

	public HelloDynamicPublisher() throws InterruptedException {

		JPanel panel = SimpleGUI.createGUI("Dynamic Publisher", new Runnable() {

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
		panel.add(pauseButton);
		panel.setVisible(true);

		Publisher publisher = participant.create_publisher(
				DomainParticipant.PUBLISHER_QOS_DEFAULT, null,
				StatusKind.STATUS_MASK_NONE);
		DataWriterQos writerQos = new DataWriterQos();
		publisher.get_default_datawriter_qos(writerQos);
		
//		writerQos.reliability.kind = ReliabilityQosPolicyKind.RELIABLE_RELIABILITY_QOS;
//		writerQos.reliability.max_blocking_time.sec = 10;
//		writerQos.history.kind = HistoryQosPolicyKind.KEEP_LAST_HISTORY_QOS;
//		writerQos.history.depth = 10;
//		writerQos.resource_limits.max_samples = 100;
		
		
		dataWriter = (DynamicDataWriter) publisher
				.create_datawriter(topic, writerQos, new DataWriterAdapter() {
					@Override
					public void on_reliable_reader_activity_changed(
							DataWriter arg0,
							ReliableReaderActivityChangedStatus arg1) {
						System.out.println("on_reliable_reader_activity_changed"+arg1);
					}
				},
						StatusKind.STATUS_MASK_ALL);

		instance = new DynamicData(helloDynamicType,
				DynamicData.PROPERTY_DEFAULT);
		instanceHandle = dataWriter.register_instance(instance);
		instance.set_int(HelloDynamicWorldType.SAMPLE_ID_FIELD,
				DynamicData.MEMBER_ID_UNSPECIFIED, 11);

		int counter = 0;
		while (isLive.get()) {
			Thread.sleep(1000);
			if (paused){			
				continue;
			}
			instance.set_int(HelloDynamicWorldType.SAMPLE_ID_FIELD,
					DynamicData.MEMBER_ID_UNSPECIFIED, counter%10);
			instance.set_string(HelloDynamicWorldType.NAME_FIELD,
					DynamicData.MEMBER_ID_UNSPECIFIED, "Hello Dynamic Type! " + counter++);
			instance.set_double(HelloDynamicWorldType.DOUBLE_FIELD, DynamicData.MEMBER_ID_UNSPECIFIED, Math.random()*100);
			for (int i = 0; i < HelloDynamicWorldType.NUMBER_OF_FIELDS; i++) {
				instance.set_string("field" + i,
						DynamicData.MEMBER_ID_UNSPECIFIED, "Hello field" + i);
			}
			byte[] payload = new byte[HelloDynamicWorldType.HELLO_MAX_PAYLOAD_SIZE];
			for (int i = 0; i < payload.length; i++) {
				payload[i] = (byte) ((Math.random() * 255 * i) % 0xff);
			}
			instance.set_byte_seq(HelloDynamicWorldType.PAYLOAD_FIELD,
					DynamicData.MEMBER_ID_UNSPECIFIED, new ByteSeq(payload));
			try {
				synchronized(this){
					if(isLive.get())
						dataWriter.write(instance, InstanceHandle_t.HANDLE_NIL);
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			System.out.println("write " + instance + " " + counter);


		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		new HelloDynamicPublisher();
	}
	
	@Override
	protected synchronized void dispose() {
		dataWriter.dispose(instance, instanceHandle);
		super.dispose();	
	}

}

package com.rti.xihui.fromscratch.dynamictype;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.rti.dds.domain.DomainParticipant;
import com.rti.dds.dynamicdata.DynamicData;
import com.rti.dds.dynamicdata.DynamicDataWriter;
import com.rti.dds.infrastructure.ByteSeq;
import com.rti.dds.infrastructure.InstanceHandle_t;
import com.rti.dds.infrastructure.StatusKind;
import com.rti.dds.publication.DataWriterQos;
import com.rti.dds.publication.Publisher;
import com.rti.xihui.fromscratch.ui.SimpleGUI;

public class HelloDynamicPublisher extends AbstractHelloDynamicParticipant {

	private boolean paused;

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

		DynamicDataWriter dataWriter = (DynamicDataWriter) publisher
				.create_datawriter(topic, writerQos, null,
						StatusKind.STATUS_MASK_NONE);

		DynamicData instance = new DynamicData(helloDynamicType,
				DynamicData.PROPERTY_DEFAULT);
		instance.set_string(HelloDynamicWorldType.NAME_FIELD,
				DynamicData.MEMBER_ID_UNSPECIFIED, "Hello Dynamic World!");
		instance.set_int(HelloDynamicWorldType.SAMPLE_ID_FIELD,
				DynamicData.MEMBER_ID_UNSPECIFIED, 11);

		while (isLive.get()) {
			Thread.sleep(100);
			if (paused)
				continue;
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
			synchronized(this){
				if(isLive.get())
					dataWriter.write(instance, InstanceHandle_t.HANDLE_NIL);
			}
			System.out.println("write " + instance);


		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		new HelloDynamicPublisher();
	}

}

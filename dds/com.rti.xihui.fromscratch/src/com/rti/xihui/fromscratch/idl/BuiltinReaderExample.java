package com.rti.xihui.fromscratch.idl;

import java.util.ArrayList;
import java.util.List;

import com.rti.dds.domain.DomainParticipant;
import com.rti.dds.domain.DomainParticipantAdapter;
import com.rti.dds.domain.DomainParticipantFactory;
import com.rti.dds.domain.DomainParticipantFactoryQos;
import com.rti.dds.domain.DomainParticipantQos;
import com.rti.dds.domain.builtin.ParticipantBuiltinTopicData;
import com.rti.dds.domain.builtin.ParticipantBuiltinTopicDataDataReader;
import com.rti.dds.domain.builtin.ParticipantBuiltinTopicDataSeq;
import com.rti.dds.domain.builtin.ParticipantBuiltinTopicDataTypeSupport;
import com.rti.dds.infrastructure.InstanceHandle_t;
import com.rti.dds.infrastructure.Property_t;
import com.rti.dds.infrastructure.RETCODE_NO_DATA;
import com.rti.dds.infrastructure.ResourceLimitsQosPolicy;
import com.rti.dds.infrastructure.StatusKind;
import com.rti.dds.infrastructure.TransportBuiltinKind;
import com.rti.dds.publication.AcknowledgmentInfo;
import com.rti.dds.publication.DataWriter;
import com.rti.dds.publication.LivelinessLostStatus;
import com.rti.dds.publication.PublicationMatchedStatus;
import com.rti.dds.publication.builtin.PublicationBuiltinTopicData;
import com.rti.dds.publication.builtin.PublicationBuiltinTopicDataDataReader;
import com.rti.dds.publication.builtin.PublicationBuiltinTopicDataSeq;
import com.rti.dds.publication.builtin.PublicationBuiltinTopicDataTypeSupport;
import com.rti.dds.subscription.DataReader;
import com.rti.dds.subscription.DataReaderAdapter;
import com.rti.dds.subscription.DataReaderSeq;
import com.rti.dds.subscription.InstanceStateKind;
import com.rti.dds.subscription.LivelinessChangedStatus;
import com.rti.dds.subscription.SampleInfo;
import com.rti.dds.subscription.SampleInfoSeq;
import com.rti.dds.subscription.SampleRejectedStatus;
import com.rti.dds.subscription.SampleStateKind;
import com.rti.dds.subscription.Subscriber;
import com.rti.dds.subscription.SubscriptionMatchedStatus;
import com.rti.dds.subscription.ViewStateKind;
import com.rti.xihui.fromscratch.ui.SimpleGUI;

public class BuiltinReaderExample {

	private DomainParticipant participant;

	private List<InstanceHandle_t> foundDataWriters = new ArrayList<InstanceHandle_t>();

	public BuiltinReaderExample() {
		SimpleGUI.createGUI("BuiltinReader", new Runnable() {

			@Override
			public void run() {
				dispose();
			}
		});
		
		/*
		 * By default, the participant is enabled upon construction. At that
		 * time our listeners for the builtin topics have not been
		 * installed, so we disable the participant until we set up the
		 * listeners.
		 */

//		DomainParticipantFactoryQos factory_qos = new DomainParticipantFactoryQos();
//		DomainParticipantFactory.TheParticipantFactory.get_qos(factory_qos);
//		factory_qos.entity_factory.autoenable_created_entities = false;
//		DomainParticipantFactory.TheParticipantFactory.set_qos(factory_qos);
		
		

		DomainParticipantQos participantQoS = new DomainParticipantQos();

		DomainParticipantFactory.get_instance().get_default_participant_qos(
				participantQoS);
//		
//		participantQoS.resource_limits.type_code_max_serialized_length=0;
//		participantQoS.resource_limits.type_object_max_serialized_length=1000;
//		
//		participantQoS.transport_builtin.mask=TransportBuiltinKind.SHMEM;
//		participantQoS.discovery.initial_peers.clear();
//		participantQoS.discovery.initial_peers.add("builtin.udpv4://239.255.0.1");
		
//		participantQoS.resource_limits.local_writer_allocation.initial_count=4;
//		participantQoS.resource_limits.local_writer_allocation.max_count=4;
//		participantQoS.resource_limits.local_writer_allocation.incremental_count=0;		
//		participantQoS.discovery_config.publication_writer.heartbeats_per_max_samples = 1;
//		
//		participantQoS.resource_limits.local_reader_allocation.initial_count=5;
//		participantQoS.resource_limits.local_reader_allocation.max_count=5;
//		participantQoS.resource_limits.local_reader_allocation.incremental_count=0;		
//		participantQoS.discovery_config.subscription_writer.heartbeats_per_max_samples = 1;
//		
//		parti
//		participantQoS.resource_limits.local_writer_allocation.initial_count=5;
//		
//		participantQoS.resource_limits.local_writer_allocation.max_count=5;
//		participantQoS.resource_limits.local_writer_allocation.incremental_count=0;
		//why builtin reader doesn't need below information?
//
//		participantQoS.discovery.initial_peers.clear();
//		participantQoS.discovery.initial_peers.add("udpv4://239.255.0.3");
////
//		participantQoS.discovery.multicast_receive_addresses.clear();
//		participantQoS.discovery.multicast_receive_addresses
//				.add("udpv4://239.255.0.2");
		
//		participantQoS.property.value.add(new Property_t("rti.monitor.library", "rtimonitoring", true));
//		participantQoS.property.value.add(new Property_t("rti.monitor.create_function", "RTIDefaultMonitor_create", true));
//	

		participant = DomainParticipantFactory.get_instance()
				.create_participant(AbstractHelloMsgParticipant.DOMAIN_ID,
						participantQoS, null,
						StatusKind.STATUS_MASK_NONE);
		
		
		
//		
//		participant = DomainParticipantFactory.get_instance()
//				.create_participant_with_profile(AbstractHelloMsgParticipant.DOMAIN_ID,
//						"UMF_Library", 
//						"DataTransform",  null,
//						StatusKind.STATUS_MASK_NONE);

		Subscriber builtin_subscriber = participant.get_builtin_subscriber();

		DataReader participantBuiltInReader = builtin_subscriber
				.lookup_datareader(ParticipantBuiltinTopicDataTypeSupport.PARTICIPANT_TOPIC_NAME);

		participantBuiltInReader.set_listener(
				new MyParticipantBuiltinReaderListener(),
				StatusKind.STATUS_MASK_ALL);

		PublicationBuiltinTopicDataDataReader publicationBuiltinReader = (PublicationBuiltinTopicDataDataReader) builtin_subscriber
				.lookup_datareader(PublicationBuiltinTopicDataTypeSupport.PUBLICATION_TOPIC_NAME);
//		DataReaderSeq dataReaderSeq = new DataReaderSeq();
//		builtin_subscriber.get_all_datareaders(dataReaderSeq);
//		for(Object r: dataReaderSeq){
//			System.out.println(r);
//		}
		if (publicationBuiltinReader == null) {
			System.err.println("Faield to get builtin dataReader");
			return;
		}
		publicationBuiltinReader.set_listener(
				new MyPublicationBuiltinReaderListener(),
				StatusKind.STATUS_MASK_ALL);
		
		participant.enable();
		
		/*
		 * recover factory QoS
		 */
//		DomainParticipantFactory.TheParticipantFactory.get_qos(factory_qos);
//		factory_qos.entity_factory.autoenable_created_entities = true;
//		DomainParticipantFactory.TheParticipantFactory.set_qos(factory_qos);

	}

	public static void main(String[] args) {

		new BuiltinReaderExample();

	}

	protected synchronized void dispose() {
		if (participant != null) {
			participant.delete_contained_entities();
			DomainParticipantFactory.TheParticipantFactory
					.delete_participant(participant);
			participant = null;
		}
	}

	public static String instanceStateToString(int state) {
		StringBuilder sb = new StringBuilder();
		if ((state & InstanceStateKind.ALIVE_INSTANCE_STATE) != 0) {
			sb.append("ALIVE");
		}
		if ((state & InstanceStateKind.NOT_ALIVE_DISPOSED_INSTANCE_STATE) != 0) {
			if (sb.length() != 0)
				sb.append('|');
			sb.append("NOT_ALIVE_DISPOSED");
		}
		if ((state & InstanceStateKind.NOT_ALIVE_INSTANCE_STATE) != 0) {
			if (sb.length() != 0)
				sb.append('|');
			sb.append("NOT_ALIVE");
		}
		if ((state & InstanceStateKind.NOT_ALIVE_NO_WRITERS_INSTANCE_STATE) != 0) {
			if (sb.length() != 0)
				sb.append('|');
			sb.append("NOT_ALIVE_NO_WRITERS");
		}

		return sb.toString();
	}

	private static class MyParticipantBuiltinReaderListener extends
			DataReaderAdapter {

		ParticipantBuiltinTopicDataSeq dataSeq = new ParticipantBuiltinTopicDataSeq();
		SampleInfoSeq inforSeq = new SampleInfoSeq();

		@Override
		public void on_data_available(DataReader reader) {
			ParticipantBuiltinTopicDataDataReader participantBuiltinReader = (ParticipantBuiltinTopicDataDataReader) reader;

			try {

				participantBuiltinReader.take(dataSeq, inforSeq,
						ResourceLimitsQosPolicy.LENGTH_UNLIMITED,
						SampleStateKind.ANY_SAMPLE_STATE,
						ViewStateKind.NEW_VIEW_STATE,
						InstanceStateKind.ANY_INSTANCE_STATE);

				for (int i = 0; i < dataSeq.size(); i++) {
					SampleInfo info = (SampleInfo) inforSeq.get(i);

					if (info.valid_data) {
						ParticipantBuiltinTopicData participantTopicData = (ParticipantBuiltinTopicData) dataSeq
								.get(i);
						System.out.println("\nFound participant: Domain " + participantTopicData.participant_name + " "
								+ participantTopicData.key + participantTopicData.property + participantTopicData.user_data);
					}
				}

			} catch (RETCODE_NO_DATA noData) {
				// No data to process
				return;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				participantBuiltinReader.return_loan(dataSeq, inforSeq);

			}

		}
	}

	public class MyPublicationBuiltinReaderListener extends DataReaderAdapter {

		private PublicationBuiltinTopicDataSeq received_data = new PublicationBuiltinTopicDataSeq();
		private SampleInfoSeq info_seq = new SampleInfoSeq();

		@Override
		public void on_sample_rejected(DataReader arg0,
				SampleRejectedStatus arg1) {
			System.out.println("Builtin sample was rejected: " + arg1);
			super.on_sample_rejected(arg0, arg1);
		}
		
		@Override
		public void on_data_available(DataReader reader) {
			try {
				((PublicationBuiltinTopicDataDataReader) reader).take(
						received_data, info_seq,
						ResourceLimitsQosPolicy.LENGTH_UNLIMITED,
						SampleStateKind.ANY_SAMPLE_STATE,
						ViewStateKind.ANY_VIEW_STATE,
						InstanceStateKind.ANY_INSTANCE_STATE);

				for (int i = 0; i < received_data.size(); i++) {
					SampleInfo info = (SampleInfo) info_seq.get(i);
					if (info.valid_data) {
						PublicationBuiltinTopicData data = (PublicationBuiltinTopicData) received_data
								.get(i);
//						System.out.println("new data" + data.key + " " + info.instance_handle);
						if (!foundDataWriters.contains(info.instance_handle)) {
							foundDataWriters.add(new InstanceHandle_t(info.instance_handle));
							System.out
									.println("\nFound DataWriter: "
											+ info.instance_handle

											+ "\n\tInstance State: "
											+ instanceStateToString(info.instance_state)
											+ "\n\tTopic Name: "
											+ data.topic_name
											+ "\n\tType Name: "
											+ data.type_name
											+"\n\tParticipant Key: " + data.participant_key);
						}
					} else {
						// 7.4.6.6 Valid Data Flag
//						System.out.println("Instance State Changed: "
//								+ info.instance_handle + "\n\tInstance State: "
//								+ instanceStateToString(info.instance_state));
						if ((info.instance_state & InstanceStateKind.NOT_ALIVE_INSTANCE_STATE) != 0) {
							if (foundDataWriters.contains(info.instance_handle)) {
								foundDataWriters.remove(info.instance_handle);
								System.out
										.println("\nDataWriter left "
												+ ((info.instance_state & InstanceStateKind.NOT_ALIVE_DISPOSED_INSTANCE_STATE) != 0 ? "gracefully."
														: "ungracefully!")
												+ info.instance_handle);
							}
						}
					}
				}
			} catch (RETCODE_NO_DATA noData) {
				// No data to process
				return;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				((PublicationBuiltinTopicDataDataReader) reader).return_loan(
						received_data, info_seq);
			}
		}

		@Override
		public void on_liveliness_changed(DataReader reader,
				LivelinessChangedStatus status) {
			System.out.println("liveliness changed");
		}

		@Override
		public void on_subscription_matched(DataReader reader,
				SubscriptionMatchedStatus status) {
			System.out.println("on subscription matched " + status);
		}
	}

	private static class PlainParticipantListener extends
			DomainParticipantAdapter {
		@Override
		public void on_publication_matched(DataWriter writer,
				PublicationMatchedStatus status) {
			System.out.println("Participant on_publication_matched: " + status);
		}

		@Override
		public void on_data_available(DataReader reader) {
			System.out.println("Participant on_data_available");
		}

		@Override
		public void on_data_on_readers(Subscriber subs) {
			System.out.println("Participant on_data_on_readers");
		}

		@Override
		public void on_liveliness_changed(DataReader reader,
				LivelinessChangedStatus status) {
			System.out.println("Participant on_liveliness_changed");

		}

		@Override
		public void on_subscription_matched(DataReader reader,
				SubscriptionMatchedStatus status) {
			System.out.println("Participant on_subscription_matched");

		}

		@Override
		public void on_liveliness_lost(DataWriter writer,
				LivelinessLostStatus status) {
			// TODO Auto-generated method stub
			System.out.println("Participant on_liveliness_lost");
		}

		@Override
		public void on_application_acknowledgment(DataWriter writer,
				AcknowledgmentInfo ackInfo) {
			// TODO Auto-generated method stub
			System.out.println("Participant on_application_acknowledgment");
		}

	}
}

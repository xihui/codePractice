// ****************************************************************************
//         (c) Copyright, Real-Time Innovations, All rights reserved.       
//                                                                          
//         Permission to modify and use for internal purposes granted.      
// This software is provided "as is", without warranty, express or implied. 
//                                                                          
// ****************************************************************************

package com.rti.example.simple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.rti.dds.domain.DomainParticipant;
import com.rti.dds.domain.DomainParticipantFactory;
import com.rti.dds.domain.DomainParticipantQos;
import com.rti.dds.infrastructure.InstanceHandle_t;
import com.rti.dds.infrastructure.Property_t;
import com.rti.dds.infrastructure.RETCODE_ERROR;
import com.rti.dds.infrastructure.StatusKind;
import com.rti.dds.infrastructure.TransportBuiltinKind;
import com.rti.dds.publication.Publisher;
import com.rti.dds.topic.Topic;
import com.rti.dds.type.builtin.StringDataWriter;
import com.rti.dds.type.builtin.StringTypeSupport;

//****************************************************************************
public class HelloPublisher {
    public static final void main(String[] args) {
        // Create the DDS Domain participant on domain ID 0
    	
    	DomainParticipantQos participantQoS = new DomainParticipantQos();
		DomainParticipantFactory.get_instance().get_default_participant_qos(participantQoS);
//		participantQoS.transport_builtin.mask=TransportBuiltinKind.UDPv4;
//		participantQoS.discovery.initial_peers.clear();
//		participantQoS.discovery.multicast_receive_addresses.clear();
//		participantQoS.property.value.add(new Property_t("dds.transport.UDPv4.builtin.multicast_enabled", "0", false));

    	
        DomainParticipant participant = DomainParticipantFactory.get_instance().create_participant(
                21, // Domain ID = 0
               participantQoS, 
                null, // listener
                StatusKind.STATUS_MASK_NONE);
        if (participant == null) {
            System.err.println("Unable to create domain participant");
            return;
        }

        // Create the topic "Hello World" for the String type
        Topic topic = participant.create_topic(
                "Hello, World", 
                StringTypeSupport.get_type_name(), 
                DomainParticipant.TOPIC_QOS_DEFAULT, 
                null, // listener
                StatusKind.STATUS_MASK_NONE);
        if (topic == null) {
            System.err.println("Unable to create topic.");
            return;
        }

        // Create the data writer using the default publisher
        
        StringDataWriter dataWriter =
            (StringDataWriter) participant.create_datawriter(
                topic, 
                Publisher.DATAWRITER_QOS_DEFAULT,
                null, // listener
                StatusKind.STATUS_MASK_NONE);
        if (dataWriter == null) {
            System.err.println("Unable to create data writer\n");
            return;
        }

        System.out.println("Ready to write data.");
        System.out.println("When the subscriber is ready, you can start writing.");
        System.out.print("Press CTRL+C to terminate or enter an empty line to do a clean shutdown.\n\n");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            while (true) {
                System.out.print("Please type a message> ");
                String toWrite = reader.readLine();
                if (toWrite == null) break;     // shouldn't happen
                dataWriter.write(toWrite, InstanceHandle_t.HANDLE_NIL);
                if (toWrite.equals("")) break;
            }
        } catch (IOException e) {
            // This exception can be thrown from the BufferedReader class
            e.printStackTrace();
        } catch (RETCODE_ERROR e) {
            // This exception can be thrown from DDS write operation
            e.printStackTrace();
        }

        System.out.println("Exiting...");
        participant.delete_contained_entities();
        DomainParticipantFactory.get_instance().delete_participant(participant);
    }
}

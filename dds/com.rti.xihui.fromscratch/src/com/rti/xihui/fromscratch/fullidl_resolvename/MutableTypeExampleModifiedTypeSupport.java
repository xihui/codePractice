
/*
  WARNING: THIS FILE IS AUTO-GENERATED. DO NOT MODIFY.

  This file was generated from .idl using "rtiddsgen".
  The rtiddsgen tool is part of the RTI Connext distribution.
  For more information, type 'rtiddsgen -help' at a command shell
  or consult the RTI Connext manual.
*/
    
package com.rti.xihui.fromscratch.fullidl_resolvename;
        

import com.rti.dds.cdr.CdrEncapsulation;
import com.rti.dds.cdr.CdrInputStream;
import com.rti.dds.cdr.CdrOutputStream;
import com.rti.dds.cdr.CdrPrimitiveType;
import com.rti.dds.cdr.CdrBuffer;
import com.rti.dds.cdr.CdrHelper;
import com.rti.dds.cdr.CdrMemberInfo;
import com.rti.dds.domain.DomainParticipant;
import com.rti.dds.publication.DataWriter;
import com.rti.dds.publication.DataWriterListener;
import com.rti.dds.subscription.DataReader;
import com.rti.dds.subscription.DataReaderListener;
import com.rti.dds.topic.KeyHash_t;
import com.rti.dds.topic.TypeSupportImpl;
import com.rti.dds.topic.TypeSupportType;
import com.rti.dds.util.Sequence;
import com.rti.dds.topic.DefaultEndpointData;
import com.rti.dds.infrastructure.RETCODE_ERROR;

    
import com.rti.dds.infrastructure.*;
import com.rti.dds.topic.TypeSupportParticipantInfo;
import com.rti.dds.topic.TypeSupportEndpointInfo;
import com.rti.dds.typecode.TypeCode;
import com.rti.dds.cdr.IllegalCdrStateException;


import com.rti.dds.infrastructure.Copyable;


/**
 * A collection of useful methods for dealing with objects of type
 * MutableTypeExampleModified.
 */
public class MutableTypeExampleModifiedTypeSupport extends TypeSupportImpl {
    // -----------------------------------------------------------------------
    // Private Fields
    // -----------------------------------------------------------------------

    private static final String TYPE_NAME = "com::rti::xihui::fromscratch::fullidl::MutableTypeExampleModified";

    private static final char[] PLUGIN_VERSION = {2, 0, 0, 0};

    
    public static final int LAST_MEMBER_ID = 
        22;
    

    private static final MutableTypeExampleModifiedTypeSupport _singleton
        = new MutableTypeExampleModifiedTypeSupport();
    
    // -----------------------------------------------------------------------
    // Public Methods
    // -----------------------------------------------------------------------

    // --- External methods: -------------------------------------------------
    /* The methods in this section are for use by users of RTI Connext
     */

    public static String get_type_name() {
        return _singleton.get_type_nameI();
    }

    public static void register_type(DomainParticipant participant,
                                     String type_name) {
        _singleton.register_typeI(participant, type_name);
    }

    public static void unregister_type(DomainParticipant participant,
                                       String type_name) {
        _singleton.unregister_typeI(participant, type_name);
    }

    
     /* The methods in this section are for use by RTI Connext
     * itself and by the code generated by rtiddsgen for other types.
     * They should be used directly or modified only by advanced users and are
     * subject to change in future versions of RTI Connext.
     */
    public static MutableTypeExampleModifiedTypeSupport get_instance() {
        return _singleton;
    }

    public static MutableTypeExampleModifiedTypeSupport getInstance() {
        return get_instance();
    }

    public Object create_data() {
        return MutableTypeExampleModified.create();
    }

    public void destroy_data(Object data) {
        return;
    }

    public Object create_key() {
        return new MutableTypeExampleModified();
    }

    public void destroy_key(Object key) {
        return;
    }

    public Class get_type() {
        return MutableTypeExampleModified.class;
    }
    
    /**
     * This is a concrete implementation of this method inherited from the base class.
     * This method will perform a deep copy of <code>source</code> into
     * <code>destination</code>.
     * 
     * @param src The Object which contains the data to be copied.
     * @return Returns <code>destination</code>.
     * @exception NullPointerException If <code>destination</code> or 
     * <code>source</code> is null.
     * @exception ClassCastException If either <code>destination</code> or
     * <code>this</code> is not a <code>MutableTypeExampleModified</code>
     * type.
     */
    public Object copy_data(Object destination, Object source) {
        MutableTypeExampleModified typedDst = (MutableTypeExampleModified) destination;
        MutableTypeExampleModified typedSrc = (MutableTypeExampleModified) source;

        return typedDst.copy_from(typedSrc);
    
    }


    
    public long get_serialized_sample_max_size(Object endpoint_data,boolean include_encapsulation,short encapsulation_id,long currentAlignment) {
        long origAlignment = currentAlignment;

        long encapsulation_size = currentAlignment;


        if(include_encapsulation) {
          if (!CdrEncapsulation.isValidEncapsulationKind(encapsulation_id)) {
              throw new RETCODE_ERROR("Unsupported encapsulation");
          }

          encapsulation_size += CdrPrimitiveType.SHORT.getMaxSizeSerialized(encapsulation_size);
          encapsulation_size += CdrPrimitiveType.SHORT.getMaxSizeSerialized(encapsulation_size);
          encapsulation_size -= currentAlignment;
          currentAlignment = 0;
          origAlignment = 0;

        }

 
    currentAlignment += (CdrPrimitiveType.getPadSize(currentAlignment, 4) + 12);

        currentAlignment +=  CdrPrimitiveType.DOUBLE.getMaxSizeSerialized(0);
                currentAlignment += (CdrPrimitiveType.getPadSize(currentAlignment, 4) + 12);

        currentAlignment +=  CdrPrimitiveType.INT.getMaxSizeSerialized(0);
                currentAlignment += (CdrPrimitiveType.getPadSize(currentAlignment, 4) + 12);

        currentAlignment +=  CdrPrimitiveType.DOUBLE.getMaxSizeSerialized(0);
             
    // Sentinel
    currentAlignment += (CdrPrimitiveType.getPadSize(currentAlignment, 4) + 12);

        if (include_encapsulation) {
            currentAlignment += encapsulation_size;
        }
    
        return currentAlignment - origAlignment;
    }

    
    public long get_serialized_sample_min_size(Object endpoint_data,boolean include_encapsulation,short encapsulation_id,long currentAlignment) {
        long origAlignment = currentAlignment;

        long encapsulation_size = currentAlignment;
       
    
        if(include_encapsulation) {
            if (!CdrEncapsulation.isValidEncapsulationKind(encapsulation_id)) {
                throw new RETCODE_ERROR("Unsupported encapsulation");
            }

            encapsulation_size += CdrPrimitiveType.SHORT.getMaxSizeSerialized(encapsulation_size);
            encapsulation_size += CdrPrimitiveType.SHORT.getMaxSizeSerialized(encapsulation_size);
            encapsulation_size -= currentAlignment;
            currentAlignment = 0;
            origAlignment = 0;

        }
        
            currentAlignment += (CdrPrimitiveType.getPadSize(currentAlignment, 4) + 4);

        currentAlignment +=  CdrPrimitiveType.DOUBLE.getMaxSizeSerialized(0);
                currentAlignment += (CdrPrimitiveType.getPadSize(currentAlignment, 4) + 4);

        currentAlignment +=  CdrPrimitiveType.INT.getMaxSizeSerialized(0);
                currentAlignment += (CdrPrimitiveType.getPadSize(currentAlignment, 4) + 4);

        currentAlignment +=  CdrPrimitiveType.DOUBLE.getMaxSizeSerialized(0);
             
        // Sentinel
        currentAlignment += (CdrPrimitiveType.getPadSize(currentAlignment, 4) + 4);
        
        if (include_encapsulation) {
            currentAlignment += encapsulation_size;
        }
    
        return currentAlignment - origAlignment;
    }

    
    public long get_serialized_sample_size(
	Object endpoint_data, boolean include_encapsulation, 
        short encapsulation_id, long currentAlignment,
	Object sample) 
    {
        long origAlignment = currentAlignment;

        long encapsulation_size = currentAlignment;
        MutableTypeExampleModified typedSrc = (MutableTypeExampleModified) sample;
    
        if(include_encapsulation) {
            if (!CdrEncapsulation.isValidEncapsulationKind(encapsulation_id)) {
                throw new RETCODE_ERROR("Unsupported encapsulation");
            }


            encapsulation_size += CdrPrimitiveType.SHORT.getMaxSizeSerialized(encapsulation_size);
            encapsulation_size += CdrPrimitiveType.SHORT.getMaxSizeSerialized(encapsulation_size);
            encapsulation_size -= currentAlignment;
            currentAlignment = 0;
            origAlignment = 0;

        }
        
    currentAlignment += (CdrPrimitiveType.getPadSize(currentAlignment, 4) + 4);

        currentAlignment += CdrPrimitiveType.DOUBLE.getMaxSizeSerialized(0);
                currentAlignment += (CdrPrimitiveType.getPadSize(currentAlignment, 4) + 4);

        currentAlignment += CdrPrimitiveType.INT.getMaxSizeSerialized(0);
                currentAlignment += (CdrPrimitiveType.getPadSize(currentAlignment, 4) + 4);

        currentAlignment += CdrPrimitiveType.DOUBLE.getMaxSizeSerialized(0);
             
        // Sentinel
        currentAlignment += (CdrPrimitiveType.getPadSize(currentAlignment, 4) + 4);
    
     
    
        if (include_encapsulation) {
            currentAlignment += encapsulation_size;
        }

        return currentAlignment - origAlignment;
    }

    
    public long get_serialized_key_max_size(
        Object endpoint_data,
        boolean include_encapsulation, 
        short encapsulation_id,
        long currentAlignment) 
    {

        long encapsulation_size = currentAlignment;

        long origAlignment = currentAlignment;
                

        if(include_encapsulation) {
            if (!CdrEncapsulation.isValidEncapsulationKind(encapsulation_id)) {
                throw new RETCODE_ERROR("Unsupported encapsulation");
            }


            encapsulation_size += CdrPrimitiveType.SHORT.getMaxSizeSerialized(encapsulation_size);
            encapsulation_size += CdrPrimitiveType.SHORT.getMaxSizeSerialized(encapsulation_size);
            encapsulation_size -= currentAlignment;
            currentAlignment = 0;
            origAlignment = 0;

        }


    currentAlignment += get_serialized_sample_max_size(
                            endpoint_data,false,encapsulation_id,currentAlignment);
    
    // Sentinel
    currentAlignment += (CdrPrimitiveType.getPadSize(currentAlignment, 4) +
                                       2*CdrPrimitiveType.SHORT.size +
                                       2*CdrPrimitiveType.INT.size);
    
        if (include_encapsulation) {
            currentAlignment += encapsulation_size;
        }

        return currentAlignment - origAlignment;
    }

    
    public void serialize(Object endpoint_data,Object src, CdrOutputStream dst,boolean serialize_encapsulation,
                          short encapsulation_id, boolean serialize_sample, Object endpoint_plugin_qos) {
        int position = 0;
         
        long memberId = 0;
        int memberLengthPosition = 0;
        boolean skipListEndId_tmp = false;
        long maxLength = 0;
         
        if (!dst.isDirty()) {
            dst.setDirtyBit(true);

            maxLength = get_serialized_sample_max_size(endpoint_data, false, encapsulation_id,0);

            if (maxLength > 65535) {
                dst.useExtendedMemberId = true;
            } else {
                dst.useExtendedMemberId = false;
            }
        }

          
        skipListEndId_tmp =  dst.skipListEndId;
        dst.skipListEndId = false;
          

        if(serialize_encapsulation) {
         
            if (encapsulation_id == CdrEncapsulation.CDR_ENCAPSULATION_ID_CDR_BE) {
                encapsulation_id = CdrEncapsulation.CDR_ENCAPSULATION_ID_PL_CDR_BE;
            } else if (encapsulation_id == CdrEncapsulation.CDR_ENCAPSULATION_ID_CDR_LE) {
                encapsulation_id = CdrEncapsulation.CDR_ENCAPSULATION_ID_PL_CDR_LE;
            }
        
            dst.serializeAndSetCdrEncapsulation(encapsulation_id);;


            position = dst.resetAlignment();

        }


        if(serialize_sample) {
MutableTypeExampleModified typedSrc = (MutableTypeExampleModified) src;    
            memberId = 12;
            memberLengthPosition = dst.writeMemberId((short)memberId);

        dst.writeDouble(typedSrc.c);        
                        dst.writeMemberLength(memberLengthPosition, false);
            memberId = 21;
            memberLengthPosition = dst.writeMemberId((short)memberId);

        dst.writeInt(typedSrc.b);        
                        dst.writeMemberLength(memberLengthPosition, false);
            memberId = 22;
            memberLengthPosition = dst.writeMemberId((short)memberId);

        dst.writeDouble(typedSrc.d);        
                        dst.writeMemberLength(memberLengthPosition, false);

        if (!(skipListEndId_tmp)) {
            memberLengthPosition = 
                dst.writeMemberId(
                    (short)CdrEncapsulation.CDR_ENCAPSULATION_MEMBER_ID_LIST_END);
            dst.writeMemberLength(memberLengthPosition, false);
        }
        dst.skipListEndId = skipListEndId_tmp;
    
        }


        if (serialize_encapsulation) {
          dst.restoreAlignment(position);
        }
    
    }
 
    
    public void serialize_key(
        Object endpoint_data,
        Object src,
        CdrOutputStream dst,
        boolean serialize_encapsulation,
        short encapsulation_id,
        boolean serialize_key,
        Object endpoint_plugin_qos) 
    {
        int position = 0;
 
        long maxLength = 0;
        boolean skipListEndId_tmp = false;
 
        if (!dst.isDirty()) {
            // Top level
            dst.setDirtyBit(true);

            maxLength = get_serialized_sample_max_size(endpoint_data, false, encapsulation_id,0);

            if (maxLength > 65535) {
                dst.useExtendedMemberId = true;
            } else {
                dst.useExtendedMemberId = false;
            }
        }

        skipListEndId_tmp = dst.skipListEndId;
        dst.skipListEndId = false;

        if (serialize_encapsulation) {
             
            if (encapsulation_id == CdrEncapsulation.CDR_ENCAPSULATION_ID_CDR_BE) {
                encapsulation_id = CdrEncapsulation.CDR_ENCAPSULATION_ID_PL_CDR_BE;
            } else if (encapsulation_id == CdrEncapsulation.CDR_ENCAPSULATION_ID_CDR_LE) {
                encapsulation_id = CdrEncapsulation.CDR_ENCAPSULATION_ID_PL_CDR_LE;
            }
            
            dst.serializeAndSetCdrEncapsulation(encapsulation_id);


            position = dst.resetAlignment();

        }

        if (serialize_key) {
MutableTypeExampleModified typedSrc = (MutableTypeExampleModified) src;    

            serialize(endpoint_data, src, dst, false, CdrEncapsulation.CDR_ENCAPSULATION_ID_CDR_BE, true, endpoint_plugin_qos);
    
        }


        if (serialize_encapsulation) {
            dst.restoreAlignment(position);
        }

    }

    
    public Object deserialize_sample(
        Object endpoint_data,
        Object dst, 
        CdrInputStream src, boolean deserialize_encapsulation,
        boolean deserialize_sample,
        Object endpoint_plugin_qos) 
    {
        int position = 0;

        int memberId = 0;

        CdrMemberInfo memberInfo;
        long length = 0;
        boolean end = false;
        int tmpPosition, tmpSize;
        long tmpLength;
        

        if(deserialize_encapsulation) {
            src.deserializeAndSetCdrEncapsulation();


            position = src.resetAlignment();

        }

        if(deserialize_sample) {
MutableTypeExampleModified typedDst = (MutableTypeExampleModified) dst;
        
            typedDst.clear();
            while (end != true && src.available() > 0) {
                memberInfo = src.readMemberInfo();
                tmpPosition = src.getBuffer().currentPosition();
                tmpSize = src.getBuffer().getSize();
                tmpLength = memberInfo.length;
                src.getBuffer().setDesBufferSize((int)(tmpPosition + memberInfo.length));

                switch (memberInfo.memberId) {
                    case CdrEncapsulation.CDR_ENCAPSULATION_MEMBER_ID_IGNORE:
                        break;                        
                    case CdrEncapsulation.CDR_ENCAPSULATION_MEMBER_ID_LIST_END: 
                        end = true;
                        break;
                case 12:
            typedDst.c = src.readDouble();
            break;
                case 21:
            typedDst.b = src.readInt();
            break;
                case 22:
            typedDst.d = src.readDouble();
            break;
                    default:
                        if (memberInfo.flagMustUnderstand) {
                            throw new RETCODE_ERROR(
                                          "unknown member ID "+ 
                                          memberInfo.memberId);
                        } break;
                }
            
                src.getBuffer().setDesBufferSize(tmpSize);
                src.getBuffer().setCurrentPosition((int)(tmpPosition + tmpLength));
            }
        }


        if (deserialize_encapsulation) {
            src.restoreAlignment(position);
        }


        return dst;
    }


    
    public Object deserialize_key_sample(
        Object endpoint_data,
        Object dst,
        CdrInputStream src,
        boolean deserialize_encapsulation,
        boolean deserialize_key,
        Object endpoint_plugin_qos) 
    {
        int position = 0;

        CdrMemberInfo memberInfo;
        int memberId = 0;
        long length = 0;
        boolean end = false;
        int tmpPosition, tmpSize;
        long tmpLength;
        

        if(deserialize_encapsulation) {
            src.deserializeAndSetCdrEncapsulation();


            position = src.resetAlignment();

        }

        if(deserialize_key) {
MutableTypeExampleModified typedDst = (MutableTypeExampleModified) dst;


        deserialize_sample(endpoint_data, dst, src, false, true, endpoint_plugin_qos);
    
        }


        if (deserialize_encapsulation) {
            src.restoreAlignment(position);
        }


        return dst;
    }

    
    public void skip(Object endpoint_data, 
                     CdrInputStream src,
                     boolean skip_encapsulation, 
                     boolean skip_sample, 
                     Object endpoint_plugin_qos)
    {
        int position = 0;

        int memberId = 0;

        CdrMemberInfo memberInfo;
        long length = 0;
        boolean end = false;
        int tmpPosition, tmpSize;
        long tmpLength;
        

        if (skip_encapsulation) {
            src.skipEncapsulation();


            position = src.resetAlignment();

        }

        if (skip_sample) {

            while (end != true && src.available() > 0) {
                memberInfo = src.readMemberInfo();
                tmpPosition = src.getBuffer().currentPosition();
                tmpSize = src.getBuffer().getSize();
                tmpLength = memberInfo.length;
                src.getBuffer().setDesBufferSize((int)(tmpPosition + memberInfo.length));

                switch (memberInfo.memberId) {
                    case CdrEncapsulation.CDR_ENCAPSULATION_MEMBER_ID_IGNORE:
                        break;                        
                    case CdrEncapsulation.CDR_ENCAPSULATION_MEMBER_ID_LIST_END: 
                        end = true;
                        break;
                case 12:
            src.skipDouble();
            break;
                case 21:
            src.skipInt();
            break;
                case 22:
            src.skipDouble();
            break;
                    default:
                        if (memberInfo.flagMustUnderstand) {
                            throw new RETCODE_ERROR(
                                          "unknown member ID "+ 
                                          memberInfo.memberId);
                        } break;
                }
            
                src.getBuffer().setDesBufferSize(tmpSize);
                src.getBuffer().setCurrentPosition((int)(tmpPosition + tmpLength));
            }
        }


        if (skip_encapsulation) {
            src.restoreAlignment(position);
        }

    }

    public Object serialized_sample_to_key(
        Object endpoint_data,
        Object sample,
        CdrInputStream src, 
        boolean deserialize_encapsulation,  
        boolean deserialize_key, 
        Object endpoint_plugin_qos) 
    {
        int position = 0;
 
       
        if(deserialize_encapsulation) {
            src.deserializeAndSetCdrEncapsulation();

            position = src.resetAlignment();

        }

        if (deserialize_key) {
MutableTypeExampleModified typedDst = (MutableTypeExampleModified) sample;


            deserialize_sample(
                endpoint_data, sample, src, false, 
                true, endpoint_plugin_qos);

        }


        if (deserialize_encapsulation) {
            src.restoreAlignment(position);
        }


        return sample;
    }



    // -----------------------------------------------------------------------
    // Callbacks
    // -----------------------------------------------------------------------

    public Object on_participant_attached(Object registration_data,
                                          TypeSupportParticipantInfo participant_info,
                                          boolean top_level_registration,
                                          Object container_plugin_context,
                                          TypeCode type_code) {
        return super.on_participant_attached(
            registration_data, participant_info, top_level_registration,
            container_plugin_context, type_code);
    }

    public void on_participant_detached(Object participant_data) {
        super.on_participant_detached(participant_data);
    }

    public Object on_endpoint_attached(Object participantData,
                                       TypeSupportEndpointInfo endpoint_info,
                                       boolean top_level_registration,
                                       Object container_plugin_context) {
        return super.on_endpoint_attached(
              participantData,  endpoint_info,  
              top_level_registration, container_plugin_context);        
    }

    public void on_endpoint_detached(Object endpoint_data) {
        super.on_endpoint_detached(endpoint_data);
    }

    // -----------------------------------------------------------------------
    // Protected Methods
    // -----------------------------------------------------------------------

    protected DataWriter create_datawriter(long native_writer,
                                           DataWriterListener listener,
                                           int mask) {
        
        return new MutableTypeExampleModifiedDataWriter(native_writer, listener, mask, this);                
            
    }

    protected DataReader create_datareader(long native_reader,
                                           DataReaderListener listener,
                                           int mask) {
        
        return new MutableTypeExampleModifiedDataReader(native_reader, listener, mask, this);                
            
    }

    // -----------------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------------

    protected MutableTypeExampleModifiedTypeSupport() {
        
        /* If the user data type supports keys, then the second argument
        to the constructor below should be true.  Otherwise it should
        be false. */        

        super(TYPE_NAME, false,MutableTypeExampleModifiedTypeCode.VALUE,MutableTypeExampleModified.class,TypeSupportType.TST_STRUCT, PLUGIN_VERSION);
    
    }

    protected MutableTypeExampleModifiedTypeSupport(boolean enableKeySupport) {
    
        super(TYPE_NAME, enableKeySupport,MutableTypeExampleModifiedTypeCode.VALUE,MutableTypeExampleModified.class,TypeSupportType.TST_STRUCT, PLUGIN_VERSION);
    }
}


/*
  WARNING: THIS FILE IS AUTO-GENERATED. DO NOT MODIFY.

  This file was generated from .idl using "rtiddsgen".
  The rtiddsgen tool is part of the RTI Connext distribution.
  For more information, type 'rtiddsgen -help' at a command shell
  or consult the RTI Connext manual.
*/
    
package com.rti.xihui.fromscratch.truck.truckfleet;
        
import com.rti.dds.typecode.*;


public class GPSInfoTypeTypeCode {
    public static final TypeCode VALUE = getTypeCode();

    private static TypeCode getTypeCode() {
        TypeCode tc = null;
        int i=0;
        StructMember sm[] = new StructMember[4];

        sm[i]=new StructMember("VIN",false,(short)-1,true,(TypeCode)com.rti.xihui.fromscratch.truck.truckfleet.VINTypeTypeCode.VALUE); i++;
        sm[i]=new StructMember("position",false,(short)-1,false,(TypeCode)com.rti.xihui.fromscratch.truck.truckfleet.LongLatTypeTypeCode.VALUE); i++;
        sm[i]=new StructMember("speed",false,(short)-1,false,(TypeCode)TypeCode.TC_ULONG); i++;
        sm[i]=new StructMember("direction",false,(short)-1,false,(TypeCode)TypeCode.TC_ULONG); i++;

        tc = TypeCodeFactory.TheTypeCodeFactory.create_struct_tc("com::rti::xihui::fromscratch::truck::truckfleet::GPSInfoType",ExtensibilityKind.EXTENSIBLE_EXTENSIBILITY,sm);
        return tc;
    }
}

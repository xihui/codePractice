
/*
  WARNING: THIS FILE IS AUTO-GENERATED. DO NOT MODIFY.

  This file was generated from .idl using "rtiddsgen".
  The rtiddsgen tool is part of the RTI Connext distribution.
  For more information, type 'rtiddsgen -help' at a command shell
  or consult the RTI Connext manual.
*/
    
package com.rti.xihui.fromscratch.truck.truckfleet;
        
import com.rti.dds.typecode.*;


public class ServiceKindTypeCode {
    public static final TypeCode VALUE = getTypeCode();

    private static TypeCode getTypeCode() {
        TypeCode tc = null;
        int i=0;
        EnumMember em[] = new EnumMember[3];

        int[] ordinals = ServiceKind.getOrdinals();
        for (i=0;i<3;i++) {
            em[i]=new EnumMember(ServiceKind.valueOf(ordinals[i]).toString(),ordinals[i]);
        }

        tc = TypeCodeFactory.TheTypeCodeFactory.create_enum_tc("com::rti::xihui::fromscratch::truck::truckfleet::ServiceKind",em);
        return tc;
    }
}

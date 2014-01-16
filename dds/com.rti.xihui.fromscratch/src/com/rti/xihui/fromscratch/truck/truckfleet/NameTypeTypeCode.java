
/*
  WARNING: THIS FILE IS AUTO-GENERATED. DO NOT MODIFY.

  This file was generated from .idl using "rtiddsgen".
  The rtiddsgen tool is part of the RTI Connext distribution.
  For more information, type 'rtiddsgen -help' at a command shell
  or consult the RTI Connext manual.
*/
    
package com.rti.xihui.fromscratch.truck.truckfleet;
        
import com.rti.dds.typecode.*;


public class NameTypeTypeCode {
    public static final TypeCode VALUE = getTypeCode();

    private static TypeCode getTypeCode() {
        TypeCode tc = null;
        tc = TypeCodeFactory.TheTypeCodeFactory.create_alias_tc("com::rti::xihui::fromscratch::truck::truckfleet::NameType",new TypeCode(TCKind.TK_STRING,(com.rti.xihui.fromscratch.truck.truckfleet.NAME_MAXLEN.VALUE)),false);
        return tc;
    }
}


/*
  WARNING: THIS FILE IS AUTO-GENERATED. DO NOT MODIFY.

  This file was generated from .idl using "rtiddsgen".
  The rtiddsgen tool is part of the RTI Connext distribution.
  For more information, type 'rtiddsgen -help' at a command shell
  or consult the RTI Connext manual.
*/
    
package com.rti.xihui.fromscratch.fullidl;
        
import com.rti.dds.typecode.*;


public class PointerStructTypeCode {
    public static final TypeCode VALUE = getTypeCode();

    private static TypeCode getTypeCode() {
        TypeCode tc = null;
        int i=0;
        StructMember sm[] = new StructMember[1];

        sm[i]=new StructMember("member",true,(short)-1,false,(TypeCode)TypeCode.TC_LONG,0,false); i++;

        tc = TypeCodeFactory.TheTypeCodeFactory.create_struct_tc("com::rti::xihui::fromscratch::fullidl::PointerStruct",ExtensibilityKind.EXTENSIBLE_EXTENSIBILITY,sm);
        return tc;
    }
}

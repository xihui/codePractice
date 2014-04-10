
/*
  WARNING: THIS FILE IS AUTO-GENERATED. DO NOT MODIFY.

  This file was generated from .idl using "rtiddsgen".
  The rtiddsgen tool is part of the RTI Connext distribution.
  For more information, type 'rtiddsgen -help' at a command shell
  or consult the RTI Connext manual.
*/
    
package com.rti.xihui.fromscratch.fullidl2;
        
import com.rti.dds.typecode.*;

//Text that is copied from IDL before BaseValueType

public class MutableTypeExampleTypeCode {
    public static final TypeCode VALUE = getTypeCode();

    private static TypeCode getTypeCode() {
        TypeCode tc = null;
        int i=0;
        StructMember sm[] = new StructMember[3];

        sm[i]=new StructMember("a",false,(short)-1,false,(TypeCode)TypeCode.TC_SHORT,20,false); i++;
        sm[i]=new StructMember("b",false,(short)-1,false,(TypeCode)TypeCode.TC_LONG,21,false); i++;
        sm[i]=new StructMember("c",false,(short)-1,false,(TypeCode)TypeCode.TC_DOUBLE,100,false); i++;

        tc = TypeCodeFactory.TheTypeCodeFactory.create_struct_tc("com::rti::xihui::fromscratch::fullidl::MutableTypeExample",ExtensibilityKind.MUTABLE_EXTENSIBILITY,sm);
        return tc;
    }
}

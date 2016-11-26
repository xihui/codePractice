package com.rti.xihui.fromscratch.nested.idl;

/*
  WARNING: THIS FILE IS AUTO-GENERATED. DO NOT MODIFY.

  This file was generated from .idl using "rtiddsgen".
  The rtiddsgen tool is part of the RTI Connext distribution.
  For more information, type 'rtiddsgen -help' at a command shell
  or consult the RTI Connext manual.
*/
    
import com.rti.dds.typecode.ExtensibilityKind;
import com.rti.dds.typecode.StructMember;
import com.rti.dds.typecode.TypeCode;
import com.rti.dds.typecode.TypeCodeFactory;


public class LongLatTypeTypeCode {
    public static final TypeCode VALUE = getTypeCode();

    private static TypeCode getTypeCode() {
        TypeCode tc = null;
        int i=0;
        StructMember sm[] = new StructMember[2];

        sm[i]=new StructMember("longtitude",false,(short)-1,false,(TypeCode)TypeCode.TC_FLOAT,0,false); i++;
        sm[i]=new StructMember("latitude",false,(short)-1,false,(TypeCode)TypeCode.TC_FLOAT,1,false); i++;

        tc = TypeCodeFactory.TheTypeCodeFactory.create_struct_tc("LongLatType",ExtensibilityKind.EXTENSIBLE_EXTENSIBILITY,sm);
        return tc;
    }
}

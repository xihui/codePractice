
/*
  WARNING: THIS FILE IS AUTO-GENERATED. DO NOT MODIFY.

  This file was generated from .idl using "rtiddsgen".
  The rtiddsgen tool is part of the RTI Connext distribution.
  For more information, type 'rtiddsgen -help' at a command shell
  or consult the RTI Connext manual.
*/
    
package com.rti.xihui.fromscratch.idl;
        
import com.rti.dds.typecode.*;


public class HelloMsgTypeCode {
    public static final TypeCode VALUE = getTypeCode();

    private static TypeCode getTypeCode() {
        TypeCode tc = null;
        int i=0;
        StructMember sm[] = new StructMember[3];

        sm[i]=new StructMember("id",false,(short)-1,true,(TypeCode)TypeCode.TC_LONG,0,false); i++;
        sm[i]=new StructMember("msg",false,(short)-1,false,(TypeCode)new TypeCode(TCKind.TK_STRING,(com.rti.xihui.fromscratch.idl.MAX_MSG_LENGTH.VALUE)),1,false); i++;
        sm[i]=new StructMember("mySeq",false,(short)-1,false,(TypeCode)new TypeCode((com.rti.xihui.fromscratch.idl.SEQ_LENGTH.VALUE),TypeCode.TC_LONG),2,false); i++;

        tc = TypeCodeFactory.TheTypeCodeFactory.create_struct_tc("HelloMsg",ExtensibilityKind.EXTENSIBLE_EXTENSIBILITY,sm);
        return tc;
    }
}

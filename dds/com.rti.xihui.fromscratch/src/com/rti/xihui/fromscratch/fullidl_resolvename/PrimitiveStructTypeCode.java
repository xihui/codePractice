
/*
  WARNING: THIS FILE IS AUTO-GENERATED. DO NOT MODIFY.

  This file was generated from .idl using "rtiddsgen".
  The rtiddsgen tool is part of the RTI Connext distribution.
  For more information, type 'rtiddsgen -help' at a command shell
  or consult the RTI Connext manual.
*/
    
package com.rti.xihui.fromscratch.fullidl_resolvename;
        
import com.rti.dds.typecode.*;


public class PrimitiveStructTypeCode {
    public static final TypeCode VALUE = getTypeCode();

    private static TypeCode getTypeCode() {
        TypeCode tc = null;
        int i=0;
        StructMember sm[] = new StructMember[14];

        sm[i]=new StructMember("charMember",false,(short)-1,false,(TypeCode)TypeCode.TC_CHAR,0,false); i++;
        sm[i]=new StructMember("wcharMember",false,(short)-1,false,(TypeCode)TypeCode.TC_WCHAR,1,false); i++;
        sm[i]=new StructMember("octetmember",false,(short)-1,false,(TypeCode)TypeCode.TC_OCTET,2,false); i++;
        sm[i]=new StructMember("shortMember",false,(short)-1,false,(TypeCode)TypeCode.TC_SHORT,3,false); i++;
        sm[i]=new StructMember("unsignedShortMember",false,(short)-1,false,(TypeCode)TypeCode.TC_USHORT,4,false); i++;
        sm[i]=new StructMember("longMember",false,(short)-1,true,(TypeCode)TypeCode.TC_LONG,5,false); i++;
        sm[i]=new StructMember("unsignedLongMember",false,(short)-1,false,(TypeCode)TypeCode.TC_ULONG,6,false); i++;
        sm[i]=new StructMember("longLongMember",false,(short)-1,false,(TypeCode)TypeCode.TC_LONGLONG,7,false); i++;
        sm[i]=new StructMember("unsignedLongLongMember",false,(short)-1,false,(TypeCode)TypeCode.TC_ULONGLONG,8,false); i++;
        sm[i]=new StructMember("floatMember",false,(short)-1,false,(TypeCode)TypeCode.TC_FLOAT,9,false); i++;
        sm[i]=new StructMember("doubleMember",false,(short)-1,false,(TypeCode)TypeCode.TC_DOUBLE,10,false); i++;
        sm[i]=new StructMember("longDoubleMember",false,(short)-1,false,(TypeCode)TypeCode.TC_LONGDOUBLE,11,false); i++;
        sm[i]=new StructMember("booleanMember",false,(short)-1,false,(TypeCode)TypeCode.TC_BOOLEAN,12,false); i++;
        sm[i]=new StructMember("pointerStruct",false,(short)-1,false,(TypeCode)PointerStructTypeCode.VALUE,13,false); i++;

        tc = TypeCodeFactory.TheTypeCodeFactory.create_struct_tc("com::rti::xihui::fromscratch::fullidl::PrimitiveStruct",ExtensibilityKind.EXTENSIBLE_EXTENSIBILITY,sm);
        return tc;
    }
}

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


public class ServiceTypeTypeCode {
    public static final TypeCode VALUE = getTypeCode();

    private static TypeCode getTypeCode() {
        TypeCode tc = null;
        int i=0;
        StructMember sm[] = new StructMember[6];

        sm[i]=new StructMember("VIN",false,(short)-1,true,(TypeCode)VINTypeTypeCode.VALUE,0,false); i++;
        sm[i]=new StructMember("date",false,(short)-1,true,(TypeCode)TypeCode.TC_ULONG,1,false); i++;
        sm[i]=new StructMember("kind",false,(short)-1,false,(TypeCode)ServiceKindTypeCode.VALUE,2,false); i++;
        sm[i]=new StructMember("remarks",false,(short)-1,false,(TypeCode)RemarksTypeTypeCode.VALUE,3,false); i++;
        sm[i]=new StructMember("fuelgauge",false,(short)-1,false,(TypeCode)FuelGaugeTypeTypeCode.VALUE,4,false); i++;
        sm[i]=new StructMember("position",false,(short)-1,false,(TypeCode)LongLatTypeTypeCode.VALUE,5,false); i++;

        tc = TypeCodeFactory.TheTypeCodeFactory.create_struct_tc("ServiceType",ExtensibilityKind.EXTENSIBLE_EXTENSIBILITY,sm);
        return tc;
    }
}

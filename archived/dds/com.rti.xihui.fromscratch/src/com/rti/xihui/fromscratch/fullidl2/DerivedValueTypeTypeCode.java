
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

public class DerivedValueTypeTypeCode {
    public static final TypeCode VALUE = getTypeCode();

    private static TypeCode getTypeCode() {
        TypeCode tc = null;
        int i=0;
        ValueMember sm[] = new ValueMember[1];

        sm[i]=new ValueMember("longMember2",false,(short)-1,false,PUBLIC_MEMBER.VALUE,(TypeCode)TypeCode.TC_LONG,(com.rti.xihui.fromscratch.fullidl2.BaseValueTypeTypeSupport.LAST_MEMBER_ID + 1),false); i++;

        tc = TypeCodeFactory.TheTypeCodeFactory.create_value_tc("com::rti::xihui::fromscratch::fullidl::DerivedValueType",ExtensibilityKind.EXTENSIBLE_EXTENSIBILITY,VM_NONE.VALUE,com.rti.xihui.fromscratch.fullidl2.BaseValueTypeTypeCode.VALUE,sm);
        return tc;
    }
}

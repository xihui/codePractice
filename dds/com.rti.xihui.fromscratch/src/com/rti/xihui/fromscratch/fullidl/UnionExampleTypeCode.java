
/*
  WARNING: THIS FILE IS AUTO-GENERATED. DO NOT MODIFY.

  This file was generated from .idl using "rtiddsgen".
  The rtiddsgen tool is part of the RTI Connext distribution.
  For more information, type 'rtiddsgen -help' at a command shell
  or consult the RTI Connext manual.
*/
    
package com.rti.xihui.fromscratch.fullidl;
        
import com.rti.dds.typecode.*;


public class UnionExampleTypeCode {
    public static final TypeCode VALUE = getTypeCode();

    private static TypeCode getTypeCode() {
        TypeCode tc = null;
        int i=0;
        UnionMember um[] = new UnionMember[3];

        um[i]=new UnionMember("shortMember",false,new int[] {(int)(1)}, (TypeCode)TypeCode.TC_SHORT,1); i++;
        um[i]=new UnionMember("longMember",false,new int[] {(int)(2),(int)(3)}, (TypeCode)TypeCode.TC_LONG,2); i++;
        um[i]=new UnionMember("doubleMember",false,new int[] {0x40000001}, (TypeCode)TypeCode.TC_DOUBLE,3); i++;

        tc = TypeCodeFactory.TheTypeCodeFactory.create_union_tc("com::rti::xihui::fromscratch::fullidl::UnionExample",ExtensibilityKind.EXTENSIBLE_EXTENSIBILITY,TypeCode.TC_LONG,2,um);
        return tc;
    }
}

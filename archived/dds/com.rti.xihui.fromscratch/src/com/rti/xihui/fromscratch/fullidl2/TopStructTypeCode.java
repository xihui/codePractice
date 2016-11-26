
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

public class TopStructTypeCode {
    public static final TypeCode VALUE = getTypeCode();

    private static TypeCode getTypeCode() {
        TypeCode tc = null;
        int i=0;
        StructMember sm[] = new StructMember[13];

        sm[i]=new StructMember("smallStringKey",false,(short)-1,true,(TypeCode)SMALL_STRINGTypeCode.VALUE,0,false); i++;
        sm[i]=new StructMember("primitiveStructMember",false,(short)-1,true,(TypeCode)PrimitiveStructTypeCode.VALUE,1,false); i++;
        sm[i]=new StructMember("shortArray",false,(short)-1,true,(TypeCode)ShortArrayTypeCode.VALUE,2,false); i++;
        sm[i]=new StructMember("stringUnbounded",false,(short)-1,false,(TypeCode)new TypeCode(TCKind.TK_STRING,255),3,false); i++;
        sm[i]=new StructMember("wideString",false,(short)-1,false,(TypeCode)new TypeCode(TCKind.TK_WSTRING,255),4,false); i++;
        sm[i]=new StructMember("unionMember",false,(short)-1,false,(TypeCode)UnionExampleTypeCode.VALUE,5,false); i++;
        sm[i]=new StructMember("enumExample",false,(short)-1,false,(TypeCode)EnumExampleTypeCode.VALUE,6,false); i++;
        sm[i]=new StructMember("shortSeq",false,(short)-1,false,(TypeCode)SeqOfShortTTypeCode.VALUE,7,false); i++;
        sm[i]=new StructMember("shortArraySeq",false,(short)-1,false,(TypeCode)SeqOfShortArrayTTypeCode.VALUE,8,false); i++;
        sm[i]=new StructMember("seqOfShortSeq",false,(short)-1,false,(TypeCode)SeqOfShortSeqTTypeCode.VALUE,9,false); i++;
        sm[i]=new StructMember("short1DArray",false,(short)-1,false,(TypeCode)new TypeCode(new int[] {2},TypeCode.TC_SHORT),10,false); i++;
        sm[i]=new StructMember("short2DArray",false,(short)-1,false,(TypeCode)new TypeCode(new int[] {2,3},TypeCode.TC_SHORT),11,false); i++;
        sm[i]=new StructMember("derivedValueTypeMember",false,(short)-1,false,(TypeCode)DerivedValueTypeTypeCode.VALUE,12,false); i++;

        tc = TypeCodeFactory.TheTypeCodeFactory.create_struct_tc("com::rti::xihui::fromscratch::fullidl::TopStruct",ExtensibilityKind.EXTENSIBLE_EXTENSIBILITY,sm);
        return tc;
    }
}

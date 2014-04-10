
/*
  WARNING: THIS FILE IS AUTO-GENERATED. DO NOT MODIFY.

  This file was generated from .idl using "rtiddsgen".
  The rtiddsgen tool is part of the RTI Connext distribution.
  For more information, type 'rtiddsgen -help' at a command shell
  or consult the RTI Connext manual.
*/
    
package shape;
        
import com.rti.dds.typecode.*;


public class ShapeTypeExtendedTypeCode {
    public static final TypeCode VALUE = getTypeCode();

    private static TypeCode getTypeCode() {
        TypeCode tc = null;
        int i=0;
        ValueMember sm[] = new ValueMember[2];

        sm[i]=new ValueMember("fillKind",false,(short)-1,false,PUBLIC_MEMBER.VALUE,(TypeCode)shape.ShapeFillKindTypeCode.VALUE,(shape.ShapeTypeTypeSupport.LAST_MEMBER_ID + 1),false); i++;
        sm[i]=new ValueMember("angle",false,(short)-1,false,PUBLIC_MEMBER.VALUE,(TypeCode)TypeCode.TC_FLOAT,(shape.ShapeTypeTypeSupport.LAST_MEMBER_ID + 2),false); i++;

        tc = TypeCodeFactory.TheTypeCodeFactory.create_value_tc("shape::ShapeTypeExtended",ExtensibilityKind.EXTENSIBLE_EXTENSIBILITY,VM_NONE.VALUE,shape.ShapeTypeTypeCode.VALUE,sm);
        return tc;
    }
}

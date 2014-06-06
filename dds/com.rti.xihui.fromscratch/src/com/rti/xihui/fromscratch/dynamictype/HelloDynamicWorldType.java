package com.rti.xihui.fromscratch.dynamictype;

import com.rti.dds.infrastructure.BAD_PARAM;
import com.rti.dds.infrastructure.BadKind;
import com.rti.dds.infrastructure.BadMemberId;
import com.rti.dds.infrastructure.BadMemberName;
import com.rti.dds.typecode.ExtensibilityKind;
import com.rti.dds.typecode.PUBLIC_MEMBER;
import com.rti.dds.typecode.StructMember;
import com.rti.dds.typecode.TCKind;
import com.rti.dds.typecode.TypeCode;
import com.rti.dds.typecode.TypeCodeFactory;
import com.rti.dds.typecode.VM_NONE;

public class HelloDynamicWorldType {
	public static final String PAYLOAD_FIELD = "payload";
	public static final int NUMBER_OF_FIELDS = 10;
	public static final String SAMPLE_ID_FIELD = "sampleId";
	public static final String DOUBLE_FIELD = "doubleField";
	public static final String NAME_FIELD = "name";
	public static final int HELLO_MAX_STRING_SIZE = 64;
	public static final int HELLO_MAX_PAYLOAD_SIZE = 2;

	/**
	 * Returns the name of the type
	 */
	public static String getTypeName() {
		return "HelloDynamicWorld";
	}

	/**
	 * Creates type code for a structure corresponding to:
	 * 
	 * struct HelloWorld { string<HELLO_MAX_STRING_SIZE> prefix; long sampleId;
	 * sequence<octet, HELLO_MAX_PAYLOAD_SIZE> payload; };
	 * 
	 * Returns NULL in case of error
	 */
	public static TypeCode create() {
		TypeCodeFactory factory = TypeCodeFactory.get_instance();
		if (factory == null) {
			System.err.println("! Unable to get type code factory singleton");
			return null;
		}

		try {
			TypeCode stringTc = factory.create_string_tc(HELLO_MAX_STRING_SIZE);

			TypeCode structTc =//factory.create_sparse_tc("MySparseType", VM_NONE.VALUE, null); 
					factory.create_struct_tc("DyanmicStruct", ExtensibilityKind.FINAL_EXTENSIBILITY,
					new StructMember[0]);
			structTc.add_member(NAME_FIELD, 11, stringTc,
					TypeCode.NONKEY_REQUIRED_MEMBER, PUBLIC_MEMBER.VALUE, false,
					(short) -1);
			structTc.add_member(SAMPLE_ID_FIELD, 15,
					TypeCode.TC_LONG, TypeCode.NONKEY_REQUIRED_MEMBER);
			structTc.add_member(DOUBLE_FIELD, 22,
					TypeCode.TC_DOUBLE, TypeCode.NONKEY_REQUIRED_MEMBER);
			for (int i = 0; i < NUMBER_OF_FIELDS; i++) {
				structTc.add_member("field" + i, i+23,//TypeCode.MEMBER_ID_INVALID,
						stringTc, TypeCode.NONKEY_MEMBER);
			}

			TypeCode sequenceTC = factory.create_sequence_tc(
					HELLO_MAX_PAYLOAD_SIZE, TypeCode.TC_OCTET);

			structTc.add_member(PAYLOAD_FIELD, 123, //TypeCode.MEMBER_ID_INVALID,
					sequenceTC, TypeCode.NONKEY_REQUIRED_MEMBER);
			
			
			TypeCode sparseTC = factory.create_sparse_tc("MySparseType", VM_NONE.VALUE, null);
			
			sparseTC.add_member("my_integer", 12, factory.get_primitive_tc(TCKind.TK_LONG), TypeCode.NONKEY_MEMBER);
			sparseTC.add_member("my_float", 14, factory.get_primitive_tc(TCKind.TK_FLOAT), TypeCode.NONKEY_MEMBER);
			sparseTC.add_member("my_double", 16, factory.get_primitive_tc(TCKind.TK_DOUBLE), TypeCode.NONKEY_MEMBER);
			sparseTC.add_member("my_longDouble", 19, factory.get_primitive_tc(TCKind.TK_LONGDOUBLE), TypeCode.NONKEY_MEMBER);
			sparseTC.add_member("my_bool", 20, factory.get_primitive_tc(TCKind.TK_BOOLEAN), TypeCode.NONKEY_MEMBER);
			sparseTC.add_member("my_string", 11, factory.create_string_tc(128), TypeCode.NONKEY_MEMBER);
			sparseTC.add_member("my_long", 22, factory.get_primitive_tc(TCKind.TK_LONGLONG), TypeCode.NONKEY_MEMBER);
			
			structTc.add_member("sparseMember", TypeCode.MEMBER_ID_INVALID, sparseTC, TypeCode.NONKEY_REQUIRED_MEMBER);		
			

			return structTc;
		} catch (BAD_PARAM e) {
			System.err.println("! Typecode creation error: bad param: "
					+ e.getMessage());
			e.printStackTrace();
		} catch (BadKind e) {
			System.err.println("! Typecode creation error: bad kind: "
					+ e.getMessage());
			e.printStackTrace();
		} catch (BadMemberName e) {
			System.err.println("! Typecode creation error: bad member name: "
					+ e.getMessage());
			e.printStackTrace();
		} catch (BadMemberId e) {
			System.err.println("! Typecode creation error: bad member ID: "
					+ e.getMessage());
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public static void delete(TypeCode type){
		TypeCodeFactory.get_instance().delete_tc(type);
	}
	
	private HelloDynamicWorldType(){
		
	}
	
	
}

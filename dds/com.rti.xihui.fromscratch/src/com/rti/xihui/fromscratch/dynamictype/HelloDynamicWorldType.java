package com.rti.xihui.fromscratch.dynamictype;

import com.rti.dds.typecode.TypeCode;
import com.rti.dds.typecode.TypeCodeFactory;

public class HelloDynamicWorldType {
    public static final int HELLO_MAX_STRING_SIZE = 64;
    public static final int HELLO_MAX_PAYLOAD_SIZE = 8192;
	
    /**
     * Returns the name of the type
     */
    public static String getTypeName() {
        return "HelloDynamicWorld";
    }
    
    /**
     * Creates type code for a structure corresponding to:
     *
     * struct HelloWorld {
     *    string<HELLO_MAX_STRING_SIZE>             prefix;
     *    long                                         sampleId;
     *    sequence<octet, HELLO_MAX_PAYLOAD_SIZE>   payload;
     * };
     *
     * Returns NULL in case of error
     */    
    public static TypeCode create(){
    	TypeCodeFactory factory = TypeCodeFactory.get_instance();
    	if(factory==null){
    		System.err.println("! Unable to get type code factory singleton");
    		return null;
    	}
    	
    	factory.create_string_tc(HELLO_MAX_STRING_SIZE);
    	
    	
    }
}

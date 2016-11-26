
/*
  WARNING: THIS FILE IS AUTO-GENERATED. DO NOT MODIFY.

  This file was generated from .idl using "rtiddsgen".
  The rtiddsgen tool is part of the RTI Connext distribution.
  For more information, type 'rtiddsgen -help' at a command shell
  or consult the RTI Connext manual.
*/
    
package com.rti.xihui.fromscratch.fullidl_resolvename;
        

import com.rti.dds.util.Enum;
import com.rti.dds.cdr.CdrHelper;
import java.util.Arrays;
import java.io.ObjectStreamException;



public class EnumExample extends Enum
{

    public static final EnumExample ENUM1 = new EnumExample("ENUM1", 0);
    public static final int _ENUM1 = 0;
    
    public static final EnumExample ENUM2 = new EnumExample("ENUM2", 1);
    public static final int _ENUM2 = 1;
    
    public static final EnumExample ENUM3 = new EnumExample("ENUM3", 2);
    public static final int _ENUM3 = 2;
    


    public static EnumExample valueOf(int ordinal) {
        switch(ordinal) {
            
              case 0: return EnumExample.ENUM1;
            
              case 1: return EnumExample.ENUM2;
            
              case 2: return EnumExample.ENUM3;
            

        }
        return null;
    }

    public static EnumExample from_int(int __value) {
        return valueOf(__value);
    }

    public static int[] getOrdinals() {
        int i = 0;
        int[] values = new int[3];
        
        
        values[i] = ENUM1.ordinal();
        i++;
        
        values[i] = ENUM2.ordinal();
        i++;
        
        values[i] = ENUM3.ordinal();
        i++;
        

        Arrays.sort(values);
        return values;
    }

    public int value() {
        return super.ordinal();
    }

    /**
     * Create a default instance
     */  
    public static EnumExample create() {
        

        return valueOf(0);
    }
    
    /**
     * Print Method
     */     
    public String toString(String desc, int indent) {
        StringBuffer strBuffer = new StringBuffer();

        CdrHelper.printIndent(strBuffer, indent);
            
        if (desc != null) {
            strBuffer.append(desc).append(": ");
        }
        
        strBuffer.append(this);
        strBuffer.append("\n");              
        return strBuffer.toString();
    }

    private Object readResolve() throws ObjectStreamException {
        return valueOf(ordinal());
    }

    private EnumExample(String name, int ordinal) {
        super(name, ordinal);
    }
}


//Text that is copied from IDL before BaseValueType
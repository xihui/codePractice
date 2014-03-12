package com.rti.xihui.fromscratch.nested.idl;

/*
  WARNING: THIS FILE IS AUTO-GENERATED. DO NOT MODIFY.

  This file was generated from .idl using "rtiddsgen".
  The rtiddsgen tool is part of the RTI Connext distribution.
  For more information, type 'rtiddsgen -help' at a command shell
  or consult the RTI Connext manual.
*/
    

import java.io.ObjectStreamException;
import java.util.Arrays;

import com.rti.dds.cdr.CdrHelper;
import com.rti.dds.util.Enum;



public class ServiceKind extends Enum
{

    public static final ServiceKind ServiceKind_Unknown = new ServiceKind("ServiceKind_Unknown", 0);
    public static final int _ServiceKind_Unknown = 0;
    
    public static final ServiceKind ServiceKind_Small = new ServiceKind("ServiceKind_Small", 1);
    public static final int _ServiceKind_Small = 1;
    
    public static final ServiceKind ServiceKind_Large = new ServiceKind("ServiceKind_Large", 2);
    public static final int _ServiceKind_Large = 2;
    


    public static ServiceKind valueOf(int ordinal) {
        switch(ordinal) {
            
              case 0: return ServiceKind.ServiceKind_Unknown;
            
              case 1: return ServiceKind.ServiceKind_Small;
            
              case 2: return ServiceKind.ServiceKind_Large;
            

        }
        return null;
    }

    public static ServiceKind from_int(int __value) {
        return valueOf(__value);
    }

    public static int[] getOrdinals() {
        int i = 0;
        int[] values = new int[3];
        
        
        values[i] = ServiceKind_Unknown.ordinal();
        i++;
        
        values[i] = ServiceKind_Small.ordinal();
        i++;
        
        values[i] = ServiceKind_Large.ordinal();
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
    public static ServiceKind create() {
        

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

    private ServiceKind(String name, int ordinal) {
        super(name, ordinal);
    }
}


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



public class StateKind extends Enum
{

    public static final StateKind StateKind_Unknown = new StateKind("StateKind_Unknown", 0);
    public static final int _StateKind_Unknown = 0;
    
    public static final StateKind StateKind_InGarage = new StateKind("StateKind_InGarage", 1);
    public static final int _StateKind_InGarage = 1;
    
    public static final StateKind StateKind_onTheRoad = new StateKind("StateKind_onTheRoad", 2);
    public static final int _StateKind_onTheRoad = 2;
    


    public static StateKind valueOf(int ordinal) {
        switch(ordinal) {
            
              case 0: return StateKind.StateKind_Unknown;
            
              case 1: return StateKind.StateKind_InGarage;
            
              case 2: return StateKind.StateKind_onTheRoad;
            

        }
        return null;
    }

    public static StateKind from_int(int __value) {
        return valueOf(__value);
    }

    public static int[] getOrdinals() {
        int i = 0;
        int[] values = new int[3];
        
        
        values[i] = StateKind_Unknown.ordinal();
        i++;
        
        values[i] = StateKind_InGarage.ordinal();
        i++;
        
        values[i] = StateKind_onTheRoad.ordinal();
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
    public static StateKind create() {
        

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

    private StateKind(String name, int ordinal) {
        super(name, ordinal);
    }
}


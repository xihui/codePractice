
/*
  WARNING: THIS FILE IS AUTO-GENERATED. DO NOT MODIFY.

  This file was generated from .idl using "rtiddsgen".
  The rtiddsgen tool is part of the RTI Connext distribution.
  For more information, type 'rtiddsgen -help' at a command shell
  or consult the RTI Connext manual.
*/
    
package com.rti.xihui.fromscratch.truck.truckfleet;
        

import com.rti.dds.infrastructure.*;
import com.rti.dds.infrastructure.Copyable;

import java.io.Serializable;
import com.rti.dds.cdr.CdrHelper;


public class TruckType implements Copyable, Serializable
{

    public String VIN = ""; /* maximum length = ((com.rti.xihui.fromscratch.truck.truckfleet.VIN_MAXLEN.VALUE)) */
    public String plate = ""; /* maximum length = ((com.rti.xihui.fromscratch.truck.truckfleet.PLATE_MAXLEN.VALUE)) */
    public String nickNamek = ""; /* maximum length = ((com.rti.xihui.fromscratch.truck.truckfleet.NAME_MAXLEN.VALUE)) */
    public com.rti.xihui.fromscratch.truck.truckfleet.StateKind state = (com.rti.xihui.fromscratch.truck.truckfleet.StateKind) com.rti.xihui.fromscratch.truck.truckfleet.StateKind.create();


    public TruckType() {

    }


    public TruckType(TruckType other) {

        this();
        copy_from(other);
    }



    public static Object create() {
        return new TruckType();
    }

    public boolean equals(Object o) {
                
        if (o == null) {
            return false;
        }        
        
        

        if(getClass() != o.getClass()) {
            return false;
        }

        TruckType otherObj = (TruckType)o;



        if(!VIN.equals(otherObj.VIN)) {
            return false;
        }
            
        if(!plate.equals(otherObj.plate)) {
            return false;
        }
            
        if(!nickNamek.equals(otherObj.nickNamek)) {
            return false;
        }
            
        if(!state.equals(otherObj.state)) {
            return false;
        }
            
        return true;
    }

    public int hashCode() {
        int __result = 0;

        __result += VIN.hashCode();
                
        __result += plate.hashCode();
                
        __result += nickNamek.hashCode();
                
        __result += state.hashCode();
                
        return __result;
    }
    

    /**
     * This is the implementation of the <code>Copyable</code> interface.
     * This method will perform a deep copy of <code>src</code>
     * This method could be placed into <code>TruckTypeTypeSupport</code>
     * rather than here by using the <code>-noCopyable</code> option
     * to rtiddsgen.
     * 
     * @param src The Object which contains the data to be copied.
     * @return Returns <code>this</code>.
     * @exception NullPointerException If <code>src</code> is null.
     * @exception ClassCastException If <code>src</code> is not the 
     * same type as <code>this</code>.
     * @see com.rti.dds.infrastructure.Copyable#copy_from(java.lang.Object)
     */
    public Object copy_from(Object src) {
        

        TruckType typedSrc = (TruckType) src;
        TruckType typedDst = this;

        typedDst.VIN = typedSrc.VIN;
            
        typedDst.plate = typedSrc.plate;
            
        typedDst.nickNamek = typedSrc.nickNamek;
            
        typedDst.state = (com.rti.xihui.fromscratch.truck.truckfleet.StateKind) typedDst.state.copy_from(typedSrc.state);
            
        return this;
    }


    
    public String toString(){
        return toString("", 0);
    }
        
    
    public String toString(String desc, int indent) {
        StringBuffer strBuffer = new StringBuffer();        
                        
        
        if (desc != null) {
            CdrHelper.printIndent(strBuffer, indent);
            strBuffer.append(desc).append(":\n");
        }
        
        
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("VIN: ").append(VIN).append("\n");
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("plate: ").append(plate).append("\n");
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("nickNamek: ").append(nickNamek).append("\n");
            
        strBuffer.append(state.toString("state ", indent+1));
            
        return strBuffer.toString();
    }
    
}


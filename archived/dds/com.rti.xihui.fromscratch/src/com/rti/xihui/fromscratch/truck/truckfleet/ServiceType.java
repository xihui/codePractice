
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


public class ServiceType implements Copyable, Serializable
{

    public String VIN = ""; /* maximum length = ((com.rti.xihui.fromscratch.truck.truckfleet.VIN_MAXLEN.VALUE)) */
    public int date;
    public com.rti.xihui.fromscratch.truck.truckfleet.ServiceKind kind = (com.rti.xihui.fromscratch.truck.truckfleet.ServiceKind) com.rti.xihui.fromscratch.truck.truckfleet.ServiceKind.create();
    public String remarks = ""; /* maximum length = ((com.rti.xihui.fromscratch.truck.truckfleet.REMARKS_MAXLEN.VALUE)) */


    public ServiceType() {

    }


    public ServiceType(ServiceType other) {

        this();
        copy_from(other);
    }



    public static Object create() {
        return new ServiceType();
    }

    public boolean equals(Object o) {
                
        if (o == null) {
            return false;
        }        
        
        

        if(getClass() != o.getClass()) {
            return false;
        }

        ServiceType otherObj = (ServiceType)o;



        if(!VIN.equals(otherObj.VIN)) {
            return false;
        }
            
        if(date != otherObj.date) {
            return false;
        }
            
        if(!kind.equals(otherObj.kind)) {
            return false;
        }
            
        if(!remarks.equals(otherObj.remarks)) {
            return false;
        }
            
        return true;
    }

    public int hashCode() {
        int __result = 0;

        __result += VIN.hashCode();
                
        __result += (int)date;
                
        __result += kind.hashCode();
                
        __result += remarks.hashCode();
                
        return __result;
    }
    

    /**
     * This is the implementation of the <code>Copyable</code> interface.
     * This method will perform a deep copy of <code>src</code>
     * This method could be placed into <code>ServiceTypeTypeSupport</code>
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
        

        ServiceType typedSrc = (ServiceType) src;
        ServiceType typedDst = this;

        typedDst.VIN = typedSrc.VIN;
            
        typedDst.date = typedSrc.date;
            
        typedDst.kind = (com.rti.xihui.fromscratch.truck.truckfleet.ServiceKind) typedDst.kind.copy_from(typedSrc.kind);
            
        typedDst.remarks = typedSrc.remarks;
            
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
        strBuffer.append("date: ").append(date).append("\n");
            
        strBuffer.append(kind.toString("kind ", indent+1));
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("remarks: ").append(remarks).append("\n");
            
        return strBuffer.toString();
    }
    
}


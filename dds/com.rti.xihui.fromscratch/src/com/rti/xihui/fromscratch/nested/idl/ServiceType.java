package com.rti.xihui.fromscratch.nested.idl;

/*
  WARNING: THIS FILE IS AUTO-GENERATED. DO NOT MODIFY.

  This file was generated from .idl using "rtiddsgen".
  The rtiddsgen tool is part of the RTI Connext distribution.
  For more information, type 'rtiddsgen -help' at a command shell
  or consult the RTI Connext manual.
*/
    

import java.io.Serializable;

import com.rti.dds.cdr.CdrHelper;
import com.rti.dds.infrastructure.Copyable;


public class ServiceType implements Copyable, Serializable
{

    public String VIN = ""; /* maximum length = ((VIN_MAXLEN.VALUE)) */
    public int date = 0;
    public ServiceKind kind = (ServiceKind) ServiceKind.create();
    public String remarks = ""; /* maximum length = ((REMARKS_MAXLEN.VALUE)) */
    public FuelGaugeType fuelgauge = (FuelGaugeType) FuelGaugeType.create();
    public LongLatType position = (LongLatType) LongLatType.create();


    public ServiceType() {

    }


    public ServiceType(ServiceType other) {

        this();
        copy_from(other);
    }



    public static Object create() {
        ServiceType self;
        self = new ServiceType();
         
        self.clear();
        
        return self;
    }

    public void clear() {
        
        VIN = "";
            
        date = 0;
            
        kind = ServiceKind.create();
            
        remarks = "";
            
        if (fuelgauge != null) {
            fuelgauge.clear();
        }
            
        if (position != null) {
            position.clear();
        }
            
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
            
        if(!fuelgauge.equals(otherObj.fuelgauge)) {
            return false;
        }
            
        if(!position.equals(otherObj.position)) {
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
                
        __result += fuelgauge.hashCode();
                
        __result += position.hashCode();
                
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
            
        typedDst.kind = (ServiceKind) typedDst.kind.copy_from(typedSrc.kind);
            
        typedDst.remarks = typedSrc.remarks;
            
        typedDst.fuelgauge = (FuelGaugeType) typedDst.fuelgauge.copy_from(typedSrc.fuelgauge);
            
        typedDst.position = (LongLatType) typedDst.position.copy_from(typedSrc.position);
            
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
            
        strBuffer.append(fuelgauge.toString("fuelgauge ", indent+1));
            
        strBuffer.append(position.toString("position ", indent+1));
            
        return strBuffer.toString();
    }
    
}


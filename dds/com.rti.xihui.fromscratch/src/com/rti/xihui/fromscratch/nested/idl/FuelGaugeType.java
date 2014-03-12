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


public class FuelGaugeType implements Copyable, Serializable
{

    public String VIN = ""; /* maximum length = ((VIN_MAXLEN.VALUE)) */
    public int level = 0;
    public float mileage = 0;
    public int distToEmpty = 0;


    public FuelGaugeType() {

    }


    public FuelGaugeType(FuelGaugeType other) {

        this();
        copy_from(other);
    }



    public static Object create() {
        FuelGaugeType self;
        self = new FuelGaugeType();
         
        self.clear();
        
        return self;
    }

    public void clear() {
        
        VIN = "";
            
        level = 0;
            
        mileage = 0;
            
        distToEmpty = 0;
            
    }

    public boolean equals(Object o) {
                
        if (o == null) {
            return false;
        }        
        
        

        if(getClass() != o.getClass()) {
            return false;
        }

        FuelGaugeType otherObj = (FuelGaugeType)o;



        if(!VIN.equals(otherObj.VIN)) {
            return false;
        }
            
        if(level != otherObj.level) {
            return false;
        }
            
        if(mileage != otherObj.mileage) {
            return false;
        }
            
        if(distToEmpty != otherObj.distToEmpty) {
            return false;
        }
            
        return true;
    }

    public int hashCode() {
        int __result = 0;

        __result += VIN.hashCode();
                
        __result += (int)level;
                
        __result += (int)mileage;
                
        __result += (int)distToEmpty;
                
        return __result;
    }
    

    /**
     * This is the implementation of the <code>Copyable</code> interface.
     * This method will perform a deep copy of <code>src</code>
     * This method could be placed into <code>FuelGaugeTypeTypeSupport</code>
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
        

        FuelGaugeType typedSrc = (FuelGaugeType) src;
        FuelGaugeType typedDst = this;

        typedDst.VIN = typedSrc.VIN;
            
        typedDst.level = typedSrc.level;
            
        typedDst.mileage = typedSrc.mileage;
            
        typedDst.distToEmpty = typedSrc.distToEmpty;
            
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
        strBuffer.append("level: ").append(level).append("\n");
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("mileage: ").append(mileage).append("\n");
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("distToEmpty: ").append(distToEmpty).append("\n");
            
        return strBuffer.toString();
    }
    
}


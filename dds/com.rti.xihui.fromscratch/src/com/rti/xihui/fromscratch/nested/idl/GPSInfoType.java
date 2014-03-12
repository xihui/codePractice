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


public class GPSInfoType implements Copyable, Serializable
{

    public String VIN = ""; /* maximum length = ((VIN_MAXLEN.VALUE)) */
    public LongLatType position = (LongLatType) LongLatType.create();
    public int speed = 0;
    public int direction = 0;


    public GPSInfoType() {

    }


    public GPSInfoType(GPSInfoType other) {

        this();
        copy_from(other);
    }



    public static Object create() {
        GPSInfoType self;
        self = new GPSInfoType();
         
        self.clear();
        
        return self;
    }

    public void clear() {
        
        VIN = "";
            
        if (position != null) {
            position.clear();
        }
            
        speed = 0;
            
        direction = 0;
            
    }

    public boolean equals(Object o) {
                
        if (o == null) {
            return false;
        }        
        
        

        if(getClass() != o.getClass()) {
            return false;
        }

        GPSInfoType otherObj = (GPSInfoType)o;



        if(!VIN.equals(otherObj.VIN)) {
            return false;
        }
            
        if(!position.equals(otherObj.position)) {
            return false;
        }
            
        if(speed != otherObj.speed) {
            return false;
        }
            
        if(direction != otherObj.direction) {
            return false;
        }
            
        return true;
    }

    public int hashCode() {
        int __result = 0;

        __result += VIN.hashCode();
                
        __result += position.hashCode();
                
        __result += (int)speed;
                
        __result += (int)direction;
                
        return __result;
    }
    

    /**
     * This is the implementation of the <code>Copyable</code> interface.
     * This method will perform a deep copy of <code>src</code>
     * This method could be placed into <code>GPSInfoTypeTypeSupport</code>
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
        

        GPSInfoType typedSrc = (GPSInfoType) src;
        GPSInfoType typedDst = this;

        typedDst.VIN = typedSrc.VIN;
            
        typedDst.position = (LongLatType) typedDst.position.copy_from(typedSrc.position);
            
        typedDst.speed = typedSrc.speed;
            
        typedDst.direction = typedSrc.direction;
            
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
            
        strBuffer.append(position.toString("position ", indent+1));
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("speed: ").append(speed).append("\n");
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("direction: ").append(direction).append("\n");
            
        return strBuffer.toString();
    }
    
}


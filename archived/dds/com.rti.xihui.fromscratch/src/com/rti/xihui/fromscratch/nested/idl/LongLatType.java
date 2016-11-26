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


public class LongLatType implements Copyable, Serializable
{

    public float longtitude = 0;
    public float latitude = 0;


    public LongLatType() {

    }


    public LongLatType(LongLatType other) {

        this();
        copy_from(other);
    }



    public static Object create() {
        LongLatType self;
        self = new LongLatType();
         
        self.clear();
        
        return self;
    }

    public void clear() {
        
        longtitude = 0;
            
        latitude = 0;
            
    }

    public boolean equals(Object o) {
                
        if (o == null) {
            return false;
        }        
        
        

        if(getClass() != o.getClass()) {
            return false;
        }

        LongLatType otherObj = (LongLatType)o;



        if(longtitude != otherObj.longtitude) {
            return false;
        }
            
        if(latitude != otherObj.latitude) {
            return false;
        }
            
        return true;
    }

    public int hashCode() {
        int __result = 0;

        __result += (int)longtitude;
                
        __result += (int)latitude;
                
        return __result;
    }
    

    /**
     * This is the implementation of the <code>Copyable</code> interface.
     * This method will perform a deep copy of <code>src</code>
     * This method could be placed into <code>LongLatTypeTypeSupport</code>
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
        

        LongLatType typedSrc = (LongLatType) src;
        LongLatType typedDst = this;

        typedDst.longtitude = typedSrc.longtitude;
            
        typedDst.latitude = typedSrc.latitude;
            
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
        strBuffer.append("longtitude: ").append(longtitude).append("\n");
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("latitude: ").append(latitude).append("\n");
            
        return strBuffer.toString();
    }
    
}


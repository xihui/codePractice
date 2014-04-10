
/*
  WARNING: THIS FILE IS AUTO-GENERATED. DO NOT MODIFY.

  This file was generated from .idl using "rtiddsgen".
  The rtiddsgen tool is part of the RTI Connext distribution.
  For more information, type 'rtiddsgen -help' at a command shell
  or consult the RTI Connext manual.
*/
    
package com.rti.xihui.fromscratch.fullidl2;
        

import com.rti.dds.infrastructure.*;
import com.rti.dds.infrastructure.Copyable;

import java.io.Serializable;
import com.rti.dds.cdr.CdrHelper;


public class MutableTypeExampleModified implements Copyable, Serializable
{

    public double c = 0;
    public int b = 0;
    public double d = 0;


    public MutableTypeExampleModified() {

    }


    public MutableTypeExampleModified(MutableTypeExampleModified other) {

        this();
        copy_from(other);
    }



    public static Object create() {
        MutableTypeExampleModified self;
        self = new MutableTypeExampleModified();
         
        self.clear();
        
        return self;
    }

    public void clear() {
        
        c = 0;
            
        b = 0;
            
        d = 0;
            
    }

    public boolean equals(Object o) {
                
        if (o == null) {
            return false;
        }        
        
        

        if(getClass() != o.getClass()) {
            return false;
        }

        MutableTypeExampleModified otherObj = (MutableTypeExampleModified)o;



        if(c != otherObj.c) {
            return false;
        }
            
        if(b != otherObj.b) {
            return false;
        }
            
        if(d != otherObj.d) {
            return false;
        }
            
        return true;
    }

    public int hashCode() {
        int __result = 0;

        __result += (int)c;
                
        __result += (int)b;
                
        __result += (int)d;
                
        return __result;
    }
    

    /**
     * This is the implementation of the <code>Copyable</code> interface.
     * This method will perform a deep copy of <code>src</code>
     * This method could be placed into <code>MutableTypeExampleModifiedTypeSupport</code>
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
        

        MutableTypeExampleModified typedSrc = (MutableTypeExampleModified) src;
        MutableTypeExampleModified typedDst = this;

        typedDst.c = typedSrc.c;
            
        typedDst.b = typedSrc.b;
            
        typedDst.d = typedSrc.d;
            
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
        strBuffer.append("c: ").append(c).append("\n");
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("b: ").append(b).append("\n");
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("d: ").append(d).append("\n");
            
        return strBuffer.toString();
    }
    
}


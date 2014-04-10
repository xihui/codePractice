
/*
  WARNING: THIS FILE IS AUTO-GENERATED. DO NOT MODIFY.

  This file was generated from .idl using "rtiddsgen".
  The rtiddsgen tool is part of the RTI Connext distribution.
  For more information, type 'rtiddsgen -help' at a command shell
  or consult the RTI Connext manual.
*/
    
package com.rti.xihui.fromscratch.fullidl_resolvename;
        

import com.rti.dds.infrastructure.*;
import com.rti.dds.infrastructure.Copyable;

import java.io.Serializable;
import com.rti.dds.cdr.CdrHelper;


public class SeqOfShortT implements Copyable, Serializable
{

    public ShortSeq userData = new ShortSeq(((SEQ_SIZE.VALUE)));


    public SeqOfShortT() {

    }


    public SeqOfShortT(SeqOfShortT other) {

        this();
        copy_from(other);
    }



    public static Object create() {
        SeqOfShortT self;
        self = new SeqOfShortT();
         
        self.clear();
        
        return self;
    }

    public void clear() {
        
        if (userData != null) {
            userData.clear();
        }
            
    }

    public boolean equals(Object o) {
                
        if (o == null) {
            return false;
        }        
        
        

        if(getClass() != o.getClass()) {
            return false;
        }

        SeqOfShortT otherObj = (SeqOfShortT)o;



        if(!userData.equals(otherObj.userData)) {
            return false;
        }
            
        return true;
    }

    public int hashCode() {
        int __result = 0;

        __result += userData.hashCode();
                
        return __result;
    }
    

    /**
     * This is the implementation of the <code>Copyable</code> interface.
     * This method will perform a deep copy of <code>src</code>
     * This method could be placed into <code>SeqOfShortTTypeSupport</code>
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
        

        SeqOfShortT typedSrc = (SeqOfShortT) src;
        SeqOfShortT typedDst = this;

        typedDst.userData.copy_from(typedSrc.userData);
            
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
        strBuffer.append("userData: ");
        for(int i__ = 0; i__ < userData.size(); ++i__) {
            if (i__!=0) strBuffer.append(", ");        
            strBuffer.append(userData.get(i__));
        }
        strBuffer.append("\n");
            
        return strBuffer.toString();
    }
    
}


//Text that is copied from IDL before BaseValueType
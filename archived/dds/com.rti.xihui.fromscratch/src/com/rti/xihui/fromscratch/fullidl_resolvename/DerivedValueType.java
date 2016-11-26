
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


//Text that is copied from IDL before BaseValueType             
public class DerivedValueType extends com.rti.xihui.fromscratch.fullidl_resolvename.BaseValueType implements Copyable, Serializable
{

    public int longMember2 = 0;


    public DerivedValueType() {
        super();

    }


    public DerivedValueType(DerivedValueType other) {

        this();
        copy_from(other);
    }



    public static Object create() {
        DerivedValueType self;
        self = new DerivedValueType();
         
        self.clear();
        
        return self;
    }

    public void clear() {
        
        super.clear();
        
        longMember2 = 0;
            
    }

    public boolean equals(Object o) {
                
        if (o == null) {
            return false;
        }        
        
        
        if (!super.equals(o)) {
            return false;
        }
        

        if(getClass() != o.getClass()) {
            return false;
        }

        DerivedValueType otherObj = (DerivedValueType)o;



        if(longMember2 != otherObj.longMember2) {
            return false;
        }
            
        return true;
    }

    public int hashCode() {
        int __result = 0;

        __result = super.hashCode();

        __result += (int)longMember2;
                
        return __result;
    }
    

    /**
     * This is the implementation of the <code>Copyable</code> interface.
     * This method will perform a deep copy of <code>src</code>
     * This method could be placed into <code>DerivedValueTypeTypeSupport</code>
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
        

        DerivedValueType typedSrc = (DerivedValueType) src;
        DerivedValueType typedDst = this;
        super.copy_from(typedSrc);
        typedDst.longMember2 = typedSrc.longMember2;
            
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
        
        
        strBuffer.append(super.toString("",indent));
        
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("longMember2: ").append(longMember2).append("\n");
            
        return strBuffer.toString();
    }
    
}


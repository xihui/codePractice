
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


public class PrimitiveStruct implements Copyable, Serializable
{

    public char charMember = 0;
    public char wcharMember = 0;
    public byte octetmember = 0;
    public short shortMember = 0;
    public short unsignedShortMember = 0;
    public int longMember = 0;
    public int unsignedLongMember = 0;
    public long longLongMember = 0;
    public long unsignedLongLongMember = 0;
    public float floatMember = 0;
    public double doubleMember = 0;
    public double longDoubleMember = 0;
    public boolean booleanMember = false;
    public PointerStruct pointerStruct = (PointerStruct) PointerStruct.create();


    public PrimitiveStruct() {

    }


    public PrimitiveStruct(PrimitiveStruct other) {

        this();
        copy_from(other);
    }



    public static Object create() {
        PrimitiveStruct self;
        self = new PrimitiveStruct();
         
        self.clear();
        
        return self;
    }

    public void clear() {
        
        charMember = 0;
            
        wcharMember = 0;
            
        octetmember = 0;
            
        shortMember = 0;
            
        unsignedShortMember = 0;
            
        longMember = 0;
            
        unsignedLongMember = 0;
            
        longLongMember = 0;
            
        unsignedLongLongMember = 0;
            
        floatMember = 0;
            
        doubleMember = 0;
            
        longDoubleMember = 0;
            
        booleanMember = false;
            
        if (pointerStruct != null) {
            pointerStruct.clear();
        }
            
    }

    public boolean equals(Object o) {
                
        if (o == null) {
            return false;
        }        
        
        

        if(getClass() != o.getClass()) {
            return false;
        }

        PrimitiveStruct otherObj = (PrimitiveStruct)o;



        if(charMember != otherObj.charMember) {
            return false;
        }
            
        if(wcharMember != otherObj.wcharMember) {
            return false;
        }
            
        if(octetmember != otherObj.octetmember) {
            return false;
        }
            
        if(shortMember != otherObj.shortMember) {
            return false;
        }
            
        if(unsignedShortMember != otherObj.unsignedShortMember) {
            return false;
        }
            
        if(longMember != otherObj.longMember) {
            return false;
        }
            
        if(unsignedLongMember != otherObj.unsignedLongMember) {
            return false;
        }
            
        if(longLongMember != otherObj.longLongMember) {
            return false;
        }
            
        if(unsignedLongLongMember != otherObj.unsignedLongLongMember) {
            return false;
        }
            
        if(floatMember != otherObj.floatMember) {
            return false;
        }
            
        if(doubleMember != otherObj.doubleMember) {
            return false;
        }
            
        if(longDoubleMember != otherObj.longDoubleMember) {
            return false;
        }
            
        if(booleanMember != otherObj.booleanMember) {
            return false;
        }
            
        if(!pointerStruct.equals(otherObj.pointerStruct)) {
            return false;
        }
            
        return true;
    }

    public int hashCode() {
        int __result = 0;

        __result += (int)charMember;
                
        __result += (int)wcharMember;
                
        __result += (int)octetmember;
                
        __result += (int)shortMember;
                
        __result += (int)unsignedShortMember;
                
        __result += (int)longMember;
                
        __result += (int)unsignedLongMember;
                
        __result += (int)longLongMember;
                
        __result += (int)unsignedLongLongMember;
                
        __result += (int)floatMember;
                
        __result += (int)doubleMember;
                
        __result += (int)longDoubleMember;
                
        __result += (booleanMember == true)?1:0;
                
        __result += pointerStruct.hashCode();
                
        return __result;
    }
    

    /**
     * This is the implementation of the <code>Copyable</code> interface.
     * This method will perform a deep copy of <code>src</code>
     * This method could be placed into <code>PrimitiveStructTypeSupport</code>
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
        

        PrimitiveStruct typedSrc = (PrimitiveStruct) src;
        PrimitiveStruct typedDst = this;

        typedDst.charMember = typedSrc.charMember;
            
        typedDst.wcharMember = typedSrc.wcharMember;
            
        typedDst.octetmember = typedSrc.octetmember;
            
        typedDst.shortMember = typedSrc.shortMember;
            
        typedDst.unsignedShortMember = typedSrc.unsignedShortMember;
            
        typedDst.longMember = typedSrc.longMember;
            
        typedDst.unsignedLongMember = typedSrc.unsignedLongMember;
            
        typedDst.longLongMember = typedSrc.longLongMember;
            
        typedDst.unsignedLongLongMember = typedSrc.unsignedLongLongMember;
            
        typedDst.floatMember = typedSrc.floatMember;
            
        typedDst.doubleMember = typedSrc.doubleMember;
            
        typedDst.longDoubleMember = typedSrc.longDoubleMember;
            
        typedDst.booleanMember = typedSrc.booleanMember;
            
        typedDst.pointerStruct = (PointerStruct) typedDst.pointerStruct.copy_from(typedSrc.pointerStruct);
            
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
        strBuffer.append("charMember: ").append(charMember).append("\n");
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("wcharMember: ").append(wcharMember).append("\n");
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("octetmember: ").append(octetmember).append("\n");
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("shortMember: ").append(shortMember).append("\n");
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("unsignedShortMember: ").append(unsignedShortMember).append("\n");
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("longMember: ").append(longMember).append("\n");
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("unsignedLongMember: ").append(unsignedLongMember).append("\n");
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("longLongMember: ").append(longLongMember).append("\n");
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("unsignedLongLongMember: ").append(unsignedLongLongMember).append("\n");
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("floatMember: ").append(floatMember).append("\n");
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("doubleMember: ").append(doubleMember).append("\n");
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("longDoubleMember: ").append(longDoubleMember).append("\n");
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("booleanMember: ").append(booleanMember).append("\n");
            
        strBuffer.append(pointerStruct.toString("pointerStruct ", indent+1));
            
        return strBuffer.toString();
    }
    
}


//Text that is copied from IDL before BaseValueType
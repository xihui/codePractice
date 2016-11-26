
/*
  WARNING: THIS FILE IS AUTO-GENERATED. DO NOT MODIFY.

  This file was generated from .idl using "rtiddsgen".
  The rtiddsgen tool is part of the RTI Connext distribution.
  For more information, type 'rtiddsgen -help' at a command shell
  or consult the RTI Connext manual.
*/
    
package com.rti.xihui.fromscratch.fullidl;
        

import com.rti.dds.infrastructure.*;
import com.rti.dds.infrastructure.Copyable;

import java.io.Serializable;
import com.rti.dds.cdr.CdrHelper;


//Text that is copied from IDL before BaseValueType
public class TopStruct implements Copyable, Serializable
{

    public String smallStringKey = ""; /* maximum length = ((com.rti.xihui.fromscratch.fullidl.STRING_SIZE.VALUE)) */
    public com.rti.xihui.fromscratch.fullidl.PrimitiveStruct primitiveStructMember = (com.rti.xihui.fromscratch.fullidl.PrimitiveStruct) com.rti.xihui.fromscratch.fullidl.PrimitiveStruct.create();
    public com.rti.xihui.fromscratch.fullidl.ShortArray shortArray = (com.rti.xihui.fromscratch.fullidl.ShortArray) com.rti.xihui.fromscratch.fullidl.ShortArray.create();
    public String stringUnbounded = ""; /* maximum length = (255) */
    public String wideString = ""; /* maximum length = (255) */
    public com.rti.xihui.fromscratch.fullidl.UnionExample unionMember = (com.rti.xihui.fromscratch.fullidl.UnionExample) com.rti.xihui.fromscratch.fullidl.UnionExample.create();
    public com.rti.xihui.fromscratch.fullidl.EnumExample enumExample = (com.rti.xihui.fromscratch.fullidl.EnumExample) com.rti.xihui.fromscratch.fullidl.EnumExample.create();
    public com.rti.xihui.fromscratch.fullidl.SeqOfShortT shortSeq = (com.rti.xihui.fromscratch.fullidl.SeqOfShortT) com.rti.xihui.fromscratch.fullidl.SeqOfShortT.create();
    public com.rti.xihui.fromscratch.fullidl.SeqOfShortArrayT shortArraySeq = (com.rti.xihui.fromscratch.fullidl.SeqOfShortArrayT) com.rti.xihui.fromscratch.fullidl.SeqOfShortArrayT.create();
    public com.rti.xihui.fromscratch.fullidl.SeqOfShortSeqT seqOfShortSeq = (com.rti.xihui.fromscratch.fullidl.SeqOfShortSeqT) com.rti.xihui.fromscratch.fullidl.SeqOfShortSeqT.create();
    public short[] short1DArray = new short[2];
    public short[][] short2DArray = new short[2][3];
    public com.rti.xihui.fromscratch.fullidl.DerivedValueType derivedValueTypeMember = (com.rti.xihui.fromscratch.fullidl.DerivedValueType) com.rti.xihui.fromscratch.fullidl.DerivedValueType.create();


    public TopStruct() {

    }


    public TopStruct(TopStruct other) {

        this();
        copy_from(other);
    }



    public static Object create() {
        TopStruct self;
        self = new TopStruct();
         
        self.clear();
        
        return self;
    }

    public void clear() {
        
        smallStringKey = "";
            
        if (primitiveStructMember != null) {
            primitiveStructMember.clear();
        }
            
        if (shortArray != null) {
            shortArray.clear();
        }
            
        stringUnbounded = "";
            
        wideString = "";
            
        if (unionMember != null) {
            unionMember.clear();
        }
            
        enumExample = com.rti.xihui.fromscratch.fullidl.EnumExample.create();
            
        if (shortSeq != null) {
            shortSeq.clear();
        }
            
        if (shortArraySeq != null) {
            shortArraySeq.clear();
        }
            
        if (seqOfShortSeq != null) {
            seqOfShortSeq.clear();
        }
            
        
        for(int i1__ = 0; i1__ < 2; ++i1__) {
        
            short1DArray[i1__] = 0;
        
        }
                                                                  
            
        
        for(int i1__ = 0; i1__ < 2; ++i1__) {
        
        for(int i2__ = 0; i2__ < 3; ++i2__) {
        
            short2DArray[i1__][i2__] = 0;
        
        }
        
        }
                                                                  
            
        if (derivedValueTypeMember != null) {
            derivedValueTypeMember.clear();
        }
            
    }

    public boolean equals(Object o) {
                
        if (o == null) {
            return false;
        }        
        
        

        if(getClass() != o.getClass()) {
            return false;
        }

        TopStruct otherObj = (TopStruct)o;



        if(!smallStringKey.equals(otherObj.smallStringKey)) {
            return false;
        }
            
        if(!primitiveStructMember.equals(otherObj.primitiveStructMember)) {
            return false;
        }
            
        if(!shortArray.equals(otherObj.shortArray)) {
            return false;
        }
            
        if(!stringUnbounded.equals(otherObj.stringUnbounded)) {
            return false;
        }
            
        if(!wideString.equals(otherObj.wideString)) {
            return false;
        }
            
        if(!unionMember.equals(otherObj.unionMember)) {
            return false;
        }
            
        if(!enumExample.equals(otherObj.enumExample)) {
            return false;
        }
            
        if(!shortSeq.equals(otherObj.shortSeq)) {
            return false;
        }
            
        if(!shortArraySeq.equals(otherObj.shortArraySeq)) {
            return false;
        }
            
        if(!seqOfShortSeq.equals(otherObj.seqOfShortSeq)) {
            return false;
        }
            
        
        for(int i1__ = 0; i1__ < 2; ++i1__) {
        
        if(short1DArray[i1__] != otherObj.short1DArray[i1__]) {
            return false;
        }
        
        }
                                                  
            
        
        for(int i1__ = 0; i1__ < 2; ++i1__) {
        
        for(int i2__ = 0; i2__ < 3; ++i2__) {
        
        if(short2DArray[i1__][i2__] != otherObj.short2DArray[i1__][i2__]) {
            return false;
        }
        
        }
        
        }
                                                  
            
        if(!derivedValueTypeMember.equals(otherObj.derivedValueTypeMember)) {
            return false;
        }
            
        return true;
    }

    public int hashCode() {
        int __result = 0;

        __result += smallStringKey.hashCode();
                
        __result += primitiveStructMember.hashCode();
                
        __result += shortArray.hashCode();
                
        __result += stringUnbounded.hashCode();
                
        __result += wideString.hashCode();
                
        __result += unionMember.hashCode();
                
        __result += enumExample.hashCode();
                
        __result += shortSeq.hashCode();
                
        __result += shortArraySeq.hashCode();
                
        __result += seqOfShortSeq.hashCode();
                
        
        for(int i1__ = 0; i1__ < 2; ++i1__) {
        
             __result += (int)short1DArray[i1__];
        
        }
                                                  
                
        
        for(int i1__ = 0; i1__ < 2; ++i1__) {
        
        for(int i2__ = 0; i2__ < 3; ++i2__) {
        
             __result += (int)short2DArray[i1__][i2__];
        
        }
        
        }
                                                  
                
        __result += derivedValueTypeMember.hashCode();
                
        return __result;
    }
    

    /**
     * This is the implementation of the <code>Copyable</code> interface.
     * This method will perform a deep copy of <code>src</code>
     * This method could be placed into <code>TopStructTypeSupport</code>
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
        

        TopStruct typedSrc = (TopStruct) src;
        TopStruct typedDst = this;

        typedDst.smallStringKey = typedSrc.smallStringKey;
            
        typedDst.primitiveStructMember = (com.rti.xihui.fromscratch.fullidl.PrimitiveStruct) typedDst.primitiveStructMember.copy_from(typedSrc.primitiveStructMember);
            
        typedDst.shortArray = (com.rti.xihui.fromscratch.fullidl.ShortArray) typedDst.shortArray.copy_from(typedSrc.shortArray);
            
        typedDst.stringUnbounded = typedSrc.stringUnbounded;
            
        typedDst.wideString = typedSrc.wideString;
            
        typedDst.unionMember = (com.rti.xihui.fromscratch.fullidl.UnionExample) typedDst.unionMember.copy_from(typedSrc.unionMember);
            
        typedDst.enumExample = (com.rti.xihui.fromscratch.fullidl.EnumExample) typedDst.enumExample.copy_from(typedSrc.enumExample);
            
        typedDst.shortSeq = (com.rti.xihui.fromscratch.fullidl.SeqOfShortT) typedDst.shortSeq.copy_from(typedSrc.shortSeq);
            
        typedDst.shortArraySeq = (com.rti.xihui.fromscratch.fullidl.SeqOfShortArrayT) typedDst.shortArraySeq.copy_from(typedSrc.shortArraySeq);
            
        typedDst.seqOfShortSeq = (com.rti.xihui.fromscratch.fullidl.SeqOfShortSeqT) typedDst.seqOfShortSeq.copy_from(typedSrc.seqOfShortSeq);
            
        
            System.arraycopy(typedSrc.short1DArray, 0, 
                             typedDst.short1DArray, 0, 
                             typedSrc.short1DArray.length);
        
            
        
        for(int i1__ = 0; i1__ < 2; ++i1__) {
        
            System.arraycopy(typedSrc.short2DArray[i1__], 0, 
                             typedDst.short2DArray[i1__], 0, 
                             typedSrc.short2DArray[i1__].length);
        
        }
        
            
        typedDst.derivedValueTypeMember = (com.rti.xihui.fromscratch.fullidl.DerivedValueType) typedDst.derivedValueTypeMember.copy_from(typedSrc.derivedValueTypeMember);
            
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
        strBuffer.append("smallStringKey: ").append(smallStringKey).append("\n");
            
        strBuffer.append(primitiveStructMember.toString("primitiveStructMember ", indent+1));
            
        strBuffer.append(shortArray.toString("shortArray ", indent+1));
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("stringUnbounded: ").append(stringUnbounded).append("\n");
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("wideString: ").append(wideString).append("\n");
            
        strBuffer.append(unionMember.toString("unionMember ", indent+1));
            
        strBuffer.append(enumExample.toString("enumExample ", indent+1));
            
        strBuffer.append(shortSeq.toString("shortSeq ", indent+1));
            
        strBuffer.append(shortArraySeq.toString("shortArraySeq ", indent+1));
            
        strBuffer.append(seqOfShortSeq.toString("seqOfShortSeq ", indent+1));
            
        CdrHelper.printIndent(strBuffer, indent+1);
        strBuffer.append("short1DArray: ");
        
        for(int i1__ = 0; i1__ < 2; ++i1__) {
        
        strBuffer.append(short1DArray[i1__]).append(", ");
        
        }
                                            
        strBuffer.append("\n");      
            
        CdrHelper.printIndent(strBuffer, indent+1);
        strBuffer.append("short2DArray: ");
        
        for(int i1__ = 0; i1__ < 2; ++i1__) {
        
        for(int i2__ = 0; i2__ < 3; ++i2__) {
        
        strBuffer.append(short2DArray[i1__][i2__]).append(", ");
        
        }
        
        }
                                            
        strBuffer.append("\n");      
            
        strBuffer.append(derivedValueTypeMember.toString("derivedValueTypeMember ", indent+1));
            
        return strBuffer.toString();
    }
    
}


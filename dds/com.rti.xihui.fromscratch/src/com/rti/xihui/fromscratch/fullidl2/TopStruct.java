
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


//Text that is copied from IDL before BaseValueType
public class TopStruct implements Copyable, Serializable
{

//Text that is copied from IDL before smallStringKey
    public SMALL_STRING smallStringKey = (SMALL_STRING) SMALL_STRING.create();
    public PrimitiveStruct primitiveStructMember = (PrimitiveStruct) PrimitiveStruct.create();
    public ShortArray shortArray = (ShortArray) ShortArray.create();
    public String stringUnbounded = ""; /* maximum length = (255) */
    public String wideString = ""; /* maximum length = (255) */
    public UnionExample unionMember = (UnionExample) UnionExample.create();
    public EnumExample enumExample = (EnumExample) EnumExample.create();
    public SeqOfShortT shortSeq = (SeqOfShortT) SeqOfShortT.create();
    public SeqOfShortArrayT shortArraySeq = (SeqOfShortArrayT) SeqOfShortArrayT.create();
    public SeqOfShortSeqT seqOfShortSeq = (SeqOfShortSeqT) SeqOfShortSeqT.create();
    public short[] short1DArray = new short[2];
    public short[][] short2DArray = new short[2][3];
    public DerivedValueType derivedValueTypeMember = (DerivedValueType) DerivedValueType.create();


    public TopStruct() {

//Text that is copied from IDL before smallStringKey
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
        
//Text that is copied from IDL before smallStringKey
        if (smallStringKey != null) {
            smallStringKey.clear();
        }
            
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
            
            enumExample=EnumExample.create();
        
            
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



//Text that is copied from IDL before smallStringKey
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

//Text that is copied from IDL before smallStringKey
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

//Text that is copied from IDL before smallStringKey
        typedDst.smallStringKey = (SMALL_STRING) typedDst.smallStringKey.copy_from(typedSrc.smallStringKey);
            
        typedDst.primitiveStructMember = (PrimitiveStruct) typedDst.primitiveStructMember.copy_from(typedSrc.primitiveStructMember);
            
        typedDst.shortArray = (ShortArray) typedDst.shortArray.copy_from(typedSrc.shortArray);
            
        typedDst.stringUnbounded = typedSrc.stringUnbounded;
            
        typedDst.wideString = typedSrc.wideString;
            
        typedDst.unionMember = (UnionExample) typedDst.unionMember.copy_from(typedSrc.unionMember);
            
        typedDst.enumExample = (EnumExample) typedDst.enumExample.copy_from(typedSrc.enumExample);
            
        typedDst.shortSeq = (SeqOfShortT) typedDst.shortSeq.copy_from(typedSrc.shortSeq);
            
        typedDst.shortArraySeq = (SeqOfShortArrayT) typedDst.shortArraySeq.copy_from(typedSrc.shortArraySeq);
            
        typedDst.seqOfShortSeq = (SeqOfShortSeqT) typedDst.seqOfShortSeq.copy_from(typedSrc.seqOfShortSeq);
            
        
            System.arraycopy(typedSrc.short1DArray, 0, 
                             typedDst.short1DArray, 0, 
                             typedSrc.short1DArray.length);
        
            
        
        for(int i1__ = 0; i1__ < 2; ++i1__) {
        
            System.arraycopy(typedSrc.short2DArray[i1__], 0, 
                             typedDst.short2DArray[i1__], 0, 
                             typedSrc.short2DArray[i1__].length);
        
        }
        
            
        typedDst.derivedValueTypeMember = (DerivedValueType) typedDst.derivedValueTypeMember.copy_from(typedSrc.derivedValueTypeMember);
            
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
        
        
//Text that is copied from IDL before smallStringKey
        strBuffer.append(smallStringKey.toString("smallStringKey ", indent+1));
            
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



/*
  WARNING: THIS FILE IS AUTO-GENERATED. DO NOT MODIFY.

  This file was generated from .idl using "rtiddsgen".
  The rtiddsgen tool is part of the RTI Connext distribution.
  For more information, type 'rtiddsgen -help' at a command shell
  or consult the RTI Connext manual.
*/
    
package com.rti.xihui.fromscratch.idl;
        

import com.rti.dds.infrastructure.*;
import com.rti.dds.infrastructure.Copyable;

import java.io.Serializable;
import com.rti.dds.cdr.CdrHelper;


public class HelloMsg implements Copyable, Serializable
{

    public int id = 0;
    public String msg = ""; /* maximum length = ((com.rti.xihui.fromscratch.idl.MAX_MSG_LENGTH.VALUE)) */
    public IntSeq mySeq = new IntSeq(((com.rti.xihui.fromscratch.idl.SEQ_LENGTH.VALUE)));


    public HelloMsg() {

    }


    public HelloMsg(HelloMsg other) {

        this();
        copy_from(other);
    }



    public static Object create() {
        HelloMsg self;
        self = new HelloMsg();
         
        self.clear();
        
        return self;
    }

    public void clear() {
        
        id = 0;
            
        msg = "";
            
        if (mySeq != null) {
            mySeq.clear();
        }
            
    }

    public boolean equals(Object o) {
                
        if (o == null) {
            return false;
        }        
        
        

        if(getClass() != o.getClass()) {
            return false;
        }

        HelloMsg otherObj = (HelloMsg)o;



        if(id != otherObj.id) {
            return false;
        }
            
        if(!msg.equals(otherObj.msg)) {
            return false;
        }
            
        if(!mySeq.equals(otherObj.mySeq)) {
            return false;
        }
            
        return true;
    }

    public int hashCode() {
        int __result = 0;

        __result += (int)id;
                
        __result += msg.hashCode();
                
        __result += mySeq.hashCode();
                
        return __result;
    }
    

    /**
     * This is the implementation of the <code>Copyable</code> interface.
     * This method will perform a deep copy of <code>src</code>
     * This method could be placed into <code>HelloMsgTypeSupport</code>
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
        

        HelloMsg typedSrc = (HelloMsg) src;
        HelloMsg typedDst = this;

        typedDst.id = typedSrc.id;
            
        typedDst.msg = typedSrc.msg;
            
        typedDst.mySeq.copy_from(typedSrc.mySeq);
            
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
        strBuffer.append("id: ").append(id).append("\n");
            
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("msg: ").append(msg).append("\n");
            
        CdrHelper.printIndent(strBuffer, indent+1);
        strBuffer.append("mySeq: ");
        for(int i__ = 0; i__ < mySeq.size(); ++i__) {
            if (i__!=0) strBuffer.append(", ");        
            strBuffer.append(mySeq.get(i__));
        }
        strBuffer.append("\n");
            
        return strBuffer.toString();
    }
    
}


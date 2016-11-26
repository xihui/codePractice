
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
import com.rti.dds.util.Union;



public class UnionExample extends Union
 implements Copyable, Serializable
{
    
    private static final int _default = getDefaultDiscriminator();
    

    public int _d  ;

    public short shortMember = 0;
    public int longMember = 0;
    public double doubleMember = 0;


    public UnionExample() {
        _d = getDefaultDiscriminator(); 
    }


    public UnionExample(UnionExample other) {


        this();
        copy_from(other);
    }


    public static Object create() {
            UnionExample self;
        self = new UnionExample();
         
        self.clear();
        
        return self;
    }

    
        public static int getDefaultDiscriminator() {
        int i;
        for ( i = 0; i < Integer.MAX_VALUE; i++) {
            if ((1)== i) continue;
            if ((2)== i) continue;
            if ((3)== i) continue;
            break;
        }
        return i;
    }


    

    public void clear() {
        _d = getDefaultDiscriminator();
        
         if (_d == (1)){                                
                    
        shortMember = 0;
            } else if (_d == (2)){                                
                    
        longMember = 0;
            } else if (_d == (3)){                                
                    
        longMember = 0;
            } else {
        doubleMember = 0;
            }
    }

    public int discriminator() {
        return _d;
    }

    private void verifyshortMember(int discriminator) {
        if (discriminator != 1) {
            throw new RETCODE_ILLEGAL_OPERATION();
        }
    }

    public short shortMember() {
        verifyshortMember(_d);
        return shortMember;
    }

    public void shortMember(short __value) {
        _d = 1;
        shortMember = __value;
    }

    private void verifylongMember(int discriminator) {
        if (discriminator != 2 && 
            discriminator != 3) {
            throw new RETCODE_ILLEGAL_OPERATION();
        }
    }

    public int longMember() {
        verifylongMember(_d);
        return longMember;
    }

    public void longMember(int discriminator,int __value) {
        verifylongMember(discriminator);
        _d = discriminator;
        longMember = __value;
    }

    public void longMember(int __value) {
        _d = 2;
        longMember = __value;
    }

    private void verifydoubleMember(int discriminator) {
        if (!(discriminator != 1 && 
            discriminator != 2 && 
            discriminator != 3)) {
            throw new RETCODE_ILLEGAL_OPERATION();
        }
    }

    public double doubleMember() {
        verifydoubleMember(_d);
        return doubleMember;
    }

    public void doubleMember(int discriminator,double __value) {
        verifydoubleMember(discriminator);
        _d = discriminator;
        doubleMember = __value;
    }

    public void doubleMember(double __value) {
        if (_default ==Integer.MAX_VALUE) throw new RETCODE_ILLEGAL_OPERATION();
        _d = _default;
        doubleMember = __value;
    }

    private void verifyDefault(int discriminator) {
        if (!(discriminator != 1 && 
            discriminator != 2 && 
            discriminator != 3)) {
            throw new RETCODE_ILLEGAL_OPERATION();
        }
    }

    public void __default(int discriminator) {
        verifyDefault(discriminator);
        _d = discriminator;
    }

    public void __default() {
        if (_default ==Integer.MAX_VALUE) throw new RETCODE_ILLEGAL_OPERATION();
        _d = _default;
    }



        
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
    
        if(getClass() != o.getClass()) {
            return false;
        }

        UnionExample otherObj = (UnionExample)o;

        if(_d != otherObj._d) {
            return false;
        }
 if (otherObj._d == (1)){                                
                    
        if(shortMember != otherObj.shortMember) {
            return false;
        }
            } else if (otherObj._d == (2)){                                
                    
        if(longMember != otherObj.longMember) {
            return false;
        }
            } else if (otherObj._d == (3)){                                
                    
        if(longMember != otherObj.longMember) {
            return false;
        }
            } else {
        if(doubleMember != otherObj.doubleMember) {
            return false;
        }
            }
        return true;
    }

    public int hashCode() {
        int __result = 0;
 if (_d == (1)){
                    
        __result += (int)shortMember;
                } else if (_d == (2)){
                    
        __result += (int)longMember;
                } else if (_d == (3)){
                    
        __result += (int)longMember;
                } else {
        __result += (int)doubleMember;
                }
        return __result;
    }
    

    /**
     * This is the implementation of the <code>Copyable</code> interface.
     * This method will perform a deep copy of <code>src</code>
     * This method could be placed into <code>UnionExampleTypeSupport</code>
     * rather than here by using the <code>-noCopyable</code> option
     * to rtiddsgen.
     * 
     * @param src The Object which contains the data to be copied.
     * @return Generally, return <code>this</code> but special
     *         cases (such as <code>Enum</code>) exist.
     * @exception NullPointerException If <code>src</code> is null.
     * @exception ClassCastException If <code>src</code> is not the 
     * same type as <code>this</code>.
     * @see com.rti.dds.infrastructure.Copyable#copy_from(java.lang.Object)
     */
    public Object copy_from(Object other) {
        

        UnionExample typedSrc = (UnionExample)other;
        UnionExample typedDst = this;
    
    
        typedDst._d = typedSrc._d;
             if (_d == (1)){                                
                    
        typedDst.shortMember = typedSrc.shortMember;
            } else if (_d == (2)){                                
                    
        typedDst.longMember = typedSrc.longMember;
            } else if (_d == (3)){                                
                    
        typedDst.longMember = typedSrc.longMember;
            } else {
        typedDst.doubleMember = typedSrc.doubleMember;
            }
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
        strBuffer.append("_d: ").append(_d).append("\n");
             if (_d == (1)){                                
                    
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("shortMember: ").append(shortMember).append("\n");
            } else if (_d == (2)){                                
                    
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("longMember: ").append(longMember).append("\n");
            } else if (_d == (3)){                                
                    
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("longMember: ").append(longMember).append("\n");
            } else {
        CdrHelper.printIndent(strBuffer, indent+1);            
        strBuffer.append("doubleMember: ").append(doubleMember).append("\n");
            }
        return strBuffer.toString();
    }
    
}


//Text that is copied from IDL before BaseValueType
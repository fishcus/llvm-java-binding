/*
 * MIT License
 *
 * Copyright (c) 2018 Institute on Software System and Engineering, School of Software, Tsinghua University
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package cn.edu.thu.tsmart.core.cfa.llvm;

import static org.bytedeco.javacpp.LLVM.*;

/** @author guangchen on 08/06/2017. */
public class DataLayout {
  private final Context context;
  private LLVMTargetDataRef targetDataRef;
  private final int byteOrdering;

  public DataLayout(Context context, LLVMTargetDataRef targetDataRef) {
    this.context = context;
    this.targetDataRef = targetDataRef;

    this.byteOrdering = LLVMByteOrder(targetDataRef);
  }

  public boolean isLittleEndian() {
    return this.byteOrdering == LLVMLittleEndian;
  }

  public boolean isBigEndian() {
    return this.byteOrdering == LLVMBigEndian;
  }
  //
  //    public boolean isDefault() {
  //
  //    }
  //
  //    public boolean isLegalInteger(int width) {
  //
  //    }
  //
  //    public boolean isIllegalInteger(int width) {
  //
  //    }
  ////
  ////    public boolean exceedsNaturalStackAlignment(int align) {
  ////
  ////    }
  //
  //    public int getStackAlignment() {
  //
  //    }
  //
  //    public boolean hasMicrosoftFastStdCallMangling() {
  //
  //    }
  //
  //    public boolean hasLinkerPrivateGlobalPrefix() {
  //
  //    }
  //
  //    public boolean hasLinkerPrivateGlobalPrefix() {
  //
  //    }
  //
  //    public String getLinkerPrivateGlobalPrefix() {
  //
  //    }
  //
  //    public char getGlobalPrefix() {
  //
  //    }
  //
  //    public String getPrivateGlobalPrefix() {
  //
  //    }
  //
  //    public boolean fitsInlegalInteger(int width) {
  //
  //    }
  //
  //    public int getPointerABIAlignment(int as) {
  //
  //    }
  //
  //    public int getPointerPrefAlignment(int as) {
  //
  //    }

  public int getPointerSize() {
    return getPointerSize(0);
  }

  public int getPointerSize(int as) {
    return LLVMPointerSizeForAS(targetDataRef, as);
  }

  public int getPointerSizeInBits() {
    return getPointerSizeInBits(0);
  }

  public int getPointerSizeInBits(int as) {
    return getPointerSize(as) * 8;
  }

  public int getPointerTypeSizeInBits(Type type) {
    assert type.isPtrOrPtrVectorTy()
        : "This should only be called with a pointer or pointer vector type";
    if (type.isPointerTy()) {
      return (int) getTypeSizeInBits(type);
    }
    return (int) getTypeSizeInBits(type.getScalarType());
  }

  public int getPointerTypeSize(Type type) {
    return getPointerTypeSizeInBits(type) / 8;
  }

  public long getTypeSizeInBits(Type type) {
    return LLVMSizeOfTypeInBits(targetDataRef, context.getTypeRef(type));
  }

  public long getTypeStoreSize(Type type) {
    return LLVMStoreSizeOfType(targetDataRef, context.getTypeRef(type));
  }

  public long getTypeStoreSizeInBits(Type type) {
    return 8 * getTypeStoreSize(type);
  }

  public long getTypeAllocSize(Type type) {
    return LLVMABISizeOfType(targetDataRef, context.getTypeRef(type));
  }

  public long getTypeAllocSizeInBits(Type type) {
    return 8 * getTypeAllocSize(type);
  }

  public int getABITypeAlignment(Type type) {
    return LLVMABIAlignmentOfType(targetDataRef, context.getTypeRef(type));
  }
  //
  //    public int getABIIntegerTypeAlignment(int bitwidth) {
  //
  //    }
  //
  //    public StructLayout getStructLayout(StructType type) {
  //
  //    }
}

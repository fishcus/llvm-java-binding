/*
 * Copyright (c) 2017
 * ------------------
 * Institute on Software System and Engineering
 * School of Software, Tsinghua University
 *
 * All Rights Reserved.
 *
 * NOTICE:
 * All information contained herein is, and remains the property of Tsinghua University.
 *
 * The intellectual and technical concepts contained herein are proprietary to
 * Tsinghua University and may be covered by China and Foreign Patents, patents in process,
 * and are protected by copyright law.
 *
 * Dissemination of this information or reproduction of this material is strictly forbidden
 * unless prior written permission is obtained from Tsinghua University.
 *
 */
package cn.edu.thu.tsmart.core.cfa.llvm;

/**
 * @author guangchen on 01/03/2017.
 */
public class VectorType extends SequentialType {
  private Type elementType;
  private int numElements;

  protected VectorType(Context context, Type elementType, int numElements) {
    super(context, TypeID.VectorTyID);
    this.elementType = elementType;
    this.numElements = numElements;
  }

  public Type getElementType() { return this.elementType; }

  public int getNumElements() { return this.numElements; }

  public static VectorType get(Type elementType, int numElements) {
    return new VectorType(elementType.getContext(), elementType, numElements);
  }

  public int getBitWidth() { return numElements * elementType.getPrimitiveSizeInBits(); }
}
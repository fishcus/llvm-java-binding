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

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/** @author guangchen on 01/03/2017. */
public class FunctionType extends Type {
  protected FunctionType(Type result, Type[] params, boolean isVarArg) {
    super(result.getContext(), TypeID.FunctionTyID);
    this.returnType = result;
    this.params = params;
    this.isVarArg = isVarArg;
  }

  private boolean isVarArg;
  private Type returnType;
  private Type[] params;

  public boolean isVarArg() {
    return isVarArg;
  }

  public Type getReturnType() {
    return returnType;
  }

  public Type[] getParams() {
    return params;
  }

  public int getNumParams() {
    return params.length;
  }

  @Override
  public boolean isFunctionVarArg() {
    return isVarArg();
  }

  @Override
  public Type[] getFunctionParamType() {
    return getParams();
  }

  @Override
  public int getFunctionNumParams() {
    return getNumParams();
  }

  public static FunctionType get(Type result, Type[] params, boolean isVarArg) {
    return new FunctionType(result, params, isVarArg);
  }

  public static FunctionType get(Type result, boolean isVarArg) {
    return get(result, new Type[] {}, isVarArg);
  }

  public static boolean isValidReturnType(Type retTy) {
    throw new NotImplementedException();
  }

  public static boolean isValidArgumentType(Type argTy) {
    throw new NotImplementedException();
  }

  @Override
  public String toString() {
    String res = returnType + " (";
    for(int i = 0; i < params.length; i++) {
      res += params[i].toString();
      if(i < params.length - 1 || isVarArg())
        res += ", ";
    }
    if(isVarArg())
      res += "...)";
    else
      res += ")";
    return res;
  }
}
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

import static cn.edu.thu.tsmart.core.cfa.llvm.InstructionProperties.OpCode;

import cn.edu.thu.tsmart.core.cfa.util.Casting;
import cn.edu.thu.tsmart.core.cfa.util.visitor.InstructionVisitor;
import cn.edu.thu.tsmart.core.exceptions.CPAException;
import com.google.common.collect.ImmutableList;
import java.util.Arrays;
import java.util.List;

/**
 * @author guangchen on 27/02/2017.
 */
public class ExtractValueInst extends UnaryInstruction {

  // TODO initialize in Converter
  private ImmutableList<Integer> indices;

  public ExtractValueInst(String name, Type type) {
    super(name, type);
    super.opCode = OpCode.EXTRACTVALUE;
  }

  // only for Converter
  public void setIndices(ImmutableList<Integer> indices) {
    this.indices = indices;
  }

  public Value getAggregateOperand() {
    return getOperand(0);
  }

  public boolean hasIndices() {
    return true;
  }

  public int getNumIndices() {
    return indices.size();
  }

  public ImmutableList<Integer> getIndices() {
    return indices;
  }

  @Override
  public <R, E extends CPAException> R accept(InstructionVisitor<R, E> visitor) throws E {
    return visitor.visit(this);
  }

  public static Instruction create(Value operand, ImmutableList<Integer> indices) {
    List<Value> list = Arrays.asList(operand);
    ExtractValueInst instruction = new ExtractValueInst("", null);
    instruction.setOperands(list);
    instruction.setIndices(indices);
    return instruction;
  }

  @Override
  public String toString() {
    CallInst callInst = Casting.dyncast(getOperand(0), CallInst.class);
    if (callInst == null) {
      return "";
    }
    String res = "%" + getName().toString() + " = " + getOpcode().toString() + " " + callInst.getType().toString();
    res += " %" + callInst.getName().toString() + ", ";
    for(int i = 0; i <= callInst.getNumArgOperands(); i++) {
      if(callInst.getArgOperandUse(i).getUser() == this) {
        res += callInst.getNumArgOperands() - i;
        break;
      }
    }
    return res;
  }
}
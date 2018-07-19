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

import static cn.edu.thu.tsmart.core.cfa.llvm.InstructionProperties.*;

/**
 * @author guangchen on 27/02/2017.
 */
public class AddrSpaceCastInst extends CastInst {

  public AddrSpaceCastInst(String name, Type type) {
    super(name, type);
    super.opCode = OpCode.ADDRSPACECAST;
  }

  public Value getPointerOperand() {
    return getOperand(0);
  }

  // NOTICE return type uses int to store unsigned
  public int getSrcPointerAddressSpace() {
    return getPointerOperand().getType().getPointerAddressSpace();
  }

  // NOTICE return type uses int to store unsigned
  public int getDestPointerAddressSpace() {
    return getType().getPointerAddressSpace();
  }
}
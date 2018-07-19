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

import java.util.ArrayList;
import java.util.List;

/**
 * @author guangchen on 26/02/2017.
 */
public class BasicBlock extends Value {
    private List<Instruction> instList = new ArrayList<>();
    private LlvmFunction parent;

    public BasicBlock() {}

    public BasicBlock(String name, Type type, LlvmFunction parent, List<Instruction> instList) {
        super(name, type);
        this.parent = parent;
        this.instList = instList;
    }

    public BasicBlock(String name, Type type, LlvmFunction parent) {
        super(name, type);
        this.parent = parent;
    }

    public List<Instruction> getInstList() {
        return instList;
    }

    public void setInstList(List<Instruction> instList) {
        this.instList = instList;
    }

    public LlvmFunction getParent() {
        return parent;
    }

    public void setParent(LlvmFunction parent) {
        this.parent = parent;
    }

    @Override
    public int hashCode() {
        return instList.hashCode();
    }
}
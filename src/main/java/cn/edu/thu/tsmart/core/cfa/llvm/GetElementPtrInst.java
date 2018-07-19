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
import static cn.edu.thu.tsmart.core.cfa.util.Casting.cast;

import cn.edu.thu.tsmart.core.cfa.util.Casting;
import cn.edu.thu.tsmart.core.cfa.util.Formatter;
import cn.edu.thu.tsmart.core.cfa.util.visitor.InstructionVisitor;
import cn.edu.thu.tsmart.core.exceptions.CPAException;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author guangchen on 27/02/2017.
 */
public class GetElementPtrInst extends Instruction {

    // TODO initialize in Converter
    private Type sourceElementType = null;
    private Type resultElementType = null;
    private boolean isInBounds = false;
    private List<Boolean> isInRangeForIndexes = Lists.newArrayList();

    public GetElementPtrInst(String name, Type type) {
        super(name, type);
        super.opCode = OpCode.GETELEMENTPTR;
    }

    // only for Converter
    public void setSourceElementType(Type sourceElementType) {
        this.sourceElementType = sourceElementType;
    }

    // only for Converter
    public void setResultElementType(Type resultElementType) {
        this.resultElementType = resultElementType;
    }

    // only for Converter
    public void setIsInBounds(boolean isInBounds) {
        this.isInBounds = isInBounds;
    }

    // only for Converter
    public void setIsInRange(List<Boolean> isInRangeForIndexes) {
        this.isInRangeForIndexes = Lists.newArrayList(isInRangeForIndexes);
    }

    public Type getSourceElementType() {
        return cast(getOperand(0).getType().getScalarType(), PointerType.class).getElementType();
    }

    public Type getResultElementType() {
        return cast(getType().getScalarType(), PointerType.class).getElementType();
    }

    public Value getPointerOperand() {
        return getOperand(0);
    }

    public Type getPointerOperandType() {
        return getPointerOperand().getType();
    }

    // NOTICE return type uses int to store unsigned
    public int getPointerAddressSpace() {
        return getPointerOperandType().getPointerAddressSpace();
    }

    // NOTICE return type uses int to store unsigned
    public int getAddressSpace() {
        return getPointerAddressSpace();
    }

    public int getNumIndices() {
        return getNumOperands() - 1;
    }

    public boolean hasIndices() {
        return getNumOperands() > 1;
    }

    public boolean hasAllZeroIndices() {
        for (int i = 1; i < getNumOperands(); ++i) {
            ConstantInt ci = Casting.dyncast(getOperand(i), ConstantInt.class);
            if (ci == null || !ci.isZero()) {
                return false;
            }
        }
        return true;
    }

    public boolean hasAllConstantIndices() {
        for (int i = 1; i < getNumOperands(); ++i) {
            if (!ConstantInt.class.isInstance(getOperand(i))) {
                return false;
            }
        }
        return true;
    }

    public boolean isInBounds() {
        return isInBounds;
    }

    public List<Boolean> isInRangeForIndexes() {
        return isInRangeForIndexes;
    }

    public boolean isInRange(int idx) {
        Preconditions.checkArgument(idx < getNumIndices());
        return isInRangeForIndexes.get(idx).booleanValue();
    }

    @Override
    public <R, E extends CPAException> R accept(InstructionVisitor<R, E> visitor) throws E {
        return visitor.visit(this);
    }

    // TODO require APInt and DataLayout
    // accumulateConstantOffset

    @Override
    public String toString() {
        String res = "%" + getName();
        res += " = getelementptr ";
        if (isInBounds()) {
            res += "inbounds ";
        }
        res += getSourceElementType().toString();
        for (int i = 0; i < getNumOperands(); i++) {
            res += ", ";
            res += getOperand(i).getType().toString() + " ";
            res += Formatter.asOperand(getOperand(i));
        }
        return res;
    }
}
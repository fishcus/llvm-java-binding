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

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author guangchen on 26/02/2017.
 */
public class Value {
    private String name;
    private Type type;
    private List<Use> uses;

    public Value() {}

    public Value(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean hasName() {
        return name != null && !name.equals("");
    }

    public List<Use> uses() {
        return this.uses;
    }

    public void setUses(final List<Use> uses) {
        this.uses = uses;
    }

    public List<User> users() {
        return Lists.transform(this.uses, new Function<Use, User>() {
            @Override
            public User apply(Use input) {
                return Optional.fromNullable(input).transform(new Function<Use, User>() {
                    @Override
                    public User apply(Use input) {
                        return input.getUser();
                    }
                }).orNull();
            }
        });
    }

}

package edu.iteso.logic;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Variable extends Expression {

    @Getter
    private String name;
    private boolean value;

    public boolean getValue() {
        return this.value;
    }

    @Override
    public Expression simplify() {
        return this;
    }

    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof Variable)) return false;
        Variable v = (Variable) o;
        return getName().equals(v.getName());
    }

    @Override
    public Variable clone() {
        return new Variable(this.name, this.value);
    }

}

package edu.iteso.logic;

import edu.iteso.logic.simplifiers.Simplifier;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public abstract class Expression {

    public abstract boolean getValue();

    public abstract Expression simplify();

    public Expression simplify(Simplifier simplifier) {
        Expression exp = simplifier.simplify(this);
        return exp;
    }

    public Expression simplify(List<Simplifier> simplifiers) {
        for(Simplifier s : simplifiers) {
            Expression exp = s.simplify(this);
            if(!this.equals(exp)) return exp;
        }
        return this;
    }

    public abstract Expression clone();

}

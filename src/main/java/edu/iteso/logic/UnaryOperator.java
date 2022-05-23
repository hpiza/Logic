package edu.iteso.logic;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@NoArgsConstructor
public abstract class UnaryOperator extends Expression {

    @Getter
    private Expression expression;
    private char symbol;

    public String toString() {
        String strExp = expression.toString();
        if(expression instanceof BinaryOperator) strExp = String.format("(%s)", strExp);
        return String.format("%c%s", symbol, strExp);
    }

}

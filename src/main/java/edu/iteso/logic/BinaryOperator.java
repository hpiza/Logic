package edu.iteso.logic;

import lombok.Getter;

public abstract class BinaryOperator extends Expression {
    @Getter
    private Expression left, right;
    @Getter
    private char symbol;

    public BinaryOperator(Expression left, Expression right, char symbol) {
        this.left = (Expression) left.clone();
        this.right = (Expression) right.clone();
        this.symbol = symbol;
    }

    public String toString() {
        String strLeft = left.toString();
        if (strLeft.length() > 2) strLeft = "(" + strLeft + ")";
        String strRight = right.toString();
        if (strRight.length() > 2) strRight = "(" + strRight + ")";
        return String.format("%s %c %s", strLeft, symbol, strRight);
    }

}

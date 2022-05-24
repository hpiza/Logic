package edu.iteso.logic;

public class And extends BinaryOperator {

    public And(Expression left, Expression right) {
        super(left, right, '&');
    }

    @Override
    public boolean getValue() {
        return getLeft().getValue() && getRight().getValue();
    }

    @Override
    public Expression simplify() {
        Expression left = getLeft();
        Expression right = getRight();
        if (left.equals(right)) return left;
        if (left == Constant.False || right == Constant.False) return Constant.False;
        if (left instanceof Not) {
            Not not = (Not) left;
            if (not.getExpression().equals(right)) return Constant.False;
        }
        if (right instanceof Not) {
            Not not = (Not) right;
            if (not.getExpression().equals(left)) return Constant.False;
        }
        if (left == Constant.True) return right;
        if (right == Constant.True) return left;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof And)) return false;
        And and = (And) o;
        return getLeft().equals(and.getLeft()) && getRight().equals(and.getRight()) || getRight().equals(and.getLeft()) && getLeft().equals(and.getRight());
    }

    @Override
    public And clone() {
        return new And(getLeft(), getRight());
    }

}

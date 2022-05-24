package edu.iteso.logic;

public class Then extends BinaryOperator {

    public Then(Expression left, Expression right) {
        super(left, right, '>');
    }

    @Override
    public boolean getValue() {
        return !getLeft().getValue() || getRight().getValue();
    }

    @Override
    public Expression simplify() {
        Expression left = getLeft();
        Expression right = getRight();
        if (left == Constant.False || right == Constant.True) return Constant.True;
        if (left == Constant.True && right == Constant.False) return Constant.False;
        if (left.equals(right)) return left;
        if (left instanceof Not) {
            Not not = (Not) left;
            if (not.getExpression().equals(right)) return Constant.True;
            else return new Or(not.getExpression(), right);
        } else if (right instanceof Not) {
            Not not = (Not) right;
            if (not.getExpression().equals(left)) return Constant.True;
        }
        return new Or(new Not(left), right);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof Then)) return false;
        Then then = (Then) o;
        return getLeft().equals(then.getLeft()) && getRight().equals(then.getRight());
    }

    @Override
    public Then clone() {
        return new Then(this.getLeft(), this.getRight());
    }
}

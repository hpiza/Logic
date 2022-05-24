package edu.iteso.logic;

public class Or extends BinaryOperator {

    public Or(Expression left, Expression right) {
        super(left, right, '|');
    }

    @Override
    public boolean getValue() {
        return getLeft().getValue() || getRight().getValue();
    }

    @Override
    public Expression simplify() {
        Expression left = getLeft();
        Expression right = getRight();
        if(left.equals(right)) return left;
        if(left == Constant.True || right == Constant.True) return Constant.True;
        if(left == Constant.False) return right;
        if(right == Constant.False) return left;
        if(left instanceof Not) {
            Not not = (Not) left;
            if(not.getExpression().equals(right)) return Constant.True;
        }
        else if(right instanceof Not) {
            Not not = (Not) right;
            if(not.getExpression().equals(left)) return Constant.True;
        }
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof Or)) return false;
        Or or = (Or) o;
        return getLeft().equals(or.getLeft()) && getRight().equals(or.getRight()) || getLeft().equals(or.getRight()) && getRight().equals(or.getLeft());
    }

    @Override
    public Or clone() {
        return new Or(this.getLeft(), this.getRight());
    }

}

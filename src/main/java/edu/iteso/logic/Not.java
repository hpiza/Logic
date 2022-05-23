package edu.iteso.logic;

public class Not extends UnaryOperator {

    public Not(Expression expression) {
        super(expression, '!');
    }

    public boolean getValue() {
        return !getExpression().getValue();
    }

    public Expression simplify() {
        Expression exp = getExpression();
        if(exp == Constant.False) return Constant.True;
        if(exp == Constant.True) return Constant.False;
        if(exp instanceof Not) {
            Not not = (Not) exp;
            return not.getExpression();
        }
        if(exp instanceof BinaryOperator) {
            BinaryOperator bo = (BinaryOperator) exp;
            Expression left = bo.getLeft();
            Expression right = bo.getRight();
            if(bo instanceof And) return new Or (new Not(left), new Not(right));
            if(bo instanceof Or)  return new And(new Not(left), new Not(right));
        }
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof Not)) return false;
        Not not = (Not) o;
        return getExpression().equals(not.getExpression());
    }

    @Override
    public Not clone() {
        return new Not(getExpression());
    }

}

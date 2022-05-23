package edu.iteso.logic.simplifiers;

import edu.iteso.logic.And;
import edu.iteso.logic.Expression;
import edu.iteso.logic.Or;

public class OrAbsorption implements Simplifier {

    @Override
    public Expression simplify(Expression expression) {
        if(!(expression instanceof Or)) return expression;
        Or or = (Or) expression;
        Expression left = or.getLeft();
        Expression right = or.getRight();
        if(right instanceof And) {
            And and = (And) right;
            if(and.getLeft().equals(left) || and.getRight().equals(left)) return left;
        }
        else if(left instanceof Or) {
            And and = (And) left;
            if(and.getLeft().equals(right) || and.getRight().equals(right)) return right;
        }
        return expression;
    }

}

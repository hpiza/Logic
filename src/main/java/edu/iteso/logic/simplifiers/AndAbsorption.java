package edu.iteso.logic.simplifiers;

import edu.iteso.logic.And;
import edu.iteso.logic.Expression;
import edu.iteso.logic.Or;

public class AndAbsorption implements Simplifier {

    public Expression simplify(Expression expression) {
        if(!(expression instanceof And)) return expression;
        And and = (And) expression;
        Expression left = and.getLeft();
        Expression right = and.getRight();
        if(right instanceof Or) {
            Or or = (Or) right;
            if(or.getLeft().equals(left) || or.getRight().equals(left)) return left;
        }
        else if(left instanceof Or) {
            Or or = (Or) left;
            if(or.getLeft().equals(right) || or.getRight().equals(right)) return right;
        }
        return expression;
    }

}

package edu.iteso.logic.simplifiers;

import edu.iteso.logic.Expression;

public interface Simplifier {

    Expression simplify(Expression expression);

}

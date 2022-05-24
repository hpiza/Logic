package edu.iteso;

import edu.iteso.logic.*;
import edu.iteso.logic.simplifiers.AndAbsorption;
import edu.iteso.logic.simplifiers.OrAbsorption;
import edu.iteso.logic.simplifiers.Simplifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Variable va = new Variable("a", true);
        Variable vb = new Variable("b", false);
        And and1 = new And(va, vb);
        System.out.println(and1);
        System.out.println(and1.getValue());
        Or or = new Or(new Not(and1), new Variable("c", false));
        System.out.println(or);
        System.out.println(or.getValue());
        System.out.println(va.getValue());
        System.out.println(or.simplify());

        Not not1 = new Not(new Not(new Not(va)));
        System.out.println(not1);
        Expression exp = not1.simplify();
        System.out.println(exp);

        Not not2 = new Not(new Or(va, vb));
        Not not3 = new Not(new And(va, vb));
        System.out.println(not2);
        System.out.println(not3);
        System.out.println(not2.simplify());
        System.out.println(not3.simplify());

        And and3 = new And(not2, Constant.True);
        And and4 = new And(not2, Constant.False);
        System.out.println(and3.simplify());
        System.out.println(and4.simplify());

        Or or3 = new Or(and1, Constant.True);
        Or or4 = new Or(and1, Constant.False);
        System.out.println(or3.simplify());
        System.out.println(or4.simplify());

        System.out.println(new And(Constant.False, Constant.True).simplify());
        System.out.println(new Or(Constant.False, Constant.True).simplify());
        System.out.println(new And(Constant.True, Constant.True).simplify());
        System.out.println(new Or(Constant.False, Constant.False).simplify());

        And and5 = new And(
                            new Not(new And(new Variable("c", true), new Variable("d", true))),
                            new Not(new And(new Variable("c", true), new Variable("d", true)))
        );
        Not not5 = new Not(and5);
        System.out.println(and5);
        System.out.println(and5.simplify());
        System.out.println(not5);
        System.out.println(not5.simplify());
        System.out.println(not5.getValue());
        System.out.println(not5.simplify().getValue());

        AndAbsorption aa = new AndAbsorption();
        Expression exp1 = aa.simplify(new And(va, new Or(va, vb)));
        System.out.println(exp1);
        Expression exp2 = aa.simplify(new And(vb, new Or(va, vb)));
        System.out.println(exp2);
        Expression exp3 = aa.simplify(new And(new Or(va, vb), va));
        System.out.println(exp3);
        Expression exp4 = aa.simplify(new And(new Or(va, vb), vb));
        System.out.println(exp4);
        Expression exp5 = aa.simplify(new And(new And(va, vb), vb));
        System.out.println(exp5);
        System.out.println("-----------");

        And and6 = new And(va, new Or(va, vb));
        Expression exp6 = and6.simplify();
        System.out.println(and6 + " --> " + exp6);
        Expression exp7 = and6.simplify(new AndAbsorption());
        System.out.println(and6 + " --> " + exp7);

        Or or7 = new Or(va, new And(va, vb));
        Expression exp8 = or7.simplify();
        System.out.println(or7 + " --> " + exp8);
        Expression exp9 = or7.simplify(new OrAbsorption());
        System.out.println(or7 + " --> " + exp9);

        Expression expA = or7.simplify(new AndAbsorption());
        System.out.println(or7 + " --> " + expA);

        List<Simplifier> simplifiers = new ArrayList<>();
        Collections.addAll(simplifiers, new OrAbsorption(), new AndAbsorption());
        Expression expB = or7.simplify(simplifiers);
        System.out.println(or7 + " --> " + expB);

        Then then0 = new Then(Constant.False, Constant.False);
        System.out.println(then0 + " --> " + then0.simplify());

        Then then1 = new Then(Constant.True, Constant.False);
        System.out.println(then1 + " --> " + then1.simplify());

        Then then2 = new Then(va, va);
        System.out.println(then2 + " --> " + then2.simplify());

        Then then3 = new Then(new Not(va), va);
        System.out.println(then3 + " --> " + then3.simplify());

        Then then4 = new Then(new Not(va), vb);
        System.out.println(then4 + " --> " + then4.simplify());

        Then then5 = new Then(va, new Not(vb));
        System.out.println(then5 + " --> " + then5.simplify());

    }
}

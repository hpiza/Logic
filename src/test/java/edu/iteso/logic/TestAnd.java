package edu.iteso.logic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.RepeatedTest;
import static org.junit.jupiter.api.Assertions.*;
import static edu.iteso.logic.Utils.*;

public class TestAnd {

    @RepeatedTest(value=10)
    public void testGetValue() {
        And and =  anyAnd();
        System.out.println("Test getValue(): " + and);
        assertEquals(and.getValue(), and.getLeft().getValue() && and.getRight().getValue());
    }

    @Test
    public void testSimplifyIdempotence() {
        Expression e1 = anyExpression();
        Expression e11 = new And(e1, e1).simplify();
        System.out.println(e1 + " ... " + e11 + " ... idempotence");
        assertEquals(e11, e1);
    }

    @Test
    public void testSimplifyNegation() {
        Expression e1 = anyExpression();
        Expression e11 = new And(e1, new Not(e1)).simplify();
        System.out.println(e1 + " ... " + e11 + " ... contradiction");
        assertEquals(e11, Constant.False);
        Expression e12 = new And(new Not(e1), e1).simplify();
        System.out.println(e1 + " ... " + e12 + " ... contradiction");
        assertEquals(e12, Constant.False);
    }

    @Test
    public void testSimplifyIdentity() {
        Expression e1 = anyExpression();
        Expression e11 = new And(e1, Constant.True).simplify();
        System.out.println(e1 + " ... " + e11 + " ... identity");
        assertEquals(e11, e1);
        Expression e12 = new And(Constant.True, e1).simplify();
        System.out.println(e1 + " ... " + e12 + " ... identity");
        assertEquals(e12, e1);

    }

    @Test
    public void testSimplifyDomination() {
        Expression e1 = anyExpression();
        Expression e11 = new And(e1, Constant.False).simplify();
        assertEquals(e11, Constant.False);
        Expression e12 = new And(Constant.False, e1).simplify();
        assertEquals(e12, Constant.False);
    }

    @Test
    public void testEquals() {
        Expression e1 = anyExpression();
        Expression e2 = anyExpression();
        And and1 = new And(e1, e2);
        And and2 = new And(e1, e2);
        And and3 = new And(e2, e1);
        assertEquals(and1, and2);
        assertEquals(and1, and3);
    }

    @RepeatedTest(value=5)
    public void testClone() {
        Expression e1 = anyNonConstantExpression();
        Expression e2 = anyNonConstantExpression();
        And and1 = new And(e1, e2);
        And and2 = and1.clone();
        assertEquals(and1, and2);
        assertNotSame(and1, and2);
        assertNotSame(e1, and2.getLeft());
        assertNotSame(e2, and2.getRight());
        System.out.println(and1 + " ... " + and2 + " ... clone");
    }

    @RepeatedTest(value=10)
    public void testToString() {
        Expression e1 = anyExpression();
        String s1 = e1.toString();
        if(s1.length() > 2) s1 = String.format("(%s)", s1);
        Expression e2 = anyExpression();
        String s2 = e2.toString();
        if(s2.length() > 2) s2 = String.format("(%s)", s2);
        String s3 = String.format("%s & %s", s1, s2);
        And and = new And(e1, e2);
        System.out.println("toString(): " + s3 + " ... " + and);
        assertEquals(and.toString(), s3);
    }

}

package edu.iteso.logic;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static edu.iteso.logic.Utils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

public class TestThen {

    @RepeatedTest(value=10)
    public void testGetValue() {
        Then then =  anyThen();
        System.out.println("Test getValue(): " + then);
        assertEquals(then.getValue(), !then.getLeft().getValue() || then.getRight().getValue());
    }

    @Test
    public void testEquals() {
        Expression e1 = anyExpression();
        Expression e2 = anyExpression();
        Then then1 = new Then(e1, e2);
        Then then2 = new Then(e1, e2);
        Then then3 = new Then(e2, e1);
        assertEquals(then1, then2);
        assertNotEquals(then1, then3);
    }

    @RepeatedTest(value=5)
    public void testClone() {
        Expression e1 = anyNonConstantExpression();
        Expression e2 = anyNonConstantExpression();
        Then then1 = new Then(e1, e2);
        Then then2 = then1.clone();
        assertEquals(then1, then2);
        assertNotSame(then1, then2);
        assertNotSame(then1.getLeft(), then2.getLeft());
        assertNotSame(then1.getRight(), then2.getRight());
        System.out.println(then1 + " ... " + then2 + " ... clone");
    }

    @RepeatedTest(value=10)
    public void testToString() {
        Expression e1 = anyExpression();
        String s1 = e1.toString();
        if(s1.length() > 2) s1 = String.format("(%s)", s1);
        Expression e2 = anyExpression();
        String s2 = e2.toString();
        if(s2.length() > 2) s2 = String.format("(%s)", s2);
        String s3 = String.format("%s > %s", s1, s2);
        Then then = new Then(e1, e2);
        System.out.println("toString(): " + s3 + " ... " + then);
        assertEquals(then.toString(), s3);
    }

}

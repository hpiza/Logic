package edu.iteso.logic;

public class Utils {

    public static String anyLowercase() {
        return String.format("%c", 'a' + (char) (('z' - 'a' + 1) * Math.random()));
    }

    public static boolean anyBoolean() {
        return Math.random() < 0.5;
    }

    public static Constant anyConstant() {
        return anyBoolean()? Constant.True : Constant.False;
    }

    public static Variable anyVariable() {
        return new Variable(anyLowercase(), anyBoolean());
    }

    public static Expression anyExpression() {
        int r = (int) (7 * Math.random());
        switch(r) {
            case 0: case 1: return anyConstant();
            case 2: case 3: return anyVariable();
            case 4: return anyNot();
            case 5: return anyAnd();
            default: return anyOr();
        }
    }

    public static Expression anyNonConstantExpression() {
        int r = (int) (5 * Math.random());
        switch(r) {
            case 0: case 1: return anyVariable();
            case 2: return anyNot();
            case 3: return anyAnd();
            default: return anyOr();
        }
    }

    public static Not anyNot() {
        return new Not(anyExpression());
    }

    public static And anyAnd() {
        return new And(anyExpression(), anyExpression());
    }

    public static Or anyOr() {
        return new Or(anyExpression(), anyExpression());
    }

    public static Then anyThen() {
        return new Then(anyExpression(), anyExpression());
    }

}

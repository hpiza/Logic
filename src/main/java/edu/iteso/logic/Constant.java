package edu.iteso.logic;

public class Constant extends Expression {

    public static final Constant False = new Constant(false);
    public static final Constant True = new Constant(true);

    private boolean value;

    private Constant(boolean value) {
        this.value = value;
    }

    @Override
    public boolean getValue() {
        return this.value;
    }

    @Override
    public Expression simplify() {
        return this;
    }

    @Override
    public String toString() {
        return this.value? "T" : "F";
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof Constant)) return false;
        Constant c = (Constant) o;
        return this.getValue() == c.getValue();
    }

    @Override
    public Constant clone() {
        return this;
    }

}

package edu.hw2.Task1;

public sealed interface Expr {
    double evaluate();

    record Constant(double value) implements Expr {
        @Override
        public double evaluate() {
            return value;
        }

    }

    record Negate(Expr expression) implements Expr {
        @Override
        public double evaluate() {
            return -expression.evaluate();
        }
    }

    record Exponent(Expr base, int power) implements Expr {
        @Override
        public double evaluate() {
            if (base.evaluate() == 0 && power == 0) {
                throw new ArithmeticException("Undefined expression: 0^0");
            }
            return Math.pow(base.evaluate(), power);
        }
    }

    record Addition(Expr left, Expr right) implements Expr {
        @Override
        public double evaluate() {
            return left.evaluate() + right.evaluate();
        }
    }

    record Multiplication(Expr left, Expr right) implements Expr {
        @Override
        public double evaluate() {
            double result = left.evaluate() * right.evaluate();
            if ((left.evaluate() < 0 && right.evaluate() < 0) || (left.evaluate() > 0 && right.evaluate() > 0)) {
                return result;
            } else {
                return -Math.abs(result);
            }
        }
    }
}

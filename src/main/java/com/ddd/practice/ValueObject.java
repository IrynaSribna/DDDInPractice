package com.ddd.practice;

import java.util.function.Function;


public abstract class ValueObject<T> {

    @Override
    public int hashCode() {
        return hashCodeCore();
    }

    @Override
    public boolean equals(Object obj) {
        T valueObject = (T) obj;
        if (valueObject == null) {
            return false;
        }

        return equalsCore(valueObject);
    }

    abstract int hashCodeCore();

    abstract boolean equalsCore(T other);

    Function<ValueObject<T>, Function<ValueObject<T>, Boolean>> equalsOperator = x -> y ->
        x == null && y == null || (x != null && y != null && x.equals(y));

    public boolean equalOperator(ValueObject<T> valueObject1, ValueObject<T> valueObject2) {
        return equalsOperator.apply(valueObject1).apply(valueObject2);
    }

    public boolean unequalOperator(ValueObject<T> valueObject1, ValueObject<T> valueObject2) {
        return !equalsOperator.apply(valueObject1).apply(valueObject2);
    }
}

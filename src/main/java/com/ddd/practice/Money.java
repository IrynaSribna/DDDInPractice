package com.ddd.practice;

import java.util.Objects;

import lombok.Getter;


@Getter
public final class Money extends ValueObject<Money> {

    public static final Money NONE = new Money(0, 0, 0, 0, 0, 0);
    public static final Money ONE_CENT = new Money(1, 0, 0, 0, 0, 0);
    public static final Money TEN_CENT = new Money(0, 1, 0, 0, 0, 0);
    public static final Money QUARTER_CENT = new Money(0, 0, 1, 0, 0, 0);
    public static final Money ONE_DOLLAR = new Money(0, 0, 0, 1, 0, 0);
    public static final Money FIVE_DOLLAR = new Money(0, 0, 0, 0, 1, 0);
    public static final Money TWENTY_DOLLAR = new Money(0, 0, 0, 0, 0, 1);

    private int oneCentCount;
    private int tenCentCount;
    private int quarterCount;
    private int oneDollarCount;
    private int fiveDollarCount;
    private int twentyDollarCount;
    private double amount;

    public Money(
        int oneCentCount,
        int tenCentCount,
        int quarterCount,
        int oneDollarCount,
        int fiveDollarCount,
        int twentyDollarCount
    ) {
        this.oneCentCount = oneCentCount;
        this.tenCentCount = tenCentCount;
        this.quarterCount = quarterCount;
        this.oneDollarCount = oneDollarCount;
        this.fiveDollarCount = fiveDollarCount;
        this.twentyDollarCount = twentyDollarCount;

        if (oneCentCount < 0) {
            throw new IllegalArgumentException();
        }
        if (tenCentCount < 0) {
            throw new IllegalArgumentException();
        }
        if (quarterCount < 0) {
            throw new IllegalArgumentException();
        }
        if (oneDollarCount < 0) {
            throw new IllegalArgumentException();
        }
        if (fiveDollarCount < 0) {
            throw new IllegalArgumentException();
        }
        if (twentyDollarCount < 0) {
            throw new IllegalArgumentException();
        }

        amount = oneCentCount * 0.01 +
            tenCentCount * 0.1 +
            quarterCount * 0.25 +
            oneDollarCount +
            fiveDollarCount * 5 +
            twentyDollarCount * 20;

    }

    public static Money plus(Money first, Money second) {
        return new Money(
            first.oneCentCount + second.oneCentCount,
            first.tenCentCount + second.tenCentCount,
            first.quarterCount + second.quarterCount,
            first.oneDollarCount + second.oneDollarCount,
            first.fiveDollarCount + second.fiveDollarCount,
            first.twentyDollarCount + second.twentyDollarCount
        );
    }

    public static Money subtract(Money first, Money second) {
        return new Money(
            first.oneCentCount - second.oneCentCount,
            first.tenCentCount - second.tenCentCount,
            first.quarterCount - second.quarterCount,
            first.oneDollarCount - second.oneDollarCount,
            first.fiveDollarCount - second.fiveDollarCount,
            first.twentyDollarCount - second.twentyDollarCount
        );
    }

    @Override
    public boolean equalsCore(Money other) {
        if (this == other) {
            return true;
        }

        return oneCentCount == other.oneCentCount &&
            tenCentCount == other.tenCentCount &&
            quarterCount == other.quarterCount &&
            oneDollarCount == other.oneDollarCount &&
            fiveDollarCount == other.fiveDollarCount &&
            twentyDollarCount == other.twentyDollarCount;
    }

    @Override
    public int hashCodeCore() {
        return Objects.hash(
            oneCentCount,
            tenCentCount,
            quarterCount,
            oneDollarCount,
            fiveDollarCount,
            twentyDollarCount
        );
    }

    @Override
    public String toString() {
        if (amount < 1) {
            return "¢" + (int)(amount * 100);
        }

        return "$" + amount;
    }

}

package com.ddd.practice;

import static com.ddd.practice.Money.ONE_CENT;
import static com.ddd.practice.Money.ONE_DOLLAR;
import static com.ddd.practice.Money.TEN_CENT;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;


@RunWith(JUnitParamsRunner.class)
public class MoneyTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Test
    public void sum_of_two_moneys_returns_correct_result() {
        Money money1 = new Money(1, 2, 3, 4, 5, 6);
        Money money2 = new Money(1, 2, 3, 4, 5, 6);

        Money sum = Money.plus(money1, money2);

        assertThat(sum.getOneCentCount()).as("Sum of one cent").isEqualTo(2);
        assertThat(sum.getTenCentCount()).as("sum of ten cent").isEqualTo(4);
        assertThat(sum.getQuarterCount()).as("Sum of quarter").isEqualTo(6);
        assertThat(sum.getOneDollarCount()).as("Sum of one dollar").isEqualTo(8);
        assertThat(sum.getFiveDollarCount()).as("Sum of five dollar").isEqualTo(10);
        assertThat(sum.getTwentyDollarCount()).as("Sum of twenty dollar").isEqualTo(12);
    }

    @Test
    public void two_money_instances_equal_if_contain_same_amount() {
        Money money1 = new Money(1, 2, 3, 4, 5, 6);
        Money money2 = new Money(1, 2, 3, 4, 5, 6);

        assertThat(money1.equals(money2)).isTrue();
        assertThat(money1.hashCode() == money2.hashCode()).isTrue();
    }

    @Test
    public void  two_money_instances_not_equal_if_contain_different_amount() {
        Money oneDollar = Money.ONE_DOLLAR;
        Money hundredCent = new Money(100, 0, 0, 0, 0, 0);

        assertThat(oneDollar.equals(hundredCent)).isFalse();
        assertThat(oneDollar.hashCode() == hundredCent.hashCode()).isFalse();
    }

    @Test(expected = IllegalArgumentException.class)
    @Parameters({
        "-1, 0, 0, 0, 0, 0",
        "0, -1, 0, 0, 0, 0",
        "0, 0, -1, 0, 0, 0",
        "0, 0, 0, -1, 0, 0",
        "0, 0, 0, 0, -1, 0",
        "0, 0, 0, 0, 0, -1"})
    public void cannot_create_money_with_negative_value(
        int oneCent,
        int tenCent,
        int quarterCent,
        int oneDollar,
        int fiveDollar,
        int twentyDollar
    ) {

        new Money(oneCent, tenCent, quarterCent, oneDollar, fiveDollar, twentyDollar);
    }

    @Test
    @Parameters({
        "0,   0, 0, 0, 0,   0,     0",
        "1,   0, 0, 0, 0,   0,     0.01",
        "1,  20, 0, 0, 0,   0,     2.01",
        "1,   2, 3, 0, 0,   0,     0.96",
        "1,   2, 3, 4, 0,   0,     4.96",
        "1,   2, 3, 4, 5,   0,     29.96",
        "1,   2, 3, 4, 5,   6,     149.96",
        "11,  0, 0, 0, 0,   0,     0.11",
        "110, 0, 0, 0, 100, 0,     501.1",
    })
    public void amount_is_calculated_correctly(
        int oneCent,
        int tenCent,
        int quarterCent,
        int oneDollar,
        int fiveDollar,
        int twentyDollar,
        double amount
    ) {
        Money money = new Money(
            oneCent,
            tenCent,
            quarterCent,
            oneDollar,
            fiveDollar,
            twentyDollar
        );

        assertThat(money.getAmount()).isEqualTo(amount);
    }

    @Test
    public void subtraction_of_two_money_produces_correct_result() {
        Money money1 = new Money(10, 10, 10, 10, 10, 10);
        Money money2 = new Money(1, 2, 3, 4, 5, 6);

        Money sub = Money.subtract(money1, money2);
        assertThat(sub.getOneCentCount()).isEqualTo(9);
        assertThat(sub.getTenCentCount()).isEqualTo(8);
        assertThat(sub.getQuarterCount()).isEqualTo(7);
        assertThat(sub.getOneDollarCount()).isEqualTo(6);
        assertThat(sub.getFiveDollarCount()).isEqualTo(5);
        assertThat(sub.getTwentyDollarCount()).isEqualTo(4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void could_not_subtract_more_than_exist() {
        Money money1 = TEN_CENT;
        Money money2 = ONE_CENT;

        Money.subtract(money1, money2);
    }

    @Test
    @Parameters({
        "0,   0, 0, 0, 0,   0,     ¢0",
        "1,   0, 0, 0, 0,   0,     ¢1",
        "1,  20, 0, 0, 0,   0,     $2.01",
        "1,   2, 3, 0, 0,   0,     ¢96",
        "1,   2, 3, 4, 0,   0,     $4.96",
        "1,   2, 3, 4, 5,   0,     $29.96",
        "1,   2, 3, 4, 5,   6,     $149.96",
        "11,  0, 0, 0, 0,   0,     ¢11",
        "110, 0, 0, 0, 100, 0,     $501.1",
    })
    public void toString_should_return_amount_of_money(
        int oneCent,
        int tenCent,
        int quarterCent,
        int oneDollar,
        int fiveDollar,
        int twentyDollar,
        String expectedString
    ) {
        Money money = new Money(
            oneCent,
            tenCent,
            quarterCent,
            oneDollar,
            fiveDollar,
            twentyDollar
        );
        assertThat(money.toString()).isEqualTo(expectedString);
    }

}

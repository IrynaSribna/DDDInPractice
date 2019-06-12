package com.ddd.practice;

import static com.ddd.practice.Money.ONE_CENT;
import static com.ddd.practice.Money.ONE_DOLLAR;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;


public class SnackMachineTest {

    @Test
    public void return_money_empties_money_in_transaction() {
        SnackMachine snackMachine = new SnackMachine();
        snackMachine.insertMoney(ONE_DOLLAR);
        snackMachine.returnMoney();

        assertThat(snackMachine.getMoneyInTransaction().getAmount()).isEqualTo(0);
    }

    @Test
    public void inserted_money_goes_into_money_in_transaction() {
        SnackMachine snackMachine = new SnackMachine();
        snackMachine.insertMoney(ONE_CENT);
        snackMachine.insertMoney(ONE_DOLLAR);

        assertThat(snackMachine.getMoneyInTransaction().getAmount()).isEqualTo(1.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannot_insert_more_than_one_coin_or_a_note_at_a_time() {
        SnackMachine snackMachine = new SnackMachine();
        Money twoCents = Money.plus(ONE_CENT, ONE_CENT);

        snackMachine.insertMoney(twoCents);
    }

    @Test
    public void money_in_transaction_goes_inside_after_purchase() {
        SnackMachine snackMachine = new SnackMachine();
        snackMachine.insertMoney(ONE_DOLLAR);
        snackMachine.insertMoney(ONE_DOLLAR);
        snackMachine.buySnack();

        assertThat(snackMachine.getMoneyInTransaction().getAmount()).isEqualTo(0);
        assertThat(snackMachine.getMoneyInside().getAmount()).isEqualTo(2);
    }

}

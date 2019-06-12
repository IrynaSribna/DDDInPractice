package com.ddd.practice;

import static com.ddd.practice.Money.FIVE_DOLLAR;
import static com.ddd.practice.Money.NONE;
import static com.ddd.practice.Money.ONE_CENT;
import static com.ddd.practice.Money.ONE_DOLLAR;
import static com.ddd.practice.Money.QUARTER_CENT;
import static com.ddd.practice.Money.TEN_CENT;
import static com.ddd.practice.Money.TWENTY_DOLLAR;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;


@Getter
public final class SnackMachine extends Entity {

    private Money moneyInside = NONE;
    private Money moneyInTransaction = NONE;

    public void insertMoney(Money money) {
        List<Money> allowedMoney = new ArrayList<Money> () {
            {
                add(ONE_CENT);
                add(TEN_CENT);
                add(QUARTER_CENT);
                add(ONE_DOLLAR);
                add(FIVE_DOLLAR);
                add(TWENTY_DOLLAR);
            }
        };

        if (!allowedMoney.contains(money)) {
            throw new IllegalArgumentException();
        }

        moneyInTransaction = Money.plus(moneyInTransaction, money);
    }

    public void returnMoney() {
        moneyInTransaction = NONE;
    }

    public void buySnack() {
        moneyInside = Money.plus(moneyInside, moneyInTransaction);
        moneyInTransaction = NONE;
    }
}

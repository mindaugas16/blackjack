package com.company.module;

import java.util.ArrayList;

public class MePlayer extends Player {
    private String name;
    private int money;

    public MePlayer(String name) {
        this.name = name;
        this.money = 250;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}

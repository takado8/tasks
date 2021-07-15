package com.test1;

public class MainClass {
    ButtonLike buttonLike;
    int someValue = 0;

    public MainClass() {
        buttonLike = new ButtonLike(this::updateValue);
    }

    void updateValue(int newVal){
        this.someValue = newVal;
    }
}

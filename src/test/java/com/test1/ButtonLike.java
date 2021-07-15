package com.test1;

public class ButtonLike {
    private final Callback callback;

    public ButtonLike(Callback callback) {
        this.callback = callback;
    }

    public void someButtonCallback(){
        int someNewVal = 7;
        callback.updateValue(someNewVal);
    }
}

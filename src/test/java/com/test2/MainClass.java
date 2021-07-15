package com.test2;

public class MainClass {
    ButtonLike buttonLike;
    WrappedInt wrap = new WrappedInt();

    public MainClass() {
        wrap.value = 0;
        buttonLike = new ButtonLike(wrap);
    }
}

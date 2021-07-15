package com.test2;

public class ButtonLike {
    WrappedInt wrap;

    public ButtonLike(WrappedInt wrap) {
        this.wrap = wrap;
    }

    public void someButtonCallback() {
        wrap.value = 7;
    }
}

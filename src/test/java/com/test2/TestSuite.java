package com.test2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSuite {
    @Test
    void test(){
        //given
        MainClass mainClass = new MainClass();
        // when
        assertEquals(0, mainClass.wrap.value);
        mainClass.buttonLike.someButtonCallback();
        // then
        assertEquals(7, mainClass.wrap.value);
    }
}

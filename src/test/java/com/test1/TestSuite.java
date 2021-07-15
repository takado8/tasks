package com.test1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSuite {
    @Test
    void test(){
        //given
        MainClass mainClass = new MainClass();
        // when
        assertEquals(0, mainClass.someValue);
        mainClass.buttonLike.someButtonCallback();
        // then
        assertEquals(7, mainClass.someValue);
    }
}

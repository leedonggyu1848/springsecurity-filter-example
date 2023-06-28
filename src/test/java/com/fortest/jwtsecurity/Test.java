package com.fortest.jwtsecurity;


import static org.assertj.core.api.Assertions.assertThat;

public class Test {
    static enum TestEnum {
        TEST1,
    }
    @org.junit.jupiter.api.Test
    public void enumTest() {
        assertThat(TestEnum.valueOf("TEST1")).isEqualTo(TestEnum.TEST1);
        assertThat(TestEnum.TEST1.toString()).isEqualTo("TEST1");
    }
}

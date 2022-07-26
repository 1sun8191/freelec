package com.jojoldu.book.springboot.web.dto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
class HelloResponseDtoTest {

    @Test
    public void 롬복_기능_테스트() {

    String name = "test";
    int amount = 1000;

    HelloResponseDto dto = new HelloResponseDto(name, amount);

    Assertions.assertThat(dto.getName()).isEqualTo(name);
    Assertions.assertThat(dto.getAmount()).isEqualTo(amount);

    }
}
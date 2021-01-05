package com.example.rublerateapp;


import com.example.rublerateapp.rate.Rate;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

// Тесты для проверки того что Json файлы предоставляемые API курсов валют
// сохраняются в объектах Rate без ошибок и сохраняют всю необходимую информацию для наших расчетов
// Тестируем на 2х Json файлах сохраненных локально в test/resources - /01-12-2020.json и /29-12-2020.json
@JsonTest
public class TestingOfRate {

    private JacksonTester<Rate> jacksonTester;

    @Test
    public void firstRateFileTest() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        JacksonTester.initFields(this, objectMapper);

        Rate rateOfFirstDecember = this.jacksonTester.read("/01-12-2020.json").getObject();

        // Тестируем что значения полей объекта тип Rate соответсвует значениям в Json файле
        Assertions.assertEquals("1606867199", rateOfFirstDecember.getTimestamp());
        Assertions.assertEquals(Float.valueOf(75.9935F), rateOfFirstDecember.getRates().get("RUB"));


    }

    @Test
    public void secondRateFileTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JacksonTester.initFields(this, objectMapper);

        Rate rateOfTwentyNineDecember = this.jacksonTester.read("/29-12-2020.json").getObject();

        Assertions.assertEquals("1609286399", rateOfTwentyNineDecember.getTimestamp());
        Assertions.assertEquals(Float.valueOf(0.816093F), rateOfTwentyNineDecember.getRates().get("EUR"));

    }


}

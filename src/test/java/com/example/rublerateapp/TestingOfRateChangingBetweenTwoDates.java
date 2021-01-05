package com.example.rublerateapp;

import com.example.rublerateapp.rate.Rate;
import com.example.rublerateapp.rate.RateChangingBetweenTwoDates;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.json.JacksonTester;

// Тесты для проверки класса который высчитывает разницу курса выбранной валюты к Рублю
// При работе сервиса высчитывается разница курса сегодня и вчера,
// в тестах между 1 и 20 декабря 2020 из Json файлов сохраненных локально test/resources

public class TestingOfRateChangingBetweenTwoDates {

    private JacksonTester<Rate> jacksonTester;


    @Test
    public void firstRateTest() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        JacksonTester.initFields(this, objectMapper);

        Rate rateOfFirstDecember = this.jacksonTester.read("/01-12-2020.json").getObject();
        Rate rateOfTwentyNineDecember = this.jacksonTester.read("/29-12-2020.json").getObject();

        RateChangingBetweenTwoDates rateChanging =
                new RateChangingBetweenTwoDates(rateOfTwentyNineDecember, rateOfFirstDecember);

        // Для расчета разницы к рублю выбираем доллар США(код валюты "USD")
        float answerUSD = rateChanging.calculateChangeOfRating("USD");
        // 29.12 - Курс к доллару 74.0564,
        // 01.12 - Курс к доллару 75.9935
        // Курс даллара к рублю стал меньше, соответственно метод высчитывающий разницу курсов
        // должен вернуть значение больше нуля
        Assertions.assertTrue(answerUSD > 0);

        // Для расчета разницы к рублю выбираем биткоин("BTC")
        float answerBTC = rateChanging.calculateChangeOfRating("BTC");
        // Курс биткоина на последний месяц значительно вырос, соответственно метод высчитывающий разницу курсов
        // должен вернуть значение меньше нуля
        Assertions.assertTrue(answerBTC < 0);
    }
}

package com.example.rublerateapp.client;

import com.example.rublerateapp.rate.Rate;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/*
Feign клиент для подключения к API курса валют
Так как используется бесплатная версия подключения к API
используются только два вида запросов:
1) Запрос по всем курсам валют по отношению к доллару на данный момент - getTodayRate()
2) Запрос по всем курсам валют по отношению к доллару в опроделенную дату в прошлом - getRateOfCustomDate()
 */

@FeignClient(url = "${currency.api.url}", name = "Rate-Client")
public interface RateClient {

    @GetMapping("latest.json?app_id=${currency.application.id}")
    public Rate getTodayRate();

    @GetMapping("historical/{dateOfRate}.json?app_id=${currency.application.id}")
    public Rate getRateOfCustomDate(@PathVariable String dateOfRate);

}

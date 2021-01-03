package com.example.rublerateapp.controller;

import com.example.rublerateapp.client.GifClient;
import com.example.rublerateapp.client.RateClient;
import com.example.rublerateapp.gif.GifResponse;
import com.example.rublerateapp.rate.Rate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;

@RestController
public class RestControllerOfRates {

    Map<String, String> mapOfCurrencies;

    RateClient rateClient;

    GifClient gifClient;

    @Autowired
    public RestControllerOfRates(RateClient rateClient,
                                 GifClient gifClient,
                                 @Value("#{${currencyMap}}") Map<String, String> map) {
        this.rateClient = rateClient;
        this.gifClient = gifClient;
        this.mapOfCurrencies = map;
    }

    @GetMapping("/todayRates")
    public Rate todayRates() {

        return rateClient.getTodayRate();

    }

    @GetMapping("/yesterdayRates")
    public Rate yesterdayRates() {

        String yesterday = LocalDate.now().minusDays(1).toString();

        return rateClient.getRateOfCustomDate(yesterday);
    }

    @GetMapping("/richgif")
    public GifResponse richGif() {
        return gifClient.getRichGif(5);

    }

    @GetMapping("/brokegif")
    public GifResponse brokeGif() {
        return gifClient.getBrokeGif(5);

    }

    @GetMapping("/map")
    public Map<String, String> currencyMap() {
        return mapOfCurrencies;
    }



}

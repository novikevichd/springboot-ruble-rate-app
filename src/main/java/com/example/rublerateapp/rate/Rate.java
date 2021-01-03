package com.example.rublerateapp.rate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.LinkedHashMap;

// Класс в который мы сохраняем информацию по курсам валют,
// которую нам предоставляет Клиент для подключения к API курса валют
@JsonIgnoreProperties(ignoreUnknown = true)
public class Rate {

    private String disclaimer;
    private String license;
    private String timestamp;
    private String base;

    // Мапа в которой непосредственно хранятся курсы всех валют
    // (Ключ=Код валюты, Значение=Курс по отношению к доллару)
    private LinkedHashMap<String, Float> rates;

    public String getDisclaimer() {
        return disclaimer;
    }

    public String getLicense() {
        return license;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getBase() {
        return base;
    }

    public LinkedHashMap<String, Float> getRates() {
        return rates;
    }

    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public void setRates(LinkedHashMap<String, Float> rates) {
        this.rates = rates;
    }
}

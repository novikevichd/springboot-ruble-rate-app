package com.example.rublerateapp.gif;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// Этот класс и все классы в пэккедже gif нужны для сохранения
// нужной нам информации по gif файлам которую нам предоставляет
// Клиент для подключения к API gif файлов
@JsonIgnoreProperties(ignoreUnknown = true)
public class OriginalImageData {
    String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

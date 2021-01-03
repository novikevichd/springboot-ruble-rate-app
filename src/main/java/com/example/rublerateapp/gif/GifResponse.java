package com.example.rublerateapp.gif;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// Этот класс и все классы в пэккедже gif нужны для сохранения
// нужной нам информации по gif файлам которую нам предоставляет
// Клиент для подключения к API gif файлов
@JsonIgnoreProperties(ignoreUnknown = true)
public class GifResponse {
    private DataOfResponse[] data;

    public DataOfResponse[] getData() {
        return data;
    }

    public void setData(DataOfResponse[] data) {
        this.data = data;
    }

    // Метод который предоставляет нам url gif файла
    public String getUrl() {
        return data[0].getImages().getOriginal().getUrl();
    }


}

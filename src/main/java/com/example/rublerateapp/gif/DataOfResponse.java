package com.example.rublerateapp.gif;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// Этот класс и все классы в пэккедже gif нужны для сохранения
// нужной нам информации по gif файлам которую нам предоставляет
// Клиент для подключения к API gif файлов
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataOfResponse {
    private ImagesOfData images;

    public ImagesOfData getImages() {
        return images;
    }

    public void setImages(ImagesOfData images) {
        this.images = images;
    }
}

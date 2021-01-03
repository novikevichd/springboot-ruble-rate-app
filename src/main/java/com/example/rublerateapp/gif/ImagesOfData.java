package com.example.rublerateapp.gif;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// Этот класс и все классы в пэккедже gif нужны для сохранения
// нужной нам информации по gif файлам которую нам предоставляет
// Клиент для подключения к API gif файлов
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImagesOfData {
    private OriginalImageData downsized_medium;

    public OriginalImageData getOriginal() {
        return downsized_medium;
    }

    public void setOriginal(OriginalImageData original) {
        this.downsized_medium = original;
    }
}

package com.example.rublerateapp;

import com.example.rublerateapp.gif.GifResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

// Тесты для проверки того что Json файлы предоставляемые API gif файлов
// сохраняются в объектах GifResponse и сохраняют ссылки на gif файлы без ошибок
// Тестируем на 2х Json файлах сохраненных локально в test/resources - /rich-gif.json и /broke-gif.json
@JsonTest
public class TestingGifResponse {

    private JacksonTester<GifResponse> jacksonTester;

    @Test
    public void richGifResponseFileTest() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        JacksonTester.initFields(this, objectMapper);

        GifResponse richGiffResponse = this.jacksonTester.read("/rich-gif.json").getObject();

        // Тестируем метод по возвращению url gif файла
        Assertions.assertEquals("https://media0.giphy.com/media/SsTcO55LJDBsI/" +
                        "giphy.gif?cid=5775f765ub9wqs1ywzs1jpxjjk3bea8pglo8xp26iylhykr6&rid=giphy.gif",
                richGiffResponse.getUrl());

    }

    @Test
    public void brokeGifResponseFileTest() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        JacksonTester.initFields(this, objectMapper);

        GifResponse richGiffResponse = this.jacksonTester.read("/broke-gif.json").getObject();

        Assertions.assertEquals("https://media1.giphy.com/media/f7MO098FCipmq0eUpV/" +
                        "giphy.gif?cid=5775f765o6f4gp878s6vug2lfk91ni05rodqzhir4j71b536&rid=giphy.gif",
                richGiffResponse.getUrl());

    }


}

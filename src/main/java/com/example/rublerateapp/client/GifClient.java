package com.example.rublerateapp.client;

import com.example.rublerateapp.gif.GifResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/*
Feign клиент для подключения к API gif файлов
Используются два запроса:
1) Берет gif файлы из https://giphy.com/search/rich - getRichGif()
2) Берет gif файлы из https://giphy.com/search/broke - getBrokeGif()
 */
@FeignClient(url="${gif.api.url}", name = "Gif-Client")
public interface GifClient {

    @GetMapping("search?api_key=${gif.application.id}&q=rich&limit=1&offset={offsetInSearch}&rating=g&lang=en")
    public GifResponse getRichGif(@PathVariable int offsetInSearch);

    @GetMapping("search?api_key=${gif.application.id}&q=broke&limit=1&offset={offsetInSearch}&rating=g&lang=en")
    public GifResponse getBrokeGif(@PathVariable int offsetInSearch);

}

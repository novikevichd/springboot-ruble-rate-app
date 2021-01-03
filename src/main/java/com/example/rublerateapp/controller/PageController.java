package com.example.rublerateapp.controller;

import com.example.rublerateapp.client.GifClient;
import com.example.rublerateapp.client.RateClient;
import com.example.rublerateapp.rate.Rate;
import com.example.rublerateapp.rate.RateChangingBetweenTwoDates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Map;
import java.util.Random;

@Controller
public class PageController {

    // Клиент для подключения к API курса валют
    RateClient rateClient;

    // Клиент для подключения к API gif файлов
    GifClient gifClient;

    // Мапа валют (Ключ=Код валюты, Значение=Полное названия валюты)
    // Значения мапы заданы в файле application.properties пункт currencyMap.
    // Используется в отображении menu.html для вывода тех валют
    // по отношению к которым можно разчитать разницу курса валюты к Рублю
    Map<String, String> currencyMap;


    @Autowired
    public PageController(RateClient rateClient,
                          GifClient gifClient,
                          @Value("#{${currencyMap}}") Map<String, String> map) {
        this.rateClient = rateClient;
        this.gifClient = gifClient;
        this.currencyMap = map;
    }


    @GetMapping("/menu")
    public String menu(Model model) {

        // В строку будет сохранен Код валюты которая будет выбрана
        // из выпадающего списка в отображении menu.html
        String currencyCode = null;

        model.addAttribute("currencyCode", currencyCode);
        model.addAttribute("currencyMap", currencyMap);
        return "menu";
    }

    @PostMapping("/result")
    public String result(@RequestParam("currencyCode") String currencyCode,
                         Model model) {

        // С помощью клиента курса валют получаем всю информацию по
        // всем валютам на данный момент времени
        Rate todaysRateOfAllCurrencies = rateClient.getTodayRate();

        // Получаем всю информацию по всем валютам за вчера
        String yesterday = LocalDate.now().minusDays(1).toString();
        Rate yesterdayRateOfAllCurrencies = rateClient.getRateOfCustomDate(yesterday);

        // На основе данных за вчера и сегодня конструируем класс
        // который будет высчитывать изменения нужной нам валюты по отношению к рублю
        RateChangingBetweenTwoDates changingBetweenTwoDates =
                new RateChangingBetweenTwoDates(todaysRateOfAllCurrencies,
                                                yesterdayRateOfAllCurrencies);

        // Передав код выбранной валюты в метод класса, получаем разницу
        // в курсе валют за сегодня и вчера по отношению к рублю
        float difference =
                changingBetweenTwoDates.calculateChangeOfRating(currencyCode);

        // В строку будет сохранен url gif файла который будет выведен на страницу результата
        String gifUrl;
        // В строку будет сохранено сообщение про изменение курса выбранной валюты
        String message;

        // В зависимсти от значения разницы курсов в строку gifUrl сохраняется ссылка на случайный gif
        // из нужной категории (https://giphy.com/search/rich  или https://giphy.com/search/broke)
        // которую предоставляет клиент для подключения к API gif файлов.
        // Если расчитанная разница меньше нуля, значит курс выбранной валюты
        // по отношению к рублю за сегодня стал выше вчерашнего
        if (difference < 0 ) {
            gifUrl = gifClient.getRichGif(new Random().nextInt(10)).getUrl();
            message = "Курс выбранной валюты по отношению к рублю за сегодня стал ВЫШЕ вчерашнего";
        } else {
            gifUrl = gifClient.getBrokeGif(new Random().nextInt(10)).getUrl();
            if (difference > 0) message = "Курс выбранной валюты по отношению к рублю за сегодня стал НИЖЕ вчерашнего";
            else message = "Курс выбранной валюты по отношению к рублю за сегодня не изменился";
        }

        // В модель передеается Код и Полное название выбранной в Меню валюты
        model.addAttribute("currencyCode", currencyCode);
        String value = currencyMap.get(currencyCode);
        model.addAttribute("value",value);

        // Так же передается ссылка на gif файл
        // и сообшение отображающее изменение курса выбранной валюты
        model.addAttribute("gifUrl", gifUrl);
        model.addAttribute("message", message);

        return "result";
    }
}

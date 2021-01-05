package com.example.rublerateapp.rate;


/*
Класс который высчитывает изменения в курсе выбранной валюты по отношению к рублю.
Код выбранной валюты передается в метод calculateChangeOfRating(String key).
Так как API курса валют в бесплатной версии предоставляет только курсы всех валют по отношению к доллару,
изменения курса выбранной валюты к рублю приходится высчитывать через курс доллара.
Весь процесс расчета разницы курса будет сохранен в переменную calculatingProcessString и выведен в консоль.
 */
public class RateChangingBetweenTwoDates {
    private Rate todayRateOfAllCurrencies;
    private Rate yesterdayRateOfAllCurrencies;

    public RateChangingBetweenTwoDates(Rate todayRateOfAllCurrencies, Rate yesterdayRateOfAllCurrencies) {
        this.todayRateOfAllCurrencies = todayRateOfAllCurrencies;
        this.yesterdayRateOfAllCurrencies = yesterdayRateOfAllCurrencies;
    }

    public float calculateChangeOfRating (String key) {
        StringBuilder calculatingProcessString = new StringBuilder();

        float todayRateOfRubleToUSD = todayRateOfAllCurrencies.getRates().get("RUB");
        calculatingProcessString.append("Цена за доллар в рублях сегодня " + todayRateOfRubleToUSD + "\n");

        float yesterdayRateOfRubleToUSD = yesterdayRateOfAllCurrencies.getRates().get("RUB");
        calculatingProcessString.append("Цена за доллар в рублях вчера " + yesterdayRateOfRubleToUSD + "\n");

        float todayRateOfInputCurrency = todayRateOfAllCurrencies.getRates().get(key);
        calculatingProcessString.append("Цена за выбранную валюту в долларах сегодня " + todayRateOfInputCurrency + "\n");

        float yesterdayRateOfInputCurrency = yesterdayRateOfAllCurrencies.getRates().get(key);
        calculatingProcessString.append("Цена за выбранную валюту в долларах вчера " + yesterdayRateOfInputCurrency + "\n");

        float todayInputCurrencyToRuble = todayRateOfInputCurrency / todayRateOfRubleToUSD;
        calculatingProcessString.append("Курс выбранной валюты к 1 рублю сегодня " + todayInputCurrencyToRuble + "\n");

        float yesterdayInputCurrencyToRuble = yesterdayRateOfInputCurrency / yesterdayRateOfRubleToUSD;
        calculatingProcessString.append("Курс выбранной валюты к 1 рублю вчера " + yesterdayInputCurrencyToRuble + "\n");

        float answer = todayInputCurrencyToRuble - yesterdayInputCurrencyToRuble;
        calculatingProcessString.append("Разница курса выбранной валюты к рублю сегодня и вчера " + answer +"\n");

        System.out.println(calculatingProcessString.toString());

        return answer;
    }

}

package com.example.rublerateapp;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

// Тест контроллера
@SpringBootTest
@AutoConfigureMockMvc
public class TestingPageControllerForRequestMappings {

    @Autowired
    private MockMvc mockMvc;


    // Тест на возврат страницы меню при get запросе /map
    @Test
    public void shouldReturnMenuPage() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.get("/menu"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string(Matchers.containsString("Выберите валюту, курс которой выхотите сравнить с курсом Рубля")));
    }

    // Тест на возврат страницы результата, при post запросе с параметром кода валюты Арабских Эмиратов /result
    @Test
    public void shouldReturnDefaultMessage() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.post("/result").param("currencyCode", "AED"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string(Matchers.containsString("United Arab Emirates Dirham")));
    }


}

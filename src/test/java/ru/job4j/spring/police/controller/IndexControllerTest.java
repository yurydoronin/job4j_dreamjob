package ru.job4j.spring.police.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.job4j.spring.police.config.DataConfig;
import ru.job4j.spring.police.config.WebConfig;
import ru.job4j.spring.police.config.WebInit;
import ru.job4j.spring.police.model.Accident;
import ru.job4j.spring.police.service.AccidentService;

import static org.mockito.Mockito.verify;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Test.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 10.07.2020
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {WebInit.class, WebConfig.class, DataConfig.class})
@WebAppConfiguration
public class IndexControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AccidentService accidentService;

    @InjectMocks
    private IndexController index;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(index).build();
    }

    @Test
    public void returnIndexPageWithAllAccidents() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void returnAddAccidentPage() throws Exception {
        this.mockMvc.perform(get("/accident.do"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("accident"));
    }

    @Test
    public void saveAccidentAndRedirectToIndex() throws Exception {
        this.mockMvc.perform(post("/add.do")
                .param("name", "Новое нарушение"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Accident> argument = ArgumentCaptor.forClass(Accident.class);
        verify(accidentService).save(argument.capture());
        assertEquals("name", "Новое нарушение", argument.getValue().getName());
    }

    @Test
    public void returnEditPage() throws Exception {
        this.mockMvc.perform(get("/edit.do").param("id", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("edit"));
    }

    @Test
    public void deleteAccidentAndRedirectToIndex() throws Exception {
        this.mockMvc.perform(post("/delete.do")
                .param("name", "Новое нарушение"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Accident> argument = ArgumentCaptor.forClass(Accident.class);
        verify(accidentService).delete(argument.capture());
        assertEquals("name", "Новое нарушение", argument.getValue().getName());
    }
}
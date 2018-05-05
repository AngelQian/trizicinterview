package trizic.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import trizic.model.Advisor;
import trizic.repo.AdvisorRepo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

/**
 * Created by AngelQian on 5/4/18.
 */
@WebMvcTest(AdvisorController.class)
public class AdvisorControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private AdvisorRepo advisorRepo;

    @Test
    public void addOne() throws Exception {
        //given
        Advisor firstArrival = new Advisor();
        firstArrival.setName("Angel1");
        firstArrival.setDescription("abc");
        firstArrival.setCashHoldingPercentage(20);
        firstArrival.setDriftPercentage(80);
        firstArrival.setModelType(Advisor.ModelType.QUALIFIED.toString());
        firstArrival.setRebalanceFrequency(Advisor.RebalanceFrequency.ANNUAL.toString());
        firstArrival.setAdvisorId("3");

        RequestBuilder requestBuilder = put("/trizic/add", firstArrival);
        ResultActions rt = mvc.perform(requestBuilder);
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", is(1)))
//                .andExpect(jsonPath("$.name", is("John")))
//                .andExpect(jsonPath("$.age", is(25)));
        System.out.println("abc");
    }

    @Test
    public void getAdvisors() throws Exception {

    }

}
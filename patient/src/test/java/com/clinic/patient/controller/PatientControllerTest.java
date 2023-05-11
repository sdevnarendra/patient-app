package com.clinic.patient.controller;

import com.clinic.patient.PatientMasterApplication;
import com.clinic.patient.dto.QuickSearchDTO;
import com.clinic.patient.model.Gender;
import com.clinic.patient.model.Patient;
import com.clinic.patient.model.State;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@AutoConfigureMockMvc
@SpringBootTest(classes = {PatientMasterApplication.class})
public class PatientControllerTest {


    @Autowired
    MockMvc mockMvc;

    Long capturedId = 0l;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    void onTestCreate() throws Exception {
        String api = "/patient";

        Patient patient = mockPatient();
        patient.setLastName("Kennedy");

        MvcResult result = mockMvc.perform(post(api)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(patient)))
                .andDo(MockMvcResultHandlers.print()).andReturn();

        Patient patientResult = mapper.readValue(result.getResponse().getContentAsString(), Patient.class);

        capturedId = patientResult.getId();
    }

    @Test
    void onTestList() throws Exception {

        String api = "/patient/list";

        QuickSearchDTO quickSearchDTO = new QuickSearchDTO();
        quickSearchDTO.setSearch("john");

        mockMvc.perform(post(api)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(quickSearchDTO)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void onTestUpdate() throws Exception {
        String api = "/patient";

        Patient patient = mockPatient();
        patient.setLastName("Kennedy");

        mockMvc.perform(post(api)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(patient)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void onTestFindOne() throws Exception {
        String api = "/patient/" + capturedId;

        mockMvc.perform(get(api)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void onTestDelete() throws Exception {
        String api = "/patient/" + capturedId;

        mockMvc.perform(delete(api)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print());
    }


    Patient mockPatient() {
        return new Patient("John",
                "Doe",
                new Date(),
                Gender.Male,
                "New Street",
                "New Suburb",
                State.NSW,
                "1234",
                "+61829832200");
    }
}

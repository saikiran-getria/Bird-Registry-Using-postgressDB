package com.ria.birdService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ria.birdService.config.DbContainer;
import com.ria.birdService.model.dto.BirdDTO;
import org.junit.ClassRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
@ActiveProfiles("test")
public class DeleteBirdByIDTests {
    @ClassRule
    public static PostgreSQLContainer<DbContainer> postgreSQLContainer = DbContainer.getInstance();

    private MockMvc mockMvc;
    @Autowired
    protected WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testDeleteBirdById_removedSuccessfully() throws Exception {
        BirdDTO birdDTO = new BirdDTO("1",
                "birdMockName",
                "birdMockFamily", Arrays.asList("Asia", "Africa"),
                "2021-10-10",
                null);
        ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();
        mockMvc.perform(MockMvcRequestBuilders.post("/birds")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(birdDTO)))
                .andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.delete("/birds/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent()).andReturn();
    }

    @Test
    public void testDeleteByID_throwsException() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/birds/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}

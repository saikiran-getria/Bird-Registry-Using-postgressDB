package com.ria.birdService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ria.birdService.config.DbContainer;
import com.ria.birdService.model.Bird;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
@ActiveProfiles("test")
public class GetBirdByIDTests {
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
    public void testGetBirdById_returnBird_VisibilityFalse() throws Exception{
        BirdDTO birdDTO = new BirdDTO("1",
                "birdMockName",
                "birdMockFamily", Arrays.asList("Asia", "Africa"),
                "2021-10-10",
                false);
        ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();
        mockMvc.perform(MockMvcRequestBuilders.post("/birds")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(birdDTO)))
                .andExpect(status().isOk());
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/birds/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        assertThat(result.getResponse().getContentAsString()).isNotNull();
        assertThat(result.getResponse().getContentAsString()).isEqualTo(mapper.writeValueAsString(birdDTO));
    }

    @Test
    public void testGetBirdById_returnBird_VisibilityTrue() throws Exception{
        BirdDTO birdDTO = new BirdDTO("1",
                "birdMockName",
                "birdMockFamily", Arrays.asList("Asia", "Africa"),
                "2021-10-10",
                true);
        ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();
        mockMvc.perform(MockMvcRequestBuilders.post("/birds")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(birdDTO)))
                .andExpect(status().isOk());
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/birds/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        assertThat(result.getResponse().getContentAsString()).isNotNull();
        assertThat(result.getResponse().getContentAsString()).isEqualTo(mapper.writeValueAsString(birdDTO));
    }

    @Test
    public void testGetBirdByID_throwsNotFoundException() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/birds/invalidID")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
    }

}

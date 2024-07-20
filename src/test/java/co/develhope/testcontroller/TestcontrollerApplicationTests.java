package co.develhope.testcontroller;

import co.develhope.testcontroller.controllers.HomeController;
import co.develhope.testcontroller.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class TestcontrollerApplicationTests {

    @LocalServerPort
    private Integer port;

    @Autowired
    private HomeController controller;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    void restTemplateTest() {
        String output = this.restTemplate.getForObject("http://127.0.0.1:" + port + "/", String.class);
        assertThat(output).contains("Hello World!");

        User user = this.restTemplate.getForObject("http://127.0.0.1:" + port + "/users", User.class);
        assertThat(user.getFirstName()).contains("Albano");
        assertThat(user.getLastName()).isEqualTo("Persechino");
    }

    @Test
    public void testMockMvc() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello World")));

        this.mockMvc.perform(get("/users")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firtName").value("Albano"))
                .andExpect(jsonPath("$.lastName").value("Persechino"));
    }
}

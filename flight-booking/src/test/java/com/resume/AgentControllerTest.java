//package com.resume;
//
//import com.resume.controller.AgentController;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//public class AgentControllerTest {
//
//    private MockMvc mockMvc;
//
//    @BeforeEach
//    void setup() {
//        mockMvc = MockMvcBuilders.standaloneSetup(new AgentController()).build();
//    }
//
//    @Test
//    void statusEndpoint_returnsAgentIsRunning() throws Exception {
//        mockMvc.perform(get("/agent/status"))
//                .andExpect(status().isOk())
//                .andExpect(content().string("Agent is running"));
//    }
//}

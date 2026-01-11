package com.resume.copilot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.chat.client.ChatClient;

@SpringBootTest(properties = {"spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration",
        "spring.ai.vectorstore.type=none"})
class CopilotApplicationTests {

    @MockBean
    VectorStore vectorStore;

    @MockBean
    ChatClient.Builder chatClientBuilder;

    @MockBean
    ChatClient chatClient;

	@Test
	void contextLoads() {
	}

}

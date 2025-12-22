package com.resume.copilot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {"spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration",
        "spring.ai.vectorstore.type=none"})
class CopilotApplicationTests {

	@Test
	void contextLoads() {
	}

}

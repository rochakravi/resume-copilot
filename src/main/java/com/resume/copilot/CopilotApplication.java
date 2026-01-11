package com.resume.copilot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ai.model.chat.client.autoconfigure.ChatClientAutoConfiguration;

@SpringBootApplication(scanBasePackages = "com.resume", exclude = {ChatClientAutoConfiguration.class})
public class CopilotApplication {

	public static void main(String[] args) {
		SpringApplication.run(CopilotApplication.class, args);
	}

}

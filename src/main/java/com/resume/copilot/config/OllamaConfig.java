//package com.resume.copilot.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.ai.chat.model.ChatModel;
//import org.springframework.ai.model.ollama.OllamaChatModel;
//import org.springframework.web.client.RestClient;
//
//@Configuration
//public class OllamaConfig {
//
//    @Bean
//    public ChatModel chatModel() {
//        RestClient restClient = RestClient.builder()
//                .baseUrl("http://localhost:11434")
//                .build();
//        return new OllamaChatModel(restClient);   // single ChatModel bean
//    }
//}

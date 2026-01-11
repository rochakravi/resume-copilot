package org.springframework.ai.chat.client;

// Minimal stub to satisfy compilation in this workspace. Real implementation comes from Spring AI.
public class ChatClient {

    public static class Builder {
        public ChatClient build() {
            return new ChatClient();
        }
    }

    public PromptBuilder prompt(String prompt) {
        return new PromptBuilder(prompt);
    }

    public PromptBuilder prompt() {
        return new PromptBuilder(null);
    }

    public static class PromptBuilder {
        private final String initialPrompt;
        private String system;
        private String user;

        public PromptBuilder(String prompt) { this.initialPrompt = prompt; }

        public PromptBuilder system(String system) {
            this.system = system;
            return this;
        }

        public PromptBuilder user(String user) {
            this.user = user;
            return this;
        }

        public Response call() {
            // For the stub, return an empty JSON object or compose a simple response
            String content = "{}";
            return new Response(content);
        }
    }

    public static class Response {
        private final String content;
        public Response(String content) { this.content = content; }
        public String content() { return content; }
    }
}

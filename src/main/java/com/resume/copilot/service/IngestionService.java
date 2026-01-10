package com.resume.copilot.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.resume.copilot.dto.AskMeAnythingResponse;
import com.resume.copilot.dto.ResumeQueryRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class IngestionService {

    private static final Logger logger = LoggerFactory.getLogger(IngestionService.class);

    private final VectorStore vectorStore;
    private final ChatClient chatClient;
    private final ObjectMapper objectMapper;


    public IngestionService(VectorStore vectorStore, ChatClient.Builder builder, ObjectMapper objectMapper) {
        this.vectorStore = vectorStore;
        this.chatClient = builder.build();
        this.objectMapper = objectMapper;
    }


    private Map<String, Object> cleanMetadata(Map<String, Object> metadata) {
        if (metadata == null) {
            return Map.of();
        }
        return metadata.entrySet().stream()
                .filter(e -> e.getKey() != null && e.getValue() != null)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));
    }


    public void ingestResume(
            String candidateId,
            MultipartFile file
//            String filename,
//            String role,
//            Integer experience,
//            String skills
    ) throws IOException {

        Resource resource = new InputStreamResource(file.getInputStream());

        PagePdfDocumentReader reader = new PagePdfDocumentReader(resource);
        List<Document> docs = reader.get();

        List<Document> safeDocs = docs.stream()
                .filter(d -> d.getText() != null && !d.getText().isBlank())
                .map(d -> Document.builder()
                        .text(d.getText())
                        .metadata(cleanMetadata(d.getMetadata()))
                        .build())
                .toList();

        vectorStore.add(safeDocs);
    }

    public void ingestFacts(String candidateId, MultipartFile file) throws IOException {

        String text = new String(file.getBytes(), StandardCharsets.UTF_8);

        if (text.isBlank()) {
            throw new IllegalArgumentException("Fact sheet is empty");
        }

        Document document = Document.builder()
                .text(text)
                .metadata(Map.of(
                        "candidateId", candidateId,
                        "source", "fact_sheet",
                        "priority", "high"
                ))
                .build();

        vectorStore.add(List.of(document));
    }


    public AskMeAnythingResponse askResume(ResumeQueryRequest request) throws JsonProcessingException {

        List<Document> docs = vectorStore.similaritySearch(request.question());
        logger.info("Found {} similar documents for question: {}", docs.size(), request.question());
//        if (docs.isEmpty()) {
//            return "No resume data found for this candidate.";
//        }

        String context = docs.stream()
                .map(Document::getText)
                .collect(Collectors.joining("\n"));

        String answer = chatClient
                .prompt()
//                .system("""
//                            You are a Resume Analysis AI.
//                            Answer ONLY using the provided resume content.
//                            If data is not present, say "Not mentioned in resume".
//                        """)
                .system("""
        You are a Resume Analysis AI helping a recruiter understand a candidate.
        Use ONLY the provided resume content.
        If the answer is not present, say "Not mentioned in resume" in the `answer` field.

        Respond STRICTLY as a JSON object with this schema:
        {
          "answer": "short natural-language answer for the chat bubble",
          "highlights": ["bullet 1", "bullet 2"],
          "meta": {
            "source": "resume",
            "confidence": 0.0 to 1.0
          }
        }
        Do not include any extra text outside the JSON.
        """)
                .user("""
                            Resume Content:
                            %s
                        
                            Question:
                            %s
                        """.formatted(context, request.question()))
                .call()
                .content();

        return objectMapper.readValue(answer, AskMeAnythingResponse.class);

       // return answer;
    }
}


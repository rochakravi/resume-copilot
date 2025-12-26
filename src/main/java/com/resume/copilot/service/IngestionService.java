package com.resume.copilot.service;

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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class IngestionService {

    private static final Logger logger = LoggerFactory.getLogger(IngestionService.class);

    private final VectorStore vectorStore;
    private final ChatClient chatClient;


    public IngestionService(VectorStore vectorStore, ChatClient.Builder builder) {
        this.vectorStore = vectorStore;
        this.chatClient = builder.build();
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

    public String askResume(ResumeQueryRequest request) {

        List<Document> docs = vectorStore.similaritySearch(request.question());
        logger.info("Found {} similar documents for question: {}", docs.size(), request.question());
        if (docs.isEmpty()) {
            return "No resume data found for this candidate.";
        }

        String context = docs.stream()
                .map(Document::getText)
                .collect(Collectors.joining("\n"));


        return chatClient
                .prompt()
                .system("""
                            You are a Resume Analysis AI.
                            Answer ONLY using the provided resume content.
                            If data is not present, say "Not mentioned in resume".
                        """)
                .user("""
                            Resume Content:
                            %s
                        
                            Question:
                            %s
                        """.formatted(context, request.question()))
                .call()
                .content();
    }
}


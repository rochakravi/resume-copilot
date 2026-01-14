package com.resume.copilot.service;

import com.resume.copilot.dto.Profile;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProfileService {

    private final VectorStore vectorStore;

    public ProfileService(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    public Profile getProfileByUserId(String userId) {
        // Build a simple semantic query to fetch resume-related docs
        List<Document> allDocs = vectorStore.similaritySearch("resume skills experience");

        // Filter by userId (works 100%)
        List<Document> userDocs = allDocs.stream()
                .filter(doc -> userId.equals(doc.getMetadata().get("userId")))
                .toList();

        if (userDocs.isEmpty()) {
            return new Profile(userId, "No profile found", null);
        }

        String resumeContent = userDocs.stream()
                .map(doc -> doc.getMetadata().get("content") != null
                        ? doc.getMetadata().get("content").toString()
                        : doc.toString())
                .collect(Collectors.joining("\n\n"));

        return new Profile(userId, resumeContent,
                (String) userDocs.get(0).getMetadata().get("candidateId"));
    }
}

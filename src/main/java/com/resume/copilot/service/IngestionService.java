package com.resume.copilot.service;

//import jakarta.annotation.Resource;

import com.resume.copilot.dto.RequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.core.io.Resource;

import java.util.Collections;
import java.util.List;

@Service
public class IngestionService {

    private static final Logger logger = LoggerFactory.getLogger(IngestionService.class);

    private VectorStore vectorStore;

    public IngestionService(@Autowired(required = false) VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    public void ingestPdf(Resource pdfResource) {
        // PagePdfDocumentReader expects a Resource; pass it directly
        List<Document> docs = new PagePdfDocumentReader(pdfResource).get();
        logger.info("ingestPDFDocs docs size: {}", docs.size());

        if (this.vectorStore == null) {
            logger.warn("VectorStore not configured - skipping vector persistence ({} pages)", docs.size());
            return;
        }

        this.vectorStore.add(docs);
        logger.info("ingestPDFDocs completed and saved to VectorStore");
    }

    public void ingestPdf(byte[] fileContent, String originalFilename, String ingestType) {
        Resource docSource = new ByteArrayResource(fileContent) {
            @Override
            public String getFilename() {
                return originalFilename;
            }
        };
        ingestPdf(docSource);
        logger.info("Ingestion completed successfully for file={}", originalFilename);
    }

    public List<Document> retrieveAnswer(RequestDTO requestDTO) {
        if (requestDTO == null || requestDTO.prompt() == null) {
            logger.warn("retrieveAnswer called with empty requestDTO or prompt");
            return Collections.emptyList();
        }

        if (this.vectorStore == null) {
            logger.warn("Attempted retrieveAnswer but VectorStore is not configured; returning empty result");
            return Collections.emptyList();
        }

        try {
            List<Document> docs = vectorStore.similaritySearch(requestDTO.prompt());
            return docs == null ? Collections.emptyList() : docs;
        } catch (Exception ex) {
            logger.error("Error during similaritySearch: {}", ex.getMessage(), ex);
            return Collections.emptyList();
        }
    }
}

package com.resume.copilot.config;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.pgvector.PgVectorStore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class VectorStoreConfig {

    private final EmbeddingModel embeddingModel;

    public VectorStoreConfig(EmbeddingModel embeddingModel) {
        this.embeddingModel = embeddingModel;
    }

    @Bean(name = "resumeVectorStore")
    @ConditionalOnProperty(prefix = "spring.ai.vectorstore", name = "type", havingValue = "pgvector")
    public PgVectorStore resumeTableVectorStore(JdbcTemplate jdbcTemplate) {
        return PgVectorStore.builder(jdbcTemplate, this.embeddingModel)
                .initializeSchema(true)
                .schemaName(PgVectorStore.DEFAULT_SCHEMA_NAME)
                .vectorTableName("resumedocs")
                .build();
    }
}

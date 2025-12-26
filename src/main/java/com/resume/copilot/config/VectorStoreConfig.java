//package com.resume.copilot.config;
//
//import org.springframework.ai.embedding.EmbeddingModel;
//import org.springframework.ai.embedding.EmbeddingClient;
//import org.springframework.ai.vectorstore.VectorStore;
//import org.springframework.ai.vectorstore.PgVectorStore;
////import org.springframework.ai.vectorstore.pgvector.PgVectorStore;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//@Configuration
//public class VectorStoreConfig {
//
//    private final EmbeddingModel embeddingModel;
//
////    public VectorStoreConfig(EmbeddingModel embeddingModel) {
////        this.embeddingModel = embeddingModel;
////    }
//
//    @Bean
//    VectorStore pgVectorStore(JdbcTemplate jdbcTemplate,
//                              @Qualifier("ollamaEmbeddingClient") EmbeddingClient embeddingClient){
//        return new PgVectorStore(jdbcTemplate, embeddingClient);
//    }
//
//    public VectorStoreConfig(EmbeddingModel embeddingModel) {
//        this.embeddingModel = embeddingModel;
//    }
//
//
//   // PgVectorStore when enabled via properties
//   @Bean(name = "qaVectorStore")
//    @ConditionalOnProperty(prefix = "spring.ai.vectorstore", name = "type", havingValue = "pgvector")
//    public PgVectorStore resumeTableVectorStore(JdbcTemplate jdbcTemplate) {
//        // Use an explicit dimension to avoid contacting the embedding model at startup
//        //final int explicitDimensions = 1536; // match spring.ai.vectorstore.pgvector.dimensions property
//
//        return PgVectorStore.builder(jdbcTemplate, this.embeddingModel)
//                .initializeSchema(true)
//                .schemaName(PgVectorStore.DEFAULT_SCHEMA_NAME)
//                .vectorTableName("resumedocs")
//              ///  .dimensions(explicitDimensions)
//                .build();
//    }
//
////    // Fallback no-op VectorStore when pgvector isn't configured
////    @Bean(name = "qaVectorStore")
////    @ConditionalOnMissingBean(name = "qaVectorStore")
////    public VectorStore fallbackVectorStore() {
////        return new VectorStore() {
////            @Override
////            public void add(List<Document> documents) {
////                // no-op fallback
////            }
////
////            @Override
////            public List<Document> similaritySearch(String query) {
////                return Collections.emptyList();
////            }
////        };
////    }
//}

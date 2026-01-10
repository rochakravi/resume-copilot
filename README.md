# resume-copilot

# How to run locally

## Prerequisites
  # pgvector
  # Ollama

    - run pgvector using docker compose
        docker compose up -d
        # using 5440 port for pgvector, which needs to be set in the .env file as PGVECTOR_PORT
    - run ollama models
        ollama pull llama3.1 // ollama pull nomic-embed-text


    - for running database locally
          psql "postgresql://postgres:admin@localhost:5440/docs"

## Ollama

ollama list
NAME                       ID              SIZE      MODIFIED     
llama3.1:latest            46e0c10c039e    4.9 GB    10 days ago     
nomic-embed-text:latest    0a109f422b47    274 MB    12 days ago     
llama3:latest              365c0bd3c000    4.7 GB    4 months ago   
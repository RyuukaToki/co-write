package com.example.yjs_endpoint.model.DTO;

import lombok.Data;

@Data
public class KnowledgeBaseDTO {
    private Long id;

    private String name;

    private String ownerId;

    private String coverUrl;
}

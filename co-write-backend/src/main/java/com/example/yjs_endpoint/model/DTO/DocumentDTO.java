package com.example.yjs_endpoint.model.DTO;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class DocumentDTO {
    @Id
    private String id;

    private String knowledgeBaseId;

    private String name;

    private String description;

    private String color;

    private byte[] content; // 存储Tiptap JSON

    private Integer currentVersion;
}
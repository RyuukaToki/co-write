package com.example.yjs_endpoint.model.PO;

import com.example.yjs_endpoint.model.DTO.KnowledgeBaseDTO;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

import java.util.Date;

@Data
public class Document {
    private String id;

    private String title;

    private Byte[] content;

    private Integer currentVersion;

    private KnowledgeBaseDTO knowledgeBase;

    private Date createdAt;

    private Date updatedAt;

}

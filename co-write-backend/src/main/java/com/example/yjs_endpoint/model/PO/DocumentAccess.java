package com.example.yjs_endpoint.model.PO;

import com.example.yjs_endpoint.model.DTO.KnowledgeBaseDTO;
import lombok.Data;

import java.util.Date;

@Data
public class DocumentAccess {
    private String id;

    private String userId;

    private String documentId;

    private Date createdAt;

    private Date updatedAt;
}

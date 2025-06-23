package com.example.yjs_endpoint.service;

import com.example.yjs_endpoint.model.DTO.KnowledgeBaseDTO;
import com.example.yjs_endpoint.model.PO.KnowledgeBase;

import java.util.List;

public interface KnowledgeBaseService {
    KnowledgeBase createKnowledgeBase(KnowledgeBaseDTO knowledgeBaseDTO);
    List<KnowledgeBase> listByUser(String userId);
    KnowledgeBase updateKnowledgeBase(KnowledgeBaseDTO knowledgeBaseDTO);
    Boolean deleteKnowledgeBaseById(String userId);
}

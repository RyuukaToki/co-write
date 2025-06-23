package com.example.yjs_endpoint.service;

import com.example.yjs_endpoint.model.DTO.DocumentDTO;
import com.example.yjs_endpoint.model.PO.Document;
import com.example.yjs_endpoint.model.VO.RecentDocumentVO;

import java.util.List;

public interface DocumentService {
    Document getDocument(String id);
    Document createDocument(DocumentDTO dto);
    Document updateDocument(String id, DocumentDTO dto);
    boolean deleteDocument(String id);

    void recordAccess(String userId, String docId);

    List<RecentDocumentVO> getRecentDocuments(String userId);
}

package com.example.yjs_endpoint.controller;

import com.example.yjs_endpoint.model.DTO.DocumentDTO;
import com.example.yjs_endpoint.model.PO.Document;
import com.example.yjs_endpoint.model.VO.RecentDocumentVO;
import com.example.yjs_endpoint.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("docs")

public class DocumentController {
    @Autowired
    private DocumentService documentService;

    @GetMapping("/{docId}")
    public ResponseEntity<Document> getDocument(@PathVariable("docId") String docId,
                                                @RequestHeader("X-User-Id") String userId) {
        // 记录访问（在返回文档前调用）
        documentService.recordAccess(userId, docId);
        return ResponseEntity.ok(documentService.getDocument(docId));
    }

    @PostMapping
    public ResponseEntity<Document> createDocument(@RequestBody DocumentDTO documentDTO) {
        return ResponseEntity.ok(documentService.createDocument(documentDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Document> updateDocument(@PathVariable("id") String id,
                                                   @RequestBody DocumentDTO documentDTO) {
        return ResponseEntity.ok(documentService.updateDocument(id, documentDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteDocument(@PathVariable("id") String id) {
        return ResponseEntity.ok(documentService.deleteDocument(id)) ;
    }

    @GetMapping("/recent/{userId}")
    public ResponseEntity<List<RecentDocumentVO>> getRecentDocuments(@PathVariable("userId") String userId) {
        return ResponseEntity.ok(documentService.getRecentDocuments(userId));
    }
}

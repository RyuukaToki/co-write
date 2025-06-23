package com.example.yjs_endpoint.controller;

import com.example.yjs_endpoint.model.DTO.KnowledgeBaseDTO;
import com.example.yjs_endpoint.model.PO.KnowledgeBase;
import com.example.yjs_endpoint.service.KnowledgeBaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kbs")
@RequiredArgsConstructor
public class KnowledgeBaseController {
    @Autowired
    private KnowledgeBaseService kbService;

    @GetMapping("/{id}")
    public ResponseEntity<List<KnowledgeBase>> list(@PathVariable("id") String userId) {
        return ResponseEntity.ok(kbService.listByUser(userId));
    }

    @PostMapping
    public ResponseEntity<KnowledgeBase> create(@RequestBody KnowledgeBaseDTO knowledgeBaseDTO) {
        return ResponseEntity.ok(kbService.createKnowledgeBase(knowledgeBaseDTO));
    }

    @PutMapping
    public ResponseEntity<KnowledgeBase> update(@RequestBody KnowledgeBaseDTO knowledgeBaseDTO) {
        return ResponseEntity.ok(kbService.updateKnowledgeBase(knowledgeBaseDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") String id) {
        return ResponseEntity.ok(kbService.deleteKnowledgeBaseById(id));
    }
}

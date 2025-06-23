package com.example.yjs_endpoint.service.impl;

import com.example.yjs_endpoint.mapper.DocumentAccessMapper;
import com.example.yjs_endpoint.mapper.DocumentMapper;
import com.example.yjs_endpoint.model.DTO.DocumentDTO;
import com.example.yjs_endpoint.model.PO.Document;
import com.example.yjs_endpoint.model.VO.RecentDocumentVO;
import com.example.yjs_endpoint.service.DocumentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {
    @Autowired
    private DocumentMapper documentMapper;

    @Autowired
    private DocumentAccessMapper accessMapper;

    @Override
    public Document getDocument(String id) {
        return documentMapper.selectByPrimaryKey(id);
    }

    @Override
    public Document createDocument(DocumentDTO dto) {
        Document document = new Document();
        BeanUtils.copyProperties(dto, document);
        documentMapper.insert(document);
        return document;
    }

    @Override
    public Document updateDocument(String id, DocumentDTO documentDTO){
        Document document = new Document();
        BeanUtils.copyProperties(documentDTO, document);
        document.setId(id);
        documentMapper.updateByPrimaryKeySelective(document);
        return document;
    }

    @Override
    public boolean deleteDocument(String id) {
        Document doc = documentMapper.selectByPrimaryKey(id);
        if(doc != null){
            return false;
        }
        documentMapper.deleteByPrimaryKey(id);
        return true;
    }

    @Override
    public void recordAccess(String userId, String docId) {
        accessMapper.upsertAccess(userId, docId);
    }

    @Override
    public List<RecentDocumentVO> getRecentDocuments(String userId) {
        return accessMapper.selectRecentByUser(userId, 10);
    }
}

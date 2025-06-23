package com.example.yjs_endpoint.service.impl;

import com.example.yjs_endpoint.mapper.KnowledgeBaseMapper;
import com.example.yjs_endpoint.model.DTO.KnowledgeBaseDTO;
import com.example.yjs_endpoint.model.PO.KnowledgeBase;
import com.example.yjs_endpoint.service.KnowledgeBaseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class KnowledgeBaseServiceImpl implements KnowledgeBaseService {
    @Autowired
    private KnowledgeBaseMapper knowledgeBaseMapper;

    @Override
    public KnowledgeBase createKnowledgeBase(KnowledgeBaseDTO knowledgeBaseDTO){
        KnowledgeBase knowledgeBase = new KnowledgeBase();
        BeanUtils.copyProperties(knowledgeBaseDTO,knowledgeBase);
        knowledgeBase.setCreatedAt(new Date());
        knowledgeBase.setUpdatedAt(new Date());
        knowledgeBaseMapper.insert(knowledgeBase);
        return knowledgeBaseMapper.selectKbByName(knowledgeBase.getName(), knowledgeBase.getOwnerId());
    }

    @Override
    public List<KnowledgeBase> listByUser(String userId){
        return knowledgeBaseMapper.selectAccessibleByUser(userId);
    }

    @Override
    public KnowledgeBase updateKnowledgeBase(KnowledgeBaseDTO knowledgeBaseDTO){
        KnowledgeBase knowledgeBase = knowledgeBaseMapper.selectByPrimaryKey(knowledgeBaseDTO.getId());
        BeanUtils.copyProperties(knowledgeBaseDTO, knowledgeBase);
        knowledgeBase.setUpdatedAt(new Date());
        knowledgeBaseMapper.updateByPrimaryKeySelective(knowledgeBase);
        return knowledgeBase;
    }

    @Override
    public Boolean deleteKnowledgeBaseById(String id){
        KnowledgeBase knowledgeBase = knowledgeBaseMapper.selectByPrimaryKey(id);
        if(knowledgeBase==null){
            return false;
        }
        knowledgeBaseMapper.deleteByPrimaryKey(knowledgeBase.getId());
        return true;
    }
}

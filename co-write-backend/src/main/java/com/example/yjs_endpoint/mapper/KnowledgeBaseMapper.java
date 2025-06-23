package com.example.yjs_endpoint.mapper;

import com.example.yjs_endpoint.model.PO.KnowledgeBase;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

@Mapper
public interface KnowledgeBaseMapper extends tk.mybatis.mapper.common.Mapper<KnowledgeBase>, MySqlMapper<KnowledgeBase> {
    @Select("SELECT kb.* FROM knowledge_base kb " + "WHERE kb.owner_id = #{userId}")
    List<KnowledgeBase> selectAccessibleByUser(@Param("userId") String userId);

    @Select("SELECT kb.* FROM knowledge_base kb " + "WHERE kb.name = #{name} AND kb.owner_id = #{userId}")
    KnowledgeBase selectKbByName(@Param("name") String name, @Param("userId") String userId);
}

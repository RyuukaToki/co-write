package com.example.yjs_endpoint.mapper;

import com.example.yjs_endpoint.model.PO.Document;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.MySqlMapper;

@Mapper
public interface DocumentMapper extends tk.mybatis.mapper.common.Mapper<Document>, MySqlMapper<Document> {
    @Update("UPDATE documents SET content = #{content}, version = version + 1 " +
            "WHERE id = #{id} AND version = #{expectedVersion}")
    int updateWithVersion(String id, JsonNode content, int expectedVersion);
}

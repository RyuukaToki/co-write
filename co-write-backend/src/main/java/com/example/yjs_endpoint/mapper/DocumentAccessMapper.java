package com.example.yjs_endpoint.mapper;

import com.example.yjs_endpoint.model.PO.DocumentAccess;
import com.example.yjs_endpoint.model.VO.RecentDocumentVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

@Mapper
public interface DocumentAccessMapper extends tk.mybatis.mapper.common.Mapper<DocumentAccess>, MySqlMapper<DocumentAccess> {
    @Select("SELECT d.id, d.name, da.access_time AS accessTime"+
        "FROM document_access da"+
        "JOIN documents d ON da.document_id = d.id"+
        "WHERE da.user_id = #{userId}"+
        "ORDER BY da.access_time DESC"+
        "LIMIT #{limit}")
    List<RecentDocumentVO> selectRecentByUser(
            @Param("userId") String userId,
            @Param("limit") int limit
    );

    @Insert("INSERT INTO document_access (user_id, document_id, access_time) " +
            "VALUES (#{userId}, #{docId}, NOW()) " +
            "ON DUPLICATE KEY UPDATE access_time = NOW()")
    void upsertAccess(@Param("userId") String userId, @Param("docId") String docId);
}

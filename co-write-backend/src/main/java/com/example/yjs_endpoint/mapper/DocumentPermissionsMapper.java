package com.example.yjs_endpoint.mapper;

import com.example.yjs_endpoint.model.PO.DocumentPermissions;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

@Mapper
public interface DocumentPermissionsMapper extends tk.mybatis.mapper.common.Mapper<DocumentPermissions>, MySqlMapper<DocumentPermissions> {
}

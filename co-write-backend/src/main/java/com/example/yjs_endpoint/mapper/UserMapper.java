package com.example.yjs_endpoint.mapper;

import com.example.yjs_endpoint.model.PO.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.MySqlMapper;

@Mapper
public interface UserMapper extends tk.mybatis.mapper.common.Mapper<User>, MySqlMapper<User> {
    @Select("SELECT * FROM user " + "WHERE username = #{username}")
    User selectByUsername(@Param("username") String username);

    @Select("SELECT COUNT(1) > 0 FROM user WHERE username = #{username}")
    boolean existsByUsername(@Param("username") String username);
}

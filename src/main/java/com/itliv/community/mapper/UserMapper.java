package com.itliv.community.mapper;

import com.itliv.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {

    @Insert("insert into user (name, account_id, token, gmt_create, gmt_modified, avatar_url) values (#{name}, #{accountId}, #{token}, #{gmtCreate}, #{gmtModified}, #{avatarUrl})")
    public void insertUser(User user);

    @Select("select * from user where token = #{token}")
    List<User> findUserByToken(String token);

    @Select("select * from user where account_id = #{accountId}")
    User findUserByAccountId(String accountId);

    @Update("update user set token = #{token}, gmt_modified = #{gmtModified}, name = #{name}, avatar_url = #{avatarUrl}")
    void updateUser(User user);
}

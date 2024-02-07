package org.databasetest.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.databasetest.model.UserInfo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface UserMapper {
//    @Select ("SELECT id, username, password FROM user_info WHERE username = #{username}")
    UserInfo findByUsername(@Param ( "username" ) String username);

    void insert(UserInfo userInfo);

    List<UserInfo> findAllUserInfo();
}

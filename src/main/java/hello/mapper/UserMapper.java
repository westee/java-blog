package hello.mapper;

import hello.entiry.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user WHERE username = #{id}")
    User findUserById(@Param("id") int id);

    @Select("select * from user where username = #{username}")
    User findUserByUsername(@Param("username") String username);

    @Select("insert into user(username, encrypted_password, created_at, updated_at) " +
            "values(#{username}, #{password}, now(), now())")
    void save(@Param("username") String username, @Param("password") String password);
}

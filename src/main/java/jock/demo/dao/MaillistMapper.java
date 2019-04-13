package jock.demo.dao;


import jock.demo.model.Maillist;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface MaillistMapper {
    @Delete({
            "delete from maillist",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);


    @Delete({
            "delete from maillist",
            "where email = #{email,jdbcType=VARCHAR}"
    })
    int deleteByEmail(String email);

    @Insert({
            "insert into maillist (id, email, ",
            "create_date)",
            "values (#{id,jdbcType=INTEGER}, #{email,jdbcType=VARCHAR}, ",
            "#{createDate,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Integer.class)
    int insert(Maillist record);

    @Select({
            "select",
            "id, email, create_date",
            "from maillist",
            "where email = #{email,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "email", property = "email", jdbcType = JdbcType.VARCHAR),
            @Result(column = "create_date", property = "createDate", jdbcType = JdbcType.TIMESTAMP)
    })
    Maillist selectByMail(String mail);

    @Select({
            "select",
            "id, email, create_date",
            "from maillist"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "email", property = "email", jdbcType = JdbcType.VARCHAR),
            @Result(column = "create_date", property = "createDate", jdbcType = JdbcType.TIMESTAMP)
    })
    List<Maillist> selectAll();

    @Update({
            "update maillist",
            "set email = #{email,jdbcType=VARCHAR},",
            "create_date = #{createDate,jdbcType=TIMESTAMP}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Maillist record);
}

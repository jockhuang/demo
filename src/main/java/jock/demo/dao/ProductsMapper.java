package jock.demo.dao;


import jock.demo.model.Products;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface ProductsMapper {
    @Delete({
            "delete from products",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
            "insert into products (id, `name`, ",
            "description, is_release, ",
            "create_date, update_date, ",
            "is_delete)",
            "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
            "#{description,jdbcType=VARCHAR}, #{isRelease,jdbcType=BIT}, ",
            "#{createDate,jdbcType=TIMESTAMP}, #{updateDate,jdbcType=TIMESTAMP}, ",
            "#{isDelete,jdbcType=BIT})"
    })
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = true, resultType = Integer.class)
    int insert(Products record);

    @Select({
            "select",
            "id, `name`, description, is_release, create_date, update_date, is_delete",
            "from products",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
            @Result(column = "is_release", property = "isRelease", jdbcType = JdbcType.BIT),
            @Result(column = "create_date", property = "createDate", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_date", property = "updateDate", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "is_delete", property = "isDelete", jdbcType = JdbcType.BIT)
    })
    Products selectByPrimaryKey(Integer id);


    @Select({
            "select",
            "id, `name`, description, is_release, create_date, update_date, is_delete",
            "from products",
            "where name = #{name,jdbcType=VARCHAR} limit 1"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
            @Result(column = "is_release", property = "isRelease", jdbcType = JdbcType.BIT),
            @Result(column = "create_date", property = "createDate", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_date", property = "updateDate", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "is_delete", property = "isDelete", jdbcType = JdbcType.BIT)
    })
    Products selectByPrimaryName(String name);


    @Select({
            "select",
            "id, `name`, description, is_release, create_date, update_date, is_delete",
            "from products"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
            @Result(column = "is_release", property = "isRelease", jdbcType = JdbcType.BIT),
            @Result(column = "create_date", property = "createDate", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_date", property = "updateDate", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "is_delete", property = "isDelete", jdbcType = JdbcType.BIT)
    })
    List<Products> selectAll();

    @Select({
            "select",
            "id, `name`, description, is_release, create_date, update_date, is_delete",
            "from products",
            "where is_delete=0 and is_release = #{isRelease,jdbcType=BIT}",

    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
            @Result(column = "is_release", property = "isRelease", jdbcType = JdbcType.BIT),
            @Result(column = "create_date", property = "createDate", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_date", property = "updateDate", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "is_delete", property = "isDelete", jdbcType = JdbcType.BIT)
    })
    List<Products> selectReleased(Boolean isRelease);


    @Update({
            "update products",
            "set `name` = #{name,jdbcType=VARCHAR},",
            "description = #{description,jdbcType=VARCHAR},",
            "is_release = #{isRelease,jdbcType=BIT},",
            "create_date = #{createDate,jdbcType=TIMESTAMP},",
            "update_date = #{updateDate,jdbcType=TIMESTAMP},",
            "is_delete = #{isDelete,jdbcType=BIT}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Products record);
}
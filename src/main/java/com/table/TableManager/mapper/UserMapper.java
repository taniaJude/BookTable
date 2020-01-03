package com.table.TableManager.mapper;


//used for maping the collumns in the user table with
//the fields in the User class

import org.springframework.jdbc.core.RowMapper;
import com.table.TableManager.modul.User;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    public static final String BASE_SQL//
    ="Select u.id,u.name,u.password FROM Users u";
   // @Override
    public User mapRow(ResultSet rs,int rowNum) throws SQLException
    {
        Long id=rs.getLong("id");
        String name=rs.getString("name");
        String password=rs.getString("password");
        return new User(id,name,password);
    }
}
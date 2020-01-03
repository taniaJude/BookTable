package com.table.TableManager.dao;


//data access object classes are used to access to a db
//used to manipulate with the User table


import com.table.TableManager.mapper.UserMapper;
import com.table.TableManager.modul.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;


@Repository
@Transactional
public class UserDAO extends JdbcDaoSupport {
    @Autowired
    public UserDAO(DataSource dataSource)
    {
        this.setDataSource(dataSource);
    }
    public User findUserAccount(String username)
    {
        //select..from User u where u.User_username=?
        String sql= UserMapper.BASE_SQL+" where u.name=?";
        Object[] params=new Object[] {username};
        UserMapper mapper=new UserMapper();
        try
        {
            User userInfo=this.getJdbcTemplate().queryForObject(sql,params,mapper);
            return userInfo;
        }
        catch(EmptyResultDataAccessException e)
        {
            return null;
        }
    }
}

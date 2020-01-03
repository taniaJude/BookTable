package com.table.TableManager.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class RoleDAO extends JdbcDaoSupport {
    @Autowired
    public RoleDAO(DataSource dataSource)
    {
        this.setDataSource(dataSource);
    }
    public List<String> getRoleNames(Long id)
    {
        String sql="Select r.name "//
        +"from user_role ur, role r "//
        +"where ur.role_id=r.id and ur.user_id=?";
        Object[] params=new Object[]{id};
        List<String> roles=this.getJdbcTemplate().queryForList(sql,params,String.class);
        return roles;
    }
}

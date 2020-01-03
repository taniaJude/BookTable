package com.table.TableManager.service;

import com.table.TableManager.dao.RoleDAO;
import com.table.TableManager.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private RoleDAO roleDAO;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException
    {
        com.table.TableManager.modul.User user=this.userDAO.findUserAccount(userName);
        if(user==null)
        {
            System.out.println("User not found"+userName);
            throw new UsernameNotFoundException("User "+userName+" was not found in the database");
        }
        System.out.println("Found user: "+user);
        List<String> roleNames=this.roleDAO.getRoleNames(user.getId());
        List<GrantedAuthority> grantList=new ArrayList<GrantedAuthority>();
        if(roleNames!=null)
            for (String role:roleNames)
            {
                GrantedAuthority authority=new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        UserDetails userDetails=(UserDetails) new User(user.getName(),user.getPassword(),grantList);
        return userDetails;
    }
}

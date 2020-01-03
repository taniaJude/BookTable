package com.table.TableManager.service;

import com.table.TableManager.modul.User;
import com.table.TableManager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;
    public List<User> listAll()
    {
        return repo.findAll();
    }
    public void save(User user)
    {
        repo.save(user);
    }
    public Optional<User> get(int id)
    {
        return repo.findById((long) id);
    }
    public void delete(Long id)
    {
        repo.deleteById(id);
    }
}

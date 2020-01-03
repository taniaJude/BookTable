package com.table.TableManager.service;

import com.table.TableManager.repository.ProductRepository;
import com.table.TableManager.modul.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//we create this class for the purpose of exensibility in future we inject an instance of ProductRepository via
// private field using Autowired. At runtime Spring data JPA will generate a proxy instance of ProductRepository
// and inject it to the instance of ProdyctService class.

@Service
public class ProductService
{
    @Autowired
    private ProductRepository repo;
    public List<Product> listAll()
    {
        return repo.findAll();
    }
    public void save(Product product)
    {
        repo.save(product);
    }
    public Optional<Product> get(int id)
    {
        return repo.findById((long) id);
    }
    public void delete(Long id)
    {
        repo.deleteById(id);
    }
}

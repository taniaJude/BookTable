package com.table.TableManager.repository;

import com.table.TableManager.modul.Product;
import org.springframework.data.jpa.repository.JpaRepository;

//JpaRepository defines standard CRUD methods plus JPA specific operations
//the purpose of writing the repository interface is to tell Spring data JPA about the
//domain type(Product) and ID type(Long) to work with

public interface ProductRepository extends JpaRepository<Product, Long>
{
}

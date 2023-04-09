package io.codefountain.spring.product.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import io.codefountain.spring.product.domain.Product;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
	List<Product> findAll();
}

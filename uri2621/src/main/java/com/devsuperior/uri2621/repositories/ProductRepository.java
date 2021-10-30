package com.devsuperior.uri2621.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.uri2621.dto.ProductMinDTO;
import com.devsuperior.uri2621.entities.Product;
import com.devsuperior.uri2621.projections.ProductMinProjection;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query(nativeQuery = true, value = "SELECT products.name "
			+ "FROM products "
			+ "INNER JOIN providers ON products.id_providers = providers.id "
			+ "WHERE products.amount BETWEEN :amount1 AND :amount2 "
			+ "AND providers.name LIKE CONCAT(:firstLetter, '%')")
	List<ProductMinProjection> seartch1(Integer amount1, Integer amount2, String firstLetter);
	
	@Query("SELECT new com.devsuperior.uri2621.dto.ProductMinDTO(obj.name) "
			+ "FROM Product obj "
			+ "WHERE obj.amount BETWEEN :amount1 AND :amount2 "
			+ "AND obj.provider.name LIKE CONCAT(:firstLetter, '%')")
	List<ProductMinDTO> seartch2(Integer amount1, Integer amount2, String firstLetter);
}

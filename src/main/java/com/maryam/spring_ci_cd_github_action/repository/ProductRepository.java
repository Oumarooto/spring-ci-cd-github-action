package com.maryam.spring_ci_cd_github_action.repository;

import com.maryam.spring_ci_cd_github_action.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}

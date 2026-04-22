package com.maryam.spring_ci_cd_github_action.service;

import com.maryam.spring_ci_cd_github_action.dto.ProductDTO;

import java.util.List;

public interface IProductService {

    ProductDTO saveProduct(ProductDTO productDTO);
    List<ProductDTO> getAllProducts();
    ProductDTO getProductById(Long id);
    ProductDTO updateProduct(Long id, ProductDTO productDTO);
    void deleteProduct(Long id);
}

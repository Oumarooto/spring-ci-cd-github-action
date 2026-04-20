package com.maryam.spring_ci_cd_github_action.service.Impl;

import com.maryam.spring_ci_cd_github_action.dto.ProductDTO;
import com.maryam.spring_ci_cd_github_action.entity.Product;
import com.maryam.spring_ci_cd_github_action.repository.ProductRepository;
import com.maryam.spring_ci_cd_github_action.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

    private ProductRepository productRepository;

    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) {
        // 1. Mapping DTO -> Entity
        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product);

        // 2. Sauvegarde (L'ID est généré ici)
        Product savedProduct = productRepository.save(product);

        // 3. Mapping Entity -> Nouveau DTO de réponse
        ProductDTO responseDTO = new ProductDTO();
        BeanUtils.copyProperties(savedProduct, responseDTO);

        return responseDTO;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream().map(p -> {
            ProductDTO dto = new ProductDTO();
            BeanUtils.copyProperties(p, dto);
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        ProductDTO dto = new ProductDTO();
        BeanUtils.copyProperties(product, dto);
        return dto;
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}

package com.maryam.spring_ci_cd_github_action.service.Impl;

import com.maryam.spring_ci_cd_github_action.dto.ProductDTO;
import com.maryam.spring_ci_cd_github_action.entity.Product;
import com.maryam.spring_ci_cd_github_action.mapper.ProductMapper;
import com.maryam.spring_ci_cd_github_action.repository.ProductRepository;
import com.maryam.spring_ci_cd_github_action.service.IProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;


    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) {
        log.info("Tentative de sauvegarde du produit : {}", productDTO.getName());

        Product product = productMapper.toEntity(productDTO);
        Product savedProduct = productRepository.save(product);

        log.info("Produit sauvegardé avec succès. ID généré : {}", savedProduct.getId());
        return productMapper.toDto(savedProduct);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        log.debug("Récupération de la liste complète des produits");
        List<Product> products = productRepository.findAll();

        if (products.isEmpty()){
            log.info("Products is empty");
        }

        log.info("{} produits récupérés de la base de données", products.size());
        return products.stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(Long id) {
        log.info("Recherche du produit avec l'ID : {}", id);

        return productRepository.findById(id)
                .map(product -> {
                    log.debug("Produit trouvé : {}", product.getName());
                    return productMapper.toDto(product);
                })
                .orElseThrow(() -> {
                    log.error("ÉCHEC : Produit introuvable avec l'ID : {}", id);
                    return new RuntimeException("Product not found with id: " + id);
                });
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        log.info("Mise à jour partielle du produit ID : {}", id);

        return productRepository.findById(id)
                .map(existingProduct -> {
                    // On ne met à jour que si la valeur n'est pas nulle dans le DTO
                    if (productDTO.getName() != null) {
                        existingProduct.setName(productDTO.getName());
                    }
                    if (productDTO.getDescription() != null) {
                        existingProduct.setDescription(productDTO.getDescription());
                    }
                    if (productDTO.getPrice() != null) {
                        existingProduct.setPrice(productDTO.getPrice());
                    }
                    if (productDTO.getQuantity() != null) {
                        existingProduct.setQuantity(productDTO.getQuantity());
                    }

                    Product updatedProduct = productRepository.save(existingProduct);
                    log.info("Produit ID : {} mis à jour avec succès", id);
                    return productMapper.toDto(updatedProduct);
                })
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    @Override
    public void deleteProduct(Long id) {
        log.warn("Demande de suppression du produit ID : {}", id);

        if (!productRepository.existsById(id)) {
            log.error("Suppression impossible : l'ID {} n'existe pas", id);
            throw new RuntimeException("Product not found with id: " + id);
        }

        productRepository.deleteById(id);
        log.info("Produit ID : {} supprimé avec succès", id);
    }

}

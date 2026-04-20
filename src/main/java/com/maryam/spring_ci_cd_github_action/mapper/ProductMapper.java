package com.maryam.spring_ci_cd_github_action.mapper;

import com.maryam.spring_ci_cd_github_action.dto.ProductDTO;
import com.maryam.spring_ci_cd_github_action.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    /**
     * Convertit une Entité JPA en DTO pour la sortie (API)
     */
    public ProductDTO toDto(Product entity) {
        if (entity == null) {
            return null;
        }

        ProductDTO dto = new ProductDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setPrice(entity.getPrice());
        dto.setQuantity(entity.getQuantity());

        return dto;
    }

    /**
     * Convertit un DTO en Entité JPA pour l'entrée (Base de données)
     */
    public Product toEntity(ProductDTO dto) {
        if (dto == null) {
            return null;
        }

        Product entity = new Product();
        // On ne définit généralement pas l'ID ici car il est généré par la DB
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setQuantity(dto.getQuantity());

        return entity;
    }
}

package com.maryam.spring_ci_cd_github_action.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {

    @Schema(description = "Identifiant unique du produit", example = "1")
    private Long id;

    @Schema(description = "Nom du produit", example = "Ordinateur Portable", required = true)
    private String name;

    @Schema(description = "Description du produit", example = "")
    private String description;


    @Schema(description = "Prix unitaire", example = "999.99")
    private Double price;

    @Schema(description = "Quantite du produit", example = "25")
    private Integer quantity;
}

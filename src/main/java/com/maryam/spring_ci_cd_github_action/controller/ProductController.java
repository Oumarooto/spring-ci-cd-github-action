package com.maryam.spring_ci_cd_github_action.controller;

import com.maryam.spring_ci_cd_github_action.dto.ProductDTO;
import com.maryam.spring_ci_cd_github_action.service.IProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Product", description = "Endpoints pour la gestion des produits")
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private IProductService productService;

    @Operation(summary = "Créer un nouveau produit", description = "Enregistre un produit et génère un log d'audit.")
    @PostMapping
    public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(productService.saveProduct(productDTO));
    }

    @Operation(summary = "Afficher la liste des produits", description = "Liste des produit et génère un log d'audit.")
    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @Operation(summary = "Afficher un produit", description = "Details dun produit et génère un log d'audit.")
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @Operation(summary = "Supprimer un produit", description = "Supprimer un produit de la DB et génère un log d'audit.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}

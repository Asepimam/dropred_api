package dropred_api.com.mappers;

import dropred_api.com.Models.Product;
import dropred_api.com.dtos.ProductDTO;
import java.util.Optional;

public class ProductMapper {
    
    // Konversi Entity ke DTO (Response)
    public static ProductDTO toDTO(Product product) {
        return Optional.ofNullable(product)
            .map(p -> new ProductDTO(
                p.getProductName(),
                p.getProductDescription(),
                p.getProductPrice(),
                p.getProductImage(),
                p.getId() != null ? p.getId().toString() : null // Hindari NullPointerException
            ))
            .orElse(null);
    }

    // Konversi DTO ke Entity (Input - Insert)
    public static Product toEntity(ProductDTO dto) {
        Product product = new Product();
        product.setProductName(dto.getName());
        product.setProductDescription(dto.getDescription());
        product.setProductPrice(dto.getPrice());
        product.setProductImage(dto.getImage());
        return product;
    }

    // Konversi DTO ke Entity (Input - Update)
    public static Product toEntity(ProductDTO dto, Product existingProduct) {
        existingProduct.setProductName(dto.getName());
        existingProduct.setProductDescription(dto.getDescription());
        existingProduct.setProductPrice(dto.getPrice());
        existingProduct.setProductImage(dto.getImage());
        return existingProduct;
    }
}

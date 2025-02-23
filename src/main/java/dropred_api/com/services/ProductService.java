package dropred_api.com.services;

import java.util.List;

import dropred_api.com.Models.Product;
import dropred_api.com.dtos.ProductDTO;
import dropred_api.com.repositories.ProductRepositori;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ProductService {
    @Inject
    ProductRepositori productRepositori;

    @Transactional
    public ProductDTO addProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setProductName(productDTO.getName());
        product.setProductDescription(productDTO.getDescription());
        product.setProductPrice(Double.parseDouble(productDTO.getPrice()));
        product.setProductImage(productDTO.getImage());

        productRepositori.persist(product);
        return new ProductDTO(
            product.getProductName(),
            product.getProductDescription(),
            String.valueOf(product.getProductPrice()),
            product.getProductImage(),
            product.getId().toString()
        );
    }
}

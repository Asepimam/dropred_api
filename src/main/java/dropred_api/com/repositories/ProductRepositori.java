package dropred_api.com.repositories;


import java.util.Optional;
import java.util.UUID;

import dropred_api.com.Models.Product;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;




@ApplicationScoped
public class ProductRepositori implements PanacheRepository<Product> {
        // get product by id uuid
        public Optional<Product> getProductById(UUID id) {
            return Optional.ofNullable(find("id", id).firstResult());
        }
        
}

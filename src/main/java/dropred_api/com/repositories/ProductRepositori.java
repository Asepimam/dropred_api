package dropred_api.com.repositories;


import dropred_api.com.Models.Product;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;




@ApplicationScoped
public class ProductRepositori implements PanacheRepository<Product> {
    
}

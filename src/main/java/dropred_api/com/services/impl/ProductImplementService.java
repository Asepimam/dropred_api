package dropred_api.com.services.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import dropred_api.com.Models.Product;
import dropred_api.com.dtos.ProductDTO;
import dropred_api.com.exceptions.InternalServerException;
import dropred_api.com.mappers.ProductMapper;
import dropred_api.com.repositories.ProductRepositori;
import dropred_api.com.responses.BaseResponse;
import dropred_api.com.responses.SuccessResponse;
import dropred_api.com.services.interfaces.FilterInterface;
import dropred_api.com.services.interfaces.ProductInterface;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import dropred_api.com.exceptions.NotfoundException;

@ApplicationScoped
public class ProductImplementService implements ProductInterface, FilterInterface<ProductDTO> {
    private final Logger log = Logger.getLogger(ProductImplementService.class.getName());

    @Inject
    ProductRepositori productRepositori;

    @Transactional
    @Override
    public BaseResponse<ProductDTO> add(ProductDTO t) {
        try {
            Product product = ProductMapper.toEntity(t);
            productRepositori.persist(product);
            return new SuccessResponse<>("Product added successfully", ProductMapper.toDTO(product));
        } catch (Exception e) {
            throw new InternalServerException("Failed to add product");
        }
    }

    @Transactional
    @Override
    public BaseResponse<ProductDTO> update(ProductDTO t) {
        // 1. Cari produk berdasarkan ID
        try{
        Product existingProduct = productRepositori.getProductById(UUID.fromString(t.getId()))
                .orElseThrow(() -> new NotfoundException("Product with ID " + t.getId() + " not found"));

        // 2. Update data produk
        Product updatedProduct = ProductMapper.toEntity(t, existingProduct);
        productRepositori.persist(updatedProduct);

        // 3. Return response
        return new SuccessResponse<>("Product updated successfully", ProductMapper.toDTO(updatedProduct));
        }catch(Exception e){
            throw new InternalServerException("Failed to update product");
        }
    }

    @Transactional
    @Override
    public BaseResponse<Boolean> delete(String id) {
        // 1. Cari produk berdasarkan ID
        Product product = productRepositori.getProductById(UUID.fromString(id))
                .orElseThrow(() -> new NotfoundException("Product with ID " + id + " not found"));

        // 2. Hapus produk
        productRepositori.delete(product);

        // 3. Return response
        return new SuccessResponse<>("Product deleted successfully", true);
    }

    @Override
    public BaseResponse<ProductDTO> getById(String id) {
        try {
            Product product = productRepositori.getProductById(UUID.fromString(id))
                .orElseThrow(() -> new NotfoundException("Product with ID " + id + " not found"));

            return new SuccessResponse<>("Product retrieved successfully", ProductMapper.toDTO(product));
        } catch (NotfoundException e) {
            throw e; // Biarkan NotFoundException naik ke GlobalExceptionHandler
        } catch (Exception e) {
            throw new InternalServerException("Failed to retrieve product");
        }
    }



    @Override
    public BaseResponse<List<ProductDTO>> getAll() {
        // 1. Ambil semua produk dari database
        List<Product> products = productRepositori.listAll();

        // 2. Konversi ke DTO
        List<ProductDTO> productDTOs = products.stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
        
        log.info("Products retrieved successfully " + productDTOs);
        // 3. Return response
        return new SuccessResponse<>("Products retrieved successfully", productDTOs);
    }

    @Override
    public int maxPage(Map<String, String> filters, int size) {
        // 1. Hitung total produk
        long totalProducts = productRepositori.count();
        
        // 2. Hitung jumlah halaman
        return (int) Math.ceil((double) totalProducts / size);
    }
}

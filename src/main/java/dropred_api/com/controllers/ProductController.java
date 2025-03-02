package dropred_api.com.controllers;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import dropred_api.com.services.impl.ProductImplementService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import dropred_api.com.oas.ProductOAS;
import dropred_api.com.responses.BaseResponse;
import dropred_api.com.dtos.ProductDTO;

@Path("/product")
@Tag(name = "Product")
@Produces("application/json")
@Consumes("application/json")
public class ProductController {
    @Inject
    ProductImplementService productService;

    @POST
    @Path("/add_product")
    @Operation(summary = "Add a product",description = "Add a product to the data store")
    @APIResponses(value = {
        @APIResponse(responseCode = "200",description = "Product added successfully",content = @Content(mediaType = "application/json",schema = @Schema(implementation = ProductOAS.request.class))),
        @APIResponse(responseCode = "400",description = "Invalid input")
    })
    public Response addProduct(ProductDTO product) {  
        BaseResponse<ProductDTO> response = productService.add(product);
        return Response.ok(response).build();
    }


    @GET
    @Path("/get_all_product")
    @Operation(summary = "Get all products",description = "Get all products from the data store")
    @APIResponse(responseCode = "200",description = "All products",content = @Content(mediaType = "application/json"))
    public Response getAllProduct() {
        BaseResponse<List<ProductDTO>> response = productService.getAll();
        return Response.ok(response).build();
    }


    @GET
    @Path("/get_product_by_id")
    @Operation(summary = "Get product by id",description = "Get product by id from the data store")
    @APIResponses(value = {
        @APIResponse(responseCode = "200",description = "Product found",content = @Content(mediaType = "application/json")),
        @APIResponse(responseCode = "404",description = "Product not found")
    })
    public Response getProductById( @QueryParam("id") String id ) {
        BaseResponse<ProductDTO> response = productService.getById(id);
        return Response.ok(response).build();
    }

    @PUT
    @Path("/update_product")
    @Operation(summary = "Update product",description = "Update product in the data store")
    @APIResponses(value = {
        @APIResponse(responseCode = "200",description = "Product updated successfully",content = @Content(mediaType = "application/json")),
        @APIResponse(responseCode = "404",description = "Product not found")
    })
    public Response updateProduct(ProductDTO product) {
        BaseResponse<ProductDTO> response = productService.update(product);
        return Response.ok(response).build();
    }
}

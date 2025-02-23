package dropred_api.com.controllers;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import dropred_api.com.Models.Product;
import dropred_api.com.services.ProductService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import dropred_api.com.oas.ProductOAS;
import dropred_api.com.dtos.ProductDTO;

@Path("/product")
@Tag(name = "Product")
@Produces("application/json")
@Consumes("application/json")
public class ProductController {
    @Inject
    ProductService productService;

    @POST
    @Path("/add_product")
    @Operation(summary = "Add a product",description = "Add a product to the data store")
    @APIResponses(value = {
        @APIResponse(responseCode = "200",description = "Product added successfully",content = @Content(mediaType = "application/json",schema = @Schema(implementation = ProductOAS.request.class))),
        @APIResponse(responseCode = "400",description = "Invalid input")
    })
    public Response addProduct(ProductDTO product) {  
        ProductDTO response = productService.addProduct(product);
        return Response.ok(response).build();

    }
}

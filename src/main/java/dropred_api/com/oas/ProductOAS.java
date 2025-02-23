package dropred_api.com.oas;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class ProductOAS {
    @Schema(name = "ProductOAS.AddProduct",description = "request body for adding a product")
    public static class request {
        @Schema(required = true,example = "product name")
        public String name;
        @Schema(required = true,example = "product description")
        public String description;
        @Schema(required = true,example = "product price")
        public String price;
        @Schema(required = true,example = "product image")
        public String image;
    }
}

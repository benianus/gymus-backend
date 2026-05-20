package com.jetbrains.gymusserverjava.store;

import com.jetbrains.gymusserverjava.auth.entities.User;
import com.jetbrains.gymusserverjava.store.dtos.requests.CreateProductRequestDto;
import com.jetbrains.gymusserverjava.store.dtos.requests.RegisterSaleRequestDto;
import com.jetbrains.gymusserverjava.store.dtos.requests.UpdateProductRequestDto;
import com.jetbrains.gymusserverjava.store.dtos.responses.ProductResponseDto;
import com.jetbrains.gymusserverjava.store.entities.Product;
import com.jetbrains.gymusserverjava.store.entities.Sale;
import com.jetbrains.shared.FileStorage.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StoreMapper {

    @Autowired
    private FileStorageService fileStorageService;

    public ProductResponseDto productToDto(Product product) {
        return new ProductResponseDto(
                product.getId(),
                product.getProductName(),
                product.getProductImage(),
                product.getProductDescription(),
                product.getQuantity(),
                product.getPrice(),
                product.getUser(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }

    public Product dtoToEntity(CreateProductRequestDto dto, User user) {
        var product = new Product();

        var imageFile = fileStorageService.storeFile(dto.productImage());

        product.setProductName(dto.productName());
        product.setProductImage(imageFile);
        product.setProductDescription(dto.productDescription());
        product.setQuantity(dto.quantity());
        product.setPrice(dto.price());
        product.setUser(user);

        return product;
    }

    public Product dtoToEntity(UpdateProductRequestDto dto, User user, int productId) {
        var product = new Product();

        var imageFile = fileStorageService.storeFile(dto.productImage());

        product.setId(productId);
        product.setProductName(dto.productName());
        product.setProductImage(imageFile);
        product.setProductDescription(dto.productDescription());
        product.setQuantity(dto.quantity());
        product.setPrice(dto.price());
        product.setUser(user);

        return product;
    }

    public Sale dtoToEntity(RegisterSaleRequestDto dto, User user, Product product) {
        var sale = new Sale();

        sale.setProduct(product);
        sale.setUser(user);
        sale.setQuantity(dto.quantity());
        sale.setTotalPrice(dto.totalPrice());

        return sale;
    }

}

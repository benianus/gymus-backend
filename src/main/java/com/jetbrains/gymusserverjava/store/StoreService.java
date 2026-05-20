package com.jetbrains.gymusserverjava.store;

import com.jetbrains.gymusserverjava.store.dtos.requests.CreateProductRequestDto;
import com.jetbrains.gymusserverjava.store.dtos.requests.RegisterSaleRequestDto;
import com.jetbrains.gymusserverjava.store.dtos.requests.UpdateProductRequestDto;
import com.jetbrains.gymusserverjava.store.dtos.responses.ProductResponseDto;
import org.springframework.data.domain.Page;

public interface StoreService {

    Page<ProductResponseDto> findAll(int pageNumber, int pageSize);

    ProductResponseDto findById(int id);

    void save(CreateProductRequestDto requestDto);

    void update(UpdateProductRequestDto requestDto, int productId);

    void delete(int id);

    void registerSale(RegisterSaleRequestDto requestDto, int productId);

}

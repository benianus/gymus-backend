package com.jetbrains.gymusserverjava.store.Impl;

import com.jetbrains.gymusserverjava.auth.repositories.UserRepository;
import com.jetbrains.gymusserverjava.store.StoreMapper;
import com.jetbrains.gymusserverjava.store.StoreService;
import com.jetbrains.gymusserverjava.store.dtos.requests.CreateProductRequestDto;
import com.jetbrains.gymusserverjava.store.dtos.requests.RegisterSaleRequestDto;
import com.jetbrains.gymusserverjava.store.dtos.requests.UpdateProductRequestDto;
import com.jetbrains.gymusserverjava.store.dtos.responses.ProductResponseDto;
import com.jetbrains.gymusserverjava.store.repositories.ProductRepository;
import com.jetbrains.gymusserverjava.store.repositories.SaleRepository;
import com.jetbrains.shared.exceptions.CustomExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StoreMapper storeMapper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Override public Page<ProductResponseDto> findAll(int pageNumber, int pageSize) {
        var pageable = PageRequest.of(pageNumber - 1, pageSize);
        var products = productRepository.findAll(pageable);
        return products.map(product -> storeMapper.productToDto(product));
    }

    @Override
    public ProductResponseDto findById(int id) {
        var product = productRepository.findById(id)
                                       .orElseThrow(() -> CustomExceptionHandler.resourceNotFound(
                                               "product not found"));
        return storeMapper.productToDto(product);
    }

    @Override
    @Transactional
    public void save(CreateProductRequestDto requestDto) {
        // TODO: 20/05/2026 get the user from security context later
        var user = userRepository.findOneByUsername("benianus")
                                 .orElseThrow(() -> CustomExceptionHandler.resourceNotFound(
                                         "user not found"));
        productRepository.save(storeMapper.dtoToEntity(requestDto, user));
    }

    @Override
    @Transactional
    public void update(UpdateProductRequestDto requestDto, int productId) {
        // TODO: 20/05/2026 get the user from security context later
        var user = userRepository.findOneByUsername("benianus")
                                 .orElseThrow(() -> CustomExceptionHandler.resourceNotFound(
                                         "user not found"));
        productRepository.save(storeMapper.dtoToEntity(requestDto, user, productId));
    }

    @Override public void delete(int id) {
        var product = productRepository.findById(id)
                                       .orElseThrow(() -> CustomExceptionHandler.resourceNotFound(
                                               "product not found"));
        productRepository.delete(product);
    }

    @Override
    @Transactional
    public void registerSale(RegisterSaleRequestDto requestDto, int productId) {
        // TODO: 20/05/2026 get the user from security context later
        var user = userRepository.findOneByUsername("benianus")
                                 .orElseThrow(() -> CustomExceptionHandler.resourceNotFound(
                                         "user not found"));
        var product = productRepository.findById(productId)
                                       .orElseThrow(() -> CustomExceptionHandler.resourceNotFound(
                                               "product not found"));
        saleRepository.save(storeMapper.dtoToEntity(requestDto, user, product));
    }

}

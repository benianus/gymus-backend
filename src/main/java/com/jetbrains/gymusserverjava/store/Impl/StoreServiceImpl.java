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
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.jetbrains.shared.utils.HelpersKt.getUserDetails;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StoreMapper storeMapper;

    @Autowired
    private UserRepository userRepository;

    @Override public Page<ProductResponseDto> findAll(int pageNumber, int pageSize) {
        var pageable = PageRequest.of(
                pageNumber - 1,
                pageSize,
                Sort.by(Sort.Direction.DESC, "id")
        );
        var products = productRepository.findAll(pageable);
        return products.map(product -> storeMapper.productToDto(product));
    }

    @Override
    @PreAuthorize("@securityUtils.isProductOwner(#productId)")
    public ProductResponseDto findById(int productId) {
        var product = productRepository.findById(productId)
                                       .orElseThrow(() -> CustomExceptionHandler.resourceNotFound(
                                               "product not found"));
        return storeMapper.productToDto(product);
    }

    @Override
    @Transactional
    public void save(CreateProductRequestDto requestDto) {
        var username = getUserDetails().getUsername();
        var user = userRepository.findByUsername(username)
                                 .orElseThrow(() -> CustomExceptionHandler.resourceNotFound(
                                         "user not found"));
        productRepository.save(storeMapper.dtoToEntity(requestDto, user));
    }

    @Override
    @Transactional
    @PreAuthorize("@securityUtils.isProductOwner(#productId)")
    public void update(UpdateProductRequestDto requestDto, int productId) {
        var username = getUserDetails().getUsername();
        var user = userRepository.findByUsername(username)
                                 .orElseThrow(() -> CustomExceptionHandler.resourceNotFound(
                                         "user not found"));
        productRepository.save(storeMapper.dtoToEntity(requestDto, user, productId));
    }

    @Override
    @Transactional
    @PreAuthorize("@securityUtils.isProductOwner(#productId)")
    public void delete(int productId) {
        var product = productRepository.findById(productId)
                                       .orElseThrow(() -> CustomExceptionHandler.resourceNotFound(
                                               "product not found"));
        productRepository.delete(product);
    }

    @Override
    @Transactional
    @PreAuthorize("@securityUtils.isProductOwner(#productId)")
    public void registerSale(RegisterSaleRequestDto requestDto, int productId) {
        var username = getUserDetails().getUsername();
        var user = userRepository.findByUsername(username)
                                 .orElseThrow(() -> CustomExceptionHandler.resourceNotFound(
                                         "user not found"));
        var product = productRepository.findById(productId)
                                       .orElseThrow(() -> CustomExceptionHandler.resourceNotFound(
                                               "product not found"));
        saleRepository.save(storeMapper.dtoToEntity(requestDto, user, product));
    }

}

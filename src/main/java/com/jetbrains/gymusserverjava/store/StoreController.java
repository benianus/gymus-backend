package com.jetbrains.gymusserverjava.store;

import com.jetbrains.gymusserverjava.store.dtos.requests.CreateProductRequestDto;
import com.jetbrains.gymusserverjava.store.dtos.requests.RegisterSaleRequestDto;
import com.jetbrains.gymusserverjava.store.dtos.requests.UpdateProductRequestDto;
import com.jetbrains.gymusserverjava.store.dtos.responses.ProductResponseDto;
import com.jetbrains.shared.dtos.ApiResponse;
import com.jetbrains.shared.dtos.PagedResponse;
import com.jetbrains.shared.utils.Helpers;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @Autowired
    private Helpers helpers;

    @GetMapping
    public ResponseEntity<ApiResponse<PagedResponse<List<ProductResponseDto>>>> findAll(
            @RequestParam(name = "page", defaultValue = "1", required = false) int page,
            @RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        var products = storeService.findAll(page, pageSize);

        var pagedResponse = helpers.getPagedResponse(products);

        return new ResponseEntity<>(new ApiResponse<>(pagedResponse), HttpStatus.OK);
    }

    @GetMapping("{productId}")
    public ResponseEntity<ApiResponse<ProductResponseDto>> findById(@PathVariable int productId) {
        var product = storeService.findById(productId);
        return new ResponseEntity<>(new ApiResponse<>(product), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> save(
            @ModelAttribute @Valid CreateProductRequestDto requestDto
    ) {
        storeService.save(requestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("{productId}")
    public ResponseEntity<ApiResponse<ProductResponseDto>> update(
            @ModelAttribute @Valid UpdateProductRequestDto dto,
            @PathVariable int productId
    ) {
        storeService.update(dto, productId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{productId}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable int productId) {
        storeService.delete(productId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("{productId}/sales")
    public ResponseEntity<ApiResponse<Void>> registerSale(
            @Valid @RequestBody RegisterSaleRequestDto requestDto,
            @PathVariable int productId
    ) {
        storeService.registerSale(requestDto, productId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}

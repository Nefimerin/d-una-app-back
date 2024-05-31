package com.d.una.app.back.rest;

import com.d.una.app.back.business.IProductBusiness;
import com.d.una.app.back.domain.ProductDto;
import com.d.una.app.back.domain.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor(onConstructor = @__ (@Autowired))
public class ProductRest {

    private final IProductBusiness productBusiness;

    @GetMapping
    public ResponseEntity<ResponseDto<List<ProductDto>>> findAllProducts() {
        ResponseDto<List<ProductDto>> responseDto = productBusiness.findAllProducts();
        return responseDto.of();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ResponseDto<ProductDto>> getProductById(@PathVariable Long productId) {
        ResponseDto<ProductDto> responseDto = productBusiness.getProductById(productId);
        return responseDto.of();
    }

    @PostMapping
    public ResponseEntity<ResponseDto<ProductDto>> createProduct(@RequestBody @Valid ProductDto productDto) {
        ResponseDto<ProductDto> responseDto = productBusiness.createProduct(productDto);
        return responseDto.of();
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ResponseDto<ProductDto>> updateProduct(@PathVariable Long productId, @RequestBody @Valid ProductDto productDto) {
        ResponseDto<ProductDto> responseDto = productBusiness.updateProduct(productId, productDto);
        return responseDto.of();
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ResponseDto<Void>> deleteProduct(@PathVariable Long productId) {
        ResponseDto<Void> responseDto = productBusiness.deleteProduct(productId);
        return responseDto.of();
    }
}

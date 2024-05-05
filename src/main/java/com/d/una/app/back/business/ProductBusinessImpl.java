package com.d.una.app.back.business;

import com.d.una.app.back.config.MessageLoader;
import com.d.una.app.back.domain.ProductDto;
import com.d.una.app.back.domain.ResponseDto;
import com.d.una.app.back.exception.BusinessRuleException;
import com.d.una.app.back.mapper.IProductMapper;
import com.d.una.app.back.model.Product;
import com.d.una.app.back.repository.IProductRepository;
import com.d.una.app.back.util.MessagesConstants;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__ (@Autowired))
public class ProductBusinessImpl implements IProductBusiness {
    private static final Logger logger = LoggerFactory.getLogger(ProductBusinessImpl.class);
    private final IProductRepository productRepository;
    private final IProductMapper productMapper;

    @Override
    public ResponseDto<List<ProductDto>> findAllProducts() {
        logger.info("Fetching all products");
        List<Product> productList = productRepository.findAll();
        List<ProductDto> productDtoList = productList.stream()
                .map(productMapper::toDto)
                .toList();
        return new ResponseDto<>(HttpStatus.OK.value(), MessageLoader.getInstance().getMessage(MessagesConstants.IM001), productDtoList);
    }

    @Override
    public ResponseDto<ProductDto> getProductById(Long productId) {
        logger.info("Fetching product by ID: {}", productId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new BusinessRuleException(HttpStatus.BAD_REQUEST.value(), MessagesConstants.EM001, MessageLoader.getInstance().getMessage(MessagesConstants.EM001, productId)));
        return new ResponseDto<>(HttpStatus.OK.value(),MessageLoader.getInstance().getMessage(MessagesConstants.IM001), productMapper.toDto(product));
    }

    @Override
    public ResponseDto<ProductDto> createProduct(ProductDto productDto) {
        Product product = productMapper.toDomain(productDto);
        final var productSaved = productRepository.save(product);
        return new ResponseDto<>(HttpStatus.OK.value(),MessageLoader.getInstance().getMessage(MessagesConstants.IM002), productMapper.toDto(productSaved));
    }

    @Override
    public ResponseDto<ProductDto> updateProduct(Long productId, ProductDto productDto) {
        logger.info("Updating product with ID: {}", productId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new BusinessRuleException(HttpStatus.BAD_REQUEST.value(), MessagesConstants.EM001, MessageLoader.getInstance().getMessage(MessagesConstants.EM001, productId)));
        product.setDescription(productDto.getDescription());
        product.setName(productDto.getName());
        product.setType(productDto.getType());
        product.setPrice(productDto.getPrice());
        Product updatedProduct = productRepository.save(product);
        ProductDto updatedProductDto = productMapper.toDto(updatedProduct);
        return new ResponseDto<>(HttpStatus.OK.value(),MessageLoader.getInstance().getMessage(MessagesConstants.IM001), updatedProductDto);
    }

    @Override
    public ResponseDto<Void> deleteProduct(Long productId) {
        if (!productRepository.existsById(productId)) {
                throw new BusinessRuleException(HttpStatus.BAD_REQUEST.value(), MessagesConstants.EM001, MessageLoader.getInstance().getMessage(MessagesConstants.EM001, productId));

        }
        productRepository.deleteById(productId);
        return new ResponseDto<>(HttpStatus.OK.value(),MessageLoader.getInstance().getMessage(MessagesConstants.IM001));
    }
}

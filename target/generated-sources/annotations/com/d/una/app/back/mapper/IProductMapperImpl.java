package com.d.una.app.back.mapper;

import com.d.una.app.back.domain.ProductDto;
import com.d.una.app.back.model.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-07T21:54:19-0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class IProductMapperImpl implements IProductMapper {

    @Override
    public Product toDomain(ProductDto dto) {
        if ( dto == null ) {
            return null;
        }

        Product product = new Product();

        product.setProductId( dto.getProductId() );
        product.setName( dto.getName() );
        product.setDescription( dto.getDescription() );
        product.setPrice( dto.getPrice() );
        product.setType( dto.getType() );

        return product;
    }

    @Override
    public ProductDto toDto(Product entity) {
        if ( entity == null ) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        productDto.setProductId( entity.getProductId() );
        productDto.setName( entity.getName() );
        productDto.setDescription( entity.getDescription() );
        productDto.setPrice( entity.getPrice() );
        productDto.setType( entity.getType() );

        return productDto;
    }
}

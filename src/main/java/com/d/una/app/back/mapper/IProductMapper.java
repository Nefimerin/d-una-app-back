package com.d.una.app.back.mapper;

import com.d.una.app.back.domain.ProductDto;
import com.d.una.app.back.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IProductMapper extends IEntityMapper<ProductDto, Product>{

    Product toDomain(ProductDto dto);
    ProductDto toDto(Product entity);

}

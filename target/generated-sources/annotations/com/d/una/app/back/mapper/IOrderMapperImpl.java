package com.d.una.app.back.mapper;

import com.d.una.app.back.domain.OrderRequestDto;
import com.d.una.app.back.domain.OrderResponseDto;
import com.d.una.app.back.domain.ProductDto;
import com.d.una.app.back.domain.RoleDto;
import com.d.una.app.back.domain.UserResponseDto;
import com.d.una.app.back.model.Order;
import com.d.una.app.back.model.Product;
import com.d.una.app.back.model.Role;
import com.d.una.app.back.model.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-16T18:53:36-0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class IOrderMapperImpl implements IOrderMapper {

    @Override
    public Order toDomain(OrderRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Order order = new Order();

        order.setOrderId( dto.getOrderId() );
        order.setStatus( dto.getStatus() );
        order.setTotalPrice( dto.getTotalPrice() );

        return order;
    }

    @Override
    public OrderRequestDto toDto(Order entity) {
        if ( entity == null ) {
            return null;
        }

        OrderRequestDto orderRequestDto = new OrderRequestDto();

        orderRequestDto.setOrderId( entity.getOrderId() );
        orderRequestDto.setTotalPrice( entity.getTotalPrice() );
        orderRequestDto.setStatus( entity.getStatus() );

        return orderRequestDto;
    }

    @Override
    public OrderResponseDto toDtoResponse(Order entity) {
        if ( entity == null ) {
            return null;
        }

        OrderResponseDto orderResponseDto = new OrderResponseDto();

        orderResponseDto.setOrderId( entity.getOrderId() );
        orderResponseDto.setTotalPrice( entity.getTotalPrice() );
        orderResponseDto.setProducts( productListToProductDtoList( entity.getProducts() ) );
        orderResponseDto.setUser( userToUserResponseDto( entity.getUser() ) );

        return orderResponseDto;
    }

    protected ProductDto productToProductDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        productDto.setProductId( product.getProductId() );
        productDto.setName( product.getName() );
        productDto.setDescription( product.getDescription() );
        productDto.setPrice( product.getPrice() );
        productDto.setStock( product.getStock() );
        productDto.setType( product.getType() );

        return productDto;
    }

    protected List<ProductDto> productListToProductDtoList(List<Product> list) {
        if ( list == null ) {
            return null;
        }

        List<ProductDto> list1 = new ArrayList<ProductDto>( list.size() );
        for ( Product product : list ) {
            list1.add( productToProductDto( product ) );
        }

        return list1;
    }

    protected RoleDto roleToRoleDto(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleDto roleDto = new RoleDto();

        roleDto.setRoleId( role.getRoleId() );
        roleDto.setName( role.getName() );
        roleDto.setDescription( role.getDescription() );

        return roleDto;
    }

    protected List<RoleDto> roleListToRoleDtoList(List<Role> list) {
        if ( list == null ) {
            return null;
        }

        List<RoleDto> list1 = new ArrayList<RoleDto>( list.size() );
        for ( Role role : list ) {
            list1.add( roleToRoleDto( role ) );
        }

        return list1;
    }

    protected UserResponseDto userToUserResponseDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setId( user.getId() );
        userResponseDto.setFirstName( user.getFirstName() );
        userResponseDto.setLastName( user.getLastName() );
        userResponseDto.setRoles( roleListToRoleDtoList( user.getRoles() ) );
        userResponseDto.setEmail( user.getEmail() );

        return userResponseDto;
    }
}

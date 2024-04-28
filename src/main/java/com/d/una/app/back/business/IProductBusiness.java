package com.d.una.app.back.business;

import com.d.una.app.back.domain.ProductDto;
import com.d.una.app.back.domain.ResponseDto;

import java.util.List;

/**
 * An interface for Customer Business operations.
 */
public interface IProductBusiness {

    /**
     * Retrieves all products.
     *
     * @return a ResponseDto containing a list of ProductDto objects representing all products in the system.
     */
    ResponseDto<List<ProductDto>> findAllProducts();


    /**
     * Retrieves a Product based on the provided product ID.
     *
     * @param productId The ID of the product to retrieve.
     * @return A ResponseDto containing the ProductDto information if found, or an appropriate response if not found.
     */
    ResponseDto<ProductDto> getProductById(final Long productId);

    /**
     * Creates a new Product using the provided ProductDto.
     *
     * @param productDto The ProductDto containing the information needed to create the new product.
     * @return A ResponseDto indicating the result of the creation process, including the newly created ProductDto.
     */
    ResponseDto<ProductDto> createProduct(final ProductDto productDto);

    /**
     * Updates an existing Product identified by the provided product ID using the new information in ProductDto.
     *
     * @param productId  The ID of the product to update.
     * @param productDto The ProductDto containing the updated product information.
     * @return A ResponseDto indicating the result of the update process, including the updated ProductDto.
     */
    ResponseDto<ProductDto> updateProduct(final Long productId, final ProductDto productDto);

    /**
     * Deletes a Product based on the provided product ID.
     *
     * @param productId The ID of the product to delete.
     * @return A ResponseDto indicating the result of the deletion process.
     */
    ResponseDto<Void> deleteProduct(final Long productId);

}


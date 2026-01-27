package com.dansmultipro.ims.service.impl;

import com.dansmultipro.ims.dto.CreateResponseDto;
import com.dansmultipro.ims.dto.DeleteResponseDto;
import com.dansmultipro.ims.dto.PaginatedResponseDto;
import com.dansmultipro.ims.dto.UpdateResponseDto;
import com.dansmultipro.ims.dto.product.CreateProductRequestDto;
import com.dansmultipro.ims.dto.product.ProductResponseDto;
import com.dansmultipro.ims.dto.product.UpdateProductRequestDto;
import com.dansmultipro.ims.exception.DataMissMatchException;
import com.dansmultipro.ims.exception.NotFoundException;
import com.dansmultipro.ims.model.Product;
import com.dansmultipro.ims.model.ProductCategory;
import com.dansmultipro.ims.repo.ProductCategoryRepo;
import com.dansmultipro.ims.repo.ProductRepo;
import com.dansmultipro.ims.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl extends BaseService implements ProductService {

    private final ProductRepo productRepo;
    private final ProductCategoryRepo productCategoryRepo;

    public ProductServiceImpl(ProductRepo productRepo, ProductCategoryRepo productCategoryRepo) {
        this.productRepo = productRepo;
        this.productCategoryRepo = productCategoryRepo;
    }

    @Override
    public PaginatedResponseDto<ProductResponseDto> getAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPages = productRepo.findAll(pageable);

        List<Product> productList = productPages.getContent();
        List<ProductResponseDto> responseDtoList = productList.stream()
                .map(v -> new ProductResponseDto(v.getId(), v.getName(), v.getQuantity().toString(), v.getVersion().toString()))
                .toList();

        PaginatedResponseDto<ProductResponseDto> paginatedProductResponse = new PaginatedResponseDto<>(
                responseDtoList,
                productPages.getTotalElements()
        );

        return paginatedProductResponse;
    }

    @Override
    public ProductResponseDto getById(String id) {
        Product product = productRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new NotFoundException("Product not found")
        );
        return new ProductResponseDto(product.getId(), product.getName(), product.getQuantity().toString(), product.getVersion().toString());
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public CreateResponseDto create(CreateProductRequestDto requestDto) {
        ProductCategory category = productCategoryRepo.findById(UUID.fromString(requestDto.getCategoryId())).orElseThrow(
                () -> new NotFoundException("Category not found")
        );

        Product newProduct = prepareForInsert(new Product());
        newProduct.setName(requestDto.getName());
        newProduct.setQuantity(0);
        newProduct.setCategory(category);
        Product createdProduct = productRepo.save(newProduct);
        return new CreateResponseDto(createdProduct.getId(), "Saved");
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public UpdateResponseDto update(String id, UpdateProductRequestDto requestDto) {
        Product product = productRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new NotFoundException("Product not found")
        );
        ProductCategory category = productCategoryRepo.findById(UUID.fromString(requestDto.getCategoryId())).orElseThrow(
                () -> new NotFoundException("Category not found")
        );
        if (!product.getVersion().equals(requestDto.getVersion())) {
            throw new DataMissMatchException("Version not match");
        }
        Product productUpdate = prepareForUpdate(product);
        productUpdate.setName(requestDto.getName());
        productUpdate.setCategory(category);
        Product updatedProduct = productRepo.saveAndFlush(productUpdate);
        return new UpdateResponseDto(updatedProduct.getVersion(), "Updated");
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public DeleteResponseDto deleteById(String id) {
        Product product = productRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new NotFoundException("Product not found")
        );
        productRepo.deleteById(product.getId());
        return new DeleteResponseDto("Deleted");
    }

}

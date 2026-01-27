package com.dansmultipro.ims.service.impl;

import com.dansmultipro.ims.dto.CreateResponseDto;
import com.dansmultipro.ims.dto.DeleteResponseDto;
import com.dansmultipro.ims.dto.PaginatedResponseDto;
import com.dansmultipro.ims.dto.UpdateResponseDto;
import com.dansmultipro.ims.dto.productcategory.CreateProductCategoryRequestDto;
import com.dansmultipro.ims.dto.productcategory.ProductCategoryResponseDto;
import com.dansmultipro.ims.dto.productcategory.UpdateProductCategoryRequestDto;
import com.dansmultipro.ims.exception.DataMissMatchException;
import com.dansmultipro.ims.exception.NotFoundException;
import com.dansmultipro.ims.model.ProductCategory;
import com.dansmultipro.ims.repo.ProductCategoryRepo;
import com.dansmultipro.ims.service.ProductCategoryService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductCategoryServiceImpl extends BaseService implements ProductCategoryService {

    private final ProductCategoryRepo productCategoryRepo;

    public ProductCategoryServiceImpl(ProductCategoryRepo productCategoryRepo) {
        this.productCategoryRepo = productCategoryRepo;
    }

    @Override
    public PaginatedResponseDto<ProductCategoryResponseDto> getAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductCategory> categoryPages = productCategoryRepo.findAll(pageable);

        List<ProductCategory> categoryList = categoryPages.getContent();
        List<ProductCategoryResponseDto> responseDtoList = categoryList.stream()
                .map(v -> new ProductCategoryResponseDto(
                        v.getId(), v.getName(), v.getVersion().toString())).toList();

        PaginatedResponseDto<ProductCategoryResponseDto> paginatedCategoryResponse = new PaginatedResponseDto<>(
                responseDtoList,
                categoryPages.getTotalElements()
        );

        return paginatedCategoryResponse;
    }

    @Override
    public ProductCategoryResponseDto getById(String id) {
        ProductCategory productCategory = productCategoryRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new NotFoundException("Category not found")
        );
        return new ProductCategoryResponseDto(productCategory.getId(), productCategory.getName(), productCategory.getVersion().toString());
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public CreateResponseDto create(CreateProductCategoryRequestDto requestDto) {
        //TODO CHECK EXISTING NAME

        ProductCategory newCategory = prepareForInsert(new ProductCategory());
        newCategory.setName(requestDto.getName());
        ProductCategory createdCategory = productCategoryRepo.save(newCategory);
        return new CreateResponseDto(createdCategory.getId(), "Saved");
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public UpdateResponseDto update(String id, UpdateProductCategoryRequestDto requestDto) {
        ProductCategory category = productCategoryRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new NotFoundException("Category not found")
        );
        if (!category.getVersion().equals(requestDto.getVersion())) {
            throw new DataMissMatchException("Version not match");
        }
        ProductCategory categoryUpdate = prepareForUpdate(category);
        categoryUpdate.setName(requestDto.getName());
        ProductCategory updatedCategory = productCategoryRepo.saveAndFlush(categoryUpdate);
        return new UpdateResponseDto(updatedCategory.getVersion(), "Updated");
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public DeleteResponseDto deleteById(String id) {
        ProductCategory category = productCategoryRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new NotFoundException("Category not found")
        );
        productCategoryRepo.deleteById(category.getId());
        return new DeleteResponseDto("Deleted");
    }

}

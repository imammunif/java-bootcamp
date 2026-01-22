package com.dansmultipro.tms.service.impl;

import com.dansmultipro.tms.dto.CommonResponseDto;
import com.dansmultipro.tms.dto.productcustomer.CreateProductCustomerRequestDto;
import com.dansmultipro.tms.dto.productcustomer.ProductCustomerResponseDto;
import com.dansmultipro.tms.dto.productcustomer.UpdateProductCustomerRequestDto;
import com.dansmultipro.tms.exception.NotFoundException;
import com.dansmultipro.tms.model.Product;
import com.dansmultipro.tms.model.ProductCustomer;
import com.dansmultipro.tms.model.User;
import com.dansmultipro.tms.repository.ProductCustomerRepo;
import com.dansmultipro.tms.repository.ProductRepo;
import com.dansmultipro.tms.repository.UserRepo;
import com.dansmultipro.tms.service.ProductCustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductCustomerServiceImpl extends BaseService implements ProductCustomerService {

    private final ProductCustomerRepo productCustomerRepo;
    private final UserRepo userRepo;
    private final ProductRepo productRepo;


    public ProductCustomerServiceImpl(ProductCustomerRepo productCustomerRepo, UserRepo userRepo, ProductRepo productRepo) {
        this.productCustomerRepo = productCustomerRepo;
        this.userRepo = userRepo;
        this.productRepo = productRepo;
    }

    @Override
    public List<ProductCustomerResponseDto> getAll() {
        List<ProductCustomerResponseDto> result = productCustomerRepo.findAll().stream()
                .map(v -> new ProductCustomerResponseDto(v.getId(), v.getCustomer().getFullName(), v.getProduct().getName()))
                .toList();
        return result;
    }

    @Override
    public ProductCustomerResponseDto getById(String id) {
        ProductCustomer productCustomer = productCustomerRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new NotFoundException("Product customer not found")
        );
        return new ProductCustomerResponseDto(productCustomer.getId(), productCustomer.getCustomer().getFullName(), productCustomer.getProduct().getName());
    }

    @Override
    public CommonResponseDto create(CreateProductCustomerRequestDto data) {
        User customer = userRepo.findById(UUID.fromString(data.getCustomerId())).orElseThrow(
                () -> new NotFoundException("Customer not found")
        );
        List<String> productIdList = data.getProductIdList();
        for (String productId : productIdList) {
            Product product = productRepo.findById(UUID.fromString(productId)).orElseThrow(
                    () -> new NotFoundException("Product not found")
            );
            ProductCustomer newProductCustomer = prepareForInsert(new ProductCustomer());
            newProductCustomer.setProduct(product);
            newProductCustomer.setCustomer(customer);
            productCustomerRepo.save(newProductCustomer);
        }
        return new CommonResponseDto("Created");
    }

    @Override
    public CommonResponseDto update(String id, UpdateProductCustomerRequestDto data) {
        UUID customerId = UUID.fromString(data.getCustomerId());
        User customer = userRepo.findById(customerId).orElseThrow(
                () -> new NotFoundException("Customer not found")
        );
        List<String> productIdList = data.getProductIdList();
        List<UUID> productUUIDlist = productIdList.stream()
                .map(v -> UUID.fromString(v))
                .toList();
        List<ProductCustomer> productToDelete = productCustomerRepo.findByProductIdAndCustomerIdNotIn(customerId, productUUIDlist);
        productCustomerRepo.deleteAll(productToDelete);

        for (UUID ProductId : productUUIDlist) {
            Product product = productRepo.findById(ProductId).orElseThrow(
                    () -> new NotFoundException("Product not found")
            );
            ProductCustomer productCustomer = productCustomerRepo.findByProductId(ProductId).orElse(new ProductCustomer());
            productCustomer.setCustomer(customer);
            productCustomer.setProduct(product);

            productCustomerRepo.save(productCustomer.getId() != null ? prepareForUpdate(productCustomer) : prepareForInsert(productCustomer));
        }

        return new CommonResponseDto("Updated");
    }

}

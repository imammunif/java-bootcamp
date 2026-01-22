package com.dansmultipro.tms.service.impl;

import com.dansmultipro.tms.dto.CommonResponseDto;
import com.dansmultipro.tms.dto.piccustomer.CreatePicCustomerRequestDto;
import com.dansmultipro.tms.dto.piccustomer.PicCustomerResponseDto;
import com.dansmultipro.tms.dto.piccustomer.UpdatePicCustomerRequestDto;
import com.dansmultipro.tms.exception.NotFoundException;
import com.dansmultipro.tms.model.PicCustomer;
import com.dansmultipro.tms.model.User;
import com.dansmultipro.tms.repository.PicCustomerRepo;
import com.dansmultipro.tms.repository.UserRepo;
import com.dansmultipro.tms.service.PicCustomerService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PicCustomerServiceImpl extends BaseService implements PicCustomerService {

    private final PicCustomerRepo picCustomerRepo;
    private final UserRepo userRepo;

    public PicCustomerServiceImpl(PicCustomerRepo picCustomerRepo, UserRepo userRepo) {
        this.picCustomerRepo = picCustomerRepo;
        this.userRepo = userRepo;
    }

    @Override
    public List<PicCustomerResponseDto> getAll() {
        List<PicCustomerResponseDto> result = picCustomerRepo.findAll().stream()
                .map(v -> new PicCustomerResponseDto(v.getId(), v.getPic().getFullName(), v.getCustomer().getFullName()))
                .toList();
        return result;
    }

    @Override
    public PicCustomerResponseDto getById(String id) {
        PicCustomer picCustomer = picCustomerRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new NotFoundException("PIC-Customer not found")
        );
        return new PicCustomerResponseDto(picCustomer.getId(), picCustomer.getPic().getFullName(), picCustomer.getCustomer().getFullName());
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public CommonResponseDto create(CreatePicCustomerRequestDto data) {
        User userPic = userRepo.findById(UUID.fromString(data.getPicId())).orElseThrow(
                () -> new NotFoundException("PIC not found")
        );
        List<String> customerIdList = data.getCustomerIdList();
        for (String customerId : customerIdList) {
            User customer = userRepo.findById(UUID.fromString(customerId)).orElseThrow(
                    () -> new NotFoundException("Customer not found")
            );
            PicCustomer newPicCustomer = prepareForInsert(new PicCustomer());
            newPicCustomer.setPic(userPic);
            newPicCustomer.setCustomer(customer);
            picCustomerRepo.save(newPicCustomer);
        }
        return new CommonResponseDto("Created");
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public CommonResponseDto update(String id, UpdatePicCustomerRequestDto data) {
        UUID userPicId = UUID.fromString(data.getPicId());
        User userPic = userRepo.findById(userPicId).orElseThrow(
                () -> new NotFoundException("PIC not found")
        );
        List<String> customerIdList = data.getCustomerIdList();
        List<UUID> customerUUIDlist = customerIdList.stream()
                .map(v -> UUID.fromString(v))
                .toList();
        List<PicCustomer> customerToDelete = picCustomerRepo.findByPicIdAndCustomerIdNotIn(userPicId, customerUUIDlist);
        picCustomerRepo.deleteAll(customerToDelete);

        for (UUID customerId : customerUUIDlist) {
            User customer = userRepo.findById(customerId).orElseThrow(
                    () -> new NotFoundException("Customer not found")
            );
            PicCustomer picCustomer = picCustomerRepo.findByCustomerId(customerId).orElse(new PicCustomer());
            picCustomer.setPic(userPic);
            picCustomer.setCustomer(customer);

            picCustomerRepo.save(picCustomer.getId() != null ? prepareForUpdate(picCustomer) : prepareForInsert(picCustomer));
        }

        return new CommonResponseDto("Updated");
    }

}

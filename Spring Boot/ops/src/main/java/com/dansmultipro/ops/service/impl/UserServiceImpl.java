package com.dansmultipro.ops.service.impl;

import com.dansmultipro.ops.constant.ResponseMessage;
import com.dansmultipro.ops.constant.RoleCode;
import com.dansmultipro.ops.dto.CommonResponseDto;
import com.dansmultipro.ops.dto.CreateResponseDto;
import com.dansmultipro.ops.dto.DeleteResponseDto;
import com.dansmultipro.ops.dto.UpdateResponseDto;
import com.dansmultipro.ops.dto.user.CreateUserCustomerRequestDto;
import com.dansmultipro.ops.dto.user.CreateUserGatewayRequestDto;
import com.dansmultipro.ops.dto.user.UpdateUserRequestDto;
import com.dansmultipro.ops.dto.user.UserResponseDto;
import com.dansmultipro.ops.exception.*;
import com.dansmultipro.ops.model.Gateway;
import com.dansmultipro.ops.model.GatewayUser;
import com.dansmultipro.ops.model.User;
import com.dansmultipro.ops.model.UserRole;
import com.dansmultipro.ops.repository.*;
import com.dansmultipro.ops.service.UserService;
import com.dansmultipro.ops.util.RandomGenerator;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl extends BaseService implements UserService {

    private final UserRoleRepo userRoleRepo;
    private final UserRepo userRepo;
    private final GatewayUserRepo gatewayUserRepo;
    private final TransactionRepo transactionRepo;
    private final GatewayRepo gatewayRepo;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRoleRepo userRoleRepo, UserRepo userRepo, GatewayUserRepo gatewayUserRepo, TransactionRepo transactionRepo, GatewayRepo gatewayRepo, PasswordEncoder passwordEncoder) {
        this.userRoleRepo = userRoleRepo;
        this.userRepo = userRepo;
        this.gatewayUserRepo = gatewayUserRepo;
        this.transactionRepo = transactionRepo;
        this.gatewayRepo = gatewayRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findByEmail(String email) {
        return userRepo.findByEmail(email).orElseThrow(
                () -> new NotFoundException("User not found")
        );
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var userDb = userRepo.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException(email));
        return new org.springframework.security.core.userdetails.User(
                email, userDb.getPassword(), new ArrayList<>()
        );
    }

    @Override
    public List<UserResponseDto> getAll() {
        List<UserResponseDto> result = userRepo.findAll().stream()
                .map(v -> new UserResponseDto(
                        v.getId(), v.getName(), v.getEmail(), v.getUserRole().getName(),
                        v.getActive().toString(), v.getVersion().toString()))
                .toList();
        return result;
    }

    @Override
    public UserResponseDto getById(String id) {
        UUID validId = validateUUID(id);
        User user = userRepo.findById(validId).orElseThrow(
                () -> new NotFoundException("User not found")
        );
        return new UserResponseDto(user.getId(), user.getName(), user.getEmail(), user.getUserRole().getName(), user.getActive().toString(), user.getVersion().toString());
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public CreateResponseDto createUserCustomer(CreateUserCustomerRequestDto data) {
        UserRole userRole = userRoleRepo.findByCode(RoleCode.CUSTOMER.getCode()).orElseThrow(
                () -> new NotFoundException("Role not found")
        );
        User newUser = prepareForInsertBySystem(new User());
        String requestEmail = data.getEmail();
        if (userRepo.findByEmail(requestEmail).isPresent()) {
            throw new AlreadyExistsException("Email already exist");
        }
        String code = RandomGenerator.randomizeCode(6);
        newUser.setEmail(requestEmail);
        newUser.setPassword(passwordEncoder.encode(data.getPassword()));
        newUser.setName(data.getName());
        newUser.setActive(false);
        newUser.setActivationCode(code);
        newUser.setUserRole(userRole);
        User createdUser = userRepo.save(newUser);

        //TODO SEND EMAIL CODE VALIDATION

        return new CreateResponseDto(createdUser.getId(), ResponseMessage.CREATED.getMessage());
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public CreateResponseDto createUserGateway(CreateUserGatewayRequestDto data) {
        UserRole userRole = userRoleRepo.findByCode(RoleCode.GATEWAY.getCode()).orElseThrow(
                () -> new NotFoundException("Role not found")
        );
        User newUser = prepareForInsert(new User());
        String requestEmail = data.getEmail();
        if (userRepo.findByEmail(requestEmail).isPresent()) {
            throw new AlreadyExistsException("Email already exist");
        }
        newUser.setEmail(requestEmail);
        newUser.setPassword(passwordEncoder.encode(data.getPassword()));
        newUser.setName(data.getName());
        newUser.setActive(true);
        newUser.setUserRole(userRole);
        User createdUser = userRepo.save(newUser);

        Gateway gateway = gatewayRepo.findById(validateUUID(data.getGatewayId())).orElseThrow(
                () -> new NotFoundException("Gateway not found")
        );
        GatewayUser newGatewayUser = prepareForInsert(new GatewayUser());
        newGatewayUser.setUser(createdUser);
        newGatewayUser.setGateway(gateway);
        gatewayUserRepo.save(newGatewayUser);

        //TODO SEND EMAIL CODE VALIDATION

        return new CreateResponseDto(createdUser.getId(), ResponseMessage.CREATED.getMessage());
    }

    @Override
    public CommonResponseDto activateUserCustomer(String id, String code) {
        UUID validId = validateUUID(id);
        User user = userRepo.findById(validId).orElseThrow(
                () -> new NotFoundException("User not found")
        );
        if (!user.getActivationCode().equals(code)) {
            throw new MissMatchException("Oops!, Code is not valid");
        }
        if (user.getActive()) {
            throw new InvalidStatusException("User already activated");
        }
        User updateUser = prepareForUpdateBySystem(user);
        updateUser.setActive(true);
        userRepo.saveAndFlush(updateUser);

        return new CommonResponseDto("User is successfully activated");
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public UpdateResponseDto update(String id, UpdateUserRequestDto data) {
        UUID validId = validateUUID(id);
        User user = userRepo.findById(validId).orElseThrow(
                () -> new NotFoundException("User not found")
        );
        if (!user.getVersion().equals(data.getVersion())) {
            throw new MissMatchException("Version not match");
        }
        User userUpdate = prepareForUpdate(user);
        String requestEmail = data.getEmail();
        if (!user.getEmail().equals(requestEmail)) {
            if (userRepo.findByEmail(requestEmail).isPresent()) {
                throw new AlreadyExistsException("Email already exist");
            }
        }
        userUpdate.setEmail(requestEmail);
        userUpdate.setName(data.getName());
        User updatedUser = userRepo.saveAndFlush(userUpdate);

        return new UpdateResponseDto(updatedUser.getVersion(), ResponseMessage.UPDATED.getMessage());
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public DeleteResponseDto deleteById(String id) {
        UUID validId = validateUUID(id);
        User user = userRepo.findById(validId).orElseThrow(
                () -> new RuntimeException("User not found")
        );
        if (gatewayUserRepo.existsByUserId(validId)) {
            throw new ResourceInUseException("Unable to delete user, already referenced in gateway-user records");
        }
        if (transactionRepo.existsByCustomerId(validId)) {
            throw new ResourceInUseException("Unable to delete user, already referenced in transaction records");
        }
        userRepo.deleteById(user.getId());

        return new DeleteResponseDto(ResponseMessage.DELETED.getMessage());
    }
}

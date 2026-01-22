package com.dansmultipro.tms.service.impl;

import com.dansmultipro.tms.dto.CreateResponseDto;
import com.dansmultipro.tms.dto.DeleteResponseDto;
import com.dansmultipro.tms.dto.UpdateResponseDto;
import com.dansmultipro.tms.dto.user.CreateUserRequestDto;
import com.dansmultipro.tms.dto.user.UpdateUserRequestDto;
import com.dansmultipro.tms.dto.user.UserResponseDto;
import com.dansmultipro.tms.exception.DataIntegrityException;
import com.dansmultipro.tms.exception.DataMissMatchException;
import com.dansmultipro.tms.exception.NotFoundException;
import com.dansmultipro.tms.model.Company;
import com.dansmultipro.tms.model.User;
import com.dansmultipro.tms.model.UserRole;
import com.dansmultipro.tms.repository.CompanyRepo;
import com.dansmultipro.tms.repository.UserRepo;
import com.dansmultipro.tms.repository.UserRoleRepo;
import com.dansmultipro.tms.service.UserService;
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
    private final CompanyRepo companyRepo;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRoleRepo userRoleRepo, UserRepo userRepo, CompanyRepo companyRepo, PasswordEncoder passwordEncoder) {
        this.userRoleRepo = userRoleRepo;
        this.userRepo = userRepo;
        this.companyRepo = companyRepo;
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
                .map(v -> new UserResponseDto(v.getId(), v.getEmail(), v.getFullName(), v.getUserRole().getName()))
                .toList();
        return result;
    }

    @Override
    public UserResponseDto getById(String id) {
        User user = userRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new NotFoundException("User not found")
        );
        return new UserResponseDto(user.getId(), user.getEmail(), user.getFullName(), user.getUserRole().getName());
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public CreateResponseDto create(CreateUserRequestDto data) {
        String userRoleId = data.getRoleId();
        UserRole userRole = userRoleRepo.findById(UUID.fromString(userRoleId)).orElseThrow(
                () -> new NotFoundException("Role not found")
        );
        String userCompanyId = data.getCompanyId();
        Company userCompany = companyRepo.findById(UUID.fromString(userCompanyId)).orElseThrow(
                () -> new NotFoundException("Company not found")
        );
        User userNew = new User();
        User userInsert = prepareForInsert(userNew);
        String requestEmail = data.getEmail();
        if (userRepo.findByEmail(requestEmail).isPresent()) {
            throw new DataIntegrityException("Email already exist");
        }
        userInsert.setEmail(requestEmail);
        String encodedPassword = passwordEncoder.encode(data.getPassword());
        userInsert.setPassword(encodedPassword);
        userInsert.setFullName(data.getFullName());
        userInsert.setUserRole(userRole);
        userInsert.setCompany(userCompany);
        User user = userRepo.save(userInsert);

        return new CreateResponseDto(user.getId(), "Saved");
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public UpdateResponseDto update(String id, UpdateUserRequestDto data) {
        User user = userRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new NotFoundException("User not found")
        );
        if (!user.getVersion().equals(data.getVersion())) {
            throw new DataMissMatchException("Version not match");
        }
        User userUpdate = prepareForUpdate(user);
        String requestEmail = data.getEmail();
        if (!user.getEmail().equals(requestEmail)) {
            if (userRepo.findByEmail(requestEmail).isPresent()) {
                throw new DataIntegrityException("Email already exist");
            }
        }
        userUpdate.setEmail(requestEmail);
        userRepo.saveAndFlush(userUpdate);

        return new UpdateResponseDto(userUpdate.getVersion(), "Updated");
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public DeleteResponseDto deleteById(String id) {
        User user = userRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new RuntimeException("User not found")
        );
        userRepo.deleteById(user.getId());

        return new DeleteResponseDto("Deleted");
    }
}

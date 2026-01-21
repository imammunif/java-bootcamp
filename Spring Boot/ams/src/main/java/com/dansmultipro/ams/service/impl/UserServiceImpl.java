package com.dansmultipro.ams.service.impl;

import com.dansmultipro.ams.dto.CreateResponseDto;
import com.dansmultipro.ams.dto.DeleteResponseDto;
import com.dansmultipro.ams.dto.UpdateResponseDto;
import com.dansmultipro.ams.dto.user.UpdateUserRequestDto;
import com.dansmultipro.ams.dto.user.UserRequestDto;
import com.dansmultipro.ams.dto.user.UserResponseDto;
import com.dansmultipro.ams.exception.DataIntegrityException;
import com.dansmultipro.ams.exception.DataMissMatchException;
import com.dansmultipro.ams.exception.NotFoundException;
import com.dansmultipro.ams.model.Employee;
import com.dansmultipro.ams.model.Role;
import com.dansmultipro.ams.model.User;
import com.dansmultipro.ams.repository.EmployeeRepo;
import com.dansmultipro.ams.repository.RoleRepo;
import com.dansmultipro.ams.repository.UserRepo;
import com.dansmultipro.ams.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl extends BaseService implements UserService {

    private final UserRepo userRepo;
    private final RoleRepo roleDao;
    private final EmployeeRepo employeeRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, RoleRepo roleDao, EmployeeRepo employeeRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.roleDao = roleDao;
        this.employeeRepo = employeeRepo;
        this.passwordEncoder = passwordEncoder;
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
    public User findByEmail(String email) {
        return userRepo.findByEmail(email).orElseThrow(
                () -> new NotFoundException("User not found")
        );
    }

    @Override
    public List<UserResponseDto> getAll() {
        List<UserResponseDto> result = userRepo.findAll().stream()
                .map(v -> new UserResponseDto(v.getId(), v.getEmail(), v.getEmployee().getFullName(), v.getEmployee().getPhone(), v.getEmployee().getAddress(), v.getRole().getName()))
                .toList();
        return result;
    }

    @Override
    public UserResponseDto getById(String id) {
        User user = userRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new RuntimeException("User not found")
        );
        return new UserResponseDto(user.getId(), user.getEmail(), user.getEmployee().getFullName(), user.getEmployee().getPhone(), user.getEmployee().getAddress(), user.getRole().getName());
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public CreateResponseDto insert(UserRequestDto request) {
        String userRoleId = request.getRoleId();
        Role userRole = roleDao.findById(UUID.fromString(userRoleId)).orElseThrow(
                () -> new NotFoundException("Role not found")
        );
        String userEmployeeId = request.getEmployeeId();
        Employee userEmployee = employeeRepo.findById(UUID.fromString(userEmployeeId)).orElseThrow(
                () -> new NotFoundException("Employee not found")
        );
        User userNew = new User();
        User userInsert = prepareForInsert(userNew);
        String requestEmail = request.getEmail();
        if (userRepo.findByEmail(requestEmail).isPresent()) {
            throw new DataIntegrityException("Email already exist");
        }
        userInsert.setEmail(requestEmail);
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        userInsert.setPassword(encodedPassword);
        userInsert.setRole(userRole);
        userInsert.setEmployee(userEmployee);
        User user = userRepo.save(userInsert);

        return new CreateResponseDto(user.getId(), "Saved");
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public UpdateResponseDto update(String id, UpdateUserRequestDto request) {
        User user = userRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new NotFoundException("User not found")
        );
        if (!user.getVersion().equals(request.getVersion())) {
            throw new DataMissMatchException("Version not match");
        }
        User userUpdate = prepareForUpdate(user);
        String requestEmail = request.getEmail();
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

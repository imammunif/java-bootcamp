package com.dansmultipro.ams.service.impl.jpa;

import com.dansmultipro.ams.dto.CreateResponseDto;
import com.dansmultipro.ams.dto.DeleteResponseDto;
import com.dansmultipro.ams.dto.UpdateResponseDto;
import com.dansmultipro.ams.dto.user.UpdateUserRequestDto;
import com.dansmultipro.ams.dto.user.UserRequestDto;
import com.dansmultipro.ams.dto.user.UserResponseDto;
import com.dansmultipro.ams.model.Employee;
import com.dansmultipro.ams.model.Role;
import com.dansmultipro.ams.model.User;
import com.dansmultipro.ams.repository.EmployeeRepo;
import com.dansmultipro.ams.repository.RoleRepo;
import com.dansmultipro.ams.repository.UserRepo;
import com.dansmultipro.ams.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Profile("jpa")
@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final RoleRepo roleDao;
    private final EmployeeRepo employeeRepo;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, RoleRepo roleDao, EmployeeRepo employeeRepo) {
        this.userRepo = userRepo;
        this.roleDao = roleDao;
        this.employeeRepo = employeeRepo;
    }

    @Override
    public List<UserResponseDto> getAll() {
        List<UserResponseDto> result = userRepo.findAll().stream()
                .map(v -> new UserResponseDto(v.getId(), v.getEmployee().getFullName(), v.getEmployee().getPhone(), v.getEmployee().getAddress(), v.getRole().getName()))
                .toList();
        return result;
    }

    @Override
    public UserResponseDto getById(String id) {
        User user = userRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new RuntimeException("User not found")
        );
        return new UserResponseDto(user.getId(), user.getEmployee().getFullName(), user.getEmployee().getPhone(), user.getEmployee().getAddress(), user.getRole().getName());
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public CreateResponseDto insert(UserRequestDto data) {
        String userRoleId = data.getRoleId();
        Role userRole = roleDao.findById(UUID.fromString(userRoleId)).orElseThrow(
                () -> new RuntimeException("Role not found")
        );

        String userEmployeeId = data.getEmployeeId();
        Employee userEmployee = employeeRepo.findById(UUID.fromString(userEmployeeId)).orElseThrow(
                () -> new RuntimeException("Employee not found")
        );
        User userInsert = new User();
        userInsert.setId(UUID.randomUUID());
        userInsert.setCreatedBy(UUID.randomUUID());
        userInsert.setCreatedAt(LocalDateTime.now());
        userInsert.setEmail(data.getEmail());
        userInsert.setPassword(data.getPassword());
        userInsert.setRole(userRole);
        userInsert.setEmployee(userEmployee);

        User user = userRepo.save(userInsert);

        return new CreateResponseDto(user.getId(), "Saved");
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public UpdateResponseDto update(String id, UpdateUserRequestDto data) {
        User userUpdate = userRepo.findById(UUID.fromString(id)).orElseThrow(
                () -> new RuntimeException("User not found")
        );
        userUpdate.setEmail(data.getEmail());
        userUpdate.setUpdatedBy(UUID.randomUUID());
        userUpdate.setUpdatedAt(LocalDateTime.now());

        userRepo.save(userUpdate);
        em.flush();

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

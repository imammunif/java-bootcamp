package com.dansmultipro.ams.service.impl;

import com.dansmultipro.ams.dao.EmployeeDao;
import com.dansmultipro.ams.dao.RoleDao;
import com.dansmultipro.ams.dao.UserDao;
import com.dansmultipro.ams.dto.CreateResponseDto;
import com.dansmultipro.ams.dto.DeleteResponseDto;
import com.dansmultipro.ams.dto.UpdateResponseDto;
import com.dansmultipro.ams.dto.user.UpdateUserRequestDto;
import com.dansmultipro.ams.dto.user.UserRequestDto;
import com.dansmultipro.ams.dto.user.UserResponseDto;
import com.dansmultipro.ams.model.Employee;
import com.dansmultipro.ams.model.Role;
import com.dansmultipro.ams.model.User;
import com.dansmultipro.ams.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final RoleDao roleDao;
    private final EmployeeDao employeeDao;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public UserServiceImpl(UserDao userDao, RoleDao roleDao, EmployeeDao employeeDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.employeeDao = employeeDao;
    }

    @Override
    public List<UserResponseDto> getAll() {
        List<UserResponseDto> result = userDao.getAll().stream()
                .map(v -> new UserResponseDto(v.getId(), v.getEmployee().getFullName(), v.getEmployee().getPhone(), v.getEmployee().getAddress(), v.getRole().getName()))
                .toList();
        return result;
    }

    @Override
    public UserResponseDto getById(String id) {
        User user = userDao.getById(UUID.fromString(id)).orElseThrow(
                () -> new RuntimeException("User not found")
        );
        return new UserResponseDto(user.getId(), user.getEmployee().getFullName(), user.getEmployee().getPhone(), user.getEmployee().getAddress(), user.getRole().getName());
    }

    @Transactional
    @Override
    public CreateResponseDto insert(UserRequestDto data) {
        String userRoleId = data.getRoleId();
        Role userRole = roleDao.getById(UUID.fromString(userRoleId)).orElseThrow(
                () -> new RuntimeException("Role not found")
        );

        String userEmployeeId = data.getEmployeeId();
        Employee userEmployee = employeeDao.getById(UUID.fromString(userEmployeeId)).orElseThrow(
                () -> new RuntimeException("Employee not found")
        );
        User userInsert = new User();
        userInsert.setId(UUID.randomUUID());
        userInsert.setCreatedBy(UUID.randomUUID().toString());
        userInsert.setCreatedAt(LocalDateTime.now());
        userInsert.setEmail(data.getEmail());
        userInsert.setPassword(data.getPassword());
        userInsert.setRole(userRole);
        userInsert.setEmployee(userEmployee);

        User user = userDao.insert(userInsert);

        return new CreateResponseDto(user.getId(), "Saved");
    }

    @Transactional
    @Override
    public UpdateResponseDto update(String id, UpdateUserRequestDto data) {
        User userUpdate = userDao.getById(UUID.fromString(id)).orElseThrow(
                () -> new RuntimeException("User not found")
        );
        userUpdate.setEmail(data.getEmail());
        userUpdate.setUpdatedBy(UUID.randomUUID().toString());
        userUpdate.setUpdatedAt(LocalDateTime.now());

        userDao.update(userUpdate);
        em.flush();

        return new UpdateResponseDto(userUpdate.getVersion(), "Updated");
    }

    @Transactional
    @Override
    public DeleteResponseDto deleteById(String id) {
        User user = userDao.getById(UUID.fromString(id)).orElseThrow(
                () -> new RuntimeException("User not found")
        );

        userDao.deleteById(user.getId());

        return new DeleteResponseDto("Deleted");
    }

}

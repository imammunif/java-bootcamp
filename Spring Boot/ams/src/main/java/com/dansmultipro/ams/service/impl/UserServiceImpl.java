package com.dansmultipro.ams.service.impl;

import com.dansmultipro.ams.dao.EmployeeDao;
import com.dansmultipro.ams.dao.RoleDao;
import com.dansmultipro.ams.dao.UserDao;
import com.dansmultipro.ams.dto.CreateResponseDto;
import com.dansmultipro.ams.dto.UpdateResponseDto;
import com.dansmultipro.ams.dto.user.UpdateUserRequestDto;
import com.dansmultipro.ams.dto.user.UserRequestDto;
import com.dansmultipro.ams.dto.user.UserResponseDto;
import com.dansmultipro.ams.model.Employee;
import com.dansmultipro.ams.model.Role;
import com.dansmultipro.ams.model.User;
import com.dansmultipro.ams.service.UserService;
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

    @Autowired
    public UserServiceImpl(UserDao userDao, RoleDao roleDao, EmployeeDao employeeDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.employeeDao = employeeDao;
    }

    @Override
    public List<UserResponseDto> getAll() {
        List<UserResponseDto> result = userDao.getAll().stream()
                .map(u -> new UserResponseDto(u.getId(), u.getEmployee().getName(), u.getEmployee().getPhone(), u.getEmployee().getAddress(), u.getRole().getRoleName()))
                .toList();
        return result;
    }

    @Override
    public UserResponseDto getById(UUID id) {
        User user = userDao.getById(id).orElseThrow(
                () -> new RuntimeException("User not found")
        );
        return new UserResponseDto(id, user.getEmployee().getName(), user.getEmployee().getPhone(), user.getEmployee().getAddress(), user.getRole().getRoleName());
    }

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
        // email, password, roleId, employeeId + id, createdBy, createdAt
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

    @Override
    public UpdateResponseDto update(UpdateUserRequestDto data) {
        return null;
    }

    @Override
    public void deleteById(UUID id) {

    }

}

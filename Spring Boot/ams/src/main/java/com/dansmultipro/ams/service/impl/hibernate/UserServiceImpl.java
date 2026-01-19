package com.dansmultipro.ams.service.impl.hibernate;

import com.dansmultipro.ams.dao.EmployeeDao;
import com.dansmultipro.ams.dao.RoleDao;
import com.dansmultipro.ams.dao.UserDao;
import com.dansmultipro.ams.dto.CreateResponseDto;
import com.dansmultipro.ams.dto.DeleteResponseDto;
import com.dansmultipro.ams.dto.UpdateResponseDto;
import com.dansmultipro.ams.dto.user.UpdateUserRequestDto;
import com.dansmultipro.ams.dto.user.UserRequestDto;
import com.dansmultipro.ams.dto.user.UserResponseDto;
import com.dansmultipro.ams.exception.NotFoundException;
import com.dansmultipro.ams.model.Employee;
import com.dansmultipro.ams.model.Role;
import com.dansmultipro.ams.model.User;
import com.dansmultipro.ams.service.UserService;
import com.dansmultipro.ams.service.impl.BaseService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Profile("hibernate")
@Service
public class UserServiceImpl extends BaseService implements UserService {

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
                .map(v -> new UserResponseDto(v.getId(), v.getEmail(), v.getEmployee().getFullName(), v.getEmployee().getPhone(), v.getEmployee().getAddress(), v.getRole().getName()))
                .toList();
        return result;
    }

    @Override
    public UserResponseDto getById(String id) {
        User user = userDao.getById(UUID.fromString(id)).orElseThrow(
                () -> new RuntimeException("User not found")
        );
        return new UserResponseDto(user.getId(), user.getEmail(), user.getEmployee().getFullName(), user.getEmployee().getPhone(), user.getEmployee().getAddress(), user.getRole().getName());
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public CreateResponseDto insert(UserRequestDto data) {
        String userRoleId = data.getRoleId();
        Role userRole = roleDao.getById(UUID.fromString(userRoleId)).orElseThrow(
                () -> new NotFoundException("Role not found")
        );

        String userEmployeeId = data.getEmployeeId();
        Employee userEmployee = employeeDao.getById(UUID.fromString(userEmployeeId)).orElseThrow(
                () -> new NotFoundException("Employee not found")
        );
        User userNew = new User();
        User userInsert = prepareForInsert(userNew);

        userInsert.setEmail(data.getEmail());
        userInsert.setPassword(data.getPassword());
        userInsert.setRole(userRole);
        userInsert.setEmployee(userEmployee);

        User user = userDao.insert(userInsert);

        return new CreateResponseDto(user.getId(), "Saved");
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public UpdateResponseDto update(String id, UpdateUserRequestDto data) {
        User user = userDao.getById(UUID.fromString(id)).orElseThrow(
                () -> new NotFoundException("User not found")
        );
        User userUpdate = prepareForUpdate(user);

        userUpdate.setEmail(data.getEmail());
        userDao.update(userUpdate);
        em.flush();

        return new UpdateResponseDto(userUpdate.getVersion(), "Updated");
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public DeleteResponseDto deleteById(String id) {
        User user = userDao.getById(UUID.fromString(id)).orElseThrow(
                () -> new RuntimeException("User not found")
        );

        userDao.deleteById(user.getId());

        return new DeleteResponseDto("Deleted");
    }

}

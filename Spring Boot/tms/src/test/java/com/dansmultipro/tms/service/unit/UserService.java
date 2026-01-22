package com.dansmultipro.tms.service.unit;

import com.dansmultipro.tms.dto.user.CreateUserRequestDto;
import com.dansmultipro.tms.model.Company;
import com.dansmultipro.tms.model.User;
import com.dansmultipro.tms.model.UserRole;
import com.dansmultipro.tms.pojo.AuthorizationPoJo;
import com.dansmultipro.tms.repository.CompanyRepo;
import com.dansmultipro.tms.repository.UserRepo;
import com.dansmultipro.tms.repository.UserRoleRepo;
import com.dansmultipro.tms.service.PrincipalService;
import com.dansmultipro.tms.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class UserService {

    @Mock
    private UserRoleRepo userRoleRepo;

    @Mock
    private CompanyRepo companyRepo;

    @Mock
    private UserRepo userRepo;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    protected PrincipalService principalService;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void shouldCreated_whenDataValid() {
        userService.setPrincipalService(principalService);
        var authPojo = new AuthorizationPoJo(UUID.randomUUID().toString());

        var dto = new CreateUserRequestDto();
        dto.setFullName("John Doe");
        dto.setEmail("johndoe@mail.com");
        dto.setPassword("john123");
        dto.setRoleId(UUID.randomUUID().toString());
        dto.setCompanyId(UUID.randomUUID().toString());

        var userSaved = new User();
        var id = UUID.randomUUID();
        userSaved.setId(id);

        Mockito.when(userRoleRepo.findById(Mockito.any())).thenReturn(Optional.of(new UserRole()));
        Mockito.when(companyRepo.findById(Mockito.any())).thenReturn(Optional.of(new Company()));
        Mockito.when(passwordEncoder.encode(Mockito.any())).thenReturn("john123");
        Mockito.when(principalService.getPrincipal()).thenReturn(authPojo);
        Mockito.when(userRepo.save(Mockito.any())).thenReturn(userSaved);

        var result = userService.create(dto);

        Assertions.assertEquals(id, result.getId());
        Mockito.verify(userRoleRepo, Mockito.atLeast(1)).findById(Mockito.any());
        Mockito.verify(companyRepo, Mockito.atLeast(1)).findById(Mockito.any());
        Mockito.verify(passwordEncoder, Mockito.atLeast(1)).encode(Mockito.any());
        Mockito.verify(principalService, Mockito.atLeast(1)).getPrincipal();
        Mockito.verify(userRepo, Mockito.atLeast(1)).save(Mockito.any());
    }

}

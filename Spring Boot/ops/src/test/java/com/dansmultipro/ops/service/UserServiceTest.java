package com.dansmultipro.ops.service;

import com.dansmultipro.ops.dto.CreateResponseDto;
import com.dansmultipro.ops.dto.user.CreateUserGatewayRequestDto;
import com.dansmultipro.ops.dto.user.UserGatewayResponseDto;
import com.dansmultipro.ops.dto.user.UserResponseDto;
import com.dansmultipro.ops.model.Gateway;
import com.dansmultipro.ops.model.GatewayUser;
import com.dansmultipro.ops.model.User;
import com.dansmultipro.ops.model.UserRole;
import com.dansmultipro.ops.pojo.AuthorizationPoJo;
import com.dansmultipro.ops.repository.*;
import com.dansmultipro.ops.service.impl.UserServiceImpl;
import com.dansmultipro.ops.util.MailUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRoleRepo userRoleRepo;
    @Mock
    private UserRepo userRepo;
    @Mock
    private GatewayUserRepo gatewayUserRepo;
    @Mock
    private TransactionRepo transactionRepo;
    @Mock
    private GatewayRepo gatewayRepo;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private MailUtil mailUtil;
    @Mock
    private RabbitTemplate rabbitTemplate;
    @Mock
    protected PrincipalService principalService;

    @InjectMocks
    private UserServiceImpl userService;

    private AuthorizationPoJo authPojo;
    private Gateway gateway1;
    private Gateway gateway2;
    private User user1;
    private User user2;
    private User user3;
    private User user4;
    private UserRole customerRole;
    private UserRole gatewayRole;
    private GatewayUser gatewayUser1;
    private GatewayUser gatewayUser2;

    @BeforeEach
    public void setup() {
        userService.setPrincipalService(principalService);

        customerRole = new UserRole();
        customerRole.setId(UUID.randomUUID());
        customerRole.setCode("CUST");
        customerRole.setName("Customer");

        gatewayRole = new UserRole();
        gatewayRole.setId(UUID.randomUUID());
        gatewayRole.setCode("GA");
        gatewayRole.setName("Gateway");

        gateway1 = new Gateway();
        gateway1.setId(UUID.randomUUID());
        gateway1.setName("GATEWAY1");

        gateway2 = new Gateway();
        gateway2.setId(UUID.randomUUID());
        gateway2.setName("GATEWAY2");

        user1 = new User();
        user1.setId(UUID.randomUUID());
        user1.setName("Customer 1");
        user1.setEmail("customer1@mail.com");
        user1.setPassword("secret");
        user1.setActive(true);
        user1.setVersion(0);
        user1.setUserRole(customerRole);

        user2 = new User();
        user2.setId(UUID.randomUUID());
        user2.setName("Customer 2");
        user2.setEmail("customer2@mail.com");
        user2.setPassword("secret");
        user2.setActive(false);
        user2.setVersion(0);
        user2.setUserRole(customerRole);

        user3 = new User();
        user3.setId(UUID.randomUUID());
        user3.setName("Gateway 1");
        user3.setEmail("gateway1@mail.com");
        user3.setPassword("admin");
        user3.setActive(true);
        user3.setVersion(0);
        user3.setUserRole(gatewayRole);

        user4 = new User();
        user4.setId(UUID.randomUUID());
        user4.setName("Gateway 2");
        user4.setEmail("gateway2@mail.com");
        user4.setPassword("admin");
        user4.setActive(true);
        user4.setVersion(0);
        user4.setUserRole(gatewayRole);

        gatewayUser1 = new GatewayUser();
        gatewayUser1.setId(UUID.randomUUID());
        gatewayUser1.setUser(user3);
        gatewayUser1.setGateway(gateway1);
        gatewayUser1.setVersion(0);

        gatewayUser2 = new GatewayUser();
        gatewayUser2.setId(UUID.randomUUID());
        gatewayUser2.setUser(user4);
        gatewayUser2.setGateway(gateway2);
        gatewayUser2.setVersion(0);

        authPojo = new AuthorizationPoJo(user1.getId());
    }

    @Test
    public void shouldCreated_whenDataValid() {
        var dto = new CreateUserGatewayRequestDto();
        dto.setName("New Gateway User");
        dto.setEmail("newgateway@mail.com");
        dto.setPassword("password123");
        dto.setGatewayId(gateway1.getId().toString());

        var newUser = new User();
        newUser.setId(UUID.randomUUID());
        newUser.setName(dto.getName());
        newUser.setEmail(dto.getEmail());
        newUser.setPassword("encodedPassword");
        newUser.setActive(true);
        newUser.setUserRole(gatewayRole);

        var newGatewayUser = new GatewayUser();
        newGatewayUser.setId(UUID.randomUUID());
        newGatewayUser.setUser(newUser);
        newGatewayUser.setGateway(gateway1);

        Mockito.when(principalService.getPrincipal()).thenReturn(authPojo);
        Mockito.when(userRoleRepo.findByCode(Mockito.any())).thenReturn(Optional.of(gatewayRole));
        Mockito.when(userRepo.findByEmail(dto.getEmail())).thenReturn(Optional.empty());
        Mockito.when(passwordEncoder.encode(dto.getPassword())).thenReturn("encodedPassword");
        Mockito.when(userRepo.save(Mockito.any(User.class))).thenReturn(newUser);
        Mockito.when(gatewayRepo.findById(Mockito.any(UUID.class))).thenReturn(Optional.of(gateway1));
        Mockito.when(gatewayUserRepo.save(Mockito.any(GatewayUser.class))).thenReturn(newGatewayUser);

        CreateResponseDto result = userService.createUserGateway(dto);

        Assertions.assertEquals(newUser.getId(), result.getId());
        Mockito.verify(principalService, Mockito.atLeast(1)).getPrincipal();
        Mockito.verify(userRoleRepo, Mockito.atLeast(1)).findByCode(Mockito.any());
        Mockito.verify(userRepo, Mockito.atLeast(1)).findByEmail(dto.getEmail());
        Mockito.verify(passwordEncoder, Mockito.atLeast(1)).encode(dto.getPassword());
        Mockito.verify(userRepo, Mockito.atLeast(1)).save(Mockito.any(User.class));
        Mockito.verify(gatewayRepo, Mockito.atLeast(1)).findById(Mockito.any(UUID.class));
        Mockito.verify(gatewayUserRepo, Mockito.atLeast(1)).save(Mockito.any(GatewayUser.class));
    }

    @Test
    public void shouldReturnAllUserCustomer_whenExist() {
        List<User> customerList = List.of(user1, user2);

        Mockito.when(userRoleRepo.findByCode(Mockito.any())).thenReturn(Optional.of(customerRole));
        Mockito.when(userRepo.findAllByUserRole_Id(customerRole.getId())).thenReturn(customerList);

        List<UserResponseDto> result = userService.getAllUserCustomers();

        Assertions.assertEquals(customerList.size(), result.size());

        Assertions.assertEquals(user1.getId(), result.getFirst().getId());
        Assertions.assertEquals(user1.getName(), result.getFirst().getName());
        Mockito.verify(userRoleRepo, Mockito.atLeast(1)).findByCode(Mockito.any());
        Mockito.verify(userRepo, Mockito.atLeast(1)).findAllByUserRole_Id(customerRole.getId());
    }

    @Test
    public void shouldReturnAllUserGateway_whenExist() {
        List<GatewayUser> gatewayUserList = List.of(gatewayUser1, gatewayUser2);

        Mockito.when(userRoleRepo.findByCode(Mockito.any())).thenReturn(Optional.of(gatewayRole));
        Mockito.when(gatewayUserRepo.findAllByUser_UserRole_Id(gatewayRole.getId())).thenReturn(gatewayUserList);

        List<UserGatewayResponseDto> result = userService.getAllUserGateways();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(gatewayUserList.size(), result.size());

        Assertions.assertEquals(gatewayUser1.getId(), result.getFirst().getId());
        Assertions.assertEquals(gatewayUser1.getUser().getName(), result.getFirst().getName());

        Mockito.verify(userRoleRepo, Mockito.atLeast(1)).findByCode(Mockito.any());
        Mockito.verify(gatewayUserRepo, Mockito.atLeast(1)).findAllByUser_UserRole_Id(gatewayRole.getId());
    }

}
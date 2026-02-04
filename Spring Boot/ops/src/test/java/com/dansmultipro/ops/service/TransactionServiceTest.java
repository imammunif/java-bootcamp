package com.dansmultipro.ops.service;

import com.dansmultipro.ops.dto.transaction.CreateTransactionRequestDto;
import com.dansmultipro.ops.dto.transaction.TransactionResponseDto;
import com.dansmultipro.ops.model.*;
import com.dansmultipro.ops.pojo.AuthorizationPoJo;
import com.dansmultipro.ops.repository.*;
import com.dansmultipro.ops.service.impl.TransactionServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock
    private UserRepo userRepo;
    @Mock
    private GatewayRepo gatewayRepo;
    @Mock
    private ProductRepo productRepo;
    @Mock
    private GatewayUserRepo gatewayUserRepo;
    @Mock
    private TransactionRepo transactionRepo;
    @Mock
    private TransactionStatusHistoryRepo transactionStatusHistoryRepo;
    @Mock
    private TransactionStatusRepo transactionStatusRepo;
    @Mock
    private RabbitTemplate rabbitTemplate;
    @Mock
    protected PrincipalService principalService;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    private AuthorizationPoJo authPojo;
    private Product product;
    private Gateway gateway;
    private User user1;
    private User user2;
    private GatewayUser gatewayUser;
    private Transaction transaction1;
    private Transaction transaction2;
    private TransactionStatus status;
    private TransactionStatusHistory history1;

    @BeforeEach
    public void setup() {
        transactionService.setPrincipalService(principalService);

        user1 = new User();
        user1.setId(UUID.randomUUID());
        user1.setName("USER1");
        user1.setEmail("user1@mail.com");

        user2 = new User();
        user2.setId(UUID.randomUUID());
        user2.setName("USER2");
        user2.setEmail("user2@mail.com");

        authPojo = new AuthorizationPoJo(user1.getId());

        status = new TransactionStatus();
        status.setId(UUID.randomUUID());
        status.setCode("PROCESS");
        status.setName("In Process");

        gateway = new Gateway();
        gateway.setId(UUID.randomUUID());
        gateway.setName("GATEWAY");

        gatewayUser = new GatewayUser();
        gatewayUser.setGateway(gateway);
        gatewayUser.setUser(user1);

        product = new Product();
        product.setId(UUID.randomUUID());
        product.setName("PRODUCT");

        transaction1 = new Transaction();
        transaction1.setId(UUID.randomUUID());
        transaction1.setCode("TRX001");
        transaction1.setTotalBill(new BigDecimal("13999"));
        transaction1.setAccountNumber("081234567");
        transaction1.setStatus(status);
        transaction1.setCustomer(user1);
        transaction1.setGateway(gateway);
        transaction1.setProduct(product);

        transaction2 = new Transaction();
        transaction2.setId(UUID.randomUUID());
        transaction2.setCode("TRX002");
        transaction2.setTotalBill(new BigDecimal("71999"));
        transaction2.setAccountNumber("081234568");
        transaction2.setStatus(status);
        transaction2.setCustomer(user2);
        transaction2.setGateway(gateway);
        transaction2.setProduct(product);

        history1 = new TransactionStatusHistory();
        history1.setId(UUID.randomUUID());
        history1.setStatus(status);
        history1.setTransaction(transaction1);
    }

    @Test
    public void shouldCreated_whenDataValid() {
        var dto = new CreateTransactionRequestDto();
        dto.setAccountNumber("081234568974");
        dto.setAmount(new BigDecimal("13900"));
        dto.setProductId(product.getId().toString());
        dto.setGatewayId(gateway.getId().toString());

        Mockito.when(principalService.getPrincipal()).thenReturn(authPojo);
        Mockito.when(userRepo.findById(Mockito.any())).thenReturn(Optional.of(user1));
        Mockito.when(transactionStatusRepo.findByCode(Mockito.any())).thenReturn(Optional.of(status));
        Mockito.when(gatewayRepo.findById(Mockito.any())).thenReturn(Optional.of(gateway));
        Mockito.when(productRepo.findById(Mockito.any())).thenReturn(Optional.of(product));
        Mockito.when(transactionRepo.save(Mockito.any())).thenReturn(transaction1);
        Mockito.when(transactionStatusHistoryRepo.save(Mockito.any())).thenReturn(history1);

        var result = transactionService.create(dto);

        Assertions.assertEquals(transaction1.getId(), result.getId());

        Mockito.verify(principalService, Mockito.atLeast(1)).getPrincipal();
        Mockito.verify(userRepo, Mockito.atLeast(1)).findById(Mockito.any());
        Mockito.verify(transactionStatusRepo, Mockito.atLeast(1)).findByCode(Mockito.any());
        Mockito.verify(gatewayRepo, Mockito.atLeast(1)).findById(Mockito.any());
        Mockito.verify(productRepo, Mockito.atLeast(1)).findById(Mockito.any());
        Mockito.verify(transactionRepo, Mockito.atLeast(1)).save(Mockito.any());
        Mockito.verify(transactionStatusHistoryRepo, Mockito.atLeast(1)).save(Mockito.any());
        Mockito.verify(rabbitTemplate, Mockito.atLeast(1)).convertAndSend(Mockito.any(), Mockito.any(), Mockito.any(Object.class));
    }

    @Test
    public void shouldReturnAllCustomerTransaction_whenExist() {
        List<Transaction> transactionList = List.of(transaction1, transaction2);

        Mockito.when(principalService.getPrincipal()).thenReturn(authPojo);
        Mockito.when(userRepo.findById(Mockito.any())).thenReturn(Optional.of(user1));
        Mockito.when(transactionRepo.findByCustomerId(Mockito.any())).thenReturn(transactionList);

        List<TransactionResponseDto> result = transactionService.getAllByCustomerId();

        Assertions.assertEquals(transactionList.size(), result.size());
        Assertions.assertEquals("TRX001", result.getFirst().getCode());
        Mockito.verify(principalService, Mockito.atLeast(1)).getPrincipal();
        Mockito.verify(userRepo, Mockito.atLeast(1)).findById(Mockito.any());
        Mockito.verify(transactionRepo, Mockito.atLeast(1)).findByCustomerId(authPojo.getId());
    }

    @Test
    public void shouldReturnAllGatewayTransaction_whenExist() {
        List<Transaction> transactionList = List.of(transaction1, transaction2);

        Mockito.when(principalService.getPrincipal()).thenReturn(authPojo);
        Mockito.when(gatewayUserRepo.findByUserId(Mockito.any())).thenReturn(Optional.of(gatewayUser));
        Mockito.when(transactionRepo.findByGatewayId(Mockito.any())).thenReturn(transactionList);

        List<TransactionResponseDto> result = transactionService.getAllByGatewayId();

        Assertions.assertEquals(transactionList.size(), result.size());
        Assertions.assertEquals("TRX001", result.getFirst().getCode());
        Mockito.verify(principalService, Mockito.atLeast(1)).getPrincipal();
        Mockito.verify(gatewayUserRepo, Mockito.atLeast(1)).findByUserId(Mockito.any());
        Mockito.verify(transactionRepo, Mockito.atLeast(1)).findByGatewayId(gateway.getId());
    }

}
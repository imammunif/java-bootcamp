package com.dansmultipro.ops.service;

import com.dansmultipro.ops.dto.transaction.CreateTransactionRequestDto;
import com.dansmultipro.ops.model.*;
import com.dansmultipro.ops.pojo.AuthorizationPoJo;
import com.dansmultipro.ops.repository.*;
import com.dansmultipro.ops.service.impl.TransactionServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.math.BigDecimal;
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

    @Test
    public void shouldCreated_whenDataValid() {
        transactionService.setPrincipalService(principalService);
        var authPojo = new AuthorizationPoJo(UUID.randomUUID());

        var user = new User();
        UUID userId = UUID.randomUUID();
        user.setId(userId);

        var status = new TransactionStatus();
        UUID statusId = UUID.randomUUID();
        status.setId(statusId);
        status.setCode("PROCESS");

        var gateway = new Gateway();
        UUID gatewayId = UUID.randomUUID();
        gateway.setId(gatewayId);
        gateway.setVersion(0);
        gateway.setName("GATEWAY");

        var product = new Product();
        UUID productId = UUID.randomUUID();
        product.setId(productId);
        product.setVersion(0);
        product.setName("PRODUCT");

        var dto = new CreateTransactionRequestDto();
        dto.setAccountNumber("081234568974");
        dto.setAmount(new BigDecimal("13900"));
        dto.setProductId(productId.toString());
        dto.setGatewayId(gatewayId.toString());

        var transactionSaved = new Transaction();
        var id = UUID.randomUUID();
        transactionSaved.setId(id);

        var statusHistory = new TransactionStatusHistory();
        var historyId = UUID.randomUUID();
        statusHistory.setId(historyId);

        Mockito.when(principalService.getPrincipal()).thenReturn(authPojo);
        Mockito.when(userRepo.findById(Mockito.any())).thenReturn(Optional.of(user));
        Mockito.when(transactionStatusRepo.findByCode(Mockito.any())).thenReturn(Optional.of(status));
        Mockito.when(gatewayRepo.findById(Mockito.any())).thenReturn(Optional.of(gateway));
        Mockito.when(productRepo.findById(Mockito.any())).thenReturn(Optional.of(product));
        Mockito.when(transactionRepo.save(Mockito.any())).thenReturn(transactionSaved);
        Mockito.when(transactionStatusHistoryRepo.save(Mockito.any())).thenReturn(statusHistory);

        var result = transactionService.create(dto);

        Assertions.assertEquals(id, result.getId());
        Mockito.verify(principalService, Mockito.atLeast(1)).getPrincipal();
        Mockito.verify(userRepo, Mockito.atLeast(1)).findById(Mockito.any());
        Mockito.verify(transactionStatusRepo, Mockito.atLeast(1)).findByCode(Mockito.any());
        Mockito.verify(gatewayRepo, Mockito.atLeast(1)).findById(Mockito.any());
        Mockito.verify(productRepo, Mockito.atLeast(1)).findById(Mockito.any());
        Mockito.verify(transactionRepo, Mockito.atLeast(1)).save(Mockito.any());
        Mockito.verify(transactionStatusHistoryRepo, Mockito.atLeast(1)).save(Mockito.any());
        Mockito.verify(rabbitTemplate, Mockito.times(1))
                .convertAndSend(Mockito.anyString(), Mockito.anyString(), Mockito.any(Object.class));
    }

}

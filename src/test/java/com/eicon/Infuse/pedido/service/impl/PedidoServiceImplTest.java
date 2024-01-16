package com.eicon.Infuse.pedido.service.impl;

import com.eicon.Infuse.cliente.entity.Cliente;
import com.eicon.Infuse.cliente.repository.ClienteRepository;
import com.eicon.Infuse.pedido.entity.Pedido;
import com.eicon.Infuse.pedido.entity.dto.PedidoRequest;
import com.eicon.Infuse.pedido.repository.PedidoRepository;
import com.eicon.Infuse.pedido.repository.ProdutoPedidoAssociacaoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class PedidoServiceImplTest {

    @Mock
    private ProdutoPedidoAssociacaoRepository produtoPedidoAssociacaoRepository;
    @Mock
    private ClienteRepository clienteRepository;
    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private PedidoServiceImpl pedidoService;

    @Nested
    class save {

        @Test
        @DisplayName("Should create a pedido with success")
        void deveSalvarPedidoRepository() {
            //Arrange
            var cliente = new Cliente();
            cliente.setId(2L);

            var pedido = new Pedido(1L, cliente, new BigDecimal("10.00"), new BigDecimal("01"), new BigDecimal("0.01"));

            doReturn(pedido).when(pedidoRepository).save(any());
            var input = new PedidoRequest(
                    LocalDate.now(),
                    new BigDecimal("10.10"),
                    2L,
                    null,
                    null
            );
            //Act
            var output = pedidoRepository.save(pedido);

            //Assert
            assertNotNull(output);
        }
    }

}
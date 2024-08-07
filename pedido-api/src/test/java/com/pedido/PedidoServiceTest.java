package com.pedido;

import com.pedido.model.Pedido;
import com.pedido.repository.PedidoRepository;
import com.pedido.service.PedidoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

public class PedidoServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private PedidoService pedidoService;

    private Pedido pedido;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pedido = new Pedido();
        pedido.setId(1L);
        pedido.setNomeProduto("Produto Teste");
        pedido.setValorUnitario(100.0);
    }

    @Test
    void buscarPedidoPorId_pedidoEncontrado() {
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));

        Pedido resultado = pedidoService.buscarPedidoPorId(1L);

        assertNotNull(resultado, "O pedido retornado não deve ser nulo");
        assertEquals("Produto Teste", resultado.getNomeProduto());
        assertEquals(100.0, resultado.getValorUnitario());
    }

    @Test
    void buscarPedidoPorId_pedidoNaoEncontrado() {
        when(pedidoRepository.findById(1L)).thenReturn(Optional.empty());

        Pedido resultado = pedidoService.buscarPedidoPorId(1L);

        assertNull(resultado, "O pedido retornado deve ser nulo quando não encontrado");
    }

    @Test
    void listarPedidos() {
        Pedido pedido1 = new Pedido();
        pedido1.setId(1L);
        pedido1.setNomeProduto("Produto 1");
        pedido1.setValorUnitario(50.0);

        Pedido pedido2 = new Pedido();
        pedido2.setId(2L);
        pedido2.setNomeProduto("Produto 2");
        pedido2.setValorUnitario(150.0);

        List<Pedido> pedidos = Arrays.asList(pedido1, pedido2);

        when(pedidoRepository.findAll()).thenReturn(pedidos);

        List<Pedido> resultado = pedidoService.listarPedidos();

        assertNotNull(resultado, "A lista de pedidos não deve ser nula");
        assertEquals(2, resultado.size(), "A lista de pedidos deve conter 2 elementos");

        Pedido pedidoResultado1 = resultado.get(0);
        assertEquals("Produto 1", pedidoResultado1.getNomeProduto());
        assertEquals(50.0, pedidoResultado1.getValorUnitario());

        Pedido pedidoResultado2 = resultado.get(1);
        assertEquals("Produto 2", pedidoResultado2.getNomeProduto());
        assertEquals(150.0, pedidoResultado2.getValorUnitario());
    }
}

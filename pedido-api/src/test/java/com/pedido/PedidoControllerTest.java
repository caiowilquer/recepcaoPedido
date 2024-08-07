package com.pedido;

import com.pedido.controller.PedidoController;
import com.pedido.model.Pedido;
import com.pedido.service.PedidoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PedidoController.class)
public class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PedidoService pedidoService;

    private Pedido pedido;

    @BeforeEach
    void setUp() {
        pedido = new Pedido();
        pedido.setId(1L);
        pedido.setNomeProduto("Produto Teste");
        pedido.setValorUnitario(100.0);
        pedido.setQuantidade(2);
    }

    @Test
    void criarPedido_comSucesso() throws Exception {
        when(pedidoService.salvarPedido(any(Pedido.class))).thenReturn(pedido);

        mockMvc.perform(post("/api/processar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"pedido\":{\"nomeProduto\":\"Produto Teste\",\"valorUnitario\":100.0,\"quantidade\":2}}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomeProduto").value("Produto Teste"))
                .andExpect(jsonPath("$.valorUnitario").value(100.0))
                .andExpect(jsonPath("$.quantidade").value(2));
    }

    @Test
    void criarPedidos_comSucesso() throws Exception {
        Pedido pedido1 = new Pedido();
        pedido1.setId(1L);
        pedido1.setNomeProduto("Produto 1");
        pedido1.setValorUnitario(50.0);

        Pedido pedido2 = new Pedido();
        pedido2.setId(2L);
        pedido2.setNomeProduto("Produto 2");
        pedido2.setValorUnitario(150.0);

        List<Pedido> pedidos = Arrays.asList(pedido1, pedido2);

        when(pedidoService.salvarPedidos(anyList())).thenReturn(pedidos);

        mockMvc.perform(post("/api/processar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"pedidos\":[{\"nomeProduto\":\"Produto 1\",\"valorUnitario\":50.0}," +
                                "{\"nomeProduto\":\"Produto 2\",\"valorUnitario\":150.0}]}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nomeProduto").value("Produto 1"))
                .andExpect(jsonPath("$[0].valorUnitario").value(50.0))
                .andExpect(jsonPath("$[1].nomeProduto").value("Produto 2"))
                .andExpect(jsonPath("$[1].valorUnitario").value(150.0));
    }

}

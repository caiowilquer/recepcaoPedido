package com.pedido.controller;

import com.pedido.model.Pedido;
import com.pedido.model.PedidoRequest;
import com.pedido.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping("/processar")
    public ResponseEntity<?> processarDados(@RequestBody PedidoRequest payload) {
        if (payload.getPedido() != null) {
            // Processar um único pedido
            Pedido pedidoSalvo = pedidoService.salvarPedido(payload.getPedido());
            return ResponseEntity.ok(pedidoSalvo);
        } else if (payload.getPedidos() != null) {
            // Processar uma lista de pedidos
            List<Pedido> pedidosSalvos = pedidoService.salvarPedidos(payload.getPedidos());
            return ResponseEntity.ok(pedidosSalvos);
        }
        return ResponseEntity.badRequest().body("Tipo de dado inválido");
    }

    @GetMapping("/pedidos")
    public ResponseEntity<List<Pedido>> listarPedidos() {
        List<Pedido> pedidos = pedidoService.listarPedidos();
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/pedido/{id}")
    public ResponseEntity<Pedido> buscarPedidoPorId(@PathVariable Long id) {
        Pedido pedido = pedidoService.buscarPedidoPorId(id);
        if (pedido != null) {
            return ResponseEntity.ok(pedido);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

package com.pedido.model;

import java.util.List;

public class PedidoRequest {
    private Pedido pedido;
    private List<Pedido> pedidos;

    // Getters e Setters
    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}

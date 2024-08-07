package com.pedido.model;

import java.util.List;

public class PedidosResponse {
    private String mensagem;
    private List<Pedido> pedidosDuplicados;
    private List<Pedido> pedidosSalvos;

    // Getters e setters
    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public List<Pedido> getPedidosDuplicados() {
        return pedidosDuplicados;
    }

    public void setPedidosDuplicados(List<Pedido> pedidosDuplicados) {
        this.pedidosDuplicados = pedidosDuplicados;
    }

    public List<Pedido> getPedidosSalvos() {
        return pedidosSalvos;
    }

    public void setPedidosSalvos(List<Pedido> pedidosSalvos) {
        this.pedidosSalvos = pedidosSalvos;
    }
}


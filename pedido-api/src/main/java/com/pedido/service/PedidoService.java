package com.pedido.service;

import com.pedido.model.Pedido;
import com.pedido.model.Cliente;
import com.pedido.repository.PedidoRepository;
import com.pedido.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public Pedido salvarPedido(Pedido pedido) {
        if (pedido.getCliente() == null) {
            throw new IllegalArgumentException("O cliente não pode ser nulo.");
        }

        Cliente cliente = pedido.getCliente();

        if (cliente.getCodigo() == null) {
            throw new IllegalArgumentException("O código do cliente não pode ser nulo.");
        }

        if (cliente.getNome() == null || cliente.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do cliente não pode ser nulo ou vazio.");
        }

        // Verifica se o cliente existe no banco
        Cliente clienteExistente = clienteRepository.findById(cliente.getCodigo()).orElse(null);
        if (clienteExistente == null) {
            // Se o cliente não existir, cria um novo cliente com dados default
            clienteExistente = new Cliente();
            clienteExistente.setCodigo(cliente.getCodigo());
            clienteExistente.setNome(cliente.getNome()); // Nome do cliente
            clienteRepository.save(clienteExistente);
        }

        // Atribui o cliente ao pedido
        pedido.setCliente(clienteExistente);

        if (pedido.getId() != null && pedidoRepository.existsById(pedido.getId())) {
            throw new IllegalArgumentException("Pedido com ID " + pedido.getId() + " já cadastrado.");
        }

        if (pedido.getDataCadastro() == null) {
            pedido.setDataCadastro(LocalDate.now());
        }

        if (pedido.getQuantidade() == null) {
            pedido.setQuantidade(1);
        }

        if (pedido.getValorUnitario() == null) {
            pedido.setValorUnitario(0.0);
        }

        double valorTotal = pedido.getValorUnitario() * pedido.getQuantidade();

        if (pedido.getQuantidade() >= 10) {
            valorTotal *= 0.9;
        } else if (pedido.getQuantidade() > 5) {
            valorTotal *= 0.95;
        }

        pedido.setValorTotal(valorTotal);

        return pedidoRepository.save(pedido);
    }

    public List<Pedido> salvarPedidos(List<Pedido> pedidos) {
        // Salva todos os pedidos
        return pedidos.stream()
                .map(this::salvarPedido)
                .toList(); // Use a API de streams para salvar pedidos e coletar o resultado
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido buscarPedidoPorId(Long id) {
        return pedidoRepository.findById(id).orElse(null);
    }
}

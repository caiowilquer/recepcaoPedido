package com.pedido.repository;

import com.pedido.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}



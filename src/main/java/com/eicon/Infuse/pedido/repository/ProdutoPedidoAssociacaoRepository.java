package com.eicon.Infuse.pedido.repository;

import com.eicon.Infuse.pedido.entity.ProdutoPedidoAssociacao;
import com.eicon.Infuse.pedido.entity.ProdutoPedidoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoPedidoAssociacaoRepository extends JpaRepository<ProdutoPedidoAssociacao, ProdutoPedidoId> {

    @Query(value = "Select * from rl_produto_pedido where pedido_id = :idPedido", nativeQuery = true)
    List<ProdutoPedidoAssociacao> findAllIdPedido(@Param("idPedido") Long idPedido);
}

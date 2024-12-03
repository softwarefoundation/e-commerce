package br.com.devchampions.ecommerce.repository;

import br.com.devchampions.ecommerce.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}

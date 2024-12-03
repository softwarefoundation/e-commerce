package br.com.devchampions.ecommerce.services;

import br.com.devchampions.ecommerce.entity.Produto;
import br.com.devchampions.ecommerce.entity.dto.ProdutoDto;
import br.com.devchampions.ecommerce.repository.ProdutoRepository;
import br.com.devchampions.ecommerce.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;


    public Produto cadastrar(ProdutoDto produtoDto) {

        return this.produtoRepository.save(Converter.getInstance().converter(produtoDto, Produto.class));

    }

}

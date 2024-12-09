package br.com.devchampions.ecommerce.controller;

import br.com.devchampions.ecommerce.controller.validations.ProdutoValidacao;
import br.com.devchampions.ecommerce.entity.dto.ProdutoDto;
import br.com.devchampions.ecommerce.services.ProdutoService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<?> cadastro(@Valid @RequestBody ProdutoDto produtoDto, BindingResult result) {

        log.info("Iniciando cadastro de produto");

        ProdutoValidacao.rotinaPreValidacaoCadastroProduto(produtoDto);

        this.produtoService.cadastrar(produtoDto);

        return ResponseEntity.ok().build();
    }

}

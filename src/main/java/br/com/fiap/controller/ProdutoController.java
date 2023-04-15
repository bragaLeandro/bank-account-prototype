package br.com.fiap.controller;

import br.com.fiap.constants.CommonConstants;
import br.com.fiap.dto.ProdutoDto;
import br.com.fiap.entity.Produto;
import br.com.fiap.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProdutoController {

    private final ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody ProdutoDto produtoDto) {
        try {
            produtoService.createProduct(produtoDto.toEntity(produtoDto));
            return ResponseEntity.ok().body(CommonConstants.SUCCESS_MESSAGE);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }

    @PutMapping("/inactivate")
    public ResponseEntity<String> inactivateProduct(@RequestParam("id") UUID uuid) {
        try {
            this.produtoService.inactivateProduct(uuid);
            return ResponseEntity.ok("Produto desativado");
        } catch (IllegalArgumentException ie) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ie.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @PutMapping("/activate")
    public ResponseEntity<String> activateProduct(@RequestParam("id") UUID uuid) {
        try {
            this.produtoService.activateProduct(uuid);
            return ResponseEntity.ok("Produto ativado");
        } catch (IllegalArgumentException ie) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ie.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @GetMapping("/all")
    public Collection<Produto> findAll() {
        return produtoService.findAll();
    }


}

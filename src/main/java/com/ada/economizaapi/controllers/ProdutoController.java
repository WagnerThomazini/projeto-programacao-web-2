package com.ada.economizaapi.controllers;

import com.ada.economizaapi.services.ProdutoService;
import com.ada.economizaapi.entities.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<Produto> findAll() {
        return produtoService.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Optional<Produto> findById(@PathVariable String id) {
        Long productId = Long.parseLong(id);
        return produtoService.findById(productId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public Produto post(@RequestBody Produto produto) {
        return produtoService.save(produto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public Produto update(@PathVariable Long id, @RequestBody Produto produto) {
        return produtoService.update(id, produto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        produtoService.deleteById(id);
    }
}

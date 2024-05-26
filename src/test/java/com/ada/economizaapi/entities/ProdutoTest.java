package com.ada.economizaapi.entities;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoTest {

    @Test
    void testeConstrutorSemArgumentos() {
        Produto produto = new Produto();
        assertNotNull(produto);
        assertNull(produto.getNome());
        assertNull(produto.getMarca());
        assertNull(produto.getDescricao());
        assertNotNull(produto.getProdutoPrecos());
        assertEquals(0, produto.getProdutoPrecos().size());
    }

    @Test
    void testeConstrutorComTodosArgumentos() {
        Produto produto = new Produto(1L, "Produto 1", "Marca 1", "Descrição 1", new ArrayList<>());
        assertNotNull(produto);
        assertEquals(1L, produto.getId());
        assertEquals("Produto 1", produto.getNome());
        assertEquals("Marca 1", produto.getMarca());
        assertEquals("Descrição 1", produto.getDescricao());
        assertNotNull(produto.getProdutoPrecos());
        assertEquals(0, produto.getProdutoPrecos().size());
    }

    @Test
    void testeConstrutorComArgumentos() {
        Produto produto = new Produto("Produto 1", "Marca 1", "Descrição 1");
        assertNotNull(produto);
        assertEquals("Produto 1", produto.getNome());
        assertEquals("Marca 1", produto.getMarca());
        assertEquals("Descrição 1", produto.getDescricao());
        assertEquals(0, produto.getProdutoPrecos().size());
    }

    @Test
    void testeGetId() {
        Produto produto = new Produto(1L, "Produto 1", "Marca 1", "Descrição 1", new ArrayList<>());
        assertEquals(1L, produto.getId());
    }

    @Test
    void testeSetId() {
        Produto produto = new Produto();
        produto.setId(1L);
        assertEquals(1L, produto.getId());
    }

    @Test
    void testeSetIdComNull() {
        Produto produto = new Produto();
        assertThrows(NullPointerException.class, () -> produto.setId(null));
    }

    @Test
    void testeGetNome() {
        Produto produto = new Produto("Produto 1", "Marca 1", "Descrição 1");
        assertEquals("Produto 1", produto.getNome());
    }

    @Test
    void testeSetNome() {
        Produto produto = new Produto();
        produto.setNome("Produto 1");
        assertEquals("Produto 1", produto.getNome());
    }

    @Test
    void testeSetNomeComNull() {
        Produto produto = new Produto();
        produto.setNome(null);
        assertNull(produto.getNome());
    }

    @Test
    void testeGetMarca() {
        Produto produto = new Produto("Produto 1", "Marca 1", "Descrição 1");
        assertEquals("Marca 1", produto.getMarca());
    }

    @Test
    void testeSetMarca() {
        Produto produto = new Produto();
        produto.setMarca("Marca 1");
        assertEquals("Marca 1", produto.getMarca());
    }

    @Test
    void testeSetMarcaComNull() {
        Produto produto = new Produto();
        produto.setMarca(null);
        assertNull(produto.getMarca());
    }

    @Test
    void testeGetDescricao() {
        Produto produto = new Produto("Produto 1", "Marca 1", "Descrição 1");
        assertEquals("Descrição 1", produto.getDescricao());
    }

    @Test
    void testeSetDescricao() {
        Produto produto = new Produto();
        produto.setDescricao("Descrição 1");
        assertEquals("Descrição 1", produto.getDescricao());
    }

    @Test
    void testeSetDescricaoComNull() {
        Produto produto = new Produto();
        produto.setDescricao(null);
        assertNull(produto.getDescricao());
    }

    @Test
    void testeGetProdutoPrecos() {
        Produto produto = new Produto();
        assertNotNull(produto.getProdutoPrecos());
        assertEquals(0, produto.getProdutoPrecos().size());
    }

    @Test
    void testeSetProdutoPrecos() {
        Produto produto = new Produto();
        List<ProdutoPreco> precos = new ArrayList<>();
        produto.setProdutoPrecos(precos);
        assertEquals(precos, produto.getProdutoPrecos());
    }

    @Test
    void testeSetProdutoPrecosComNull() {
        Produto produto = new Produto();
        assertThrows(NullPointerException.class, () -> produto.setProdutoPrecos(null));
    }

    @Test
    void testeToString() {
        Produto produto = new Produto(1L, "Produto 1", "Marca 1", "Descrição 1", new ArrayList<>());
        String expected = "Produto [id=1, nome='Produto 1', marca='Marca 1', descricao='Descrição 1', produtoPrecos=[]]";
        assertEquals(expected, produto.toString());
    }

    @Test
    void testeEqualsComProdutoIgual() {
        Produto produto1 = new Produto(1L, "Produto 1", "Marca 1", "Descrição 1", new ArrayList<>());
        Produto produto2 = new Produto(1L, "Produto 1", "Marca 1", "Descrição 1", new ArrayList<>());
        assertEquals(produto1, produto2);
    }

    @Test
    void testeEqualsComProdutoDiferente() {
        Produto produto1 = new Produto(1L, "Produto 1", "Marca 1", "Descrição 1", new ArrayList<>());
        Produto produto2 = new Produto(2L, "Produto 2", "Marca 2", "Descrição 2", new ArrayList<>());
        assertNotEquals(produto1, produto2);
    }

    @Test
    void testeEqualsComObjetoNull() {
        Produto produto = new Produto(1L, "Produto 1", "Marca 1", "Descrição 1", new ArrayList<>());
        assertNotEquals(produto, null);
    }

    @Test
    void testeEqualsComObjetoDeOutraClasse() {
        Produto produto = new Produto(1L, "Produto 1", "Marca 1", "Descrição 1", new ArrayList<>());
        String texto = "Produto 1";
        assertNotEquals(produto, texto);
    }

    @Test
    void testeHashCodeComProdutosIguais() {
        Produto produto1 = new Produto(1L, "Produto 1", "Marca 1", "Descrição 1", new ArrayList<>());
        Produto produto2 = new Produto(1L, "Produto 1", "Marca 1", "Descrição 1", new ArrayList<>());
        assertEquals(produto1.hashCode(), produto2.hashCode());
    }

    @Test
    void testeHashCodeComProdutosDiferentes() {
        Produto produto1 = new Produto(1L, "Produto 1", "Marca 1", "Descrição 1", new ArrayList<>());
        Produto produto2 = new Produto(2L, "Produto 2", "Marca 2", "Descrição 2", new ArrayList<>());
        assertNotEquals(produto1.hashCode(), produto2.hashCode());
    }
}
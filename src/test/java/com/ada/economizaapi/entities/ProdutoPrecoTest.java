package com.ada.economizaapi.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ProdutoPrecoTest {

    private Produto produto;
    private Mercado mercado;
    private ProdutoPreco produtoPreco;

    @BeforeEach
    public void setUp() {
        produto = new Produto();
        produto.setId(1L);
        produto.setNome("Produto Teste");

        mercado = new Mercado();
        mercado.setId(1L);
        mercado.setNome("Mercado Teste");

        produtoPreco = new ProdutoPreco();
        produtoPreco.setId(1L);
        produtoPreco.setProduto(produto);
        produtoPreco.setMercado(mercado);
        produtoPreco.setPreco(10.0);
        produtoPreco.setDataAtualizacao(LocalDate.of(2023, 1, 1));
    }

    @Test
    public void testDefaultConstructor() {
        ProdutoPreco pp = new ProdutoPreco();
        assertNull(pp.getId());
        assertNull(pp.getProduto());
        assertNull(pp.getMercado());
        assertNull(pp.getPreco());
        assertNull(pp.getDataAtualizacao());
    }

    @Test
    public void testConstructorWithProdutoAndPreco() {
        ProdutoPreco pp = new ProdutoPreco(produto, 15.0);
        assertNull(pp.getId());
        assertEquals(produto, pp.getProduto());
        assertNull(pp.getMercado());
        assertEquals(15.0, pp.getPreco());
        assertNotNull(pp.getDataAtualizacao());
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals(1L, produtoPreco.getId());
        assertEquals(produto, produtoPreco.getProduto());
        assertEquals(mercado, produtoPreco.getMercado());
        assertEquals(10.0, produtoPreco.getPreco());
        assertEquals(LocalDate.of(2023, 1, 1), produtoPreco.getDataAtualizacao());

        Produto newProduto = new Produto();
        newProduto.setId(2L);
        newProduto.setNome("Produto Novo");

        Mercado newMercado = new Mercado();
        newMercado.setId(2L);
        newMercado.setNome("Mercado Novo");

        produtoPreco.setProduto(newProduto);
        produtoPreco.setMercado(newMercado);
        produtoPreco.setPreco(20.0);
        produtoPreco.setDataAtualizacao(LocalDate.of(2024, 1, 1));

        assertEquals(newProduto, produtoPreco.getProduto());
        assertEquals(newMercado, produtoPreco.getMercado());
        assertEquals(20.0, produtoPreco.getPreco());
        assertEquals(LocalDate.of(2024, 1, 1), produtoPreco.getDataAtualizacao());
    }

    @Test
    public void testEquals() {
        ProdutoPreco pp1 = new ProdutoPreco();
        pp1.setId(1L);

        ProdutoPreco pp2 = new ProdutoPreco();
        pp2.setId(1L);

        ProdutoPreco pp3 = new ProdutoPreco();
        pp3.setId(2L);

        assertEquals(pp1, pp2);
        assertNotEquals(pp1, pp3);
        assertNotEquals(pp2, pp3);
    }

    @Test
    public void testHashCode() {
        ProdutoPreco pp1 = new ProdutoPreco();
        pp1.setId(1L);

        ProdutoPreco pp2 = new ProdutoPreco();
        pp2.setId(1L);

        ProdutoPreco pp3 = new ProdutoPreco();
        pp3.setId(2L);

        assertEquals(pp1.hashCode(), pp2.hashCode());
        assertNotEquals(pp1.hashCode(), pp3.hashCode());
    }

    @Test
    public void testToString() {
        String expected = "ProdutoPreco(id=1, produto=Produto [id=1, nome='Produto Teste', marca='null', descricao='null', produtoPrecos=[]], mercado=Mercado(id=1, nome=Mercado Teste, localizacao=null), preco=10.0, dataAtualizacao=2023-01-01)";
        assertEquals(expected, produtoPreco.toString());
    }
}

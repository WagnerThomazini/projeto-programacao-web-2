package com.ada.economizaapi.entities;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ListaCompraTest {

    @Test
    void testeIgualdade() {
        ListaCompra listaCompra1 = new ListaCompra();
        ListaCompra listaCompra2 = new ListaCompra();
        assertEquals(listaCompra1, listaCompra2);
    }

    @Test
    void testeHashCode() {
        ListaCompra listaCompra = new ListaCompra();
        assertEquals(0, listaCompra.hashCode());
    }

    @Test
    void testeMercadoMaisEconomicoListaVazia() {
        ListaCompra listaCompra = new ListaCompra();
        List<Mercado> mercados = new ArrayList<>();
        Mercado mercadoMaisEconomico = listaCompra.mercadoMaisEconomico(mercados);
        assertNull(mercadoMaisEconomico);
    }

    @Test
    void testeMercadoMaisEconomicoSemPrecos() {
        ListaCompra listaCompra = new ListaCompra();
        Produto produto1 = new Produto("Produto 1", "Marca 1", "Descrição 1");
        Produto produto2 = new Produto("Produto 2", "Marca 2", "Descrição 2");
        listaCompra.getProdutos().add(produto1);
        listaCompra.getProdutos().add(produto2);

        List<Mercado> mercados = new ArrayList<>();
        Mercado mercado1 = new Mercado();
        Mercado mercado2 = new Mercado();
        mercados.add(mercado1);
        mercados.add(mercado2);

        assertNull(listaCompra.mercadoMaisEconomico(mercados));
    }

    @Test
    void testeMercadoMaisEconomicoPrecosDiferentes() {
        ListaCompra listaCompra = new ListaCompra();
        Produto produto1 = mock(Produto.class);
        Produto produto2 = mock(Produto.class);
        listaCompra.getProdutos().add(produto1);
        listaCompra.getProdutos().add(produto2);

        List<Mercado> mercados = new ArrayList<>();
        Mercado mercado1 = mock(Mercado.class);
        Mercado mercado2 = mock(Mercado.class);
        ProdutoPreco preco1 = new ProdutoPreco(produto1, 10.0);
        ProdutoPreco preco2 = new ProdutoPreco(produto2, 15.0);
        when(mercado1.getProdutoPreco(produto1)).thenReturn(preco1);
        when(mercado2.getProdutoPreco(produto2)).thenReturn(preco2);
        mercados.add(mercado1);
        mercados.add(mercado2);

        assertEquals(mercado1, listaCompra.mercadoMaisEconomico(mercados));
    }

    @Test
    void testeMercadoMaisEconomicoUmMercadoComPrecos() {
        ListaCompra listaCompra = new ListaCompra();
        Produto produto1 = mock(Produto.class);
        Produto produto2 = mock(Produto.class);
        listaCompra.getProdutos().add(produto1);
        listaCompra.getProdutos().add(produto2);

        List<Mercado> mercados = new ArrayList<>();
        Mercado mercado1 = mock(Mercado.class);
        ProdutoPreco preco1 = new ProdutoPreco(produto1, 10.0);
        ProdutoPreco preco2 = new ProdutoPreco(produto2, 15.0);
        when(mercado1.getProdutoPreco(produto1)).thenReturn(preco1);
        when(mercado1.getProdutoPreco(produto2)).thenReturn(preco2);
        mercados.add(mercado1);

        assertEquals(mercado1, listaCompra.mercadoMaisEconomico(mercados));
    }
}
package com.ada.economizaapi.entities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class PessoaTest {

    private Pessoa pessoa1;
    private Pessoa pessoa2;
    private Localizacao localizacao;
    private ListaCompra listaCompra;

    @BeforeEach
    public void setUp() {
        localizacao = new Localizacao();
        localizacao.setId(1L);

        pessoa1 = new Pessoa();
        pessoa1.setId(1L);
        pessoa1.setNome("Pessoa Teste 1");
        pessoa1.setLocalizacao(localizacao);
        pessoa1.setCustoPorDistancia(1.5);

        pessoa2 = new Pessoa();
        pessoa2.setId(1L);
        pessoa2.setNome("Pessoa Teste 1");
        pessoa2.setLocalizacao(localizacao);
        pessoa2.setCustoPorDistancia(1.5);

        listaCompra = new ListaCompra();
        listaCompra.setId(1L);
        listaCompra.setNome("Lista de Compras Teste");
        listaCompra.setPessoa(pessoa1);
    }

    @Test
    public void testEquals() {
        assertEquals(pessoa1, pessoa2);
    }

    @Test
    public void testNotEquals() {
        pessoa2.setId(2L);
        assertNotEquals(pessoa1, pessoa2);
    }

    @Test
    public void testHashCode() {
        assertEquals(pessoa1.hashCode(), pessoa2.hashCode());
    }

    @Test
    public void testToString() {
        String expected = "Pessoa(id=1, nome=Pessoa Teste 1, localizacao=Localizacao(id=1), custoPorDistancia=1.5)";
        assertEquals(expected, pessoa1.toString());
    }


    @Test
    public void testGettersAndSetters() {
        assertEquals(1L, pessoa1.getId().longValue());
        assertEquals("Pessoa Teste 1", pessoa1.getNome());
        assertEquals(localizacao, pessoa1.getLocalizacao());
        assertEquals(1.5, pessoa1.getCustoPorDistancia().doubleValue());

        pessoa1.setId(2L);
        pessoa1.setNome("Pessoa Teste 2");
        pessoa1.setLocalizacao(null);
        pessoa1.setCustoPorDistancia(2.5);

        assertEquals(2L, pessoa1.getId().longValue());
        assertEquals("Pessoa Teste 2", pessoa1.getNome());
        assertNull(pessoa1.getLocalizacao());
        assertEquals(2.5, pessoa1.getCustoPorDistancia().doubleValue());
    }

    @Test
    public void testAddListaCompra() {
        pessoa1.addListaCompra(listaCompra);
        assertTrue(pessoa1.getListasCompra().contains(listaCompra));
    }

    @Test
    public void testRemoveListaCompra() {
        pessoa1.addListaCompra(listaCompra);
        pessoa1.removeListaCompra(listaCompra);
        assertFalse(pessoa1.getListasCompra().contains(listaCompra));
    }
}

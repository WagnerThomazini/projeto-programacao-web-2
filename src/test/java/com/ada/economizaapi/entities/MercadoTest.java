package com.ada.economizaapi.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MercadoTest {

    private Validator validator;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testMercadoValido() {
        Mercado mercado = new Mercado();
        mercado.setNome("Mercado Teste");
        mercado.setLocalizacao(new Localizacao());

        Set<ConstraintViolation<Mercado>> violations = validator.validate(mercado);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testNomeNaoPodeSerBranco() {
        Mercado mercado = new Mercado();
        mercado.setNome("");
        mercado.setLocalizacao(new Localizacao());

        Set<ConstraintViolation<Mercado>> violations = validator.validate(mercado);
        assertEquals(1, violations.size());
        ConstraintViolation<Mercado> violation = violations.iterator().next();
        assertEquals("nome", violation.getPropertyPath().toString());
        assertEquals("must not be blank", violation.getMessage());
    }

    @Test
    public void testLocalizacaoNaoPodeSerNula() {
        Mercado mercado = new Mercado();
        mercado.setNome("Mercado Teste");
        mercado.setLocalizacao(null);

        Set<ConstraintViolation<Mercado>> violations = validator.validate(mercado);
        assertEquals(1, violations.size());
        ConstraintViolation<Mercado> violation = violations.iterator().next();
        assertEquals("localizacao", violation.getPropertyPath().toString());
        assertTrue(violation.getMessage().contains("not be null"));
    }

    @Test
    public void testAdicionarProdutoPreco() {
        Mercado mercado = new Mercado();
        ProdutoPreco produtoPreco = new ProdutoPreco();
        produtoPreco.setMercado(mercado);
        produtoPreco.setProduto(new Produto());

        mercado.addProdutoPreco(produtoPreco);

        assertNotNull(mercado.getProdutoPrecos());
        assertEquals(1, mercado.getProdutoPrecos().size());
        assertEquals(produtoPreco, mercado.getProdutoPrecos().get(0));
    }

    @Test
    public void testEquals() {
        Mercado mercado1 = new Mercado();
        mercado1.setId(1L);
        mercado1.setNome("Mercado Teste 1");

        Mercado mercado2 = new Mercado();
        mercado2.setId(1L);
        mercado2.setNome("Mercado Teste 1");

        assertEquals(mercado1, mercado2);
    }

    @Test
    public void testHashCode() {
        Mercado mercado1 = new Mercado();
        mercado1.setId(1L);
        mercado1.setNome("Mercado Teste 1");

        Mercado mercado2 = new Mercado();
        mercado2.setId(1L);
        mercado2.setNome("Mercado Teste 1");

        assertEquals(mercado1.hashCode(), mercado2.hashCode());
    }

    @Test
    public void testGetProdutoPreco() {
        Mercado mercado = new Mercado();
        Produto produto = new Produto();
        ProdutoPreco produtoPreco = new ProdutoPreco();
        produtoPreco.setMercado(mercado);
        produtoPreco.setProduto(produto);
        mercado.addProdutoPreco(produtoPreco);

        ProdutoPreco result = mercado.getProdutoPreco(produto);

        assertEquals(produtoPreco, result);
    }
}
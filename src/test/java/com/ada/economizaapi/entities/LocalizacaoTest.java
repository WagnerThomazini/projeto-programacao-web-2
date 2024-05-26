package com.ada.economizaapi.entities;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

public class LocalizacaoTest {

    @Autowired
    private Validator validator;

    @Test
    void testCoordenadasNaoPodeEstarEmBranco() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        Localizacao localizacao = new Localizacao("");
        Set<ConstraintViolation<Localizacao>> violations = validator.validate(localizacao);
        assertFalse(violations.isEmpty(), "A coordenada não pode estar em branco");
    }

    @Test
    void testIgualdade() {
        Localizacao localizacao1 = new Localizacao("10.12345,20.67890");
        Localizacao localizacao2 = new Localizacao("10.12345,20.67890");
        assertEquals(localizacao1, localizacao2);
    }

    @Test
    void testDiferenca() {
        Localizacao localizacao1 = new Localizacao("10.12345,20.67890");
        Localizacao localizacao2 = new Localizacao("11.12345,20.67890");
        assertNotEquals(localizacao1, localizacao2);
    }

    @Test
    void testConstrutor() {
        Localizacao localizacao = new Localizacao(1L, "10.12345,20.67890");
        assertNotNull(localizacao);
        assertEquals(1L, localizacao.getId());
        assertEquals("10.12345,20.67890", localizacao.getCoordenadas());
    }

    @Test
    void testIgualdadeComObjetosDiferentes() {
        Localizacao localizacao1 = new Localizacao("10.12345,20.67890");
        Localizacao localizacao2 = new Localizacao("10.12345,20.67890");
        String localizacao3 = "Não é uma instancia";
        assertFalse(localizacao1.equals(localizacao3));
    }

    @Test
    void testHashCode() {
        Localizacao localizacao1 = new Localizacao("10.12345,20.67890");
        Localizacao localizacao2 = new Localizacao("10.12345,20.67890");
        assertEquals(localizacao1.hashCode(), localizacao2.hashCode());
    }

    @Test
    void testToString() {
        Localizacao localizacao = new Localizacao("10.12345,20.67890");
        localizacao.setId(0L);
        String expected = "Localizacao(id=0, coordenadas=10.12345,20.67890)";
        assertEquals(expected, localizacao.toString());
    }

    @Test
    void testCoordenadasNaoPodeEstarEmBrancoComMensagem() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        Localizacao localizacao = new Localizacao("");
        Set<ConstraintViolation<Localizacao>> violations = validator.validate(localizacao);
        assertEquals(1, violations.size());
        ConstraintViolation<Localizacao> violation = violations.iterator().next();
        assertEquals("A coordenada não pode estar em branco", violation.getMessage());
    }
}
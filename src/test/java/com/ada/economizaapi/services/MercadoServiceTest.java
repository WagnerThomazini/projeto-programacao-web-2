package com.ada.economizaapi.services;

import com.ada.economizaapi.entities.Mercado;
import com.ada.economizaapi.exceptions.EntidadeNaoExisteException;
import com.ada.economizaapi.repositories.LocalizacaoRepository;
import com.ada.economizaapi.repositories.MercadoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class MercadoServiceTest {

    @Autowired
    private MercadoService mercadoService;

    @MockBean
    private MercadoRepository mercadoRepository;

    @MockBean
    private LocalizacaoRepository localizacaoRepository;

    private Mercado mercado;

    @BeforeEach
    public void setup() {
        mercado = new Mercado();
        mercado.setId(1L);
        mercado.setNome("Mercado Teste");

        when(mercadoRepository.save(any(Mercado.class))).thenReturn(mercado);
        when(mercadoRepository.findById(1L)).thenReturn(Optional.of(mercado));
    }

    @Test
    public void testSaveMercado() {
        Mercado savedMercado = mercadoService.save(new Mercado());
        Assertions.assertNotNull(savedMercado.getId());
    }

    @Test
    public void testUpdateMercado() {
        try {
            Mercado updatedMercado = mercadoService.update(2L, new Mercado());
            Assertions.fail("Expected EntidadeNaoExisteException to be thrown");
        } catch (EntidadeNaoExisteException e) {
            // Expected exception
        }
    }
}
package com.ada.economizaapi.services;

import com.ada.economizaapi.entities.Localizacao;
import com.ada.economizaapi.repositories.LocalizacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class LocalizacaoServiceTest {

    @Mock
    private LocalizacaoRepository localizacaoRepository;
    @InjectMocks
    private LocalizacaoService localizacaoService;

    private Localizacao localizacaoCasa;
    private Localizacao localizacaoMercado;

    @BeforeEach
    public void setup() {
        localizacaoCasa = new Localizacao(null, "-34.90860637402315, -8.052514882835915");
        localizacaoMercado = new Localizacao(null, "-34.89939394133639, -8.05166126289959");
    }

    @Test
    public void testRetornarDistanciaCorreta() {
        double distance = localizacaoService.retornarDistanciaKm(localizacaoCasa, localizacaoMercado);

        assertEquals(distance, 2.1462);
    }

}
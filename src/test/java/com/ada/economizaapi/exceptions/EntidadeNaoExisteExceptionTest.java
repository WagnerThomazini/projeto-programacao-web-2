package com.ada.economizaapi.exceptions;

import com.ada.economizaapi.exceptions.EntidadeNaoExisteException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EntidadeNaoExisteExceptionTest {

    @Test
    public void testConstrutorPadrao() {
        EntidadeNaoExisteException exception = new EntidadeNaoExisteException();
        assertNotNull(exception);
    }

    @Test
    public void testConstrutorComMensagem() {
        String mensagem = "Entidade não existe!";
        EntidadeNaoExisteException exception = new EntidadeNaoExisteException(mensagem);
        assertNotNull(exception);
        assertEquals(mensagem, exception.getMessage());
    }
}

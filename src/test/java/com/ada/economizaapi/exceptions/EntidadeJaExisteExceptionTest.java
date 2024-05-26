package com.ada.economizaapi.exceptions;

import com.ada.economizaapi.exceptions.EntidadeJaExisteException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EntidadeJaExisteExceptionTest {

    @Test
    public void testConstrutorPadrao() {
        EntidadeJaExisteException exception = new EntidadeJaExisteException();
        assertEquals("Entidade já existe.", exception.getMessage());
    }

    @Test
    public void testConstrutorComMensagem() {
        String mensagem = "Usuário já cadastrado.";
        EntidadeJaExisteException exception = new EntidadeJaExisteException(mensagem);
        assertEquals(mensagem, exception.getMessage());
    }
}

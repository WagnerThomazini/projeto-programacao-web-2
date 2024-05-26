package com.ada.economizaapi.services;

import com.ada.economizaapi.exceptions.EntidadeJaExisteException;
import com.ada.economizaapi.exceptions.EntidadeNaoExisteException;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServicoAbstratoTest {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @jakarta.persistence.Entity
    private static class Entidade {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String campo;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Entidade entidade = (Entidade) o;
            return Objects.equals(id, entidade.id);
        }

        @Override
        public int hashCode() {
            return id != null ? id.hashCode() : 0;
        }
    }

    private interface RepositorioEntidade extends JpaRepository<Entidade, Long> {}

    @Mock
    private RepositorioEntidade repositorio;
    private ServicoAbstrato<Entidade, Long, RepositorioEntidade> servico;

    private Entidade entidade;

    @BeforeEach
    public void beforeEach() {
        this.servico = new ServicoAbstrato<>(repositorio) {};
        this.entidade = new Entidade(null, "test");
    }

    @Test
    public void verificarExistencia() {
        when(repositorio.exists(Example.of(entidade))).thenReturn(true);

        assertTrue(servico.exists(entidade));

        verify(repositorio, times(1)).exists(Example.of(entidade));
    }

    @Test
    public void verificarInexistencia() {
        when(repositorio.exists(any())).thenReturn(false);

        assertFalse(servico.exists(entidade));

        verify(repositorio, times(1)).exists(Example.of(entidade));
    }

    @Test
    public void verificarExistenciaPorId() {
        entidade.setId(1L);
        when(repositorio.existsById(1L)).thenReturn(true);

        assertTrue(servico.existsById(1L));

        verify(repositorio, times(1)).existsById(1L);
    }

    @Test
    public void verificarInexistenciaPorId() {
        when(repositorio.existsById(any())).thenReturn(false);

        assertFalse(servico.existsById(1L));

        verify(repositorio, times(1)).existsById(1L);
    }

    @Test
    public void salvarComSucesso() {
        Entidade savedEntity = new Entidade(1L, "test");
        when(repositorio.exists(any())).thenReturn(false);
        when(repositorio.save(entidade)).thenReturn(savedEntity);

        Entidade returnedEntity = servico.save(entidade);

        assertNotNull(returnedEntity.getId());
        assertEquals(entidade.getCampo(), returnedEntity.getCampo());

        verify(repositorio, times(1)).exists(Example.of(entidade));
        verify(repositorio, times(1)).save(entidade);
    }

    @Test
    public void lancarExcecaoAoSalvar() {
        when(repositorio.exists(any())).thenReturn(true);

        assertThrows(EntidadeJaExisteException.class, () -> servico.save(entidade));

        verify(repositorio, times(1)).exists(Example.of(entidade));
        verifyNoMoreInteractions(repositorio);
    }

    @Test
    public void buscarTodos() {
        List<Entidade> entities = servico.findAll();

        assertNotNull(entities);
        verify(repositorio, times(1)).findAll();
    }

    @Test
    public void atualizarComSucesso() {
        entidade.setId(1L);
        when(repositorio.exists(Example.of(entidade))).thenReturn(true);
        Entidade updatedEntity = new Entidade(1L, "testado");
        when(repositorio.save(updatedEntity)).thenReturn(updatedEntity);

        Entidade returnedEntity = servico.update(updatedEntity);

        assertEquals(updatedEntity.getCampo(), returnedEntity.getCampo());
        verify(repositorio, times(1)).save(entidade);
    }

    @Test
    public void lancarExcecaoAoAtualizar() {
        entidade.setId(1L);
        when(repositorio.exists(Example.of(entidade))).thenReturn(false);
        Entidade updatedEntity = new Entidade(1L, "testado");

        assertThrows(EntidadeNaoExisteException.class, () -> servico.update(updatedEntity));

        verify(repositorio, times(0)).save(entidade);
    }

    @Test
    public void deletar() {
        servico.delete(entidade);

        verify(repositorio, times(1)).delete(entidade);
        verifyNoMoreInteractions(repositorio);
    }

    @Test
    public void deletarPorId() {
        servico.deleteById(1L);

        verify(repositorio, times(1)).deleteById(1L);
        verifyNoMoreInteractions(repositorio);
    }

    @Test
    public void salvarTodos() {
        Entidade entidade2 = new Entidade(null, "test2");
        List<Entidade> entities = List.of(entidade, entidade2);
        when(repositorio.save(entidade)).thenReturn(entidade);
        when(repositorio.save(entidade2)).thenReturn(entidade2);

        List<Entidade> returnedEntities = servico.saveAll(entities);

        assertEquals(returnedEntities, entities);
        verify(repositorio, times(2)).save(any());
        verify(repositorio, times(2)).exists(any());
    }
}
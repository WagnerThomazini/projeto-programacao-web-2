package com.ada.economizaapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @ManyToOne
    @JoinColumn(name = "localizacao_id")
    private Localizacao localizacao;
    private Double custoPorDistancia;
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "pessoa")
    private List<ListaCompra> listasCompra = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(id, pessoa.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public void addListaCompra(ListaCompra listaCompra) {
        listaCompra.setPessoa(this);
        this.listasCompra.add(listaCompra);
    }

    public void removeListaCompra(ListaCompra listaCompra) {
        listaCompra.setPessoa(null);
        this.listasCompra.remove(listaCompra);
    }
    @Override
    public String toString() {
        return "Pessoa(id=" + id + ", nome=" + nome + ", localizacao=Localizacao(id=" + localizacao.getId() + "), custoPorDistancia=" + custoPorDistancia + ")";
    }

}

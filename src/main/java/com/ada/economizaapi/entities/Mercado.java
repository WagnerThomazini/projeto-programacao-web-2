package com.ada.economizaapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Mercado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @ManyToOne
    @JoinColumn(name = "localizacao_id")
    @NotNull
    private Localizacao localizacao;
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "mercado")
    private List<ProdutoPreco> produtoPrecos = new ArrayList<>();

    public <T> Mercado(String mercadoA, List<T> list) {
    }
    public void addProdutoPreco(ProdutoPreco produtoPreco) {
        this.produtoPrecos.add(produtoPreco);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mercado mercado = (Mercado) o;
        return Objects.equals(id, mercado.id) &&
                Objects.equals(nome, mercado.nome);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public ProdutoPreco getProdutoPreco(Produto produto) {
        for (ProdutoPreco produtoPreco : produtoPrecos) {
            if (produtoPreco.getProduto().equals(produto)) {
                return produtoPreco;
            }
        }
        return null;
    }
}


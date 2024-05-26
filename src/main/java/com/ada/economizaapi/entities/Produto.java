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
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String marca;
    private String descricao;
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "produto")
    private List<ProdutoPreco> produtoPrecos = new ArrayList<>();

    public Produto(String nome, String marca, String descricao) {
        this.nome = nome;
        this.marca = marca;
        this.descricao = descricao;
    }

    public Produto(String s, double v) {
    }

    public void addProdutoPreco(ProdutoPreco produtoPreco) {
        this.produtoPrecos.add(produtoPreco);
        produtoPreco.setProduto(this);
    }

    public void removeProdutoPreco(ProdutoPreco produtoPreco) {
        this.produtoPrecos.remove(produtoPreco);
        produtoPreco.setProduto(null);
    }

    public void setProdutoPrecos(List<ProdutoPreco> produtoPrecos) {
        if (produtoPrecos == null) {
            throw new NullPointerException("Produto preços cannot be null");
        }
        this.produtoPrecos = produtoPrecos;
    }

    public void setId(Long id) {
        if (id == null) {
            throw new NullPointerException("Id cannot be null");
        }
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id) &&
                Objects.equals(nome, produto.nome) &&
                Objects.equals(marca, produto.marca) &&
                Objects.equals(descricao, produto.descricao);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
    @Override
    public String toString() {
        return "Produto [id=" + id + ", nome='" + nome + "', marca='" + marca + "', descricao='" + descricao + "', produtoPrecos=" + produtoPrecos + "]";
    }

}

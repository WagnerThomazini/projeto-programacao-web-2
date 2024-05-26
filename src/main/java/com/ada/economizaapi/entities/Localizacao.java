package com.ada.economizaapi.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.Objects;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Localizacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "coordenadas", nullable = false)
    @NotBlank(message = "A coordenada não pode estar em branco")
    private String coordenadas;


    public Localizacao(String coordenadas) {
        this.coordenadas = coordenadas;    }

  
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Localizacao that = (Localizacao) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(coordenadas, that.coordenadas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, coordenadas);
    }

}

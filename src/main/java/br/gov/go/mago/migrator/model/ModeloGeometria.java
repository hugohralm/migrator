package br.gov.go.mago.migrator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "modelogeometria")
public class ModeloGeometria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "modelo_geometria_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "modelo_geometria_seq", sequenceName = "modelo_geometria_seq", allocationSize = 1)
    private Integer id;

    private String nome;

    private String tipo;
}

package br.gov.go.mago.migrator.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    public ModeloGeometria(ModeloGeometria modeloGeometria) {
        this.id = null;
        this.nome = modeloGeometria.getNome();
        this.tipo = modeloGeometria.getTipo();
    }
}

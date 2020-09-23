package br.gov.go.mago.migrator.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "unidademedida")
public class UnidadeMedida implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "unidade_medida_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "unidade_medida_seq", sequenceName = "unidade_medida_seq", allocationSize = 1, initialValue = 1)
    @Getter
    private Integer id;

    @Getter
    @Setter
    @Length(max = 255, message = "O limite do campo descricao é de 255 caracteres.")
    @Column(length = 255, nullable = false)
    private String descricao;

    @Getter
    @Setter
    @Length(max = 10, message = "O limite do campo sigla é de 10 caracteres.")
    @Column(length = 10, nullable = false)
    private String sigla;

    @Override
    public String toString() {
        return String.format("%s(%s)", descricao, sigla);
    }
}

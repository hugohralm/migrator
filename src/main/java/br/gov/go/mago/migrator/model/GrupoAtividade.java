package br.gov.go.mago.migrator.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
@Table(name = "grupoatividade")
public class GrupoAtividade implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Getter
    @GeneratedValue(generator = "grupo_atividade_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "grupo_atividade_seq", sequenceName = "grupo_atividade_seq", allocationSize = 1, initialValue = 1)
    private Integer id;

    @Getter
    @Setter
    @Length(max = 255, message = "O limite do campo nome é de 255 caracteres.")
    @Column(length = 255, nullable = false)
    private String codigo;

    @Getter
    @Setter
    @Length(max = 255, message = "O limite do campo descrição é de 255 caracteres.")
    @Column(length = 255, nullable = false)
    private String nome;

    @Getter
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "datacadastro")
    private Date dataCadastro = new Date();

    @PrePersist
    private void atualizarData() {
        this.dataCadastro = new Date();
    }

    public String toString() {
        return String.format("Grupo %s: %s", this.codigo, this.nome);
    }
}

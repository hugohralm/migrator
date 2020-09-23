package br.gov.go.mago.migrator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "arquivo")
public class Arquivo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "arquivo_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "arquivo_seq", sequenceName = "arquivo_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "datacadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;

    private String codigo;

    @Column(name = "crc32")
    private String crc32;

    private String chave;

    private String nome;
}

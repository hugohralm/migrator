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
@Table(name = "modelodocumento")
public class ModeloDocumento implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "modelo_documento_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "modelo_documento_seq", sequenceName = "modelo_documento_seq", allocationSize = 1, initialValue = 1)
    private Integer id;

    private String descricao;

    @Column(name = "tipopessoa")
    private String tipoPessoa;

    @Column(name = "tamanhodocumento")
    private String tamanhoDocumento;

    private boolean procuracao;

    private boolean opcional;

    private boolean expira;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "arquivo_id")
    private Arquivo arquivo;

    public ModeloDocumento(ModeloDocumento modeloDocumento) {
        this.id = null;
        this.descricao = modeloDocumento.getDescricao().toUpperCase().trim();
        this.tipoPessoa = modeloDocumento.getTipoPessoa();
        this.tamanhoDocumento = modeloDocumento.getTamanhoDocumento();
        this.procuracao = modeloDocumento.isProcuracao();
        this.opcional = modeloDocumento.isOpcional();
        this.expira = modeloDocumento.isExpira();
        this.arquivo = null;
    }
}

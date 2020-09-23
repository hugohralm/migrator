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
@Table(name = "questionariotemplate")
public class QuestionarioTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "questionario_template_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "questionario_template_seq", sequenceName = "questionario_template_seq", allocationSize = 1)
    private Integer id;

    private String descricao;

    @Column(name = "datacadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;

    private boolean ativo;

    @Column(name = "tipoquestionario")
    private String tipoQuestionario;

    @Column(name = "tipolicenciamento")
    private String tipoLicenciamento;

    @Column(name = "regimelicenciamento")
    private String regimeLicenciamento;

    public QuestionarioTemplate(QuestionarioTemplate questionario) {
        this.id = null;
        this.descricao = questionario.getDescricao().toUpperCase().trim();
        this.dataCadastro = new Date();
        this.ativo = questionario.isAtivo();
        this.tipoQuestionario = questionario.getTipoQuestionario();
        this.tipoLicenciamento = questionario.getTipoLicenciamento();
        this.regimeLicenciamento = questionario.getRegimeLicenciamento();
    }
}

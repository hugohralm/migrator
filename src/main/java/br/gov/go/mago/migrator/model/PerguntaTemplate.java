package br.gov.go.mago.migrator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "perguntatemplate")
public class PerguntaTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "pergunta_template_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "pergunta_template_seq", sequenceName = "pergunta_template_seq", allocationSize = 1)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "questionariotemplate_id")
    private QuestionarioTemplate questionarioTemplate;

    private Integer indice;

    private String codigo;

    private String descricao;

    @Column(name = "datacadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;

    public PerguntaTemplate(PerguntaTemplate pergunta, QuestionarioTemplate questionario) {
        this.id = null;
        this.questionarioTemplate = questionario;
        this.indice = pergunta.getIndice();
        this.codigo = pergunta.getCodigo().trim();
        this.descricao = pergunta.getDescricao().toUpperCase().trim();
        this.dataCadastro = new Date();
    }

    public boolean equalsNovaPergunta(PerguntaTemplate pergunta) {
        return Objects.equals(this.indice, pergunta.getIndice()) &&
                Objects.equals(this.codigo, pergunta.getCodigo()) &&
                Objects.equals(this.descricao, pergunta.getDescricao());
    }
}

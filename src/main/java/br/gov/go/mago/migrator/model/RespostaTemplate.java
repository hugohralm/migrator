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
@Table(name = "respostatemplate")
public class RespostaTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "resposta_template_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "resposta_template_seq", sequenceName = "resposta_template_seq", allocationSize = 1)
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "perguntatemplate_id")
    private PerguntaTemplate perguntaTemplate;

    @ManyToOne()
    @JoinColumn(name = "respostatemplateoriginal_id")
    private RespostaTemplate respostaTemplateOriginal;

    private String descricao;

    private Integer indice;

    private String mensagem;

    private String dica;

    @Column(name = "textolicenca")
    private String textoLicenca;

    @Column(name = "datacadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;

    @Column(nullable = false)
    private boolean impeditiva = false;

    @ManyToOne()
    @JoinColumn(name = "proximapergunta_id")
    private PerguntaTemplate proximaPergunta;

    public RespostaTemplate(RespostaTemplate resposta, PerguntaTemplate perguntaTemplate, PerguntaTemplate proximaPergunta) {
        this.id = null;
        this.perguntaTemplate = perguntaTemplate;
        this.descricao = resposta.getDescricao().toUpperCase().trim();
        this.indice = resposta.getIndice();
        this.mensagem = resposta.getMensagem() != null ? resposta.getMensagem().trim() : null;
        this.dica = resposta.getDica() != null ? resposta.getDica().trim() : null;
        this.textoLicenca = resposta.getTextoLicenca() != null ? resposta.getTextoLicenca().trim() : null;
        this.dataCadastro = new Date();
        this.impeditiva = resposta.isImpeditiva();
        this.proximaPergunta = proximaPergunta;
    }
}

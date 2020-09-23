package br.gov.go.mago.migrator.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import br.gov.go.mago.migrator.model.enums.EnumTipoLicenciamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tipologia")
public class Tipologia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Getter
    @GeneratedValue(generator = "tipologia_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "tipologia_seq", sequenceName = "tipologia_seq", allocationSize = 1, initialValue = 1)
    private Integer id;

    @Getter
    @Setter
    @ManyToOne(optional = false)
    @JoinColumn(name = "atividade_id")
    @NotNull(message = "Selecione uma atividade para a tipologia.")
    private Atividade atividade;

    @Getter
    @Setter
    @ManyToOne(optional = false)
    @JoinColumn(name = "tipolicenca_id")
    @NotNull(message = "Selecione um tipo de licença para a tipologia.")
    private TipoLicenca tipoLicenca;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "tipolicenciamento")
    private EnumTipoLicenciamento tipoLicenciamento;

    @Getter
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "datacadastro")
    private Date dataCadastro = new Date();

    @Getter
    @Setter
    private boolean ativa;

    @Getter
    @Setter
    @ManyToOne()
    @JoinColumn(name = "questionariorequerimento_id")
    private QuestionarioTemplate questionarioRequerimento;

    @Getter
    @Setter
    @ManyToOne()
    @JoinColumn(name = "modelorequerimento_id")
    private ModeloDeclaracao modeloRequerimento;

    @OrderBy("id")
    @Getter
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
    @JoinTable(name = "tipologia_questionariotemplate", joinColumns = {
            @JoinColumn(name = "tipologia_id") }, inverseJoinColumns = {
                    @JoinColumn(name = "questionariostemplate_id") })
    private Set<QuestionarioTemplate> questionariosTemplate = new HashSet<>();

    @OrderBy("id")
    @Getter
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
    @JoinTable(name = "tipologia_grupomodelocondicionante", joinColumns = {
            @JoinColumn(name = "tipologia_id") }, inverseJoinColumns = {
                    @JoinColumn(name = "gruposmodelocondicionante_id") })
    private Set<GrupoModeloCondicionante> gruposModeloCondicionante = new HashSet<>();

    @OrderBy("id")
    @Getter
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
    @JoinTable(name = "tipologia_grupomodelogeometria", joinColumns = {
            @JoinColumn(name = "tipologia_id") }, inverseJoinColumns = {
                    @JoinColumn(name = "gruposmodelogeometria_id") })
    private Set<GrupoModeloGeometria> gruposModeloGeometria = new HashSet<>();

    @OrderBy("id")
    @Getter
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
    @JoinTable(name = "tipologia_grupomodelotermo", joinColumns = {
            @JoinColumn(name = "tipologia_id") }, inverseJoinColumns = { @JoinColumn(name = "gruposmodelotermo_id") })
    private Set<GrupoModeloTermo> gruposModeloTermo = new HashSet<>();

    @OrderBy("id")
    @Getter
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
    @JoinTable(name = "tipologia_modelodeclaracao", joinColumns = {
            @JoinColumn(name = "tipologia_id") }, inverseJoinColumns = { @JoinColumn(name = "modelosdeclaracao_id") })
    private Set<ModeloDeclaracao> modelosDeclaracao = new HashSet<>();

    @OrderBy("id")
    @Getter
    @JoinTable(name = "tipologia_modeloslaudos", joinColumns = {
            @JoinColumn(name = "tipologia_id") }, inverseJoinColumns = { @JoinColumn(name = "modeloslaudo_id") })
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
    private Set<ModeloDeclaracao> modelosLaudos = new HashSet<>();

    @OrderBy("id")
    @Getter
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
    @JoinTable(name = "tipologia_parametrotemplate", joinColumns = {
            @JoinColumn(name = "tipologia_id") }, inverseJoinColumns = { @JoinColumn(name = "parametrosiniciais_id") })
    private Set<ParametroTemplate> parametrosIniciais = new HashSet<>();

    @Getter
    @Setter
    @Column(name = "exigepublicacaodeeditais")
    private boolean exigePublicacaoDeEditais;

    @Getter
    @Setter
    @Column(name = "exigeresponsaveltecnico")
    private boolean exigeResponsavelTecnico;

    @Getter
    @Setter
    @Column(name = "exigegeometria")
    private boolean exigeGeometria;

    @Getter
    @Setter
    private boolean descentralizada;

    @Getter
    @Setter
    @Column(name = "exigedadosempreendimento")
    private boolean exigeDadosEmpreendimento;

    @PrePersist
    private void atualizarData() {
        this.dataCadastro = new Date();
    }

    public String toString() {
        return String.format("%s: %s", this.atividade, this.tipoLicenca);
    }

    public Tipologia(Tipologia tipologia) {
        this.id = null;
        this.tipoLicenciamento = tipologia.getTipoLicenciamento();
        this.dataCadastro = new Date();
        this.ativa = tipologia.isAtiva();
        this.exigePublicacaoDeEditais = tipologia.isExigePublicacaoDeEditais();
        this.exigeResponsavelTecnico = tipologia.isExigeResponsavelTecnico();
        this.exigeGeometria = tipologia.isExigeGeometria();
        this.exigeDadosEmpreendimento = tipologia.isExigeDadosEmpreendimento();
        this.descentralizada = tipologia.isDescentralizada();
    }
}

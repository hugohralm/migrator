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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.gov.go.mago.migrator.model.enums.EnumPotencialPoluidor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "atividade")
public class Atividade implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Getter
    @GeneratedValue(generator = "atividade_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "atividade_seq", sequenceName = "atividade_seq", allocationSize = 1, initialValue = 1)
    private Integer id;

    @Getter
    @Setter
    @Length(max = 255, message = "O limite do campo codigo é de 255 caracteres.")
    @Column(length = 255, nullable = true)
    private String codigo;

    @Getter
    @Setter
    @Length(max = 255, message = "O limite do campo descricao é de 255 caracteres.")
    @Column(length = 255, nullable = true)
    private String descricao;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(nullable = true, name = "potencialpoluidor")
    private EnumPotencialPoluidor potencialPoluidor;

    @Getter
    @Setter
    @Column
    private Integer ordem;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "atividadepai_id")
    @JsonIgnore
    private Atividade atividadePai;

    @Getter
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "datacadastro")
    private Date dataCadastro = new Date();

    @Getter
    @Setter
    @ManyToOne()
    @JoinColumn(name = "unidademedida_id")
    private UnidadeMedida unidadeMedida;

    @Getter
    @Setter
    @Column(nullable = false, name = "atividadeunica")
    private boolean atividadeUnica = false;

    @Getter
    @Setter
    @Column(nullable = false, name = "atividadeoutro")
    private boolean atividadeOutro = false;

    @Getter
    @Setter
    @ManyToOne()
    @JoinColumn(name = "grupoatividade_id")
    private GrupoAtividade grupoAtividade;

    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "atividade_id")
    private Set<PorteAtividade> portes = new HashSet<>();

    @Getter
    @Setter
    @OrderBy("ordem")
    @OneToMany(cascade = { CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.REFRESH,
            CascadeType.REMOVE }, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "atividadepai_id")
    private Set<Atividade> atividades = new HashSet<>();

    @Getter
    @Setter
    @Column(nullable = false, name = "atividadeliberada")
    private boolean atividadeLiberada = false;

    @PrePersist
    private void atualizarData() {
        this.dataCadastro = new Date();
    }

    public Atividade addAtividade(Atividade atividade) {
        atividades.add(atividade);
        atividade.setAtividadePai(this);
        atividade.setOrdem(atividades.size());
        return this;
    }

    public void addPorte(PorteAtividade porte) {
        if (!portes.stream().anyMatch(p -> p.getPorte().equals(porte.getPorte()))) {
            portes.add(porte);
        }
    }

    public void removePorte(PorteAtividade porte) {
        if (portes.contains(porte)) {
            portes.remove(porte);
        }
    }

    public boolean isEditavel() {
        return this.getId() != null;
    }

    @Override
    public String toString() {
        return String.format("%s - %s", codigo, descricao);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
        result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
        result = prime * result + ((potencialPoluidor == null) ? 0 : potencialPoluidor.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Atividade other = (Atividade) obj;
        if (codigo == null) {
            if (other.codigo != null)
                return false;
        } else if (!codigo.equals(other.codigo))
            return false;
        if (descricao == null) {
            if (other.descricao != null)
                return false;
        } else if (!descricao.equals(other.descricao))
            return false;
        if (potencialPoluidor != other.potencialPoluidor)
            return false;
        return true;
    }
}

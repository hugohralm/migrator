package br.gov.go.mago.migrator.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
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
@Table(name = "grupomodelotermo")
public class GrupoModeloTermo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Getter
    @GeneratedValue(generator = "grupo_modelo_termo_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "grupo_modelo_termo_seq", sequenceName = "grupo_modelo_termo_seq", allocationSize = 1, initialValue = 1)
    private Integer id;

    @Getter
    @OrderBy("id")
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
    @JoinTable(name = "grupomodelotermo_modelotermo", joinColumns = {
            @JoinColumn(name = "grupomodelotermo_id") }, inverseJoinColumns = { @JoinColumn(name = "modelos_id") })
    private Set<ModeloTermo> modelos = new HashSet<>();

    @Getter
    @Setter
    @Length(max = 255, message = "O limite do campo descrição é de 255 caracteres.")
    @Column(nullable = false, length = 255)
    private String descricao;

    public GrupoModeloTermo(GrupoModeloTermo grupoModeloTermo) {
        this.id = null;
        this.descricao = grupoModeloTermo.getDescricao();
    }

    public void adicionarModeloTermo(ModeloTermo modeloTermo) {
        this.modelos.add(modeloTermo);
    }

    public void removerModeloTermo(ModeloTermo modeloTermo) {
        if (modelos.contains(modeloTermo)) {
            this.modelos.remove(modeloTermo);
        }
    }
}

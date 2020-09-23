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
@Table(name = "grupomodelocondicionante")
public class GrupoModeloCondicionante implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Getter
    @GeneratedValue(generator = "grupo_modelo_condicionante_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "grupo_modelo_condicionante_seq", sequenceName = "grupo_modelo_condicionante_seq", allocationSize = 1, initialValue = 1)
    private Integer id;

    @Getter
    @Setter
    @Length(max = 255, message = "O limite do campo nome é de 255 caracteres.")
    @Column(nullable = false, length = 255)
    private String nome;

    @OrderBy("id")
    @Getter
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
    @JoinTable(name = "grupomodelocondicionante_modelocondicionante", joinColumns = {
            @JoinColumn(name = "grupomodelocondicionante_id") }, inverseJoinColumns = {
                    @JoinColumn(name = "modeloscondicionante_id") })
    private Set<ModeloCondicionante> modelosCondicionante = new HashSet<>();

    public GrupoModeloCondicionante(GrupoModeloCondicionante grupoModeloCondicionante) {
        this.id = null;
        this.nome = grupoModeloCondicionante.getNome();
    }

    public void adicionarCondicionante(ModeloCondicionante condi) {
        if (!this.modelosCondicionante.contains(condi)) {
            this.modelosCondicionante.add(condi);
        }
    }

    public void removerCondicionante(ModeloCondicionante condi) {
        if (modelosCondicionante.contains(condi)) {
            this.modelosCondicionante.remove(condi);
        }
    }
}

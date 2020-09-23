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
import javax.persistence.OneToMany;
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
@Table(name = "grupomodelogeometria")
public class GrupoModeloGeometria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Getter
    @GeneratedValue(generator = "grupo_modelo_geometria_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "grupo_modelo_geometria_seq", sequenceName = "grupo_modelo_geometria_seq", allocationSize = 1, initialValue = 1)
    private Integer id;

    @Getter
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "grupomodelogeometria_id")
    private Set<ItemGrupoGeometria> itensGrupoGeo = new HashSet<>();

    @Getter
    @Setter
    @Length(max = 255, message = "O limite do campo nome é de 255 caracteres.")
    @Column(nullable = false, length = 255)
    private String nome;

    public GrupoModeloGeometria(GrupoModeloGeometria grupoModeloGeometria) {
        this.id = null;
        this.nome = grupoModeloGeometria.getNome();
    }

}

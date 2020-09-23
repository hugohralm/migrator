package br.gov.go.mago.migrator.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "itemgrupogeometria")
public class ItemGrupoGeometria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Getter
    @GeneratedValue(generator = "item_grupo_geometria_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "item_grupo_geometria_seq", sequenceName = "item_grupo_geometria_seq", allocationSize = 1, initialValue = 1)
    private Integer id;

    @Getter
    @Setter
    @ManyToOne()
    @JoinColumn(name = "modelogeo_id")
    private ModeloGeometria modeloGeo;

    @Getter
    @Setter
    private boolean opcional;

    @Getter
    @Setter
    @Column(name = "exibirnalicenca")
    private boolean exibirNaLicenca;

    public ItemGrupoGeometria(ModeloGeometria modeloGeometria, boolean opcional, boolean exibirNaLicenca) {
        this.id = null;
        this.modeloGeo = modeloGeometria;
        this.opcional = opcional;
        this.exibirNaLicenca = exibirNaLicenca;
    }

    @Override
    public int hashCode() {
        return Objects.hash(modeloGeo);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        ItemGrupoGeometria other = (ItemGrupoGeometria) obj;
        if (modeloGeo == null) {
            return other.modeloGeo == null;
        } else
            return modeloGeo.equals(other.modeloGeo);
    }
}

package br.gov.go.mago.migrator.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.gov.go.mago.migrator.util.UtilMigrator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tipolicenca")
public class TipoLicenca implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Getter
    @GeneratedValue(generator = "tipo_licenca_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "tipo_licenca_seq", sequenceName = "tipo_licenca_seq", allocationSize = 1, initialValue = 1)
    private Integer id;

    @Getter
    @Setter
    @Length(max = 255, message = "O limite do campo descrição é de 255 caracteres.")
    @Column(nullable = false, length = 255)
    private String nome;

    @Getter
    @Setter
    @Length(max = 10, message = "O limite do campo sigla é de 10 caracteres.")
    @Column(length = 10, nullable = false)
    private String sigla;

    @Getter
    @Setter
    @Length(max = 512, message = "O limite do campo descrição é de 512 caracteres.")
    @Column(nullable = false, length = 512)
    private String descricao;

    @Getter
    @Setter
    @Column(nullable = false, name = "exigeanalise")
    private boolean exigeAnalise;

    @Getter
    @Setter
    @Column(nullable = false, name = "validademeseslicenca")
    private Integer validadeMesesLicenca;

    @Getter
    @Setter
    @Column(name = "prazonotificacao")
    private Integer prazoNotificacao;

    @Getter
    @Setter
    @Column(name = "prazorecurso")
    private Integer prazoRecurso;

    @Getter
    @Setter
    @ManyToOne()
    @JoinColumn(name = "questionario_id")
    private QuestionarioTemplate questionario;

    @Getter
    @Setter
    @Length(max = 2048, message = "O limite do campo de descrição no caput da licença é de 2048 caracteres.")
    @Column(nullable = false, length = 2048, name = "descricaocaputlicenca")
    private String descricaoCaputLicenca;

    @Getter
    @Setter
    @Length(max = 255, message = "O limite do campo modelo relatório é de 255 caracteres.")
    @Column(length = 255, name = "modelorelatorio")
    private String modeloRelatorio;

    @Override
    public String toString() {
        return String.format("%s - %s", nome, sigla);
    }

    @JsonIgnore
    public Date getDataPrazoCorrecaoPendencia() {
        LocalDate hoje = LocalDate.now();
        LocalDate dataPrazoCorrecaoPendencia = hoje.plusDays(getPrazoNotificacao());
        return UtilMigrator.retornaDataProximoDiaUtil(dataPrazoCorrecaoPendencia);
    }

    @JsonIgnore
    public Date getDataPrazoRecursoIndeferimento() {
        LocalDate hoje = LocalDate.now();
        LocalDate dataPrazoRecurso = hoje.plusDays(getPrazoRecurso());
        return UtilMigrator.retornaDataProximoDiaUtil(dataPrazoRecurso);
    }

    public static final Comparator<TipoLicenca> COMPARE_BY_DESCRICAO = new Comparator<TipoLicenca>() {
        public int compare(TipoLicenca one, TipoLicenca other) {
            return one.descricao.toUpperCase().compareTo(other.descricao.toUpperCase());
        }
    };
}
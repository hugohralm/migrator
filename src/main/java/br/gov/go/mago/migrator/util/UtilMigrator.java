package br.gov.go.mago.migrator.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import br.gov.go.mago.migrator.model.enums.EnumClasse;
import br.gov.go.mago.migrator.model.enums.EnumPorte;
import br.gov.go.mago.migrator.model.enums.EnumPotencialPoluidor;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.NONE)
public class UtilMigrator {

    public static boolean isNullOrEmpty(Object o) {
        if (o != null) {
            if (o instanceof String) {
                return ((String) o).isEmpty();
            } else if (o instanceof Collection && ((Collection<?>) o).isEmpty()) {
                return true;
            } else if (o.getClass().isArray() && ((Object[]) o).length == 0) {
                return true;
            }
            return false;
        }
        return true;
    }

    public static String getBigDecimalFormatado(BigDecimal input) {
        final NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
        numberFormat.setGroupingUsed(true);
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setMinimumFractionDigits(2);
        return numberFormat.format(input);
    }

    public static EnumClasse getClasseEmpreendimento(EnumPotencialPoluidor potencial, EnumPorte porte) {
        Map<String, EnumClasse> classe = new HashMap<>();
        classe.put("PD", EnumClasse.CI);
        classe.put("PMI", EnumClasse.CR);
        classe.put("PP", EnumClasse.C1);
        classe.put("PM", EnumClasse.C2);
        classe.put("PG", EnumClasse.C4);
        classe.put("MD", EnumClasse.CI);
        classe.put("MMI", EnumClasse.CR);
        classe.put("MP", EnumClasse.C2);
        classe.put("MM", EnumClasse.C3);
        classe.put("MG", EnumClasse.C5);
        classe.put("AD", EnumClasse.CI);
        classe.put("AMI", EnumClasse.CR);
        classe.put("AP", EnumClasse.C4);
        classe.put("AM", EnumClasse.C5);
        classe.put("AG", EnumClasse.C6);
        return classe.get(potencial.name() + porte.name());
    }

    public static Date retornaDataProximoDiaUtil(Date dataOriginal) {
        Calendar c = Calendar.getInstance();
        c.setTime(dataOriginal);
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            c.add(Calendar.DATE, 2);
        } else if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            c.add(Calendar.DATE, 1);
        }
        return c.getTime();
    }

    public static Date retornaDataProximoDiaUtil(LocalDate dataOriginal) {
        Date data = convertToDateViaInstant(dataOriginal);
        return retornaDataProximoDiaUtil(data);
    }

    public static Date convertToDateViaInstant(LocalDate dateToConvert) {
        return java.util.Date.from(dateToConvert.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate convertDateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static Date adicionarDiasEmData(LocalDate dataOriginal, int dias) {
        LocalDate dataValidade = dataOriginal.plusDays(dias);
        return convertToDateViaInstant(dataValidade);
    }

    public static Date adicionarMesesEmData(LocalDate dataOriginal, int meses) {
        LocalDate dataValidade = dataOriginal.plusMonths(meses);
        return convertToDateViaInstant(dataValidade);
    }
}

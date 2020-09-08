package br.gov.go.mago.migrator.model.contract;

import br.gov.go.mago.migrator.model.QuestionarioTemplate;
import lombok.Data;

@Data
public class ValidaQuestionarioResponse {
    private QuestionarioTemplate questionarioTemplate;
    private boolean possuiPergunta = false;
    private boolean possuiResposta = false;
}

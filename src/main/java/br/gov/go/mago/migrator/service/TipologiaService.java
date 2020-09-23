package br.gov.go.mago.migrator.service;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.go.mago.migrator.model.GrupoModeloCondicionante;
import br.gov.go.mago.migrator.model.GrupoModeloGeometria;
import br.gov.go.mago.migrator.model.GrupoModeloTermo;
import br.gov.go.mago.migrator.model.ModeloDeclaracao;
import br.gov.go.mago.migrator.model.ParametroTemplate;
import br.gov.go.mago.migrator.model.QuestionarioTemplate;
import br.gov.go.mago.migrator.model.Tipologia;
import br.gov.go.mago.migrator.repository.TipologiaRepository;

@Service
public class TipologiaService {

    private final TipologiaRepository repository;

    private final AtividadeService atividadeService;

    private final TipoLicencaService tipoLicencaService;

    private final QuestionarioTemplateService questionarioTemplateService;

    private final ModeloDeclaracaoService modeloDeclaracaoService;

    private final ParametroTemplateService parametroTemplateService;

    private final GrupoModeloCondicionanteService grupoModeloCondicionanteService;

    private final GrupoModeloGeometriaService grupoModeloGeometriaService;

    private final GrupoModeloTermoService grupoModeloTermoService;

    @Autowired
    public TipologiaService(TipologiaRepository repository, AtividadeService atividadeService,
            TipoLicencaService tipoLicencaService, QuestionarioTemplateService questionarioTemplateService,
            ModeloDeclaracaoService modeloDeclaracaoService, ParametroTemplateService parametroTemplateService,
            GrupoModeloCondicionanteService grupoModeloCondicionanteService,
            GrupoModeloGeometriaService grupoModeloGeometriaService, GrupoModeloTermoService grupoModeloTermoService) {
        this.repository = repository;
        this.atividadeService = atividadeService;
        this.tipoLicencaService = tipoLicencaService;
        this.questionarioTemplateService = questionarioTemplateService;
        this.modeloDeclaracaoService = modeloDeclaracaoService;
        this.parametroTemplateService = parametroTemplateService;
        this.grupoModeloCondicionanteService = grupoModeloCondicionanteService;
        this.grupoModeloGeometriaService = grupoModeloGeometriaService;
        this.grupoModeloTermoService = grupoModeloTermoService;
    }

    public Tipologia getById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Não foi possível encontrar a tipologia solicitada."));
    }

    @Transactional(rollbackFor = Throwable.class)
    public Tipologia create(Tipologia tipologia) {
        Tipologia tipologiaNova = new Tipologia(tipologia);
        tipologiaNova.setAtividade(this.atividadeService.getByCodigoDescricao(tipologia.getAtividade()));
        tipologiaNova.setTipoLicenca(this.tipoLicencaService.getByNomeDescricao(tipologia.getTipoLicenca()));
        return repository.saveAndFlush(tipologiaNova);
    }

    @Transactional(rollbackFor = Throwable.class)
    public Tipologia update(Tipologia tipologia) {
        return repository.saveAndFlush(tipologia);
    }

    @Transactional(rollbackFor = Throwable.class)
    public Tipologia migrarItensTipologia(Tipologia tipologiaSalva, Tipologia tipologia) {
        tipologiaSalva.setQuestionarioRequerimento(
                this.questionarioTemplateService.getByDescricaoTipoQuestionarioTipoLicenciamentoRegimeLicenciamento(
                        tipologia.getQuestionarioRequerimento()));
        tipologiaSalva.setModeloRequerimento(
                this.modeloDeclaracaoService.getOrCreateByNome(tipologia.getModeloRequerimento()));
        tipologiaSalva.setQuestionariosTemplate(preencherQuestionariosTemplate(tipologia.getQuestionariosTemplate()));
        tipologiaSalva.setModelosDeclaracao(preencherModelosDeclaracao(tipologia.getModelosDeclaracao()));
        tipologiaSalva.setModelosLaudos(preencherModelosDeclaracao(tipologia.getModelosLaudos()));
        tipologiaSalva.setParametrosIniciais(preencherParametrosIniciais(tipologia.getParametrosIniciais()));
        tipologiaSalva.setGruposModeloCondicionante(
                preencherGruposModelosCondicionantes(tipologia.getGruposModeloCondicionante()));
        tipologiaSalva.setGruposModeloTermo(preencherGruposModelosTermos(tipologia.getGruposModeloTermo()));
        tipologiaSalva.setGruposModeloGeometria(preencherGruposModelosGeometrias(tipologia.getGruposModeloGeometria()));

        return update(tipologiaSalva);
    }

    private Set<GrupoModeloGeometria> preencherGruposModelosGeometrias(Set<GrupoModeloGeometria> gruposModeloGeoParam) {
        Set<GrupoModeloGeometria> gruposModelosGeometria = new HashSet<>();
        for (GrupoModeloGeometria grupoModelo : gruposModeloGeoParam) {
            gruposModelosGeometria.add(this.grupoModeloGeometriaService.getOrCreateByDescricao(grupoModelo));
        }
        return gruposModelosGeometria;
    }

    private Set<GrupoModeloTermo> preencherGruposModelosTermos(Set<GrupoModeloTermo> gruposModeloTermoParam) {
        Set<GrupoModeloTermo> gruposModelosTermos = new HashSet<>();
        for (GrupoModeloTermo grupoModelo : gruposModeloTermoParam) {
            gruposModelosTermos.add(this.grupoModeloTermoService.getOrCreateByDescricao(grupoModelo));
        }
        return gruposModelosTermos;
    }

    private Set<GrupoModeloCondicionante> preencherGruposModelosCondicionantes(
            Set<GrupoModeloCondicionante> gruposModeloCondicionanteParam) {
        Set<GrupoModeloCondicionante> gruposModelosCondicionantes = new HashSet<>();
        for (GrupoModeloCondicionante grupoModelo : gruposModeloCondicionanteParam) {
            gruposModelosCondicionantes.add(this.grupoModeloCondicionanteService.getOrCreateByDescricao(grupoModelo));
        }
        return gruposModelosCondicionantes;
    }

    private Set<QuestionarioTemplate> preencherQuestionariosTemplate(Set<QuestionarioTemplate> questsTemplate) {
        Set<QuestionarioTemplate> questionariosTemplate = new HashSet<>();
        for (QuestionarioTemplate questionarioTemplate : questsTemplate) {
            questionariosTemplate.add(this.questionarioTemplateService
                    .getByDescricaoTipoQuestionarioTipoLicenciamentoRegimeLicenciamento(questionarioTemplate));
        }
        return questionariosTemplate;
    }

    private Set<ModeloDeclaracao> preencherModelosDeclaracao(Set<ModeloDeclaracao> modDeclaracoes) {
        Set<ModeloDeclaracao> modelosDeclaracoes = new HashSet<>();
        for (ModeloDeclaracao modeloDeclaracao : modDeclaracoes) {
            modelosDeclaracoes.add(this.modeloDeclaracaoService.getOrCreateByNome(modeloDeclaracao));
        }
        return modelosDeclaracoes;
    }

    private Set<ParametroTemplate> preencherParametrosIniciais(Set<ParametroTemplate> paramIniciais) {
        Set<ParametroTemplate> parametrosIniciais = new HashSet<>();
        for (ParametroTemplate parametroTemplate : paramIniciais) {
            parametrosIniciais.add(this.parametroTemplateService.getOrCreateByDescricao(parametroTemplate));
        }
        return parametrosIniciais;
    }
}

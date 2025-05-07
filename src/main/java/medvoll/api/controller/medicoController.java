package medvoll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import medvoll.api.medico.DadosAtualizacaoMedicos;
import medvoll.api.medico.DadosCadastroMedicos;
import medvoll.api.medico.DadosDetalhamentoMedicos;
import medvoll.api.medico.DadosListagemMedicos;
import medvoll.api.medico.medico;
import medvoll.api.medico.medicoRepository;

@RestController
@RequestMapping("medicos")
public class medicoController {

    @Autowired
    private medicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedicos dados, UriComponentsBuilder uriBuilder){

        var medico = new medico(dados);
        repository.save(medico);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedicos(medico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedicos>> listar(Pageable paginacao){
        var page =  repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedicos::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoMedicos dados){
        var medico = repository.getReferenceById(dados.Id());
        medico.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoMedicos(medico));
    }

    @DeleteMapping("/{Id}")
    @Transactional
    public ResponseEntity excluir( @PathVariable Long Id){
        var medico = repository.getReferenceById(Id);
        medico.excluir();
        
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{Id}")
    public ResponseEntity detalhar( @PathVariable Long Id){
        var medico = repository.getReferenceById(Id);
        
        return ResponseEntity.ok(new DadosDetalhamentoMedicos(medico));
    }

}

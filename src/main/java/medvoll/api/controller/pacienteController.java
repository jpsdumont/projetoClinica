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
import medvoll.api.paciente.DadosAtualizacaoPaciente;
import medvoll.api.paciente.DadosCadastrosPaciente;
import medvoll.api.paciente.DadosDetalhamentoPacientes;
import medvoll.api.paciente.DadosListagemPacientes;
import medvoll.api.paciente.paciente;
import medvoll.api.paciente.pacienteRepository;

@RestController
@RequestMapping("paciente")
public class pacienteController {

    @Autowired
    private pacienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastrosPaciente dados, UriComponentsBuilder uriBuilder){

        var paciente = new paciente(dados);
        repository.save(paciente);

        var uri = uriBuilder.path("/paciente/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoPacientes(paciente));
    }

    @GetMapping
    public  ResponseEntity<Page<DadosListagemPacientes>> listar(Pageable ordenacao){
        var page =  repository.findAllByAtivoTrue(ordenacao).map(DadosListagemPacientes :: new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoPaciente dados ){
        var paciente = repository.getReferenceById(dados.id());
        paciente.atualizarInformacoes(dados);

        return ResponseEntity.ok( new DadosDetalhamentoPacientes(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir( @PathVariable Long id){
        var paciente = repository.getReferenceById(id);
        paciente.excluir();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar( @PathVariable Long id){
        var paciente = repository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoPacientes(paciente));
    }

}

package medvoll.api.paciente;

import medvoll.api.endereco.Endereco;

public record DadosDetalhamentoPacientes(Long id, String nome, String email, String telefone, String cpf, Endereco endereco)  {

    public DadosDetalhamentoPacientes(paciente paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(), paciente.getEndereco());
    }

}

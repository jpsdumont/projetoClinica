package medvoll.api.paciente;

public record DadosListagemPacientes(Long id, String nome, String telefone, String email)  {

    public DadosListagemPacientes(paciente paciente){
        this( paciente.getId(),paciente.getNome(), paciente.getTelefone(), paciente.getEmail() );
    }
}

package medvoll.api.medico;

public record DadosListagemMedicos(long Id, String nome, String email, String crm, Especialidade especialidade)  {

    public DadosListagemMedicos(medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }

}

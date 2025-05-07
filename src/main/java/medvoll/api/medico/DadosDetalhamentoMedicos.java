package medvoll.api.medico;

import medvoll.api.endereco.Endereco;

public record DadosDetalhamentoMedicos(Long Id,
    String nome, 
    String email,
    String crm, 
    String telefone, 
    Especialidade especialidade, 
    Endereco endereco)  {

        public DadosDetalhamentoMedicos(medico medico){
            this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(), medico.getEspecialidade(),medico.getEndereco() );
        }

}

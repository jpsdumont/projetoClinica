package medvoll.api.medico;

import jakarta.validation.constraints.NotNull;
import medvoll.api.endereco.DadosEndereco;

public record DadosAtualizacaoMedicos(

    @NotNull
    Long Id,
    
    String nome,

    String telefone,

    DadosEndereco endereco) {


}

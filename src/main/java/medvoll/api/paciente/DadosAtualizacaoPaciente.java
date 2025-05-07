package medvoll.api.paciente;

import jakarta.validation.constraints.NotNull;
import medvoll.api.endereco.DadosEndereco;

public record DadosAtualizacaoPaciente(
    @NotNull
    Long id, 

    String nome,

    String telefone,
     
    DadosEndereco endereco )  {

}

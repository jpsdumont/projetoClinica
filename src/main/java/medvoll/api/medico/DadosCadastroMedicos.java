package medvoll.api.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import medvoll.api.endereco.DadosEndereco;

public record DadosCadastroMedicos(
    @NotBlank
    String nome,

    @NotBlank
    @Email
    String email,

    @NotBlank
    String telefone,

    @NotBlank
    @Pattern(regexp = "\\d{4,6}")  // Corrigido o padrão para validar 4 a 6 dígitos
    String crm, 

    @NotNull
    Especialidade especialidade,

    @NotNull
    @Valid
    DadosEndereco endereco){

}

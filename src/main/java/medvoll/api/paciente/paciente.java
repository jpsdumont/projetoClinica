package medvoll.api.paciente;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import medvoll.api.endereco.Endereco;

@Table(name = "pacientes")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity

public class paciente {
    public paciente(DadosCadastrosPaciente dados) {
        this.cpf = dados.cpf();
        this.ativo = true;
        this.email = dados.email();
        this.nome = dados.nome();
        this.telefone = dados.telefone();
        this.endereco = new Endereco(dados.endereco());
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean ativo;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;

    @Embedded
    private Endereco endereco;

    public void atualizarInformacoes(DadosAtualizacaoPaciente dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
           }
           if (dados.telefone() != null) {
            this.telefone = dados.telefone();
           }
           if (dados.endereco() != null){
            this.endereco.atualizarInformacoes(dados.endereco());
           }
    }

    public void excluir() {
        this.ativo = false;
    }

}

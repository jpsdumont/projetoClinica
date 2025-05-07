package medvoll.api.medico;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import medvoll.api.endereco.Endereco;

@Table(name = "medicos")
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "Id")

public class medico {

    public medico(DadosCadastroMedicos dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.crm = dados.crm();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String nome;
    private String telefone;
    private String crm;
    private String email;

    private Boolean ativo;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    public void atualizarInformacoes(DadosAtualizacaoMedicos dados) {
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

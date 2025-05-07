package medvoll.api.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {

    public Endereco(DadosEndereco dados) {
        this.bairro = dados.bairro();
        this.cep = dados.cep();
        this.cidade = dados.cidade();
        this.complemento = dados.complemento();
        this.numero = dados.numero();
        this.uf = dados.uf();
        this.logradouro = dados.logradouro();
    }
    private String logradouro;
    private String cep;
    private String numero;
    private String complemento;
    private String uf;
    private String cidade;
    private String bairro;

    public  void atualizarInformacoes(DadosEndereco dados){
        if (dados.logradouro() != null) {
            this.logradouro = dados.logradouro();
        }
        if (dados.cep() != null) {
            this.cep = dados.cep();
        }
        if (dados.numero() != null) {
            this.numero = dados.numero();
        }
        if (dados.complemento() != null) {
            this.complemento = dados.complemento();
        }
        if (dados.uf() != null) {
            this.uf = dados.uf();
        }
        if (dados.cidade() != null) {
            this.cidade = dados.cidade();
        }
        if (dados.bairro() != null) {
            this.bairro = dados.bairro();
        }
    }

}

package desafio;

import java.util.List;

public interface IConta {

    void sacar();

    void depositar();

    void sacar(double valor);

    void depositar(double valor);


    void imprimirExtrato();

    void transferir(List<Conta> contas);

    void transferir(double valor, Conta conta);
}

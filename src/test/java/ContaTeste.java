import desafio.Cliente;
import desafio.Conta;
import desafio.ContaCorrente;
import desafio.ContaPoupanca;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContaTeste {
    @Test
    void criarContaCorrenteTeste(){
        Conta conta = new ContaCorrente(new Cliente("cliente"));
        Assertions.assertNotNull(conta);
    }

    @Test
    void criarContaPoupancaTeste(){
        Conta conta = new ContaPoupanca(new Cliente("cliente"));
        Assertions.assertNotNull(conta);
    }

    @Test
    void sacarTeste(){
        Conta conta = new ContaCorrente(new Cliente("cliente"));
        conta.sacar(50);

        Assertions.assertEquals(-50, conta.getSaldo());
    }

    @Test
    void depositoTeste(){
        Conta conta = new ContaCorrente(new Cliente("cliente"));
        conta.depositar(50);

        Assertions.assertEquals(50, conta.getSaldo());
    }

    @Test
    void transferenciaTeste(){
        Conta contaOrigem = new ContaCorrente(new Cliente("cliente 1"));
        Conta contaDestino = new ContaCorrente(new Cliente("cliente 2"));
        contaOrigem.transferir(100, contaDestino);

        Assertions.assertEquals(-100, contaOrigem.getSaldo());
        Assertions.assertEquals(100, contaDestino.getSaldo());

    }

}

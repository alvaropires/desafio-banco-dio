package desafio;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public abstract class Conta implements IConta {

    private static final int AGENCIA_PADRAO = 1;
    private static int SEQUENCIAL = 1;

    protected int agencia;
    protected int numero;
    protected double saldo;
    protected Cliente cliente;

    public Conta(Cliente cliente) {
        this.agencia = Conta.AGENCIA_PADRAO;
        this.numero = SEQUENCIAL++;
        this.cliente = cliente;
    }


    public int getAgencia() {
        return agencia;
    }

    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    @Override
    public void sacar() {
        try{
            Scanner scanner = new Scanner(System.in);
            System.out.println("Digite o valor que deseja sacar: R$ ");
            Double valor = scanner.nextDouble();
            this.saldo -= valor;
        }catch (InputMismatchException e){
            System.err.println("Tipo de dado inválido. Não foi possível realizar o saque.");
        }
    }

    @Override
    public void depositar() {
        try{
            Scanner scanner = new Scanner(System.in);
            System.out.println("Digite o valor que deseja depositar: R$ ");
            Double valor = scanner.nextDouble();
            this.saldo += valor;
        }catch (InputMismatchException e){
            System.err.println("Tipo de dado inválido. Não foi possível realizar o depósito");
        }

    }

    @Override
    public void transferir(List<Conta> contas) {
        Scanner scanner = new Scanner(System.in);
        try{
            System.out.println("Digite o valor que deseja transferir: R$ ");
            Double valor = scanner.nextDouble();
            Conta contaDestino = Conta.numeroContaDestino(contas);
            this.sacar(valor);
            contaDestino.depositar(valor);
        }catch (InputMismatchException e){
            System.err.println("Tipo de dado inválido. Não foi possível realizar a transferência.");
        }
    }

    @Override
    public void sacar(double valor) {
        this.saldo -= valor;

    }

    @Override
    public void depositar(double valor) {
        this.saldo += valor;
    }

    @Override
    public void transferir(double valor, Conta conta) {
        sacar(valor);
        conta.depositar(valor);
    }

    protected void imprimirInfosComuns() {
        System.out.println(String.format("Titular: %s", this.cliente.getNome()));
        System.out.println(String.format("Agência: %d", this.agencia));
        System.out.println(String.format("Número: %d", this.numero));
        System.out.println(String.format("Saldo: R$ %.2f", this.saldo));
    }

    public static Conta criarConta(Integer tipoConta) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome do titular da conta: ");
        String nome = scanner.next();

        if(tipoConta == 1){
            Conta contaCorrente = new ContaCorrente(new Cliente(nome));
            System.out.println("Conta Corrente criada com sucesso!");
            return contaCorrente;
        }
        else {
            Conta contaPoupanca = new ContaPoupanca(new Cliente(nome));
            System.out.println("Conta Poupança criada com sucesso!");
            return contaPoupanca;
        }
    }

    public static Integer indiceDeContaEmLista (Integer valorParaBusca,List<Conta> contas){
        Integer indice = null;
        for (Conta conta: contas) if(conta.getNumero() == valorParaBusca) indice = contas.indexOf(conta);
        return indice;
    }

    public static Conta acessarConta (List<Conta> contas) {
        Scanner scanner = new Scanner(System.in);
        Conta contaParaAcessar = null;
        Integer numeroDaConta;
        Boolean continueLooping = true;
        do{
            try {
                System.out.println("Qual o número da conta que deseja acessar? ");
                numeroDaConta = scanner.nextInt();
                Integer indiceDaContaSolicitada = Conta.indiceDeContaEmLista(numeroDaConta, contas);
                contaParaAcessar = contas.get(indiceDaContaSolicitada);
                continueLooping = false;
            } catch (NullPointerException e) {
                System.err.println("Entrada inválida. Digite um número de conta válido.");
            } catch (InputMismatchException e){
                System.err.println("Tipo de dado inválido. Digite um número de conta válido.");
                scanner.nextLine();
            }
        }while (continueLooping);
        return contaParaAcessar;
    }

    public static Conta numeroContaDestino(List<Conta> contas){
        Scanner scanner = new Scanner(System.in);
        Conta numeroDaContaDestino = null;
        Boolean continueLooping = true;
        do{
            try{
                System.out.println("Digite o numero da conta destino: ");
                Integer numeroContaDestino = scanner.nextInt();
                Integer indiceContaDestino = Conta.indiceDeContaEmLista(numeroContaDestino,contas);
                numeroDaContaDestino = contas.get(indiceContaDestino);
                continueLooping = false;
            }catch (NullPointerException e) {
                System.err.println("Entrada inválida. Digite um número de conta válido.");
            } catch (InputMismatchException e){
                System.err.println("Tipo de dado inválido. Digite um número de conta válido.");
                scanner.nextLine();
            }
        } while (continueLooping);

        return numeroDaContaDestino;
    }
}

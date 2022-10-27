package desafio;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco();
        banco.setNome("Digital Bank DIO");
        Scanner scanner = new Scanner(System.in);
        Boolean condicaoAcessoAoBanco = true;
        Integer opcoes;
        List<Conta> contas = new ArrayList<>();

        while (condicaoAcessoAoBanco){
            try{
                menuDeOpcoes(banco);
                opcoes = scanner.nextInt();
                switch (opcoes){
                    case 1:
                    case 2:
                        contas.add(Conta.criarConta(opcoes));
                        break;
                    case 3:
                        if(contas.isEmpty()){
                            System.out.println("Não existe nenhuma conta cadastrada.");
                            break;
                        }
                        Conta contaParaAcessar = Conta.acessarConta(contas);
                        Boolean condicaoMenuOperacaoBancaria = true;
                        while (condicaoMenuOperacaoBancaria){
                            menuDeOperacoesBancarias();
                            Integer opcaoMenuOperacoes = scanner.nextInt();
                            switch (opcaoMenuOperacoes){
                                case 1:
                                /*System.out.println("Digite o valor que deseja sacar: R$ ");
                                Double valorDaOperacao = scanner.nextDouble();*/
                                    contaParaAcessar.sacar();
                                    break;
                                case 2:

                                    contaParaAcessar.depositar();
                                    break;

                                case 3:
                                    contaParaAcessar.transferir(contas);
                                    break;
                                case 4:
                                    contaParaAcessar.imprimirExtrato();
                                    break;
                                case 5:
                                    condicaoMenuOperacaoBancaria = false;
                                    break;
                                default:
                                    System.out.println("Opção inválida.");
                            }
                        }
                        break;

                    case 4:
                        for (Conta conta : contas) {
                            conta.imprimirExtrato();
                            System.out.println("__________________________");
                        }
                        break;
                    case 5:
                        System.out.println(String.format("Finalizando acesso ao %s...\n", banco.getNome()));
                        condicaoAcessoAoBanco = false;
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            }catch (InputMismatchException e){
                System.err.println("Tipo de dado inválido. Digite uma opção válida.");
                scanner.nextLine();
            }
        }
    }
    public static void menuDeOpcoes(Banco banco){
        System.out.println(String.format("\n--\t Seja bem-vindo ao %s.\t--\n", banco.getNome()));
        System.out.println("1 - Criar uma nova conta corrente.");
        System.out.println("2 - Criar uma nova conta poupança.");
        System.out.println("3 - Acessar uma conta (Realizar operações).");
        System.out.println("4 - Imprimir dados das contas.");
        System.out.println("5 - Finalizar acesso.");
        System.out.println("\nDigite sua opção: ");
    }

    public static void menuDeOperacoesBancarias(){
        System.out.println("\n-- MENU DE OPERAÇÕES BANCÁRIAS --\t");
        System.out.println("QUAIS OPERAÇÕES DESEJA REALIZAR?\n");
        System.out.println("1 - Sacar.");
        System.out.println("2 - Depositar.");
        System.out.println("3 - Transferir.");
        System.out.println("4 - Imprimir extrato.");
        System.out.println("5 - Voltar ao menu de opções.");

    }

}



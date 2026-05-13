package com.TaskList;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void limparTela(){
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

    private static int lerInt(Scanner entrada){
        int valor = 0;
        while(true){
            if (entrada.hasNextInt()) {
                valor = entrada.nextInt();
                entrada.nextLine();
                return valor;
            }else {
                entrada.nextLine();
                System.out.println("Entrada invalida! Digite um numero: ");
            }
        }
    }

    private static String lerString(Scanner entrada){
        return entrada.nextLine();
    }

    private static Task.Prioridade escolherPrioridade(Scanner entrada){
        System.out.println("Prioridade: ");
        System.out.println("[0] - Alta");
        System.out.println("[1] - Moderada");
        System.out.println("[2] - Baixa");

        int op = lerInt(entrada);

        switch (op){
            case 0: return Task.Prioridade.ALTA;
            case 1: return Task.Prioridade.MODERADA;
            case 2: return Task.Prioridade.BAIXA;
            default: System.out.println("Opcao invalida. Usando Baixa.");
                return Task.Prioridade.BAIXA;
        }
    }

    private static Task.Status escolherStatus(Scanner entrada){
        System.out.println("Status: ");
        System.out.println("[0] - Pendente");
        System.out.println("[1] - Concluida");

        int op = lerInt(entrada);

        switch (op){
            case 0: return Task.Status.PENDENTE;
            case 1: return Task.Status.CONCLUIDA;
            default: System.out.println("Opcao invalida. Usando Pendente.");
                return Task.Status.PENDENTE;
        }
    }

    public static void main(String[] args) {
        TaskService service = new TaskService();
        Scanner entrada = new Scanner(System.in);
        int opcao;
        do {
            limparTela();

            System.out.println("==== MENU ====");
            System.out.println("[1] - Adicionar tarefa");
            System.out.println("[2] - Remover tarefa");
            System.out.println("[3] - Listar tarefas");
            System.out.println("[4] - Editar tarefas");
            System.out.println("[0] - Sair");
            System.out.println("Escolha: ");
            opcao = lerInt(entrada);

            switch (opcao){
                case 1 -> {
                    limparTela();
                    System.out.println(" ==== NOVA TAREFA ==== ");
                    System.out.println("Titulo: ");
                    String titulo = lerString(entrada);

                    System.out.println("ID: ");
                    int id = lerInt(entrada);

                    Task.Prioridade p = escolherPrioridade(entrada);
                    service.adicionarTask(titulo, id, p);
                    System.out.println("Tarefa adicionada com sucesso!");
                }

                case 2 -> {
                    limparTela();
                    System.out.println(" ==== REMOVER TAREFA ==== ");
                    System.out.println("ID da tarefa para remover: ");
                    int id = lerInt(entrada);
                    service.deleteTask(id);
                    System.out.println("Tarefa deletada com sucesso!");
                }

                case 3 -> {
                    limparTela();
                    System.out.println(" ==== LISTA DE TAREFAS ==== ");
                    service.listarTask();
                }

                case 4 -> {
                    limparTela();
                    System.out.println(" ==== EDITAR TAREFA ==== ");
                    System.out.println("ID da tarefa para editar: ");
                    int id = lerInt(entrada);

                    System.out.println("Novo titulo: ");
                    String titulo = lerString(entrada);

                    System.out.println("Nova prioridade: ");
                    Task.Prioridade p = escolherPrioridade(entrada);

                    System.out.println("Novo Status: ");
                    Task.Status s = escolherStatus(entrada);

                    service.atualizarTask(titulo, id, p, s);
                }

                case 0 -> {
                    limparTela();
                    System.out.println("Saindo...");
                }

                default -> {
                    limparTela();
                    System.out.println("Opcao invalida!");
                }
            }

        }while (opcao != 0);
        entrada.close();
    }
}

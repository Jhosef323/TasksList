package com.TaskList;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {

    public void salvar(List<Task> tarefas, String caminho){
        try {
            List<String> linhas = new ArrayList<>();
            for (Task t: tarefas){
            linhas.add(t.toFileString());}

            Files.write(Path.of(caminho), linhas);
            System.out.println("Salvo.");
        } catch (IOException e){
            System.out.println("Erro ao salvar: " + e.getMessage());
        }
    }
    public List<Task> carregar(String caminho){
        List<Task> tarefas = new ArrayList<>();
        Path path = Path.of(caminho);
        try{
            if(!Files.exists(path)){ return tarefas;}

            List<String> linhas = Files.readAllLines(path);

            if (!linhas.isEmpty() && linhas.getFirst().startsWith("\uFEFF")){
                linhas.set(0, linhas.getFirst().substring(1));
            }

            for (String linha: linhas) {
                String[] partes = linha.split("\\|\\|", -1);
                if (partes.length == 4) {
                    String titulo = partes[0];

                    try {
                        int id = Integer.parseInt(partes[1]);
                        Task.Prioridade nivel = Task.Prioridade.valueOf(partes[2]);
                        Task.Status stat = Task.Status.valueOf(partes[3]);
                        tarefas.add(new Task(titulo, id, nivel, stat));
                    }catch (IllegalArgumentException e){
                        System.out.println("Linha invalida ignorada: " + linha);
                    }
                }else {System.out.println("Linha ignorada (formato invalido)" + linha);}
            }
        }catch (IOException | NumberFormatException e){
            System.out.println("Erro ao carregar: " + e.getMessage());
        }
        return tarefas;
        }
    }


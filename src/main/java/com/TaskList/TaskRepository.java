package com.TaskList;


import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {

    public void salvar(List<Task> tarefas, String caminho){
        try {
            List<String> linhas = new ArrayList<>();
            linhas.add("\uFEFF");
            for (Task t: tarefas){
            linhas.add(t.toString());}
            Files.write(Path.of(caminho), linhas);
            System.out.println("Arquivo salvo com sucesso!");
        } catch (Exception e){
            System.out.println("Erro ao salvar: " + e.getMessage());
        }
    }
    public List<Task> carregar(String caminho){
        List<Task> tarefas = new ArrayList<>();
        try{
            if(!Files.exists(Path.of(caminho))){ return tarefas;}
            List<String> linhas = Files.readAllLines(Path.of(caminho));
            if (!linhas.isEmpty() && linhas.get(0).startsWith("\uFEFF")){
                linhas.set(0, linhas.get(0).substring(1));
            }
            for (String linha: linhas) {
                String[] partes = linha.split("\\|", -1);
                if (partes.length == 4) {
                    int id = Integer.parseInt(partes[0]);
                    String titulo = partes[1];
                    try {
                        Task.Prioridade nivel = Task.Prioridade.valueOf(partes[2]);
                        Task.Status stat = Task.Status.valueOf(partes[3]);
                        tarefas.add(new Task(titulo, id, nivel, stat));
                    }catch (IllegalArgumentException e){
                        System.out.println("Linha invalida ignorada: " + linha);
                    }
                }else {System.out.println("Linha ignorada (formato invalido)" + linha);}
            }
        }catch (Exception e){
            System.out.println("Erro ao carregar: " + e.getMessage());
        }
        return tarefas;
        }
    }


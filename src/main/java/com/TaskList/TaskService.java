package com.TaskList;

import java.util.*;

public class TaskService {
    private static final String CAMINHO = "tasks.txt";
    private ArrayList<Task> tasks = new ArrayList<>();
    private TaskRepository repo = new TaskRepository();

    public TaskService(){
        carregar();
    }
    public void salva(){
       repo.salvar(this.tasks, CAMINHO);
    }
    public void carregar(){
        this.tasks = new ArrayList<>(repo.carregar(CAMINHO));
        System.out.println("Arquivo carregado com sucesso!");
    }
    public void adicionarTask(String titulo, int id, Task.Prioridade prioridade){
        if (id <= 0){System.out.println("ID digitado invalido!"); return;}
        if(idExiste(id, null)){ System.out.println("ID ja existente!"); return;}

        Task novo = new Task (titulo, id, prioridade, Task.Status.PENDENTE);
        tasks.add(novo);
        salva();
    }
    public void deleteTask(int id) {
        boolean encontrada = tasks.removeIf(t -> t.getId() == id);
        if(encontrada) {
            salva();
        }else{
            System.out.println("Task nao encontrada");
        }

    }
    public boolean idExiste(int id, Task atual){
        for(Task t: tasks){
            if(t.getId() == id){
                if(atual == null) return true;
                if(t != atual) return true;
            }
        }
        return false;
    }
    public void ordenarPrioridade(){
        List<Task> t = tasks;
        Collections.sort(t, new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                return Integer.compare(o2.getNivel().ordinal(), o1.getNivel().ordinal());
            }
        });
    }
    public List<Task> listarTask() {
        if (tasks.isEmpty()) {
            System.out.println("Nenhuma tarefa cadastrada.");
            return null;
        }
        ordenarPrioridade();
        int i = 1;
        for (Task t: tasks) {
            System.out.println("Tarefa: " + i++);
            System.out.println("Titulo: " + t.getTitulo());
            System.out.println("ID: " + t.getId());
            System.out.println("Prioridade: " + t.getNivel());
            System.out.println("Status: " + t.getStat());
        }
        return null;
    }
    public void atualizarTask(String titulo, int id, Task.Prioridade prioridade, Task.Status status){
        if (tasks.isEmpty()) {
            System.out.println("Nenhuma tarefa cadastrada.");
            return;
        }
        Task encontrada = null;

        for (Task t : tasks){
            if(t.getId() == id) {
                encontrada = t;
                break;
            }
        }
        if (encontrada == null){
            System.out.println("Tarefa nao encontrada!");
            return;
        }
        if(id <= 0){
            System.out.println("ID digitado invalido!");
            return;
        }
        if(idExiste(id, encontrada)){ System.out.println("ID ja existente!");
            return;}

        encontrada.setTitulo(titulo);
        encontrada.setId(id);
        encontrada.setNivel(prioridade);
        encontrada.setStat(status);

        System.out.println("Tarefa atualizada com sucesso!");
        salva();
    }

}

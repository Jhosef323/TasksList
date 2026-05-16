package com.TaskList;

import java.util.*;

import static com.TaskList.Task.Status.CONCLUIDA;
import static com.TaskList.Task.Status.PENDENTE;

public class TaskService {
    private static final String CAMINHO = "tasks.txt";
    private ArrayList<Task> tasks = new ArrayList<>();
    private final TaskRepository repo = new TaskRepository();

    public void salva(){
       repo.salvar(this.tasks, CAMINHO);
    }
    public void carregar(){
        this.tasks = new ArrayList<>(repo.carregar(CAMINHO));
        System.out.println("Arquivo carregado com sucesso!");
    }

    public TaskService(){
        carregar();
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
        t.sort((o1, o2) -> Integer.compare(o2.getNivel().ordinal(), o1.getNivel().ordinal()));
    }
    public List<Task> listarTask() {
        return new ArrayList<>(tasks);
    }

    public Task buscarTask(int id){
        return tasks.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void editarTask(String titulo, int id, Task.Prioridade prioridade, Task.Status status){

        Task encontrada = buscarTask(id);

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
    public void concluirTask(int id){
        if(tasks.isEmpty()){
            System.out.println("Nenhuma tarefa cadastrada.");
            return;
        }

        Task procura = buscarTask(id);

        for (Task t: tasks){
            if(t.getId() == id){
                procura = t;
                break;
            }
        }

        if (id <= 0){
            System.out.println("Digite um ID valido!");
            return;
        }

        if(procura == null){
            System.out.println("Tarefa nao encontrada!");
            return;
        }
        if(procura.getStat() == PENDENTE){
            procura.setStat(CONCLUIDA);
            System.out.println("Tarefa esta concluida!");
            salva();
        }else {
            procura.setStat(PENDENTE);
            System.out.println("Tarefa pendente!");
            salva();
        }
    }
}

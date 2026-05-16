package com.TaskList;

public class Task {
    @Override
    public String toString(){
        return String.format("Titulo: %s | ID: %d | Prioridade: %s | %s",
                titulo, id, nivel, stat);
    }

    public String toFileString(){
        return titulo + "||" +
               id + "||" +
               nivel.name() + "||" +
               stat.name();
    }

    public enum Prioridade{BAIXA, MODERADA, ALTA;
    @Override
        public String toString(){
        return switch (this) {
            case BAIXA -> "Baixa";
            case MODERADA -> "Moderada";
            case ALTA -> "Alta";
        };
      }
    }
    public enum Status{PENDENTE, CONCLUIDA;
    @Override
        public String toString(){
        return switch (this) {
            case PENDENTE -> "Pendente";
            case CONCLUIDA -> "Concluida";
        };
       }
    }

    private Prioridade nivel;
    private Status stat;

    private String titulo;
    private int id;

    public Task(String titulo, int id,Prioridade nivel, Status stat){
        this.titulo = titulo;
        this.id = id;
        this.nivel = nivel;
        this.stat = stat;
    }
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }
    public String getTitulo(){
        return titulo;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
    public Prioridade getNivel(){
        return nivel;
    }
    public Status getStat(){
        return stat;
    }
    public void setNivel(Prioridade nivel){
        this.nivel = nivel;
    }
    public void setStat(Status stat){
        this.stat = stat;
    }
}

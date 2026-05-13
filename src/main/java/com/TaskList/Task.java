package com.TaskList;

public class Task {
    @Override
    public String toString(){
        return String.format("ID: %d | Titulo: %s | Prioridade: %s | Status: %s",
                this.getId(), this.getTitulo(), this.getNivel(), this.getStat());
    }
    public enum Prioridade{BAIXA, MODERADA, ALTA;
    @Override
        public String toString(){
        switch (this){
            case BAIXA: return "Baixa";
            case MODERADA: return "Moderada";
            case ALTA: return "Alta";
            default: return "Desconhecido";
        }
      }
    };
    public enum Status{PENDENTE, CONCLUIDA;
    @Override
        public String toString(){
        switch (this){
            case PENDENTE: return "Pendente";
            case CONCLUIDA: return "Concluida";
            default: return "Desconhecido";
         }
       }
    };

    private Prioridade nivel;
    private Status stat;

    private String titulo = "";
    private int id = 0;

    public Task(String titulo, int id,Prioridade nivel, Status stat){
        this.titulo = titulo;
        this.id = id;
        this.nivel = nivel;
        this.stat = stat;
    };
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

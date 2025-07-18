package com.example.eventmanagementapp;

import java.io.Serializable;
import java.util.ArrayList;

public class Utilizador implements Serializable {

    private int id;
    private String nome;
    private String email;
    private String password;
    private Boolean tipo;
    private Boolean estado;
    private ArrayList<Evento> eventos;

    // registo
    public Utilizador(String nome, String email, String password, Boolean tipo) {
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.tipo = tipo;
        this.estado = true;
        this.eventos = new ArrayList<>();
    }

    // login
    public Utilizador(int id, String nome, String email, String password, Boolean tipo, Boolean estado,ArrayList<Evento> eventos) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.tipo = tipo;
        this.estado = estado;
        this.eventos = eventos;
    }

    public void addEvento(Evento evento){
        eventos.add(evento);
    }

    public void removeEvento(Evento evento){
        eventos.remove(evento);
    }

    public ArrayList<Evento> getEventos(){
        return eventos;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getTipo() {
        return tipo;
    }

    public void setTipo(Boolean tipo) {
        this.tipo = tipo;
    }

    public Boolean getEstado() {
        return estado;
    }
    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}

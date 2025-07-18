package com.example.eventmanagementapp;

import java.util.ArrayList;

public interface EventoDAO {
    public ArrayList<Evento> getAll();
    public Evento getById(int id);
    public boolean add(Evento evento);
    public boolean alterar(Evento evento);
    public boolean remover(int id);
    public boolean inscreverUtilizadorEvento(Evento evento);
    public boolean cancelarInscricaoUtilizadorEvento(Evento evento);

}

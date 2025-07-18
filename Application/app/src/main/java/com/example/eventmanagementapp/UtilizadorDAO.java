package com.example.eventmanagementapp;

import java.util.ArrayList;

public interface UtilizadorDAO {
    public ArrayList<Utilizador> getAll();

    public Utilizador getById(int id);

    public boolean efetuarInscricao(Utilizador utilizador, Evento evento);

    public boolean cancelarInscricao(Utilizador utilizador, Evento evento);

    public ArrayList<Evento> getEventosUtilizador(int id);

    public Utilizador login(String email, String password);

    public boolean registar(Utilizador utilizador);

    // TODO: NAO NECESSARIO PARA O TP2
    public boolean update(Utilizador utilizador);

    public boolean delete(int id);


}

package com.example.eventmanagementapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class UtilizadorOpenHelper implements UtilizadorDAO{

    private SQLiteDatabase db;

    public UtilizadorOpenHelper(Context ctx){
        GestaoBaseDados gestaoBaseDados = new GestaoBaseDados(ctx);
        db = gestaoBaseDados.getWritableDatabase();
    }


    @Override
    public ArrayList<Evento> getEventosUtilizador(int utilizadorId) {
        /*
        ArrayList<Evento> eventos = new ArrayList<>();
        String query = "SELECT e.* FROM Eventos e " +
                "INNER JOIN Utilizador_Evento f ON e.id = f.eventoid " +
                "WHERE f.utilizadorid = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(utilizadorId)});

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String tipo = cursor.getString(cursor.getColumnIndex("tipo"));
                String descricao = cursor.getString(cursor.getColumnIndex("descricao"));
                String local = cursor.getString(cursor.getColumnIndex("local"));
                String dataEvento = cursor.getString(cursor.getColumnIndex("dataEvento"));
                String horaEvento = cursor.getString(cursor.getColumnIndex("horaEvento"));
                int numeroLugares = cursor.getInt(cursor.getColumnIndex("numeroLugares"));
                float preco = cursor.getFloat(cursor.getColumnIndex("preco"));
                String dataLimiteInscricao = cursor.getString(cursor.getColumnIndex("dataLimiteInscricao"));
                String horaLimiteInscricao = cursor.getString(cursor.getColumnIndex("horaLimiteInscricao"));
                boolean gratis = cursor.getInt(cursor.getColumnIndex("gratis")) == 1;
                boolean comprou = cursor.getInt(cursor.getColumnIndex("comprou")) == 1;

                // Criar objeto Evento
                Evento evento = new Evento(id, tipo, descricao, local, dataEvento, horaEvento, dataLimiteInscricao, horaLimiteInscricao, numeroLugares, gratis,preco, comprou);
                eventos.add(evento);
            } while (cursor.moveToNext());
            cursor.close();
        }

        return eventos;*/
        return null;
    }



    @Override
    public ArrayList<Utilizador> getAll() {

        return null;

    }

    @Override
    public Utilizador getById(int id) {
        return null;
    }

    @Override
    public boolean registar(Utilizador utilizador) {
        ContentValues values = new ContentValues();
        values.put("nome", utilizador.getNome());
        values.put("email", utilizador.getEmail());
        values.put("password", utilizador.getPassword());
        values.put("tipo", utilizador.getTipo() ? 1 : 0);
        values.put("estado", utilizador.getEstado() ? 1 : 0);
        try {
            db.insert("Utilizadores", null, values);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    // TODO: Implementar alteração dos dados do utilizador (nao necessario para o tp2)
    @Override
    public boolean update(Utilizador utilizador) {
        return false;
    }

    // TODO: Implementar desativação do utilizador (nao necessario para o tp2)
    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean efetuarInscricao(Utilizador utilizador, Evento evento) {
        return false;

    }
    @Override
    public boolean cancelarInscricao(Utilizador utilizador, Evento evento) {
        // alterar o estado na tabela Utilizador_Evento
        ContentValues values = new ContentValues();
        values.put("estado", 0);
        try{
            db.update("Utilizador_Evento", values, "utilizador_id=? AND evento_id=?", new String[]{String.valueOf(utilizador.getId()), String.valueOf(evento.getId())});
            return true;
        } catch (SQLException e){
            return false;
        }
    }

    @Override
    public Utilizador login(String email, String password) {
        Cursor cursor = db.query(
                "Utilizadores",   // Table name
                null,       // Columns (null = all columns)
                "email = ? AND password = ?",       // Selection (where clause, null = no filter)
                new String[]{email, password},       // Selection arguments
                null,       // Group by
                null,       // Having
                null
        );

        if(cursor != null && cursor.moveToFirst()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String nome = cursor.getString(cursor.getColumnIndex("nome"));
            boolean tipo = cursor.getInt(cursor.getColumnIndex("tipo")) == 1;
            boolean estado = cursor.getInt(cursor.getColumnIndex("estado")) == 1 ;

            ArrayList<Evento> eventos = getEventosUtilizador(id);
            Utilizador utilizador = new Utilizador(id, nome, email, password, tipo, estado,eventos);
            cursor.close();
            return utilizador;
        }
        if (cursor != null) {
            cursor.close();
        }
        return null;
    }
}

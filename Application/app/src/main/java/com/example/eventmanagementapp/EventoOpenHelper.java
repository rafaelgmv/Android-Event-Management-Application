package com.example.eventmanagementapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;



public class EventoOpenHelper implements EventoDAO{

    private SQLiteDatabase db;

    public EventoOpenHelper(Context ctx){
        GestaoBaseDados gestaoBaseDados = new GestaoBaseDados(ctx);
        db = gestaoBaseDados.getWritableDatabase();
    }

    @Override
    public boolean add(Evento evento){
        ContentValues vals = new ContentValues();
        vals.put("tipo", evento.getTipo());
        vals.put("descricao", evento.getDescricao());
        vals.put("local", evento.getLocal());
        vals.put("dataEvento", evento.getDataEvento());
        vals.put("horaEvento", evento.getHoraEvento());
        vals.put("numeroLugares", evento.getNumeroLugares());
        vals.put("preco", evento.getPreco());
        vals.put("dataLimiteInscricao", evento.getDataLimiteInscricao());
        vals.put("horaLimiteInscricao", evento.getHoraLimiteInscricao());
        vals.put("gratis", evento.getGratis() ? 1 : 0);
        vals.put("comprou", evento.getComprou() ? 1 : 0);
        try{
            db.insert("Eventos", null, vals );
            return true;
        }
        catch(SQLException e){
            return false;
        }
    }


    @Override
    public boolean alterar(Evento evento){

        if (evento == null || evento.getId() <= 0) {
            return false;
        }
        ContentValues vals = new ContentValues();
        vals.put("id", evento.getId());
        vals.put("tipo", evento.getTipo());
        vals.put("descricao", evento.getDescricao());
        vals.put("local", evento.getLocal());
        vals.put("dataEvento", evento.getDataEvento());
        vals.put("horaEvento", evento.getHoraEvento());
        vals.put("numeroLugares", evento.getNumeroLugares());
        vals.put("preco", evento.getPreco());
        vals.put("dataLimiteInscricao", evento.getDataLimiteInscricao());
        vals.put("horaLimiteInscricao", evento.getHoraLimiteInscricao());
        vals.put("gratis", evento.getGratis() ? 1 : 0);
        try{
            // whereArgs --> implica um array de strings, logo tem de se fazer o cast do id para string
            return db.update("Eventos", vals, "id=?", new String[]{String.valueOf(evento.getId())}) > 0;
        }
        catch(SQLException e){
            return false;
        }
    }

    @Override
    public boolean inscreverUtilizadorEvento(Evento evento){
        if (evento == null || evento.getId() <= 0) {
            return false;
        }

        if(evento.getDataLimiteInscricaoTimestamp() < System.currentTimeMillis()){
            return false;
        }


        ContentValues vals = new ContentValues();
        vals.put("comprou", evento.getComprou() ? 1 : 0);
        try{
            // whereArgs --> implica um array de strings, logo tem de se fazer o cast do id para string
            return db.update("Eventos", vals, "id=?", new String[]{String.valueOf(evento.getId())}) > 0;
        }
        catch(SQLException e){
            return false;
        }
    }

    @Override
    public boolean cancelarInscricaoUtilizadorEvento(Evento evento){
        if (evento == null || evento.getId() <= 0) {
            return false;
        }

        ContentValues vals = new ContentValues();
        vals.put("comprou", evento.getComprou() ? 1 : 0);
        try{
            // whereArgs --> implica um array de strings, logo tem de se fazer o cast do id para string
            return db.update("Eventos", vals, "id=?", new String[]{String.valueOf(evento.getId())}) > 0;
        }
        catch(SQLException e){
            return false;
        }
    }

    @Override
    public boolean remover(int id){
        try{
            db.delete("Eventos", "id=?", new String[]{String.valueOf(id)});
            return true;
        }
        catch(SQLException e){
            return false;
        }
    }

    @Override
    public Evento getById(int id){
        Cursor cursor = db.query(
                "Eventos",                       // Table name
                null,                           // Columns (null = all columns)
                "id=?",                         // Selection (where clause)
                new String[]{String.valueOf(id)}, // Selection arguments
                null,                           // Group by
                null,                           // Having
                null                            // Order by
        );

        if (cursor != null && cursor.moveToFirst()) {
            // Obtém os valores do cursor
            String tipo = cursor.getString(cursor.getColumnIndex("tipo"));
            String descricao = cursor.getString(cursor.getColumnIndex("descricao"));
            String local = cursor.getString(cursor.getColumnIndex("local"));
            long dataEvento = cursor.getLong(cursor.getColumnIndex("dataEvento"));
            long horaEvento = cursor.getLong(cursor.getColumnIndex("horaEvento"));
            int numeroLugares = cursor.getInt(cursor.getColumnIndex("numeroLugares"));
            float preco = cursor.getFloat(cursor.getColumnIndex("preco"));
            long dataLimiteInscricao = cursor.getLong(cursor.getColumnIndex("dataLimiteInscricao"));
            long horaLimiteInscricao = cursor.getLong(cursor.getColumnIndex("horaLimiteInscricao"));
            boolean gratis = cursor.getInt(cursor.getColumnIndex("gratis")) == 1;
            boolean comprou = cursor.getInt(cursor.getColumnIndex("comprou")) == 1;



            Evento evento = new Evento(id, tipo, descricao, local, dataEvento, horaEvento, dataLimiteInscricao, horaLimiteInscricao, numeroLugares, gratis, preco, comprou);
            cursor.close();
            return evento;
        }

        if (cursor != null) {
            cursor.close();
        }

        return null;
    }

    // Método para buscar todos os eventos
    @Override
    public ArrayList<Evento> getAll() {
        ArrayList<Evento> eventList = new ArrayList<>();
        Cursor cursor = db.query(
                "Eventos",   // Table name
                null,       // Columns (null = all columns)
                null,       // Selection (where clause, null = no filter)
                null,       // Selection arguments
                null,       // Group by
                null,       // Having
                "dataEvento ASC, horaEvento ASC" // Order by (data e hora em ordem crescente)
        );

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String tipo = cursor.getString(cursor.getColumnIndex("tipo"));
                String descricao = cursor.getString(cursor.getColumnIndex("descricao"));
                String local = cursor.getString(cursor.getColumnIndex("local"));
                long dataEvento = cursor.getLong(cursor.getColumnIndex("dataEvento"));
                long horaEvento = cursor.getLong(cursor.getColumnIndex("horaEvento"));
                int numeroLugares = cursor.getInt(cursor.getColumnIndex("numeroLugares"));
                float preco = cursor.getFloat(cursor.getColumnIndex("preco"));
                long dataLimiteInscricao = cursor.getLong(cursor.getColumnIndex("dataLimiteInscricao"));
                long horaLimiteInscricao = cursor.getLong(cursor.getColumnIndex("horaLimiteInscricao"));
                boolean gratis = cursor.getInt(cursor.getColumnIndex("gratis")) == 1;
                boolean comprou = cursor.getInt(cursor.getColumnIndex("comprou")) == 1;


                Evento event = new Evento(id, tipo, descricao, local, dataEvento, horaEvento, dataLimiteInscricao, horaLimiteInscricao, numeroLugares, gratis, preco, comprou);
                eventList.add(event);
            } while (cursor.moveToNext());
            cursor.close();
        }

        return eventList;
    }



}
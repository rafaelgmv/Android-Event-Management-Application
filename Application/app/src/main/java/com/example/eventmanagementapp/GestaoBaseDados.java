package com.example.eventmanagementapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GestaoBaseDados extends SQLiteOpenHelper {

    private static final String DB_NAME = "EventManagementApp";
    private static final int DB_VERSION = 1;

    // Scripts de criação de tabelas
    private static final String SQL_CREATE_EVENTOS = "CREATE TABLE Eventos (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "tipo TEXT NOT NULL, descricao TEXT NOT NULL, local TEXT NOT NULL, " +
            "dataEvento TIMESTAMP NOT NULL, dataLimiteInscricao TIMESTAMP NOT NULL, " +
            "horaEvento TIMESTAMP NOT NULL, horaLimiteInscricao TIMESTAMP NOT NULL, " +
            "preco FLOAT NOT NULL, numeroLugares BOOLEAN NOT NULL, " +
            "gratis BOOLEAN NOT NULL, comprou BOOLEAN NOT NULL DEFAULT 0);";

    private static final String SQL_CREATE_UTILIZADORES = "CREATE TABLE Utilizadores (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nome TEXT NOT NULL, email TEXT NOT NULL, password TEXT NOT NULL, tipo INTEGER NOT NULL, estado INTEGER NOT NULL);";


    private static final String SQL_CREATE_EVENTOS_UTILIZADORES = "CREATE TABLE Utilizador_Evento (" +
            "evento_id INTEGER NOT NULL, utilizador_id INTEGER NOT NULL, estado INTEGER NOT NULL, " +
            "FOREIGN KEY (evento_id) REFERENCES Eventos(id), " +
            "FOREIGN KEY (utilizador_id) REFERENCES Utilizadores(id));";


    public GestaoBaseDados(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_EVENTOS);
        //db.execSQL(SQL_CREATE_UTILIZADORES);
        //db.execSQL(SQL_CREATE_EVENTOS_UTILIZADORES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Eventos");
        //db.execSQL("DROP TABLE IF EXISTS Utilizadores");
        //db.execSQL("DROP TABLE IF EXISTS Utilizador_Evento");
        onCreate(db);
    }
}

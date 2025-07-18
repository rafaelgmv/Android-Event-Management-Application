package com.example.eventmanagementapp;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Evento implements Serializable {

    private int id;
    private String tipo;
    private String descricao;
    private String local;
    private int numeroLugares;
    private boolean gratis;
    private float preco;
    private long dataEvento;
    private long dataLimiteInscricao;
    private long horaEvento;
    private long horaLimiteInscricao;

    private boolean comprou;

    // construtor para retirar na base de dados
    public Evento(int id,String tipo, String descricao, String local, long dataEvento, long horaEvento, long dataLimiteInscricao, long horaLimiteInscricao, int numeroLugares, boolean gratis, float preco, boolean comprou) {
        this.id = id;
        this.tipo = tipo;
        this.descricao = descricao;
        this.local = local;
        this.dataEvento = dataEvento;
        this.horaEvento = horaEvento;
        this.dataLimiteInscricao = dataLimiteInscricao;
        this.horaLimiteInscricao = horaLimiteInscricao;
        this.numeroLugares = numeroLugares;
        this.gratis = gratis;
        this.preco = preco;
        this.comprou = comprou;

    }

    public Evento(String tipo, String descricao, String local, long dataEvento, long horaEvento, long dataLimiteInscricao, long horaLimiteInscricao, int numeroLugares, boolean gratis, float preco) {
        this.tipo = tipo;
        this.descricao = descricao;
        this.local = local;
        this.dataEvento = dataEvento;
        this.horaEvento = horaEvento;
        this.dataLimiteInscricao = dataLimiteInscricao;
        this.horaLimiteInscricao = horaLimiteInscricao;
        this.numeroLugares = numeroLugares;
        this.gratis = gratis;
        this.preco = preco;
        this.comprou = false;
    }

    public long getHoraEvento() {
        return horaEvento;
    }

    public void setHoraEvento(long horaEvento) {
        this.horaEvento = horaEvento;
    }

    public long getHoraLimiteInscricao() {
        return horaLimiteInscricao;
    }

    public void setHoraLimiteInscricao(long horaLimiteInscricao) {
        this.horaLimiteInscricao = horaLimiteInscricao;
    }

    public long getDataLimiteInscricao() {
        return dataLimiteInscricao;
    }

    public void setDataLimiteInscricao(long dataLimiteInscricao) {
        this.dataLimiteInscricao = dataLimiteInscricao;
    }

    public long getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(long dataEvento) {
        this.dataEvento = dataEvento;
    }


    public boolean getComprou() {
        return comprou;
    }

    public void setComprou(boolean comprou) {
        this.comprou = comprou;
    }
    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
    
  

    public int getNumeroLugares() {
        return numeroLugares;
    }

    public void setNumeroLugares(int numeroLugares) {
        this.numeroLugares = numeroLugares;
    }

    public boolean getGratis() {
        return gratis;
    }

    public void setGratis(boolean gratis) {
        this.gratis = gratis;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    
    
    // lidar com Timestamps

    public long getDataHoraTimestamp(){
        return dataEvento + horaEvento;
    }

    public long getDataLimiteInscricaoTimestamp(){
        return dataLimiteInscricao + horaLimiteInscricao;
    }


    public String getDataString(long dataTimestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date(dataTimestamp);
        return sdf.format(date);
    }

    public String getHoraString(long horaTimestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date date = new Date(horaTimestamp);
        return sdf.format(date);
    }

    @Override
    public String toString() {
        return "Evento{" +
                "tipo='" + tipo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", local='" + local + '\'' +
                ", dataEvento='" + dataEvento + '\'' +
                ", horaEvento='" + horaEvento + '\'' +
                ", dataLimiteInscricao='" + dataLimiteInscricao + '\'' +
                ", horaLimiteInscricao='" + horaLimiteInscricao + '\'' +
                ", numeroLugares=" + numeroLugares +
                ", gratis=" + gratis +
                ", preco=" + preco +
                '}';
    }
}
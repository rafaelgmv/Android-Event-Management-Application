package com.example.eventmanagementapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.app.TimePickerDialog;
import android.widget.TimePicker;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import java.util.Date;



import java.util.Calendar;

public class InserirEventoActivity extends AppCompatActivity {

    private TextView txtTitulo;
    private Spinner spinTipo;
    private EditText txtDescricao;
    private EditText txtLocal;
    private Button btnDataEvento;
    private Button btnHoraEvento;
    private Button btnDataLimiteInscricao;
    private Button btnHoraLimiteInscricao;
    private EditText txtnrLugares;
    private CheckBox ckbGratis;
    private EditText txtPreco;
    private Button btnInserir;
    private Button btnCancelar;
    private ImageButton btnEliminar;

    private String dataEvento;
    private long dataEventoTimestamp;
    private Date dataEventoDate;

    private String horaEvento;
    private long horaEventoTimestamp;

    private String dataLimiteInscricao;
    private long dataLimiteTimestamp;
    private Date dataLimiteDate;

    private String horaLimiteInscricao;
    private long horaLimiteInscricaoTimestamp;

    String[] opcoesSpinner = {"Culturais", "Profissionais", "Desportivos", "Académicos", "Gastronómicos"};



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inserir_evento);

        setComponentes();

    }

    @Override
    public void onConfigurationChanged(Configuration novaConfiguracao) {
        super.onConfigurationChanged(novaConfiguracao);

        setContentView(R.layout.inserir_evento);
        setComponentes();
    }


    public void setComponentes(){
        txtTitulo = findViewById(R.id.txtTituloInserirEven);
        spinTipo = findViewById(R.id.txtTipoEven);
        txtDescricao = findViewById(R.id.txtDescricaoEven);
        txtLocal = findViewById(R.id.txtLocalEven);
        btnDataEvento = findViewById(R.id.btnDataEven);
        btnHoraEvento = findViewById(R.id.btnHoraEven);
        btnDataLimiteInscricao = findViewById(R.id.btnDataLimiteEven);
        btnHoraLimiteInscricao = findViewById(R.id.btnHoraLimiteEven);
        txtnrLugares = findViewById(R.id.txtNrLugaresEven);
        ckbGratis = findViewById(R.id.chkGratuidadeEven);
        txtPreco = findViewById(R.id.txtPrecoEven);
        btnInserir = findViewById(R.id.btnInserirEven);
        btnCancelar = findViewById(R.id.btnCancelar);
        btnEliminar = findViewById(R.id.btnEliminarEven);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_lista, opcoesSpinner);
        adapter.setDropDownViewResource(R.layout.spinner_lista);
        spinTipo.setAdapter(adapter);

        Evento evento = (Evento) getIntent().getSerializableExtra("evento");


        if(evento != null){

            int spinnerPosition = adapter.getPosition(evento.getTipo());
            txtTitulo.setText("Evento");
            spinTipo.setSelection(spinnerPosition);
            spinTipo.setEnabled(false);


            txtDescricao.setText(evento.getDescricao());
            txtDescricao.setEnabled(false);

            txtLocal.setText(evento.getLocal());
            txtLocal.setEnabled(false);

            btnDataEvento.setText(evento.getDataString(evento.getDataEvento()));
            dataEventoTimestamp = evento.getDataEvento();
            btnDataEvento.setEnabled(false);

            btnHoraEvento.setText(evento.getHoraString(evento.getHoraEvento()));
            horaEventoTimestamp = evento.getHoraEvento();
            btnHoraEvento.setEnabled(false);

            btnDataLimiteInscricao.setText(evento.getDataString(evento.getDataLimiteInscricao()));
            dataLimiteTimestamp = evento.getDataLimiteInscricao();
            btnDataLimiteInscricao.setEnabled(false);

            btnHoraLimiteInscricao.setText(evento.getHoraString(evento.getHoraLimiteInscricao()));
            horaLimiteInscricaoTimestamp = evento.getHoraLimiteInscricao();
            btnHoraLimiteInscricao.setEnabled(false);

            txtnrLugares.setText(String.valueOf(evento.getNumeroLugares()));
            txtnrLugares.setEnabled(false);

            txtPreco.setEnabled(false);
            ckbGratis.setEnabled(false);

            txtPreco.setText(String.valueOf(evento.getPreco()));

            btnInserir.setText("Editar");
            btnCancelar.setText("Voltar");

        }

        setListenners(evento);
    }

    // Método para definir os listeners
    public void setListenners(Evento evento){


        btnDataEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendario = Calendar.getInstance();

                int ano = calendario.get(Calendar.YEAR);
                int mes = calendario.get(Calendar.MONTH);
                int dia = calendario.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        InserirEventoActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int ano, int mes, int dia) {
                                // Atualizar o botão com a data formatada
                                String dataFormatada = String.format("%02d/%02d/%04d", dia, mes + 1, ano);
                                btnDataEvento.setText(dataFormatada);

                                // Atualizar o objeto Calendar com a data selecionada
                                Calendar dataSelecionada = Calendar.getInstance();
                                dataSelecionada.set(Calendar.YEAR, ano);
                                dataSelecionada.set(Calendar.MONTH, mes);
                                dataSelecionada.set(Calendar.DAY_OF_MONTH, dia);
                                dataSelecionada.set(Calendar.HOUR_OF_DAY, 0);
                                dataSelecionada.set(Calendar.MINUTE, 0);
                                dataSelecionada.set(Calendar.SECOND, 0);
                                dataSelecionada.set(Calendar.MILLISECOND, 0);

                                // Atualizar os valores para timestamp e Date
                                dataEventoTimestamp = dataSelecionada.getTimeInMillis();
                                dataEventoDate = dataSelecionada.getTime();

                                // Para debug, imprimir os valores
                                System.out.println("Timestamp: " + dataEventoTimestamp);
                                System.out.println("Data em Date: " + dataEventoDate);
                            }
                        },
                        ano, mes, dia
                );
                datePickerDialog.show();
            }
        });



        btnHoraEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendario = Calendar.getInstance();

                int hora = calendario.get(Calendar.HOUR_OF_DAY);
                int minuto = calendario.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        InserirEventoActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                                calendario.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                calendario.set(Calendar.MINUTE, minute);

                                btnHoraEvento.setText(hourOfDay + ":" + minute);
                                horaEvento = hourOfDay + ":" + minute;
                                horaEventoTimestamp =  (hourOfDay * 60 * 60 * 1000) + (minute * 60 * 1000);

                            }
                        },
                        hora,
                        minuto,
                        true
                );
                timePickerDialog.show();
            }
        });


        btnDataLimiteInscricao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendario = Calendar.getInstance();

                int ano = calendario.get(Calendar.YEAR);
                int mes = calendario.get(Calendar.MONTH);
                int dia = calendario.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        InserirEventoActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int ano, int mes, int dia) {
                                // Formatação da data para exibir no botão
                                String dataFormatada = String.format("%02d/%02d/%04d", dia, mes + 1, ano);
                                btnDataLimiteInscricao.setText(dataFormatada);

                                // objeto calendario para guardar a data selecionada
                                Calendar dataSelecionada = Calendar.getInstance();
                                dataSelecionada.set(Calendar.YEAR, ano);
                                dataSelecionada.set(Calendar.MONTH, mes);
                                dataSelecionada.set(Calendar.DAY_OF_MONTH, dia);
                                dataSelecionada.set(Calendar.HOUR_OF_DAY, 0);
                                dataSelecionada.set(Calendar.MINUTE, 0);
                                dataSelecionada.set(Calendar.SECOND, 0);
                                dataSelecionada.set(Calendar.MILLISECOND, 0);

                                long timestamp = dataSelecionada.getTimeInMillis();
                                System.out.println("Timestamp: " + timestamp);

                                Date dataEmDate = dataSelecionada.getTime();
                                System.out.println("Data em Date: " + dataEmDate);

                                dataLimiteInscricao = dataFormatada;
                                dataLimiteTimestamp = timestamp;
                                dataLimiteDate = dataEmDate;
                            }
                        },
                        ano, mes, dia);
                datePickerDialog.show();
            }
        });


        btnHoraLimiteInscricao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendario = Calendar.getInstance();

                int hora = calendario.get(Calendar.HOUR_OF_DAY);
                int minuto = calendario.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        InserirEventoActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                calendario.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                calendario.set(Calendar.MINUTE, minute);

                                btnHoraLimiteInscricao.setText(hourOfDay + ":" + minute);

                                horaLimiteInscricao = hourOfDay + ":" + minute;
                                horaLimiteInscricaoTimestamp =  (hourOfDay * 60 * 60 * 1000) + (minute * 60 * 1000);
                            }
                        },
                        hora,
                        minuto,
                        true
                );
                timePickerDialog.show();
            }
        });


        ckbGratis.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                txtPreco.setEnabled(false);
            } else {
                txtPreco.setEnabled(true);
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InserirEventoActivity.this, MainActivity.class);
                intent.putExtra("evento", evento);
                setResult(RESULT_FIRST_USER, intent);
                finish();
                Toast.makeText(InserirEventoActivity.this, "Evento eliminado com sucesso!", Toast.LENGTH_SHORT).show();
            }
        });

        // Selecionar qual das ações o botão de inserir vai fazer
        btnInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String opcao = btnInserir.getText().toString();
                switch (opcao){
                    case "Inserir":
                        inserirEvento(view);
                        break;
                    case "Editar":
                        modoEdicao(view,evento);
                        break;
                    case "Guardar":
                        if(evento != null ){
                            guardarEvento(view,evento);
                        }else{
                            inserirEvento(view);
                        }
                        break;
                }
            }
        });


        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InserirEventoActivity.this, MainActivity.class);
                setResult(RESULT_CANCELED, intent);
                finish();
            }
        });
    }



    private void modoEdicao(View view, Evento evento) {
        txtTitulo.setText("Editar Evento");
        txtnrLugares.setEnabled(true);
        txtDescricao.setEnabled(true);
        txtLocal.setEnabled(true);
        btnInserir.setText("Guardar");
        btnCancelar.setText("Cancelar");
        ckbGratis.setEnabled(true);
        spinTipo.setEnabled(true);
        btnHoraEvento.setEnabled(true);
        btnDataEvento.setEnabled(true);
        btnHoraLimiteInscricao.setEnabled(true);
        btnDataLimiteInscricao.setEnabled(true);
        btnHoraEvento.setText(evento.getHoraString(evento.getHoraEvento()));
        btnDataEvento.setText(evento.getDataString(evento.getDataEvento()));
        btnHoraLimiteInscricao.setText(evento.getHoraString(evento.getHoraLimiteInscricao()));
        btnDataLimiteInscricao.setText(evento.getDataString(evento.getDataLimiteInscricao()));


        btnEliminar.setVisibility(View.VISIBLE);

        if(evento.getGratis()){
            ckbGratis.setChecked(true);
            txtPreco.setEnabled(false);

        }else {
            ckbGratis.setChecked(false);
            txtPreco.setEnabled(true);
        }
        txtPreco.setText(String.valueOf(evento.getPreco()));

    }


    private void inserirEvento(View view) {
        txtTitulo.setText("Inserir Evento");
        String tipo = spinTipo.getSelectedItem().toString();
        String descricao = txtDescricao.getText().toString();
        String local = txtLocal.getText().toString();
        int numeroLugares;
        try {

            numeroLugares = txtnrLugares.getText().toString().isEmpty() ? 0 : Integer.parseInt(txtnrLugares.getText().toString());

        }catch (NumberFormatException e) {
            Toast.makeText(InserirEventoActivity.this, "Número de lugares inválido", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean gratis = ckbGratis.isChecked();
        float preco;
        try {
            if(gratis){
                preco = 0;
            }else {
                preco = Float.parseFloat(txtPreco.getText().toString());
            }
        }catch (NumberFormatException e){
            Toast.makeText(InserirEventoActivity.this, "Preço inválido", Toast.LENGTH_SHORT).show();
            return;
        }

        Evento evento = new Evento(tipo, descricao, local, dataEventoTimestamp, horaEventoTimestamp, dataLimiteTimestamp, horaLimiteInscricaoTimestamp, numeroLugares, gratis, preco);

        Intent intent = new Intent(InserirEventoActivity.this, MainActivity.class);

        intent.putExtra("evento", evento);
        setResult(RESULT_OK, intent);
        finish();

        Toast.makeText(InserirEventoActivity.this, "Evento inserido com sucesso!", Toast.LENGTH_SHORT).show();
    }

    // Método para guardar evento
    public void guardarEvento(View view, Evento evento){
        //TODO: PASSAR DATAS E HORAS PARA LONG PARA PODER GUARDAR
        evento.setDataEvento(dataEventoTimestamp);
        evento.setHoraEvento(horaEventoTimestamp);
        evento.setDataLimiteInscricao(dataLimiteTimestamp);
        evento.setHoraLimiteInscricao(horaLimiteInscricaoTimestamp);
        evento.setTipo(spinTipo.getSelectedItem().toString());
        evento.setDescricao(txtDescricao.getText().toString());
        evento.setLocal(txtLocal.getText().toString());

        try {
            evento.setNumeroLugares(txtnrLugares.getText().toString().isEmpty() ? 0 : Integer.parseInt(txtnrLugares.getText().toString()));
        }catch (NumberFormatException e) {
            Toast.makeText(InserirEventoActivity.this, "Número de lugares inválido", Toast.LENGTH_SHORT).show();
            return;
        }
        boolean gratis = ckbGratis.isChecked();
        try {
            if(gratis){
                evento.setPreco(0);
                evento.setGratis(true);
            }else {
                evento.setPreco(Float.parseFloat(txtPreco.getText().toString()));
                evento.setGratis(false);
            }
        }catch (NumberFormatException e){
            Toast.makeText(InserirEventoActivity.this, "Preço inválido", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(InserirEventoActivity.this, MainActivity.class);

        intent.putExtra("evento", evento);
        setResult(RESULT_OK, intent);
        finish();

        Toast.makeText(InserirEventoActivity.this, "Evento atualizado com sucesso!", Toast.LENGTH_SHORT).show();
    }
}
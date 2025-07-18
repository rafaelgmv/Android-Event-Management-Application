package com.example.eventmanagementapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.Spinner;
import android.widget.CheckBox;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;




public class MainActivity extends AppCompatActivity implements EventoAdapter.EventoClickListener {


    private EventoDAO eventoDAO;
    private static final int REQUEST_CODE_INSERIR_EVENTO = 1;
    public static final int REQUEST_CODE_ALTERAR_EVENTO = 1;
    private ImageButton btnInserirEvento;
    private RecyclerView recyclerView;
    private EventoAdapter eventoAdapter;

    private Spinner spinnerTipoEvento;
    private CheckBox checkboxFuturos, checkboxInscritos;

    private boolean filtroFuturos = false;
    private boolean filtroInscritos = false;

    private ArrayList<Evento> eventoList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            eventoList = (ArrayList<Evento>) savedInstanceState.getSerializable("eventoList");
            filtroFuturos = savedInstanceState.getBoolean("filtroFuturos");
            filtroInscritos = savedInstanceState.getBoolean("filtroInscritos");
        } else {
            eventoDAO = new EventoOpenHelper(this);
            eventoList = eventoDAO.getAll() != null ? eventoDAO.getAll() : new ArrayList<>();
        }

        setComponentes();

    }

    @Override
    public void onConfigurationChanged(Configuration novaConfiguracao) {
        super.onConfigurationChanged(novaConfiguracao);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("eventoList", eventoList);
        outState.putBoolean("filtroFuturos", filtroFuturos);
        outState.putBoolean("filtroInscritos", filtroInscritos);
    }

    public void setComponentes(){
        eventoDAO = new EventoOpenHelper(this);
        spinnerTipoEvento = findViewById(R.id.filterSpinnerTipo);
        String[] opcoesSpinner = {"Qualquer","Culturais", "Profissionais", "Desportivos", "Académicos", "Gastronómicos"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_lista, opcoesSpinner);
        adapter.setDropDownViewResource(R.layout.spinner_lista);

        spinnerTipoEvento.setAdapter(adapter);
        btnInserirEvento = findViewById(R.id.btnInserirEvento);

        checkboxFuturos = findViewById(R.id.filterCheckBoxFuturos);
        checkboxInscritos = findViewById(R.id.filterCheckBoxInscritos);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        eventoAdapter = new EventoAdapter(eventoList, this);
        recyclerView.setAdapter(eventoAdapter);

        setListenners();
    }

    public void setListenners(){

        btnInserirEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InserirEventoActivity.class);
                startActivityForResult(intent, REQUEST_CODE_INSERIR_EVENTO);
            }
        });

        checkboxFuturos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filtroFuturos = checkboxFuturos.isChecked();
                aplicarFiltros();
            }
        });

        checkboxInscritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filtroInscritos = checkboxInscritos.isChecked();
                aplicarFiltros();
            }
        });

        spinnerTipoEvento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String tipoSelecionado = spinnerTipoEvento.getSelectedItem().toString();
                aplicarFiltroTipo(tipoSelecionado);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void aplicarFiltros() {
        ArrayList<Evento> eventosFiltrados = new ArrayList<>();
        for (Evento evento : eventoList) {
            boolean incluir = true;

            // Filtrar por "Apenas futuros"
            if (filtroFuturos && evento.getDataHoraTimestamp() <= System.currentTimeMillis()) {
                incluir = false;
            }

            // Filtrar por "Apenas inscritos"
            if (filtroInscritos && !evento.getComprou()) {
                incluir = false;
            }

            if (incluir) {
                eventosFiltrados.add(evento);
            }
        }
        // Atualiza o adaptor
        eventoAdapter.atualizarLista(eventosFiltrados);
    }

    private void aplicarFiltroTipo(String tipoSelecionado) {
        ArrayList<Evento> eventosFiltrados = new ArrayList<>();

        for (Evento evento : eventoList) {
            if (tipoSelecionado.equals("Qualquer") || evento.getTipo().equals(tipoSelecionado)) {
                eventosFiltrados.add(evento);
            } else {
                Toast.makeText(this, "Nenhum evento encontrado com o tipo selecionado", Toast.LENGTH_SHORT).show();
            }
        }

        // Atualiza o adaptador com a lista filtrada
        eventoAdapter.atualizarLista(eventosFiltrados);
    }

    @Override
    public void onClick(int position) {
        Evento evento = eventoList.get(position);

        Intent intent = new Intent(MainActivity.this, InserirEventoActivity.class);
        intent.putExtra("evento", evento);
        startActivityForResult(intent, REQUEST_CODE_ALTERAR_EVENTO);
    }

    @Override
    public void onAcaoInscricao(int position, String acao) {

        Evento evento = eventoList.get(position);

        switch (acao) {
            case "Comprar bilhete":
                evento.setComprou(true);
                if(eventoDAO.inscreverUtilizadorEvento(evento)){
                    Toast.makeText(this, "Inscrição efetuada com sucesso", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Não foi possível efetuar inscrição no evento", Toast.LENGTH_SHORT).show();
                }

                break;
            case "Cancelar compra":
                evento.setComprou(false);
                eventoDAO.cancelarInscricaoUtilizadorEvento(evento);
                Toast.makeText(this, "Inscrição cancelada com sucesso", Toast.LENGTH_SHORT).show();
                break;
        }
        eventoList = eventoDAO.getAll();
        eventoAdapter.atualizarLista(eventoList);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == REQUEST_CODE_INSERIR_EVENTO || requestCode == REQUEST_CODE_ALTERAR_EVENTO)  && resultCode == RESULT_OK) {

            Evento evento = (Evento) data.getSerializableExtra("evento");

                if(eventoDAO.getById(evento.getId()) != null){
                    eventoDAO.alterar(evento);
                    Toast.makeText(this, "Evento alterado com sucesso!", Toast.LENGTH_SHORT).show();

                }else{

                    eventoDAO.add(evento);

                    Toast.makeText(this, "Novo evento adicionado com sucesso!", Toast.LENGTH_SHORT).show();

                }
                eventoList = eventoDAO.getAll();
                eventoAdapter.atualizarLista(eventoList);


        }else if(requestCode == REQUEST_CODE_ALTERAR_EVENTO && resultCode == RESULT_FIRST_USER){
            Evento evento = (Evento) data.getSerializableExtra("evento");

            if(eventoDAO.getById(evento.getId()) != null){
                eventoDAO.remover(evento.getId());
                Toast.makeText(this, "Evento removido com sucesso!", Toast.LENGTH_SHORT).show();
            }

            eventoList = eventoDAO.getAll();
            eventoAdapter.atualizarLista(eventoList);

        }
    }

}

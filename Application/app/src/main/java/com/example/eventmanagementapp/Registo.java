package com.example.eventmanagementapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CheckBox;



import androidx.appcompat.app.AppCompatActivity;

public class Registo extends AppCompatActivity {

    private EditText txtNome;
    private EditText txtEmail;
    private EditText txtPassword;
    private Button btnRegistar;
    private TextView irParaAutenticacao;
    private CheckBox isAdmin;
    private UtilizadorDAO utilizadorDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        utilizadorDAO = new UtilizadorOpenHelper(this);
        setComponentes();
    }

    public void setComponentes(){
        txtNome = findViewById(R.id.txtNomeSignUp);
        txtEmail = findViewById(R.id.txtEmailSignUp);
        txtPassword = findViewById(R.id.txtPasswordSignUp);
        isAdmin = findViewById(R.id.chkAdmin);
        btnRegistar = findViewById(R.id.btnCriarConta);
        irParaAutenticacao = findViewById(R.id.txtVoltarAutenticacao);
        setListenners();
    }

    public void setListenners() {

        btnRegistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = txtNome.getText().toString();
                String email = txtEmail.getText().toString();
                String password = txtPassword.getText().toString();
                boolean tipo;

                if (nome.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Registo.this, "Tem de preencher os campos enuciados", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(isAdmin.isChecked()){
                    tipo = true;
                }else{
                    tipo = false;
                }

                Utilizador utilizador = new Utilizador(nome, email, password, tipo);
               if(  utilizadorDAO.registar(utilizador)) {
                   Toast.makeText(Registo.this, "Utilizador registado com sucesso", Toast.LENGTH_SHORT).show();
                   Intent intent = new Intent(Registo.this, Autenticacao.class);
                   intent.putExtra("password", password);
                   intent.putExtra("email", email);
                   setResult(RESULT_OK, intent);
                   finish();
               }else {
                   Toast.makeText(Registo.this, "Erro ao registar utilizador", Toast.LENGTH_SHORT).show();
               }
            }
        });

        irParaAutenticacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registo.this, Autenticacao.class);
                setResult(RESULT_CANCELED, intent);
                finish();
            }
        });
    }
}

package com.example.eventmanagementapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Autenticacao extends AppCompatActivity {


    private static final int EFEUTAR_REGISTO = 1;

    private EditText txtEmail;
    private EditText txtPassword;
    private Button btnAutenticar;
    private TextView irParaRegisto;
    private UtilizadorDAO utilizadorDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.sign_in);
        utilizadorDAO = new UtilizadorOpenHelper(this);
        setComponentes();

    }

    public void setComponentes(){
        txtEmail = findViewById(R.id.txtEmailSignIn);
        txtPassword = findViewById(R.id.txtPasswordSignIn);
        btnAutenticar = findViewById(R.id.btnIniciarSessao);
        irParaRegisto = findViewById(R.id.txtEfetuarRegisto);
        setListenners();
    }

    public void setListenners(){

        btnAutenticar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtEmail.getText().toString();
                String password = txtPassword.getText().toString();

                if(email.isEmpty() || password.isEmpty()){
                    Toast.makeText(Autenticacao.this, "Tem de preencher os campos enuciados", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    Utilizador utilizador = utilizadorDAO.login(email, password);
                    if (utilizador != null) {
                        Toast.makeText(Autenticacao.this, "Bem-Vindo "+utilizador.getNome() + " !", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Autenticacao.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Autenticacao.this, "Credenciais inválidas!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


        irParaRegisto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Autenticacao.this, "Registar", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Autenticacao.this, Registo.class);
                startActivityForResult(intent, EFEUTAR_REGISTO);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == EFEUTAR_REGISTO && resultCode == RESULT_OK){
            Toast.makeText(this, "Registo efetuado com sucesso!", Toast.LENGTH_SHORT).show();
            String email = data.getStringExtra("email");
            String password = data.getStringExtra("password");
            txtEmail.setText(email);
            txtPassword.setText(password);
        } else if (requestCode == EFEUTAR_REGISTO && resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "Registo cancelado!", Toast.LENGTH_SHORT).show();
        }

    }

}

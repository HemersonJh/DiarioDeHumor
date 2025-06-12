// CadastroActivity.java
package com.example.diariodehumor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class CadastroActivity extends AppCompatActivity {

    private EditText edtNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = getSharedPreferences("usuario", MODE_PRIVATE);
        if (prefs.contains("nome")) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

        setContentView(R.layout.activity_cadastro);
        edtNome = findViewById(R.id.edtNome);
        Button btnConfirmar = findViewById(R.id.btnConfirmar);

        btnConfirmar.setOnClickListener(v -> {
            String nome = edtNome.getText().toString();
            prefs.edit().putString("nome", nome).apply();

            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            finish();
        });
    }
}

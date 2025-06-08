package com.example.diariodehumor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private String humorSelecionado = null;
    private EditText edtAnotacao;
    private AppDatabase db;
    private RecyclerView recyclerView;
    private ImageButton btnFeliz, btnTriste, btnAnsioso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtAnotacao = findViewById(R.id.edtAnotacao);
        btnFeliz = findViewById(R.id.btnFeliz);
        btnTriste = findViewById(R.id.btnTriste);
        btnAnsioso = findViewById(R.id.btnAnsioso);
        Button btnSalvar = findViewById(R.id.btnSalvar);
        recyclerView = findViewById(R.id.recyclerView);

        // Inicializa Room DB
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "humor_db")
                .fallbackToDestructiveMigration()
                .build();

        // Configura RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        carregarRegistros();

        // Clique nos emojis com destaque visual
        btnFeliz.setOnClickListener(v -> selecionarHumor("feliz"));
        btnTriste.setOnClickListener(v -> selecionarHumor("triste"));
        btnAnsioso.setOnClickListener(v -> selecionarHumor("ansioso"));

        // Clique no botão salvar
        btnSalvar.setOnClickListener(v -> salvarHumor());
    }

    private void selecionarHumor(String humor) {
        humorSelecionado = humor;

        btnFeliz.setBackground("feliz".equals(humor) ?
                ContextCompat.getDrawable(this, R.drawable.bg_selected) : null);
        btnFeliz.setBackground("triste".equals(humor) ?
                ContextCompat.getDrawable(this, R.drawable.bg_selected) : null);
        btnFeliz.setBackground("ansioso".equals(humor) ?
                ContextCompat.getDrawable(this, R.drawable.bg_selected) : null);

//        btnTriste.setBackground("triste".equals(humor) ? getDrawable(R.drawable.bg_selected) : null);
//        btnAnsioso.setBackground("ansioso".equals(humor) ? getDrawable(R.drawable.bg_selected) : null);
}


    private void salvarHumor() {
        String anotacao = edtAnotacao.getText().toString();
        String dataAtual = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());

        if (humorSelecionado == null) {
            Toast.makeText(this, "Escolha um humor antes de salvar!", Toast.LENGTH_SHORT).show();
            return;
        }

        HumorEntry entry = new HumorEntry(humorSelecionado, anotacao, dataAtual);

        new Thread(() -> {
            db.humorDao().inserir(entry);
            List<HumorEntry> listaAtualizada = db.humorDao().getTodos();
            runOnUiThread(() -> {
                Toast.makeText(this, "Registro salvo com sucesso!", Toast.LENGTH_SHORT).show();
                edtAnotacao.setText("");
                selecionarHumor(null); // limpa a seleção
                HumorAdapter adapter = new HumorAdapter(listaAtualizada);
                recyclerView.setAdapter(adapter);
            });
        }).start();
    }

    private void carregarRegistros() {
        new Thread(() -> {
            List<HumorEntry> lista = db.humorDao().getTodos();
            runOnUiThread(() -> {
                HumorAdapter adapter = new HumorAdapter(lista);
                recyclerView.setAdapter(adapter);
            });
        }).start();
    }
}

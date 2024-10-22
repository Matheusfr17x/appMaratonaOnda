package com.example.apponda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AdicionarDoacoesActivity extends AppCompatActivity {

    private EditText etNomeProduto, etLocalDoacao, etValidade;
    private Button btAdicionarDoacao;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_doacoes);

        etNomeProduto = findViewById(R.id.et_nome_produto);
        etLocalDoacao = findViewById(R.id.et_local_doacao);
        etValidade = findViewById(R.id.et_validade_produto);
        btAdicionarDoacao = findViewById(R.id.bt_salvar_doacao);

        btAdicionarDoacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomeProduto = etNomeProduto.getText().toString();
                String localDoacao = etLocalDoacao.getText().toString();
                String validade = etValidade.getText().toString();

                if (nomeProduto.isEmpty() || localDoacao.isEmpty() || validade.isEmpty()) {
                    Toast.makeText(AdicionarDoacoesActivity.this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Criar um objeto doação
                Map<String, Object> doacao = new HashMap<>();
                doacao.put("nomeProduto", nomeProduto);
                doacao.put("localDoacao", localDoacao);
                doacao.put("validadeProduto", validade);
                doacao.put("imagemProduto", R.drawable.ic_placeholder_image); // ID da imagem

                // Salvar no Firestore
                db.collection("Doacoes").add(doacao)
                        .addOnSuccessListener(documentReference -> {
                            Toast.makeText(AdicionarDoacoesActivity.this, "Doação adicionada com sucesso!", Toast.LENGTH_SHORT).show();
                            finish(); // Voltar à tela anterior
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(AdicionarDoacoesActivity.this, "Erro ao adicionar doação: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        });
            }
        });
    }
}

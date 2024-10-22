package com.example.apponda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import androidx.annotation.Nullable;

import java.util.ArrayList;



public class VisualizarDoacoesActivity extends AppCompatActivity {

    private ListView listViewDoacoes;
    private ArrayList<Doacao> listaDoacoes;
    private DoacaoAdapter doacaoAdapter;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_doacoes);

        listViewDoacoes = findViewById(R.id.listview_doacoes);
        listaDoacoes = new ArrayList<>();
        doacaoAdapter = new DoacaoAdapter(this, listaDoacoes);
        listViewDoacoes.setAdapter(doacaoAdapter);

        // Buscar doações do Firestore
        db.collection("Doacoes")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null) {
                            return;
                        }

                        listaDoacoes.clear(); // Limpa a lista antes de adicionar os novos dados
                        for (QueryDocumentSnapshot doc : value) {
                            if (doc.exists()) {
                                String nomeProduto = doc.getString("nomeProduto");
                                String localDoacao = doc.getString("localDoacao");
                                String validadeProduto = doc.getString("validadeProduto");
                                int imagemProduto = doc.getLong("imagemProduto").intValue();

                                Doacao doacao = new Doacao(imagemProduto, nomeProduto, localDoacao, validadeProduto);
                                listaDoacoes.add(doacao);
                            }
                        }

                        doacaoAdapter.notifyDataSetChanged(); // Atualiza o adaptador com os novos dados
                    }
                });
    }
}

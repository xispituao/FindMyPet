package com.example.findmypet.Formularios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.findmypet.DAO.ConfiguracaoFirebase;
import com.example.findmypet.Intermediario.Intermediario;
import com.example.findmypet.MainActivity;
import com.example.findmypet.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FormularioPost extends AppCompatActivity {
    private EditText nomeAnimal;
    private RadioButton especieAnimal;
    private RadioButton macho;
    private AutoCompleteTextView raca;
    private EditText corPelo;
    private EditText corOlhos;
    private EditText caracteristicasAdicionais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_post);
        nomeAnimal = (EditText)findViewById(R.id.nome_animal);
        especieAnimal = (RadioButton) findViewById(R.id.cachorro);
        macho = (RadioButton)findViewById(R.id.macho);
        raca = (AutoCompleteTextView)findViewById(R.id.raca);
        corPelo = (EditText)findViewById(R.id.cor_pelo_animal);
        corOlhos = (EditText)findViewById(R.id.cor_olhos_animal);

        caracteristicasAdicionais = (EditText)findViewById(R.id.teste);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_action_bar_publicacao_formulario, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.salvar:
                Map<String, String> dadosAnimal = new HashMap<>();
                dadosAnimal.put("nome", nomeAnimal.getText().toString());
                if (especieAnimal.isChecked()){
                    dadosAnimal.put("especie", "cachorro");
                }else {
                    dadosAnimal.put("especie", "gato");
                }
                dadosAnimal.put("raca", raca.getText().toString());
                if (macho.isChecked()){
                    dadosAnimal.put("sexo", "macho");
                }else {
                    dadosAnimal.put("sexo", "femea");
                }
                dadosAnimal.put("corPelo", corPelo.getText().toString());
                dadosAnimal.put("corOlhos", corOlhos.getText().toString());
                FirebaseFirestore bd = ConfiguracaoFirebase.BancoDeDados();
                bd.collection("publicacoes").add(dadosAnimal).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(FormularioPost.this, "Publicado com sucesso!", Toast.LENGTH_SHORT).show();
                        irParaTelaPrincipal();
                    }
                });

                break;
            case  R.id.cancelar:
                finish();
                break;
        }
        return true;
    }

    private void irParaTelaPrincipal(){
        Intent intent =  new Intent(FormularioPost.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}

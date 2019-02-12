package com.example.findmypet.Formularios;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.findmypet.DAO.ConfiguracaoFirebase;
import com.example.findmypet.MainActivity;
import com.example.findmypet.Modelos.Animal;
import com.example.findmypet.Modelos.Publicacao;
import com.example.findmypet.Modelos.Usuario;
import com.example.findmypet.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;



public class FormularioPost extends AppCompatActivity {
    private EditText nomeAnimal;
    private RadioButton cachorro;
    private RadioButton macho;
    private RadioButton gato;
    private AutoCompleteTextView raca;
    private ImageView icone_animal;
    private EditText corPelo;
    private EditText corOlhos;
    private EditText caracteristicasAdicionais;
    private ImageView imagemAnimal;
    private Button tirarFoto;
    public static final int PICK_IMAGE = 1234;
    private Uri resultUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_post);
        nomeAnimal = (EditText)findViewById(R.id.nome_animal);
        cachorro = (RadioButton) findViewById(R.id.cachorro);
        gato = (RadioButton)findViewById(R.id.gato);
        macho = (RadioButton)findViewById(R.id.macho);

        raca = (AutoCompleteTextView)findViewById(R.id.raca);
        corPelo = (EditText)findViewById(R.id.cor_pelo_animal);
        corOlhos = (EditText)findViewById(R.id.cor_olhos_animal);
        icone_animal = (ImageView)findViewById(R.id.icone_animal);
        imagemAnimal = (ImageView)findViewById(R.id.imagemAnimal);
        tirarFoto = (Button) findViewById(R.id.tirarFoto);

        caracteristicasAdicionais = (EditText)findViewById(R.id.teste);



        tirarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new   Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(Intent.createChooser(intent, "Selecione uma imagem"), PICK_IMAGE);
                }

            });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE && null != data) {
            final Uri imageUri = data.getData();
            resultUri = imageUri;
            imagemAnimal.setImageURI(resultUri);
        }
    }

    public void cachorroIsClicked(View view){
        icone_animal.setImageResource(R.mipmap.ic_cachorro_foreground);
        cachorro.setChecked(true);
        gato.setChecked(false);
    }

    public  void gatoIsClicked(View view){
        icone_animal.setImageResource(R.mipmap.ic_gato_foreground);
        gato.setChecked(true);
        cachorro.setChecked(false);
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
                String nome = nomeAnimal.getText().toString();
                String especie;
                if (cachorro.isChecked()){
                    especie = "Cachorro";
                }else if(gato.isChecked()){
                    especie = "Gato";
                }
                String cor_do_Pelo = corPelo.getText().toString();
                String cor_dos_olhos = corOlhos.getText().toString();
                String racaAnimal = raca.getText().toString();
                String sexo;
                if (macho.isChecked()){
                    sexo = "Macho";
                }else {
                    sexo = "Femea";
                }
                String caracteristicasAdicionaisAnimal = caracteristicasAdicionais.getText().toString();

                Publicacao publicacao = new Publicacao(Usuario.getUsuarioLogado(), new Animal(nome,sexo,cor_do_Pelo,cor_dos_olhos,racaAnimal,sexo,caracteristicasAdicionaisAnimal));
                FirebaseFirestore bd = ConfiguracaoFirebase.BancoDeDados();
                bd.collection("publicacoes").add(publicacao).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
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

package iset.dsi.ex2gestion_des_rsultats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    EditText nom, prenom, moyenne;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        nom = findViewById(R.id.input_newNom);
        prenom = findViewById(R.id.input_newPrenom);
        moyenne = findViewById(R.id.input_newMoyenne);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newNom = nom.getText().toString();
                String newPrenom = prenom.getText().toString();
                String newMoyenne = moyenne.getText().toString();

                // Vérifier si les champs ne sont pas vides
                if (!newNom.isEmpty() && !newPrenom.isEmpty() && !newMoyenne.isEmpty()) {
                    // Créer un nouvel objet Resultat avec les valeurs
                    System.out.println("le moyenne est "+Float.parseFloat(newMoyenne));
                    Resultat newResult = new Resultat(newNom, newPrenom, Float.parseFloat(newMoyenne));

                    // Ajouter le nouveau résultat à la base de données
                    MyDataBaseManager db = new MyDataBaseManager(AddActivity.this);
                db.addResult(newResult);



                    // Revenir à l'activité principale
                    Intent intent = new Intent(AddActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(AddActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                }
            }
        });
        }
}
package com.example.projetcalculemental;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.TimeUnit;

public class Dificultes extends AppCompatActivity {
    private  long uneminute=TimeUnit.MINUTES.toMillis(1);
    private long trentesecond=TimeUnit.SECONDS.toMillis(30);
    private  long dixseconde=TimeUnit.SECONDS.toMillis(10);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dificultes);
        Button Facile = findViewById(R.id.Facile);
        Button Moyen = findViewById(R.id.Moyen);
        Button Difficile = findViewById(R.id.Difficile);
        Intent Valeur = new Intent (this , Play.class);
        Facile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Valeur.putExtra("Valeur",uneminute);
                startActivity(Valeur);
            }
        });
        Moyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Valeur.putExtra("Valeur",trentesecond);
                startActivity(Valeur);
            }
        });
        Difficile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Valeur.putExtra("Valeur",dixseconde);
                startActivity(Valeur);
            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.submit_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.score_menu_button){
            Intent intent = new Intent(this,ScoreActivity.class);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }

}
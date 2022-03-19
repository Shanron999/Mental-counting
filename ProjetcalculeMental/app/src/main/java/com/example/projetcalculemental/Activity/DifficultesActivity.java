package com.example.projetcalculemental.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.projetcalculemental.R;

import java.util.concurrent.TimeUnit;

public class DifficultesActivity extends AppCompatActivity {

    public static final String TIMER="TIMER";
    public static final String MIN ="MIN";
    public static final String MAX="MAX";



    private static final long FACILE =TimeUnit.MINUTES.toMillis(2);
    private static final long MOYENS =TimeUnit.MINUTES.toMillis(1)+TimeUnit.SECONDS.toMillis(30);
    private static final long DIFFICILE =TimeUnit.MINUTES.toMillis(1);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dificultes);
        Button Facile = findViewById(R.id.Facile);
        Button Moyen = findViewById(R.id.Moyen);
        Button Difficile = findViewById(R.id.Difficile);
        Intent Valeur = new Intent (this , PlayActivity.class);
        Facile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Valeur.putExtra(TIMER, FACILE);
                Valeur.putExtra(MIN,1);
                Valeur.putExtra(MAX,10);
                startActivity(Valeur);
            }
        });
        Moyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Valeur.putExtra(TIMER, MOYENS);
                Valeur.putExtra(MIN,1);
                Valeur.putExtra(MAX,60);
                startActivity(Valeur);
            }
        });
        Difficile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Valeur.putExtra(TIMER, DIFFICILE);
                Valeur.putExtra(MIN,1);
                Valeur.putExtra(MAX,50);
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
package com.example.projetcalculemental.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.projetcalculemental.Models.Exception.NoNumberException;
import com.example.projetcalculemental.Models.Exception.NovaluesException;
import com.example.projetcalculemental.Models.OperationsModel;
import com.example.projetcalculemental.R;
import com.example.projetcalculemental.Service.OperateurService;
import com.example.projetcalculemental.Service.VerifService;

import java.text.DecimalFormat;
import java.util.Random;

public class PlayActivity extends AppCompatActivity {
    /**
     * donnée bouton + timer+base de donnee
     */
    private int increment;
    private static long START_TIME_IN_MILIS;
    private TextView timer;
    private Button timerButton;
    private Button valid;
    private CountDownTimer countDownTimer;
    private TextView False;
    private TextView True;
    private long timeLeftMiliesecond;
    private TextView operation;
    private EditText saisie;
    private double random1;
    private double random2;
    private double resultat;
    private TextView counter;
    private int min;
    private int max;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        setTimer();
        timer = findViewById(R.id.Timer);
        timerButton = findViewById(R.id.Timerbutton);
        valid = findViewById(R.id.valid);
        False = findViewById(R.id.False);
        True = findViewById(R.id.correct);
        operation = findViewById(R.id.operation);
        saisie = findViewById(R.id.Input);
        counter = findViewById(R.id.counter);
        timerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startimer();
                random();
                saisie.setText(null);
                increment=0;
                incrementAffiche();

            }

        });
        valid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    verifSaisie();
                } catch (NoNumberException e) {
                    e.printStackTrace();

                } catch (NovaluesException e) {
                    e.printStackTrace();
                }
                incrementAffiche();
                incrementCombo();

            }
        });
        updateTimer();


    }
//region Timer

    /***
     * Change le timer max par rapport a la difficulties
     */
    private  void setTimer(){
        Intent Valeur = getIntent();
        if (Valeur.hasExtra(DifficultesActivity.TIMER))
        {
            START_TIME_IN_MILIS=Valeur.getLongExtra(DifficultesActivity.TIMER,START_TIME_IN_MILIS);
        }
        timeLeftMiliesecond =START_TIME_IN_MILIS;
    }


    /***
     * lance le timer avec 1 tic /miliesecond et modifie les bouton
     * et affiche le text time out si le timer est finie
     */
    private void startimer(){
        countDownTimer = new CountDownTimer(timeLeftMiliesecond,1) {
            @Override
            public void onTick(long l) {
                timeLeftMiliesecond =l;
                updateTimer();
            }

            @Override
            public void onFinish() {
                timerButton.setVisibility(View.VISIBLE);
                valid.setVisibility(View.GONE);
                False.setVisibility(View.VISIBLE);
                False.setText(R.string.Time_Out);

                reset();


            }
        }.start();
        False.setVisibility(View.GONE);
        timerButton.setVisibility(View.GONE);
        valid.setVisibility(View.VISIBLE);

    }

    /***
     * affiche le timer
     */
    private void updateTimer(){
        int minutes = (int) timeLeftMiliesecond /60000;
        int second = (int) timeLeftMiliesecond % 60000 / 1000;
        int miliesecond = (int) timeLeftMiliesecond % 60000 %1000;
        int score = miliesecond+second*1000 + minutes*60000;
        String timelefttext;

        timelefttext = " "+minutes;
        timelefttext += ":";
        if (second < 10 ) timelefttext += "0";
        timelefttext += second;
        timelefttext +=":";
        if(miliesecond<100)timelefttext +="0";
        if(miliesecond<10)timelefttext +="0";
        timelefttext += miliesecond;
        timer.setText(timelefttext);


    }

    /***
     * rest le timer
     */
    private  void reset ()
    {
        timeLeftMiliesecond =START_TIME_IN_MILIS;
        updateTimer();
    }


    //--------------------------Fin timer---------------------------------//
    //endregion
    //region Menu
    //--------------------------Debut Menu---------------------------------//

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
            countDownTimer.cancel();
            reset();
            timerButton.setVisibility(View.VISIBLE);
            valid.setVisibility(View.GONE);

        }

        return super.onOptionsItemSelected(item);
    }
    //--------------------------Fin Menu---------------------------------//
    //endregion
//region generate num + verif

    //--------------------------generate num + verif-----------------------------//
    private void random (){
        Intent valeur = getIntent();
            min=valeur.getIntExtra(DifficultesActivity.MIN,min);
            max=valeur.getIntExtra(DifficultesActivity.MAX,max);

         random1 = new Random().nextInt((max-min)+1)+min;
         random2 = new Random().nextInt((max-min)+1)+min;
         int randomsigne = new Random().nextInt((4-1)+1)+1;
         if (randomsigne==1)
         {
             String operateur = "+";
             OperationsModel operator = new OperationsModel(Double.toString(random1),Double.toString(random2),operateur);
             resultat = OperateurService.calculResultat(operator);
             String AfficheOperation = getString(R.string.operation_template,Double.toString(random1),operateur,Double.toString(random2));
             operation.setText(AfficheOperation);
         }
        else if (randomsigne==2)
        {
            String operateur = "-";
            OperationsModel operator = new OperationsModel(Double.toString(random1),Double.toString(random2),operateur);
             resultat = OperateurService.calculResultat(operator);
            String AfficheOperation = getString(R.string.operation_template,Double.toString(random1),operateur,Double.toString(random2));
            operation.setText(AfficheOperation);
        }
        else if (randomsigne==3)
        {
            String operateur = "*";
            OperationsModel operator = new OperationsModel(Double.toString(random1),Double.toString(random2),operateur);
             resultat = OperateurService.calculResultat(operator);
            String AfficheOperation = getString(R.string.operation_template,Double.toString(random1),operateur,Double.toString(random2));
            operation.setText(AfficheOperation);
        }
        else if (randomsigne==4 && random2!=0)
        {
            String operateur = "/";
            OperationsModel operator = new OperationsModel(Double.toString(random1),Double.toString(random2),operateur);
             resultat = OperateurService.calculResultat(operator);
            DecimalFormat resultatDeuxChiffre= new DecimalFormat("0.00");
            String str=resultatDeuxChiffre.format(resultat);
            str = str.replaceAll(",", ".");
            resultat=Double.parseDouble(str);
            String AfficheOperation = getString(R.string.operation_template,Double.toString(random1),operateur,Double.toString(random2));
            operation.setText(AfficheOperation);
        }
        else{
            String Operateur="+";
             OperationsModel operator = new OperationsModel(Double.toString(random1),Double.toString(random2),Operateur);
              resultat = OperateurService.calculResultat(operator);
             String AfficheOperation = getString(R.string.operation_template,Double.toString(random1),Operateur,Double.toString(random2));
             operation.setText(AfficheOperation);
        }




    }
   private  void verifSaisie() throws NoNumberException, NovaluesException {

      String verif = VerifService.verifResultat(resultat,saisie);


       if(verif=="true")
       {
           False.setVisibility(View.GONE);
           True.setVisibility(View.VISIBLE);
           True.setText("bien jouer");
           random();
           saisie.setText(null);
           increment++;
       }
       else if(verif=="false") {
           True.setVisibility(View.GONE);
           False.setVisibility(View.VISIBLE);
           False.setText("mauvaise reponse la reponse est " + resultat +"    fin la suite qui etais de  " + increment);
           random();
           saisie.setText(null);
           increment=0;
           verifSaisie();



       }
       else {
           timerButton.setVisibility(View.VISIBLE);
           valid.setVisibility(View.GONE);
           False.setVisibility(View.VISIBLE);
           False.setText(verif);

           reset();
       }
   }
//------------------------------fin genreation num +verif-------------------------------------//
//endregion
    //region incrementation
//------------------------------debut incrementation -----------------------------------------//



    private  void incrementAffiche()
    {

        counter.setText(Integer.toString(increment));
    }
    private void incrementCombo()
    {

        if (increment == 5)True.setText("Bravo combo de " + increment);
        if (increment == 10 )True.setText("OUAH combo de  "+ increment);
        if (increment ==50)True.setText("Vous etes un genie !!! combo de " + increment);
    }
    //endregion




}
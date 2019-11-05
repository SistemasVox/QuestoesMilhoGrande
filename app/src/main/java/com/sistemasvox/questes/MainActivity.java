package com.sistemasvox.questes;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import android.os.Handler;

import com.sistemasvox.questes.bancoDeDados.DataBaseAcess;
import com.sistemasvox.questes.model.domain.Alternativa;
import com.sistemasvox.questes.model.domain.Questao;
import com.sistemasvox.questes.utils.RandomicoVetorX;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer player;
    private boolean aguardar = false;

    private TextView enuciado, numeracao, qtd;
    private String respostaC = "", respostaU = "";
    private RadioGroup rdGrupo;
    private RadioButton a, b, c, d, e;
    ArrayList<RadioButton> arrayListButtons = new ArrayList<>();
    ArrayList<Alternativa> alternativas = new ArrayList<>();
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questao2);

        enuciado = findViewById(R.id.txtEnunciado);
        numeracao = findViewById(R.id.txtNumeracao);
        qtd = findViewById(R.id.txtQuantidade);

        rdGrupo = findViewById(R.id.RadioGrupo);
        a = findViewById(R.id.radioA);
        arrayListButtons.add(a);
        b = findViewById(R.id.radioB);
        arrayListButtons.add(b);
        c = findViewById(R.id.radioC);
        arrayListButtons.add(c);
        d = findViewById(R.id.radioD);
        arrayListButtons.add(d);
        e = findViewById(R.id.radioE);
        arrayListButtons.add(e);

        // Toast.makeText(getApplicationContext(), "Boa sorte!", Toast.LENGTH_LONG).show();


        //play("boasorte");
        //play("susp"+String.valueOf(new Random().nextInt(6) +1));
        //construirQuestao();

        resposta("boasorte", "Boa sorte!");
        construirQuestao();


    }

    public void resposta(final String resposta, final String msg) {
        new Thread() {
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                    }

                });
                playR(resposta);
            }
        }.start();
    }

    public void pegarUmaQuestao(View view) {
        for (int i = 0; i < alternativas.size(); i++) {
            if (arrayListButtons.get(i).isChecked()) {
                respostaU = String.valueOf(i);
            }
        }

        if (respostaU.equals(respostaC)) {
            //Toast.makeText(getApplicationContext(), "Acertou!", Toast.LENGTH_LONG).show();
            resposta("correto", "Acertou!");
        } else {
            //Toast.makeText(getApplicationContext(), "Errou!", Toast.LENGTH_LONG).show();
            resposta("errou", "Errou!");
        }
        construirQuestao();
    }

    private void construirQuestao() {

        DataBaseAcess dataBaseAcess = DataBaseAcess.getInstance(getApplicationContext());
        dataBaseAcess.open();
        String id_Q = String.valueOf(new Random().nextInt(Integer.parseInt(dataBaseAcess.getTotalQuestao())) + 1);

        Questao questao = dataBaseAcess.getQuestao(id_Q);

        Toast.makeText(getApplicationContext(), new RandomicoVetorX(Integer.parseInt(dataBaseAcess.getTotalQuestao()), 16).gerarJogoAleatorio().toString(), Toast.LENGTH_LONG).show();
        Log.i("Errou", questao.toString());


        numeracao.setText(questao.getCod() + ")");
        enuciado.setText(questao.getEnunciado());
        qtd.setText(questao.getCod() + "/" + dataBaseAcess.getTotalQuestao());

        alternativas.clear();
        alternativas = dataBaseAcess.getAlternativas(id_Q);

        Log.i("Errou", alternativas.toString());

        for (int i = 0; i < alternativas.size(); i++) {
            arrayListButtons.get(i).setChecked(false);
            if (alternativas.get(i).getClassificacao().equals("0"))
                respostaC = String.valueOf(i);
            arrayListButtons.get(i).setText(alternativas.get(i).getResposta());
            arrayListButtons.get(i).setVisibility(View.VISIBLE);
            if (alternativas.size() < arrayListButtons.size()) {
                for (int j = alternativas.size(); j < arrayListButtons.size(); j++) {
                    arrayListButtons.get(j).setChecked(false);
                    arrayListButtons.get(j).setVisibility(View.INVISIBLE);
                }
            }
        }
        /*
        a.setText(alternativas.get(0).getResposta());
        b.setText(alternativas.get(1).getResposta());
        c.setText(alternativas.get(2).getResposta());
        d.setText(alternativas.get(3).getResposta());
        e.setText(alternativas.get(4).getResposta());*/

        dataBaseAcess.close();
        stop();
        play("susp" + String.valueOf(new Random().nextInt(6) + 1));
    }

    public void play(String name) {
        if (player == null) {
            player = MediaPlayer.create(this, getResources().getIdentifier(name, "raw", getPackageName()));
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopPlayer();
                }
            });
        }
        player.start();
    }
    public void playR(String name) {
        if (player == null) {
            player = MediaPlayer.create(this, getResources().getIdentifier(name, "raw", getPackageName()));
        }else {
            player.release();
            player = null;
            player = MediaPlayer.create(this, getResources().getIdentifier(name, "raw", getPackageName()));
        }
        player.start();
    }
    public void pause() {
        if (player != null) {
            player.pause();
        }
    }

    public void stop() {
        stopPlayer();
    }

    private void stopPlayer() {
        if (player != null) {
            player.release();
            player = null;
            //Toast.makeText(this, "MediaPlayer released", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopPlayer();
    }
}

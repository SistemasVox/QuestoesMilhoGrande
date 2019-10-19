package com.sistemasvox.questes.bancoDeDados;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sistemasvox.questes.model.domain.Alternativa;
import com.sistemasvox.questes.model.domain.Questao;

import java.util.ArrayList;

public class DataBaseAcess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DataBaseAcess instance;
    Cursor c = null;

    private DataBaseAcess(Context context) {
        this.openHelper = new DataBaseOpenHelper(context);
    }

    public static DataBaseAcess getInstance(Context context) {
        if (instance == null) {
            instance = new DataBaseAcess(context);
        }
        return instance;
    }

    public void open() {
        this.db = openHelper.getWritableDatabase();
    }

    public void close() {
        if (db != null) {
            this.db.close();
        }
    }

    public String getTotalQuestao() {
        String numero = "";

        c = db.rawQuery("SELECT COUNT(*) FROM Questoes;", null);
        while (c.moveToNext()) {
            numero = c.getString(0);
        }
        return numero;
    }

    public Questao getQuestao(String id) {
        c = db.rawQuery("SELECT * FROM Questoes WHERE cod = '" + id + "' ; ", new String[]{});
        while (c.moveToNext()){
            return new Questao(c.getString(0), c.getString(1), c.getString(2), c.getString(3));
        }
        return null;
    }
    public ArrayList<Alternativa> getAlternativas(String id) {
        ArrayList<Alternativa> alternativas = new ArrayList<>();
        c = db.rawQuery("SELECT * FROM Alternativa WHERE cod_q = '" + id + "';", new String[]{});
        while (c.moveToNext()){
            alternativas.add(new Alternativa(c.getString(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4)));
        }
        return alternativas;
    }

}

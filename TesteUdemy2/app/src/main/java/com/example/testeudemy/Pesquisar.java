package com.example.testeudemy;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Pesquisar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar);

        SharedPreferences sh = getSharedPreferences("resultados",MODE_PRIVATE);
        TextView lbPesquisa = findViewById(R.id.txtResultadoPesquisa);

        String nomeProduto = sh.getString("produto","teste");
        String valorCusto = sh.getString("custo","teste");
        String valorVenda = sh.getString("venda","teste");

        lbPesquisa.setText("Nome do produto: " + nomeProduto + "; \n" + "Valor Custo: " + valorCusto + "; \n" + "Valor Venda: " + valorVenda + "; \n-----------------------------------------");

    }
}

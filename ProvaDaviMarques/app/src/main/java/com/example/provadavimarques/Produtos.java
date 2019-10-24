package com.example.provadavimarques;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

public class Produtos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos);

        Intent intent = getIntent();

        TextView  lbResult = findViewById(R.id.txtView2);
        lbResult.setText(intent.getExtras().getString("resultado"));

        SharedPreferences sh = getSharedPreferences("resultados",MODE_PRIVATE);
        TextView lbResultado = findViewById(R.id.txtView1);

        String nomeProduto = sh.getString("produto","teste");
        String valorCusto = sh.getString("custo","teste");
        String valorVenda = sh.getString("venda","teste");

        lbResultado.setText("Nome do produto: " + nomeProduto + "; \n" + "Valor Custo: " + valorCusto + "; \n" + "Valor Venda: " + valorVenda + ";");

    }

    public void doExibir(View v){

        Intent intent = new Intent(this,Pesquisar.class);
        startActivity(intent);
    }
}

package com.example.provadavimarques;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void adicionar(View v) {
        EditText edit1 = findViewById(R.id.edit1);
        EditText edit2 = findViewById(R.id.edit2);
        EditText edit3 = findViewById(R.id.edit3);

        Double pCusto = Double.parseDouble(edit2.getText().toString());
        Double pVenda = Double.parseDouble(edit3.getText().toString());

        SharedPreferences sh = getSharedPreferences("resultados",MODE_PRIVATE);
        sh.edit().putString("produto",edit1.getText().toString()).apply();
        sh.edit().putString("custo",edit2.getText().toString()).apply();
        sh.edit().putString("venda",edit3.getText().toString()).apply();

        String strResultado = "";

        Double resultado = pVenda - pCusto;

        if (resultado > 0 ) {
            strResultado = "Lucro: " + resultado;
        }
        else {
            strResultado = "Prejuízo: " + resultado;
        }

        Toast.makeText(this, "Produto adicionado!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, Produtos.class);
        intent.putExtra("resultado", strResultado);

        startActivity(intent);
    }

    public void doExibir(View v){

        Intent intent = new Intent(this,Pesquisar.class);
        startActivity(intent);
    }
}

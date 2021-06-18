package com.example.appcupom;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import java.text.ParseException;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView lvCupons;
    private AdapterProduct adapter;
    private List<Product> listaCupons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FormularioActivity.class);
                intent.putExtra("acao", "novo");
                startActivity(intent);
            }
        });

        lvCupons = findViewById(R.id.lvCupons);

        try {
            carregarCupons();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        configurarListView();
    }

    private void configurarListView(){
        lvCupons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product productSelecionado = listaCupons.get(position);
                Intent intent = new Intent(MainActivity.this, FormularioActivity.class);
                intent.putExtra("acao", "editar");
                intent.putExtra("productId", productSelecionado.getId() );
                startActivity(intent);
            }
        });
    }

    private void excluirCupom(Product product){
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setIcon(android.R.drawable.ic_input_delete);
        alerta.setTitle(R.string.txtAtencao);
        alerta.setMessage(this.getResources().getString(R.string.txtConfirmaExclusao) + " " + product.getProductName() + " ?");
        alerta.setNeutralButton(R.string.txtBotaoCancelar, null);
        alerta.setPositiveButton(R.string.txtBotaoSim, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ProductDAO.excluir(product.getId(), MainActivity.this);
                try {
                    carregarCupons();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        alerta.show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        try {
            carregarCupons();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void carregarCupons() throws ParseException {
        listaCupons = ProductDAO.getProducts(this);
        adapter = new AdapterProduct(this, listaCupons);
        lvCupons.setAdapter( adapter );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
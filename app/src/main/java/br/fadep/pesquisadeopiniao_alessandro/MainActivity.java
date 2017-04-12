package br.fadep.pesquisadeopiniao_alessandro;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnBom, btnRuim,btnResultado, btnPesquisar;
    private int contVotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBom = (Button) findViewById(R.id.btnBom);
        btnRuim = (Button) findViewById(R.id.btnRuim);
        btnResultado = (Button) findViewById(R.id.btnResultado);
        btnPesquisar = (Button) findViewById(R.id.btnPesquisa);

        btnBom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contaVotos(0);
            }
        });

        btnRuim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            contaVotos(1);
            }
        });

        btnResultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostraResultado();
            }
        });


        btnPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarNovaPesquisa();
            }
        });
    }

    public void mostraResultado(){
        SharedPreferences sp = getSharedPreferences("votos", 0 );
        Toast.makeText(getApplication().getBaseContext(),"Qtd de Opinião Bom: " + sp.getInt("bom", 0 ) + "\n" + "Qtd de Opinião Ruim: " + sp.getInt("ruim", 0 ), Toast.LENGTH_LONG).show();
    }

    public void contaVotos(int BomRuim){
        SharedPreferences sp = getSharedPreferences("votos", 0 );
        contVotos = 0;
        if (BomRuim == 0) {
            contVotos = sp.getInt("bom", 0 );
            SharedPreferences.Editor editor = sp.edit();
            contVotos++;
            editor.putInt("bom",contVotos);
            editor.commit();
        }else{
            contVotos = sp.getInt("ruim", 0 );
            SharedPreferences.Editor editor = sp.edit();
            contVotos++;
            editor.putInt("ruim",contVotos);
            editor.commit();
        }
    }

    public void iniciarNovaPesquisa(){
        SharedPreferences sp = getSharedPreferences("votos", 0 );
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("bom",0);
        editor.putInt("ruim",0);
        editor.commit();
        Toast.makeText(getApplication().getBaseContext(),"Contadores Reiniciados. Pode iniciar uma nova pesquisa.",
                Toast.LENGTH_SHORT).show();
    }


}

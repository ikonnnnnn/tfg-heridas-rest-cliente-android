package uca.ruiz.antonio.tfgapp.ui.activity.admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import uca.ruiz.antonio.tfgapp.R;
import uca.ruiz.antonio.tfgapp.data.api.model.Sala;
import uca.ruiz.antonio.tfgapp.data.api.model.SalaConfig;

public class SalaActivity extends AppCompatActivity {

    private Sala sala;
    private SalaConfig salaConfig;

    private TextView tv_1_1, tv_1_2, tv_2_1, tv_2_2, tv_3_1, tv_3_2, tv_4_1, tv_4_2, tv_5_1, tv_5_2;
    private TextView tv_6_1, tv_6_2, tv_7_1, tv_7_2, tv_8_1, tv_8_2, tv_9_1, tv_9_2, tv_10_1, tv_10_2;

    private final int CAMPOS = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.elemento_view);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        usar(CAMPOS);

        sala = (Sala) getIntent().getExtras().getSerializable("sala");

        tv_1_1.setText(getString(R.string.nombre));
        tv_1_2.setText(sala.getNombre());

        tv_2_1.setText(getString(R.string.centro));
        tv_2_2.setText(sala.getCentro().getNombre());

        tv_3_1.setText(getString(R.string.hora_inicio));
        tv_4_1.setText(getString(R.string.cupo_dia));
        tv_5_1.setText(getString(R.string.dias_disponibles));


        try {
            salaConfig = sala.getSalaConfig();
            if(salaConfig != null) {
                String hh = salaConfig.getHoraini().toString();
                if(hh.length() == 1) hh = "0" + hh;
                String mm = salaConfig.getMinini().toString();
                if(mm.length() == 1) mm = "0" + mm;
                tv_3_2.setText(hh + ":" + mm);
                tv_4_2.setText(salaConfig.getCupo().toString());
                String dias = "";
                if(salaConfig.getLunes())
                    dias += getString(R.string.lunes) + "\n";
                if(salaConfig.getMartes())
                    dias += getString(R.string.martes) + "\n";
                if(salaConfig.getMiercoles())
                    dias += getString(R.string.miercoles) + "\n";
                if(salaConfig.getJueves())
                    dias += getString(R.string.jueves) + "\n";
                if(salaConfig.getViernes())
                    dias += getString(R.string.viernes) + "\n";
                if(salaConfig.getSabado())
                    dias += getString(R.string.sabado) + "\n";
                if(salaConfig.getDomingo())
                    dias += getString(R.string.domingo) + "\n";
                tv_5_2.setText(dias);
            } else {
                tv_3_2.setText(getString(R.string.no_definido));
                tv_4_2.setText(getString(R.string.no_definido));
                tv_5_2.setText(getString(R.string.no_definido));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_editar_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                startActivity(new Intent(this, SalasActivity.class));
                return true;
            case R.id.action_editar:
                Intent intent = new Intent(this, SalaNewEditActivity.class);
                intent.putExtra("sala", sala);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void usar(int i) {
        if(i >= 1) {
            tv_1_1 = (TextView) findViewById(R.id.tv_1_1);
            tv_1_2 = (TextView) findViewById(R.id.tv_1_2);
            tv_1_1.setVisibility(View.VISIBLE);
            tv_1_2.setVisibility(View.VISIBLE);
        }

        if(i >= 2) {
            tv_2_1 = (TextView) findViewById(R.id.tv_2_1);
            tv_2_2 = (TextView) findViewById(R.id.tv_2_2);
            tv_2_1.setVisibility(View.VISIBLE);
            tv_2_2.setVisibility(View.VISIBLE);
        }

        if(i >= 3) {
            tv_3_1 = (TextView) findViewById(R.id.tv_3_1);
            tv_3_2 = (TextView) findViewById(R.id.tv_3_2);
            tv_3_1.setVisibility(View.VISIBLE);
            tv_3_2.setVisibility(View.VISIBLE);
        }

        if(i >= 4) {
            tv_4_1 = (TextView) findViewById(R.id.tv_4_1);
            tv_4_2 = (TextView) findViewById(R.id.tv_4_2);
            tv_4_1.setVisibility(View.VISIBLE);
            tv_4_2.setVisibility(View.VISIBLE);
        }

        if(i >= 5) {
            tv_5_1 = (TextView) findViewById(R.id.tv_5_1);
            tv_5_2 = (TextView) findViewById(R.id.tv_5_2);
            tv_5_1.setVisibility(View.VISIBLE);
            tv_5_2.setVisibility(View.VISIBLE);
        }

        if(i >= 6) {
            tv_6_1 = (TextView) findViewById(R.id.tv_6_1);
            tv_6_2 = (TextView) findViewById(R.id.tv_6_2);
            tv_6_1.setVisibility(View.VISIBLE);
            tv_6_2.setVisibility(View.VISIBLE);
        }

        if(i >= 7) {
            tv_7_1 = (TextView) findViewById(R.id.tv_7_1);
            tv_7_2 = (TextView) findViewById(R.id.tv_7_2);
            tv_7_1.setVisibility(View.VISIBLE);
            tv_7_2.setVisibility(View.VISIBLE);
        }

        if(i >= 8) {
            tv_8_1 = (TextView) findViewById(R.id.tv_8_1);
            tv_8_2 = (TextView) findViewById(R.id.tv_8_2);
            tv_8_1.setVisibility(View.VISIBLE);
            tv_8_2.setVisibility(View.VISIBLE);
        }

        if(i >= 9) {
            tv_9_1 = (TextView) findViewById(R.id.tv_9_1);
            tv_9_2 = (TextView) findViewById(R.id.tv_9_2);
            tv_9_1.setVisibility(View.VISIBLE);
            tv_9_2.setVisibility(View.VISIBLE);
        }

        if(i >= 10) {
            tv_10_1 = (TextView) findViewById(R.id.tv_10_1);
            tv_10_2 = (TextView) findViewById(R.id.tv_10_2);
            tv_10_1.setVisibility(View.VISIBLE);
            tv_10_2.setVisibility(View.VISIBLE);
        }
    }
}

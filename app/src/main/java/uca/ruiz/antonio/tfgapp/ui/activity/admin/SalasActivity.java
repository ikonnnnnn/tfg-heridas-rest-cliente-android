package uca.ruiz.antonio.tfgapp.ui.activity.admin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uca.ruiz.antonio.tfgapp.R;
import uca.ruiz.antonio.tfgapp.data.api.io.MyApiAdapter;
import uca.ruiz.antonio.tfgapp.data.api.mapping.ApiError;
import uca.ruiz.antonio.tfgapp.data.api.model.Sala;
import uca.ruiz.antonio.tfgapp.ui.activity.CuraNewEditActivity;
import uca.ruiz.antonio.tfgapp.ui.adapter.admin.SalaAdapter;
import uca.ruiz.antonio.tfgapp.utils.Pref;

public class SalasActivity extends AppCompatActivity {

    private static final String TAG = SalasActivity.class.getSimpleName();
    private RecyclerView rv_listado;
    private LinearLayoutManager mLayoutManager;
    private SalaAdapter mAdapter;
    private SwipeRefreshLayout srl_listado;
    private ProgressDialog progressDialog;
    private EditText et_buscar;
    private Button bt_buscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_crud);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        et_buscar = (EditText) findViewById(R.id.et_buscar);
        bt_buscar = (Button) findViewById(R.id.bt_buscar);

        rv_listado = (RecyclerView) findViewById(R.id.rv_listado);
        rv_listado.setHasFixedSize(true); // la altura de los elementos será la misma

        // El RecyclerView usará un LinearLayoutManager
        mLayoutManager = new LinearLayoutManager(this);
        rv_listado.setLayoutManager(mLayoutManager);

        // añade línea divisoria entre cada elemento
        rv_listado.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        // Asociamos un adapter. Define cómo se renderizará la información que tenemos
        mAdapter = new SalaAdapter(this);
        rv_listado.setAdapter(mAdapter);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.cargando));

        cargarSalas();

        srl_listado = (SwipeRefreshLayout) findViewById(R.id.srl_listado);
        srl_listado.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recreate();
            }
        });

        bt_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarSalas(et_buscar.getText().toString());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_nuevo_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                startActivity(new Intent(this, MainAdminActivity.class));
                return true;
            case R.id.add_item:
                startActivity(new Intent(this, SalaNewEditActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainAdminActivity.class));
    }

    private void cargarSalas() {
        progressDialog.show();
        Call<ArrayList<Sala>> call = MyApiAdapter.getApiService().getSalasRecientes(Pref.getToken());
        call.enqueue(new Callback<ArrayList<Sala>>() {
            @Override
            public void onResponse(Call<ArrayList<Sala>> call, Response<ArrayList<Sala>> response) {
                progressDialog.cancel();
                if(response.isSuccessful()) {
                    ArrayList<Sala> salas = response.body();
                    if(salas != null) {
                        Log.d("SALAS", "Tamaño ==> " + salas.size());
                    }
                    mAdapter.setDataSet(salas);
                } else {
                    if (response.errorBody().contentType().subtype().equals("json")) {
                        ApiError apiError = ApiError.fromResponseBody(response.errorBody());
                        Toasty.error(SalasActivity.this, apiError.getMessage(),
                                Toast.LENGTH_LONG, true).show();
                        Log.d(TAG, apiError.getPath() + " " + apiError.getMessage());
                    } else {
                        try {
                            Log.d(TAG, response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Sala>> call, Throwable t) {
                progressDialog.cancel();

                if (t instanceof IOException) {
                    Toasty.warning(SalasActivity.this, getString(R.string.error_conexion_red),
                            Toast.LENGTH_LONG, true).show();
                } else {
                    Toasty.error(SalasActivity.this, getString(R.string.error_conversion),
                            Toast.LENGTH_LONG, true).show();
                    Log.d(TAG, getString(R.string.error_conversion));
                }
            }
        });

    }

    private void cargarSalas(String s) {
        progressDialog.show();
        Call<ArrayList<Sala>> call = MyApiAdapter.getApiService().getSalasByFiltro(s, Pref.getToken());
        call.enqueue(new Callback<ArrayList<Sala>>() {
            @Override
            public void onResponse(Call<ArrayList<Sala>> call, Response<ArrayList<Sala>> response) {
                progressDialog.cancel();
                if(response.isSuccessful()) {
                    ArrayList<Sala> salas = response.body();
                    if(salas != null) {
                        Log.d("SALAS", "Tamaño ==> " + salas.size());

                        // Ordenar las Salas según el nombre del Centro al que pertenecen
                        // Prefiero hacerlo en cliente para descargar al servidor
                        Collections.sort(salas, new Comparator<Sala>() {
                            @Override
                            public int compare(Sala s1, Sala s2) {
                                return s1.getCentro().getNombre().compareTo(s2.getCentro().getNombre());
                            }
                        });

                    }
                    mAdapter.setDataSet(salas);
                } else {
                    if (response.errorBody().contentType().subtype().equals("json")) {
                        ApiError apiError = ApiError.fromResponseBody(response.errorBody());
                        Toasty.error(SalasActivity.this, apiError.getMessage(),
                                Toast.LENGTH_LONG, true).show();
                        Log.d(TAG, apiError.getPath() + " " + apiError.getMessage());
                    } else {
                        try {
                            Log.d(TAG, response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Sala>> call, Throwable t) {
                progressDialog.cancel();

                if (t instanceof IOException) {
                    Toasty.warning(SalasActivity.this, getString(R.string.error_conexion_red),
                            Toast.LENGTH_LONG, true).show();
                } else {
                    Toasty.error(SalasActivity.this, getString(R.string.error_conversion),
                            Toast.LENGTH_LONG, true).show();
                    Log.d(TAG, getString(R.string.error_conversion));
                }
            }
        });

    }


}
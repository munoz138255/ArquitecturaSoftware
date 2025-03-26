package com.example.deportes.PanelControl;



import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.example.deportes.DAO.ApiJugadorRepository;
import com.example.deportes.DAO.ApiPartidaRepository;
import com.example.deportes.R;
import com.example.deportes.modelo.Jugador;
import com.example.deportes.modelo.Partida;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_ADD = 1;
    private static final int REQUEST_DISPLAY = 3;
    private static final int RESULT_OK_DISPLAY = 2;

    private  List<Partida> partidaList = new ArrayList<Partida>();
    private ArrayAdapter<Partida> partidaAdapter;
    private ApiPartidaRepository apiPartidaRepository = new ApiPartidaRepository();
    private ApiJugadorRepository apiJugadorRepository = new ApiJugadorRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar(findViewById(R.id.toolbar));
        this.partidaAdapter = new ArrayAdapter<>(
                this,
                R.layout.row_layout,
                R.id.listText,
                partidaList);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(partidaAdapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {

            Partida partida = (Partida) parent.getItemAtPosition(position);
            Bundle bundle = new Bundle();
            bundle.putString("objectid", partida.getId());
            bundle.putString("Jugador 1", partida.getJugador1().getNombre());
            bundle.putString("Jugador 2", partida.getJugador2().getNombre());
            Intent intent = new Intent(getApplicationContext(), DisplayActivity.class);
            intent.putExtras(bundle);
            startActivityForResult(intent, REQUEST_DISPLAY);
        });


    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_DISPLAY && resultCode == RESULT_OK_DISPLAY) {
            // UPDATE
            Bundle bundle = data.getExtras();
            String player1 = bundle.getString("Jugador 1");
            String player2 = bundle.getString("Jugador 2");
            String objectid = bundle.getString("objectid");
            Log.d("DEBUG", "Jugador 1: " + player1);
            Log.d("DEBUG", "Jugador 2: " + player2);

            for (Partida partida : partidaList) {
                if (partida.getId().equals(objectid)) {
                    partida.getJugador1().setNombre(player1);
                    partida.getJugador2().setNombre(player2);
                    break;
                    }
                }
            partidaAdapter.notifyDataSetChanged();
            CoordinatorLayout coordinatorLayout = (CoordinatorLayout)
                    findViewById(R.id.coordinatorLayout);

            Snackbar.make(coordinatorLayout, "Partida editada", Snackbar.LENGTH_LONG).show();
        }
         else if (requestCode == REQUEST_ADD && resultCode == RESULT_OK_DISPLAY) {
            // ADD
            if (data != null) {
                String player1 = data.getStringExtra("play1");
                String player2 = data.getStringExtra("play2");

                if (player1 != null && player2 != null) {
                    String partidaId = "partida" + (partidaList.size() + 1);

                    Jugador jugador1 = new Jugador(player1, partidaId);
                    Jugador jugador2 = new Jugador(player2, partidaId);

                    Partida partida = Partida.nuevaPartida(partidaId, jugador1, jugador2);
                    partidaList.add(partida);
                    partidaAdapter.notifyDataSetChanged();

                    Snackbar.make(findViewById(R.id.coordinatorLayout),
                            "Partida creada con " + player1 + " y " + player2, Snackbar.LENGTH_LONG).show();
                }
            }
        }
        else if (requestCode == REQUEST_DISPLAY && resultCode == RESULT_FIRST_USER) {
            // DELETE
            Bundle bundle = data.getExtras();
            String objectid = bundle.getString("objectid");
            Iterator<Partida> iterator = partidaList.iterator();
            while (iterator.hasNext()) {
                Partida partida = iterator.next();
                if (partida.getId().equals(objectid)) {
                    iterator.remove();
                    break;
                }
            }

            partidaAdapter.notifyDataSetChanged();

            Snackbar.make(findViewById(R.id.coordinatorLayout), "Employee deleted", Snackbar.LENGTH_LONG).show();
        }
    }
    public void addItem(View view){
        Intent intent = new Intent(getApplicationContext(), AddItemActivity.class);
        startActivityForResult(intent, REQUEST_ADD);
    }

}
package com.example.deportes.PanelControl;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.deportes.R;

public class DisplayActivity extends AppCompatActivity {
    private EditText editJugador1;
    private EditText editJugador2;
    private String position;
    private static final int RESULT_OK_DISPLAY = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        this.position = bundle.getString("objectid" );
        EditText player1 = (EditText)findViewById(R.id.jugador1);
        EditText player2 = (EditText)findViewById(R.id.jugador2);

        player1.setText(bundle.getString("Jugador 1"));
        player2.setText(bundle.getString("Jugador 2"));

    }
    public void save(View view) {
        Bundle bundle = new Bundle();
        EditText player1 = (EditText)findViewById(R.id.jugador1);
        EditText player2 = (EditText)findViewById(R.id.jugador2);
        String nom = player1.getText().toString();
        String sur = player2.getText().toString();
        bundle.putString("Jugador 1", nom);
        bundle.putString("Jugador 2", sur);
        bundle.putString("objectid",this.position);
        Log.d("DEBUG", "Jugador 1: " + nom);
        Log.d("DEBUG", "Jugador 2: " + sur);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        setResult(RESULT_OK_DISPLAY, intent);
        finish();
    }
    public void cancel(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }

    public void borrar(View view){
        Bundle bundle = new Bundle();
        bundle.putString("objectid", this.position); // Enviar el ID de la partida si es necesario

        Intent intent = new Intent();
        intent.putExtras(bundle);
        setResult(RESULT_FIRST_USER, intent); // Puedes definir un código distinto para "borrar"

        finish();

    }
}
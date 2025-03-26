package com.example.deportes.PanelControl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.deportes.R;

public class AddItemActivity extends AppCompatActivity {
    private static final int RESULT_OK_DISPLAY = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_item);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void add(View view) {
        Bundle bundle = new Bundle();
        EditText play1 = findViewById(R.id.jugador1);
        EditText play2 = findViewById(R.id.jugador2);
        String player1 = play1.getText().toString().trim();
        String player2 = play2.getText().toString().trim();

        if (player1.isEmpty() || player2.isEmpty()) {
            Toast.makeText(this, "Debe ingresar ambos jugadores", Toast.LENGTH_SHORT).show();
            return;
        }

        bundle.putString("play1", player1);
        bundle.putString("play2", player2);
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtras(bundle);
        setResult(RESULT_OK_DISPLAY, intent);
        finish();
    }
}
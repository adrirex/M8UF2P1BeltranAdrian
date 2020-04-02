package abeltran.example.m8uf2p1beltranadrian;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class inicio extends AppCompatActivity {

    Button nuevo;
    Button vista;
    Button salir;
    Button eliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);

        nuevo = (Button) findViewById(R.id.button1);
        vista = (Button) findViewById(R.id.button2);
        salir = (Button) findViewById(R.id.button3);
        eliminar = (Button) findViewById(R.id.button4);

        nuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        vista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), vista.class);
                startActivityForResult(intent, 0);
            }
        });

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), eliminar.class);
                startActivityForResult(intent, 0);
            }
        });
    }
}

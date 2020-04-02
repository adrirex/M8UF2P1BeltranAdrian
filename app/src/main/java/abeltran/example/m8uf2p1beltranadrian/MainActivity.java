package abeltran.example.m8uf2p1beltranadrian;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private EditText pregunta;
    private EditText respuesta1;
    private EditText respuesta2;
    private EditText respuesta3;
    private EditText respuesta4;
    private EditText username;
    private Button mSendButton;
    private Button volver;

    private String mUsername;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessagesDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mMessagesDatabaseReference = mFirebaseDatabase.getReference().child("Preguntas");

        // Initialize references to views
        pregunta = (EditText) findViewById(R.id.editText1);
        respuesta1 = (EditText) findViewById(R.id.editText2);
        respuesta2 = (EditText) findViewById(R.id.editText3);
        respuesta3 = (EditText) findViewById(R.id.editText4);
        respuesta4 = (EditText) findViewById(R.id.editText5);
        mSendButton = (Button) findViewById(R.id.button1);
        volver = (Button) findViewById(R.id.volver);
        username = (EditText) findViewById(R.id.nombre);

        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pregunta.getText().length() == 0){
                    pregunta.setError("No puede estar vacio");
                } else if(respuesta1.getText().length() == 0){
                    respuesta1.setError("No puede estar vacio");
                } else if(respuesta2.getText().length() == 0){
                    respuesta2.setError("No puede estar vacio");
                } else if(respuesta3.getText().length() == 0){
                    respuesta3.setError("No puede estar vacio");
                } else if(respuesta4.getText().length() == 0){
                    respuesta4.setError("No puede estar vacio");
                } else {

                    if (username.getText().length() == 0){
                        mUsername = "anonymous";
                    }else {
                        mUsername = username.getText().toString();
                    }

                    Respuestas respuestas = new Respuestas(respuesta1.getText().toString(), respuesta2.getText().toString(), respuesta3.getText().toString(), respuesta4.getText().toString());
                    Preguntas preguntas = new Preguntas(pregunta.getText().toString(), respuestas, mUsername);
                    mMessagesDatabaseReference.push().setValue(preguntas).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(MainActivity.this, "Insertado Correctamente!", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity.this, "Error al insertar!", Toast.LENGTH_SHORT).show();
                        }
                    });

                    // Clear input box
                    pregunta.setText("");
                    username.setText("");
                    respuesta1.setText("");
                    respuesta2.setText("");
                    respuesta3.setText("");
                    respuesta4.setText("");
                }
            }
        });

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

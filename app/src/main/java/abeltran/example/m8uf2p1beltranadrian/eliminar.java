package abeltran.example.m8uf2p1beltranadrian;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class eliminar extends AppCompatActivity {

    private Button volver;
    private Button eliminar;
    private TextView pregunta;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessagesDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eliminar);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        pregunta = (TextView) findViewById(R.id.pregunta2);
        eliminar = (Button) findViewById(R.id.eliminar);
        volver = (Button) findViewById(R.id.volver);

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textoIntroducido = pregunta.getText().toString();
                if (textoIntroducido.contains("CONFIRMAR")) {
                    mFirebaseDatabase.getReference().child("Preguntas").removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(eliminar.this, "Eliminado Correctamente!", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(eliminar.this, "Error al eliminar!", Toast.LENGTH_SHORT).show();
                        }
                    });
                    finish();
                }else{
                    Toast.makeText(eliminar.this, "Debes introducir CONFIRMAR para eliminar", Toast.LENGTH_SHORT).show();
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

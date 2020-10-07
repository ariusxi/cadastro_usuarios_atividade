package br.com.felix.cadastro_usuarios_atividade;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.orhanobut.hawk.Hawk;

public class CadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        Hawk.init(this).build();
    }

    public void save(View view) {
        Hawk.put("has_register", true);
        finish();
    }
}
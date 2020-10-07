package br.com.felix.cadastro_usuarios_atividade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.orhanobut.hawk.Hawk;

public class LoginActivity extends AppCompatActivity {

    private TextView textViewNewRegister;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Hawk.init(this).build();

        this.textViewNewRegister = findViewById(R.id.textViewNewRegister);
        this.buttonLogin = findViewById(R.id.buttonLogin);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Hawk.contains("has_register")) {
            if (Hawk.get("has_register")) {
                this.enableLogin();
            } else {
                this.disableLogin();
            }
        } else {
            this.disableLogin();
        }
    }

    private void enableLogin() {
        this.textViewNewRegister.setVisibility(View.GONE);
        this.buttonLogin.setEnabled(true);
        this.buttonLogin.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
    }

    private void disableLogin() {
        this.textViewNewRegister.setVisibility(View.VISIBLE);
        this.buttonLogin.setEnabled(false);
        this.buttonLogin.setBackgroundColor(getResources().getColor(R.color.gray));
    }

    public void newRegister(View view) {
        Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
        startActivity(intent);
    }

    public void login(View view) {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
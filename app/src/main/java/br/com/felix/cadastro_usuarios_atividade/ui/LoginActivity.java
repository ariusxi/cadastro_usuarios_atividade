package br.com.felix.cadastro_usuarios_atividade.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;

import br.com.felix.cadastro_usuarios_atividade.R;
import br.com.felix.cadastro_usuarios_atividade.model.User;
import br.com.felix.cadastro_usuarios_atividade.model.UserViewModel;

public class LoginActivity extends AppCompatActivity {

    private UserViewModel userViewModel;
    private User userCurrent;

    public EditText editTextUser;
    public EditText editTextPassword;

    private TextView textViewNewRegister;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Hawk.init(this).build();

        this.editTextUser = findViewById(R.id.editTextUser);
        this.editTextPassword = findViewById(R.id.editTextPassword);

        this.textViewNewRegister = findViewById(R.id.textViewNewRegister);
        this.buttonLogin = findViewById(R.id.buttonLogin);

        this.userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        this.userViewModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable final User user) {
                updateUser(user);
            }
        });
    }

    private void updateUser(User user) {
        this.userCurrent = user;
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
        if (this.userCurrent != null) {
            String user = this.editTextUser.getText().toString();
            String password = this.editTextPassword.getText().toString();

            if (user.equalsIgnoreCase(this.userCurrent.getEmail()) && password.equalsIgnoreCase(this.userCurrent.getPassword())) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                this.editTextPassword.setText("");
            } else {
                Toast.makeText(this, "Usuário ou Senha inválidos!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
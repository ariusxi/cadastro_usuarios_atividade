package br.com.felix.cadastro_usuarios_atividade.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;

import br.com.felix.cadastro_usuarios_atividade.R;
import br.com.felix.cadastro_usuarios_atividade.model.User;
import br.com.felix.cadastro_usuarios_atividade.model.UserViewModel;

public class CadastroActivity extends AppCompatActivity {

    private UserViewModel userViewModel;
    private User userCurrent;

    public EditText editTextName;
    public EditText editTextCPF;
    public EditText editTextEmail;
    public EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        Hawk.init(this).build();

        this.editTextName = findViewById(R.id.editTextName);
        this.editTextCPF = findViewById(R.id.editTextCpf);
        this.editTextEmail = findViewById(R.id.editTextEmail);
        this.editTextPassword = findViewById(R.id.editTextPassword);

        this.userCurrent = new User();

        this.userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        this.userViewModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable final User user) {
                updateView(user);
            }
        });
    }

    private void updateView(User user) {
        if (user != null && user.getId() > 0) {
            this.userCurrent = user;
            this.editTextName.setText(user.getName());
            this.editTextCPF.setText(user.getCpf());
            this.editTextEmail.setText(user.getEmail());
            this.editTextPassword.setText(user.getPassword());
        }
    }

    public void save(View view) {
        this.userCurrent.setName(this.editTextName.getText().toString());
        this.userCurrent.setCpf(this.editTextCPF.getText().toString());
        this.userCurrent.setEmail(this.editTextEmail.getText().toString());
        this.userCurrent.setPassword(this.editTextPassword.getText().toString());

        this.userViewModel.insert(this.userCurrent);

        Hawk.put("has_register", true);
        Toast.makeText(getApplication(), "Registro salvo com sucesso!", Toast.LENGTH_SHORT).show();
        finish();
    }
}
package br.com.felix.cadastro_usuarios_atividade.model;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class UserViewModel extends AndroidViewModel {

    public UserRepository userRepository;
    public LiveData<User> user;

    public UserViewModel(Application application) {
        super(application);
        this.userRepository = new UserRepository(application);
        this.user = this.userRepository.getUser();
    }

    public LiveData<User> getUser() {
        return user;
    }

    public void insert(User user) {
        this.userRepository.insert(user);
    }

}

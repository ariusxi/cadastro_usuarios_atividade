package br.com.felix.cadastro_usuarios_atividade.model;

import android.app.Application;

import androidx.lifecycle.LiveData;

public class UserRepository {

    private UserDAO userDAO;
    private LiveData<User> user;

    UserRepository(Application application) {
        StudentRoomDatabase db = StudentRoomDatabase.getDatabase(application);
        this.userDAO = db.userDAO();
        this.user = this.userDAO.getUser();
    }

    LiveData<User> getUser() {
        return this.user;
    }

    void insert(User user) {
        StudentRoomDatabase.databaseWriteExecutor.execute(() -> {
            this.userDAO.insert(user);
        });
    }

}

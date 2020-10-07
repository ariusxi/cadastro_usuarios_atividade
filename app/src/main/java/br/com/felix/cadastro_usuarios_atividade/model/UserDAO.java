package br.com.felix.cadastro_usuarios_atividade.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);

    @Query("DELETE FROM table_user")
    void deleteAll();

    @Query("SELECT * FROM table_user LIMIT 1")
    LiveData<User> getUser();

}

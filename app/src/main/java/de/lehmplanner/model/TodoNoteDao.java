package de.lehmplanner.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TodoNoteDao {

    @Query("SELECT * FROM todo_notes")
    public LiveData<List<TodoNoteEntity>> getAllTodoNotes();

    @Insert
    void insert(TodoNoteEntity entity);

    @Query("DELETE FROM todo_notes WHERE id = :id")
    void delete(int id);

}

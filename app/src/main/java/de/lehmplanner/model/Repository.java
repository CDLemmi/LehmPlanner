package de.lehmplanner.model;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Repository {


    private TodoNoteDao todoNoteDao;
    private final Executor executor = Executors.newSingleThreadExecutor();


    public Repository(Context context) {
        todoNoteDao = AppDatabase.getInstance(context).todoNoteDao();
    }




    public LiveData<List<TodoNoteEntity>> getAllTodoNotes() {
        return todoNoteDao.getAllTodoNotes();
    }

    public void addNote(TodoNoteEntity entity) {
        executor.execute(() -> todoNoteDao.insert(entity));
    }

    public void deleteNote(int id) {
        executor.execute(() -> todoNoteDao.delete(id));
    }



}

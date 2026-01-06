package de.lehmplanner.model;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class TodoViewModel extends AndroidViewModel {

    private final LiveData<List<TodoNoteEntity>> todoNotes;
    private Repository repository;

    public TodoViewModel(Application app) {
        super(app);
        repository = new Repository(app);
        todoNotes = repository.getAllTodoNotes();
    }

    public LiveData<List<TodoNoteEntity>> getTodoNotes() {
        return todoNotes;
    }

    public void addNote(String title) {
        repository.addNote(new TodoNoteEntity(title));
    }

    public void deleteNote(int id) {
        repository.deleteNote(id);
    }

}

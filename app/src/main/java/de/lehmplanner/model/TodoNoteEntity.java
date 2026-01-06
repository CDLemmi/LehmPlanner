package de.lehmplanner.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "todo_notes")
public class TodoNoteEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String title;


    public TodoNoteEntity(String title) {
        this.title = title;
    }
}

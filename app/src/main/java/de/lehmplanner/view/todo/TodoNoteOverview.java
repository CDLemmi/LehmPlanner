package de.lehmplanner.view.todo;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import de.lehmplanner.R;
import de.lehmplanner.model.AppDatabase;
import de.lehmplanner.model.TodoNoteDao;
import de.lehmplanner.model.TodoNoteEntity;
import de.lehmplanner.model.TodoViewModel;

public class TodoNoteOverview extends Fragment {

    TodoNoteAdapter adapter;
    TodoViewModel viewModel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.todo_overview, container, false);

        var fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(v -> openAddTodoNoteDialog());

        RecyclerView recyclerView = view.findViewById(R.id.todo_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



        adapter = new TodoNoteAdapter(id -> viewModel.deleteNote(id));
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(TodoViewModel.class);

        viewModel.getTodoNotes().observe(
                getViewLifecycleOwner(),
                notes -> adapter.submitList(notes)
        );
    }

    private void openAddTodoNoteDialog() {
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.create_todo_note_dialog, null);
        new AlertDialog.Builder(getContext())
            .setTitle("Input")
            .setView(dialogView)
            .setPositiveButton("OK", (dialog, which) -> {
                var inputComponent = (EditText) dialogView.findViewById(R.id.textView);
                String title = String.valueOf(inputComponent.getText());
                viewModel.addNote(title);
            })
            .setNegativeButton("Cancel", null)
            .show();

    }


}

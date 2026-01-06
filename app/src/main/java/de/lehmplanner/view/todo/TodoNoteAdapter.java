package de.lehmplanner.view.todo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

import de.lehmplanner.R;
import de.lehmplanner.model.TodoNoteDao;
import de.lehmplanner.model.TodoNoteEntity;

public class TodoNoteAdapter extends ListAdapter<TodoNoteEntity, TodoNoteAdapter.TodoNodeHolder> {


    public interface OnCBDoneListener {
        void onClick(int id);
    }

    final OnCBDoneListener listener;

    private TodoNoteDao noteDao;

    public TodoNoteAdapter(OnCBDoneListener onCBDoneListener) {
        super(new DiffUtil.ItemCallback<TodoNoteEntity>() {
            @Override
            public boolean areItemsTheSame(@NonNull TodoNoteEntity oldItem, @NonNull TodoNoteEntity newItem) {
                return oldItem.id == newItem.id;
            }

            @Override
            public boolean areContentsTheSame(@NonNull TodoNoteEntity oldItem, @NonNull TodoNoteEntity newItem) {
                return Objects.equals(oldItem.title, newItem.title);
            }
        });
        this.listener = onCBDoneListener;
    }


    @Override
    public TodoNoteAdapter.TodoNodeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_note, parent, false);
        return new TodoNoteAdapter.TodoNodeHolder(view);
    }

    @Override
    public void onBindViewHolder(TodoNoteAdapter.TodoNodeHolder holder, int position) {
        var note = getItem(position);
        holder.bind(note);
    }


    class TodoNodeHolder extends RecyclerView.ViewHolder {

        int noteID;

        TextView title;

        public TodoNodeHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.etTitle);
            itemView.findViewById(R.id.cbDone).setOnClickListener(v -> listener.onClick(noteID));
        }

        public void bind(TodoNoteEntity e) {
            noteID = e.id;
            title.setText(e.title);
            ((CheckBox)itemView.findViewById(R.id.cbDone)).setChecked(false);

        }

    }

}

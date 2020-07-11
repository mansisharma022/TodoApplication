package com.symb.task.todo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Home2Activity extends AppCompatActivity {

    private EditText notesEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        notesEditText = findViewById(R.id.notesEditText);
        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String notesStr = notesEditText.getText().toString();
                NotesModel note = new NotesModel();
                note.setNotesText(notesStr);
                note.setNotesDate(Calendar.getInstance().getTimeInMillis());
                SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
                Gson gson = new Gson();
                List<NotesModel> list = new ArrayList<>();
                list.add(note);
                editor.putString("notes", gson.toJson(list));
                editor.apply();
                notesEditText.setText("");
                Toast.makeText(Home2Activity.this, "notes saved successfully", Toast.LENGTH_SHORT).show();
            }
        });

    }
}

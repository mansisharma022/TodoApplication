package com.symb.task.todo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class RecyclerActivity extends AppCompatActivity {

    private RecyclerView recyclerview;
    private String[] s1;
    private String[] s2;
    private MyAdapter myAdapter;
    private int[] images = {R.drawable.calender, R.drawable.calender, R.drawable.calender, R.drawable.calender, R.drawable.calender, R.drawable.calender};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        recyclerview = findViewById(R.id.recyclerView);
        s1 = getResources().getStringArray(R.array.input_field);
        s2 = getResources().getStringArray(R.array.date_time);
        recyclerview.setLayoutManager(new GridLayoutManager(this, 2));
        myAdapter = new MyAdapter();
        recyclerview.setAdapter(myAdapter);
        List<NotesModel> list;
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        Type listType = new TypeToken<List<NotesModel>>() {
        }.getType();
        String restoredText = prefs.getString("notes", "");
        String json = new Gson().toJson(restoredText, listType);
        list = new Gson().fromJson(json, listType);
        myAdapter.setList(list);
        myAdapter.notifyDataSetChanged();
    }
}

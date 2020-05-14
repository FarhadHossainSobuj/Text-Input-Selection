package com.example.searchviewwithrecycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ExampleAdapter adapter;
    private List<ExampleItem> exampleList;

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fillExampleList();
        setUpRecyclerView();

        editText = findViewById(R.id.edit_query);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
    }

    private void filter(String text) {
        ArrayList<ExampleItem> filteredList = new ArrayList<>();
        for(ExampleItem item: exampleList){
            if(item.getText1().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }
        adapter.filterList(filteredList);
    }

    private void fillExampleList() {
        exampleList = new ArrayList<>();
        exampleList.add(new ExampleItem(R.drawable.ic_android, "One", "Ten"));
        exampleList.add(new ExampleItem(R.drawable.ic_audio, "Two", "Eleven"));
        exampleList.add(new ExampleItem(R.drawable.ic_sun, "Three", "Twelve"));
        exampleList.add(new ExampleItem(R.drawable.ic_android, "Four", "Thirteen"));
        exampleList.add(new ExampleItem(R.drawable.ic_audio, "Five", "Fourteen"));
        exampleList.add(new ExampleItem(R.drawable.ic_sun, "Six", "Fifteen"));
        exampleList.add(new ExampleItem(R.drawable.ic_android, "Seven", "Sixteen"));
        exampleList.add(new ExampleItem(R.drawable.ic_audio, "Eight", "Seventeen"));
        exampleList.add(new ExampleItem(R.drawable.ic_sun, "Nine", "Eighteen"));
    }

    private void setUpRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new ExampleAdapter(exampleList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}

package com.example.mareu.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.mareu.R;
import com.example.mareu.di.DI;
import com.example.mareu.service.MaReuApiService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Array;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ListReunionActivity extends AppCompatActivity {

    private MyAdapter mAdapter;
    private MaReuApiService mMaReuApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAdapter = new MyAdapter();
        final RecyclerView rv = (RecyclerView) findViewById(R.id.maReu_list_recycler_view);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(mAdapter);

        mMaReuApiService = DI.getMaReuApiService();
        mAdapter.setData(mMaReuApiService.getReunions());

        FloatingActionButton mButtonCreateReunion = findViewById(R.id.button_add_reunion);
        mButtonCreateReunion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListReunionActivity.this, AddReunionActivity.class);
                startActivity(intent);
            }
        });

/*        ImageButton mButtonDeleteReunion = findViewById(R.id.item_reunion_delete);
        mButtonDeleteReunion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAdapter.setData(mMaReuApiService.getReunions());
            }
        });*/
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
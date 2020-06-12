package com.samoylenko.homework222;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CustomActivity extends AppCompatActivity {

    private ProductAdapter prodAdapter;
    //картинки
    private List<Drawable> images = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_list_view);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        myToolbar.setTitle(R.string.tittle_main);
        setSupportActionBar(myToolbar);

        init();
    }

    public void init() {
        img();

        FloatingActionButton fab = findViewById(R.id.fab);
        ListView listView = findViewById(R.id.listview_p);
        Button btn = findViewById(R.id.checkBox_prod);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                prodAdapter.removeItem(position);
                return true;
            }
        });

        prodAdapter = new ProductAdapter(this, null);
        listView.setAdapter(prodAdapter);

    }

    public void addData() {
        prodAdapter.addItem(new Product(
                "Hello" + prodAdapter.getCount(),
                "Sub",
                images.get(new Random().nextInt(images.size()))
                //new Random().nextBoolean()
        ));
    }

    private void img() {
        images.add(getDrawable(R.drawable.ic_add_alarm_black_24dp));
        images.add(getDrawable(R.drawable.ic_add_circle_black_24dp));
        images.add(getDrawable(R.drawable.ic_card_membership_black_24dp));
        images.add(getDrawable(R.drawable.ic_cloud_circle_black_24dp));
        images.add(getDrawable(R.drawable.ic_content_cut_black_24dp));
        images.add(getDrawable(R.drawable.ic_desktop_mac_black_24dp));
        images.add(getDrawable(R.drawable.ic_group_work_black_24dp));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_main) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            Toast.makeText(this, R.string.toast_main, Toast.LENGTH_LONG).show();
            return true;
        }
        if (id == R.id.action_custom_adapter) {
            Intent intent = new Intent(getApplicationContext(), CustomActivity.class);
            startActivity(intent);
            Toast.makeText(this, R.string.toast_adapter, Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

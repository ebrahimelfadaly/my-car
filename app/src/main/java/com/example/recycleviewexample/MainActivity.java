package com.example.recycleviewexample;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
private Toolbar toolbar;
private RecyclerView recyclerView;
private FloatingActionButton fab;
private DatabaseAccess DB;
private car_rv_adapter adapter;

    private static final int ADD_CArd=1; ;
    private static final int EDDIT_CArd=1;
   public static final String CAR_KEY="car_key";
    public static final int PREMATION_REQUSET_CODE=4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //PREMATION
      if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
      {
          ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},PREMATION_REQUSET_CODE);
      }
        toolbar=findViewById(R.id.tobar);
        setSupportActionBar(toolbar);
        recyclerView =findViewById(R.id.main_recyclev);
         fab=findViewById(R.id.main_fab);

         DB=DatabaseAccess.getInstance(this);
        DB.open();
        ArrayList<Cars>cars =DB.getallcar();
        DB.close();
         adapter=new car_rv_adapter(cars,new newitemonclicklicener() {
             @Override
             public void onclick(int carid) {

                 Intent i=new Intent(getBaseContext(),view_card.class);
                 i.putExtra("car_key",carid);
                 startActivityForResult(i,EDDIT_CArd);
             }
         });
         recyclerView.setAdapter( adapter);
        RecyclerView.LayoutManager LM= new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(LM);
        recyclerView.setHasFixedSize(true);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(),view_card.class);
                startActivityForResult(intent,ADD_CArd);
            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        SearchView searchView= (SearchView) menu.findItem(R.id.main_serach).getActionView();
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                DB.open();
                ArrayList<Cars>cars=DB.serechdata(query);
                DB.close();
                adapter.setCars(cars);
                adapter.notifyDataSetChanged();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                DB.open();
                ArrayList<Cars>cars=DB.serechdata(newText);
                DB.close();
                adapter.setCars(cars);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                DB.open();
                ArrayList<Cars>cars=DB.getallcar();
                DB.close();
                adapter.setCars(cars);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==ADD_CArd ){
            DB.open();
            ArrayList<Cars>cars =DB.getallcar();
            DB.close();
            adapter.setCars(cars);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case PREMATION_REQUSET_CODE:
               if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
               {

               }

        }
    }

}
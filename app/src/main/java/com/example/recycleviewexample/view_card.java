package com.example.recycleviewexample;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.opengl.EGLExt;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class view_card extends AppCompatActivity {
  private Toolbar toolbar;
  private TextInputEditText et_model,et_color,et_destance,et_description;
  private ImageView img;
     private static  int carid=-1;
    private static final int PIC_IMG_REQUSTCODE=1;
    public static final int ADD_RESULT_CODE=2;
    public static final int EDDIT_RESULT_CODE=2;
    public static final int DELET_RESULT_CODE=2;
    private DatabaseAccess db;

    private Uri imageuri=null ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_card);
        et_model=findViewById(R.id.ed_model);
        et_color=findViewById(R.id.ed_color);
        et_destance=findViewById(R.id.ed_destiance);
        img=findViewById(R.id.img_detils);
        et_description=findViewById(R.id.ed_descreption);
        toolbar=findViewById(R.id.tobar_detils);
        setSupportActionBar(toolbar);
        db=DatabaseAccess.getInstance(this);


        Intent intent=getIntent();
        carid =intent.getIntExtra("car_key",-1);
        if (carid==-1)
        {
            // save  operation
            ENABLE_fildes();
            clear_fildes();


        }
        else {
            //edit or delet operation
            disable_fildes();
            db.open();
            Cars cars = db.getcar(carid);
            db.close();
            if (cars != null){
                fill_fildes(cars);
            }


        }
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Intent.ACTION_PICK,MediaStore.Images
                        .Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(in,PIC_IMG_REQUSTCODE);


            }
        });
    }
    private void fill_fildes(Cars c){
if (c.getImage() !=null && !c.getImage().equals(""))
    img.setImageURI(Uri.parse(c.getImage()));
            et_model.setText(c.getModel());
            et_color.setText(c.getColor());
            et_description.setText(c.getDescreption());
            et_destance.setText(c.getDistancelitrer() + "");

    }
    private void disable_fildes()
    {
       img.setEnabled(false);
        et_model.setEnabled(false);
        et_description.setEnabled(false);
        et_destance.setEnabled(false);
        et_color.setEnabled(false);
    }
    private void ENABLE_fildes()
    {
        img.setEnabled(true);
        et_model.setEnabled(true);
        et_description.setEnabled(true);
        et_destance.setEnabled(true);
        et_color.setEnabled(true);
    }
    private void clear_fildes(){

        et_color.setText("");
        et_destance.setText("");
        et_model.setText("");
        et_description.setText("");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.details,menu);
        MenuItem save = menu.findItem(R.id.details_menu_save);
        MenuItem edit = menu.findItem(R.id.detialis_edit);
        MenuItem delet = menu.findItem(R.id.details_delet);
        if (carid==-1)
        {
            // save  operation
            save.setVisible(true);
            edit.setVisible(false);
            delet.setVisible(false);

        }
        else {
            //edit or delet operation
            save.setVisible(false);
            edit.setVisible(true);
            delet.setVisible(true);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       String model,color,desc,imag ="";
       double dbi;
        db.open();
        switch (item.getItemId())
        {
            case R.id.details_menu_save:
                boolean result;
                model=et_model.getText().toString();
                color=et_color.getText().toString();
                desc=et_description.getText().toString();
                dbi= Double.parseDouble(et_destance.getText().toString());
                if (imageuri!=null) imag = imageuri.toString();
                Cars c =new Cars(carid,model,color,dbi,desc,imag);

                if (carid==-1) {
                    result = db.insertdata(c);
                    if (result) {
                        Toast.makeText(getBaseContext(), "svae sucess", Toast.LENGTH_LONG).show();
                        setResult(ADD_RESULT_CODE, null);
                        finish();
                    }

                }
                else {
                    result = db.Ubdate_data(c);
                    if (result) {
                        Toast.makeText(getBaseContext(), "eddit sucess", Toast.LENGTH_LONG).show();
                        setResult(EDDIT_RESULT_CODE, null);
                        finish();

                    }
                }


            return true;
            case R.id.detialis_edit:
                ENABLE_fildes();
                MenuItem save = toolbar.getMenu().findItem(R.id.details_menu_save);
                MenuItem edit = toolbar.getMenu().findItem(R.id.detialis_edit);
                MenuItem delet =toolbar.getMenu().findItem(R.id.details_delet);
                delet.setVisible(false);
                edit.setVisible(false);
                save.setVisible(true);
                return true;
            case R.id.details_delet:
               c=new Cars(carid,null,null,0,null,null);
                result=  db.deletdata(c);
                if (result)
                {
                    Toast.makeText(getBaseContext(), "DELET sucess", Toast.LENGTH_LONG).show();
                    setResult(DELET_RESULT_CODE, null);
                    finish();
                }
                return true;
        }
        db.close();
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==PIC_IMG_REQUSTCODE && resultCode==RESULT_OK){
            if (data!=null){
                imageuri=data.getData();
                img.setImageURI(imageuri);
            }
        }
    }
}
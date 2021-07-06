package com.example.recycleviewexample;

import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class car_rv_adapter extends RecyclerView.Adapter<car_rv_adapter.carvhadpter> {
private ArrayList<Cars>cars;
private newitemonclicklicener licener;
    public car_rv_adapter(ArrayList<Cars> cars,newitemonclicklicener licener)
    {
        this.cars = cars;
        this.licener=licener;
    }

    public ArrayList<Cars> getCars() {
        return cars;
    }

    public void setCars(ArrayList<Cars> cars) {
        this.cars = cars;
    }

    @NonNull
    @Override
    public carvhadpter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_car,null,false);
        carvhadpter viewh = new carvhadpter(view);
        return viewh;
    }

    @Override
    public void onBindViewHolder(@NonNull carvhadpter holder, int position) {
     Cars c=cars.get(position);
     if (c.getImage()!=null && c.getImage().isEmpty())
     holder.imageView.setImageURI(Uri.parse(c.getImage()));
     holder.tv_model.setText(c.getModel());
     holder.tv_color.setText(c.getColor());
     try {
         holder.tv_color.setTextColor(Color.parseColor(c.getColor()));
     } catch (Exception e) {
         e.printStackTrace();
     }

        holder.tv_destiance.setText(String.valueOf( c.getDistancelitrer()));
     holder.imageView.setTag(c.getId());
     holder.id=c.getId();
    }

    @Override
    public int getItemCount() {
        return cars.size() ;
    }

    class carvhadpter extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tv_model , tv_color,tv_destiance;
        int id;
        public carvhadpter(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.img_car_view);
            tv_model = itemView.findViewById(R.id.tv_model_view_card);
            tv_color = itemView.findViewById(R.id.tv_color_view_card);
            tv_destiance = itemView.findViewById(R.id.tv_destance_viewcard);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    licener.onclick(id);

                }
            });
        }
    }
}

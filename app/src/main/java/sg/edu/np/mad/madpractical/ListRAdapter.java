package sg.edu.np.mad.madpractical;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class ListRAdapter extends RecyclerView.Adapter<ListRViewHolder>{

    public static ArrayList<User>data;

    public ListRAdapter(ArrayList<User>data)
    {
        this.data = data;
    }

    @Override
    public int getItemViewType(int position) {
        Character lastDigit = ((data.get(position).name).charAt((data.get(position).name).length() - 1)); //get last character
        if (lastDigit == '7'){
            return 1; //use the big layout
        }
        else{
            return 0; //use normal layout
        }
    }


    @Override
    public ListRViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = null;
        if(viewType ==1)
            item = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.activity_list_rview_holder,parent,false);
        else
            item = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.activity_list_rview_holder,parent,false);

        return new ListRViewHolder(item);


    }

    @Override
    public void onBindViewHolder(@NonNull ListRViewHolder holder, @SuppressLint("RecyclerView") int position) {
        User u = data.get(position);
        holder.uname.setText(u.name);
        holder.desctxt.setText(u.description);
        ImageView profile = holder.profilepic;
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(profile.getContext());
                builder.setTitle("Profile");
                builder.setMessage(data.get(position).name);
                builder.setCancelable(true);
                builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent activityName = new Intent(profile.getContext(), MainActivity.class);
                        activityName.putExtra("position", position);
                        profile.getContext().startActivity(activityName);
                    }
                });
                builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });

                AlertDialog aDialog = builder.create();
                aDialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
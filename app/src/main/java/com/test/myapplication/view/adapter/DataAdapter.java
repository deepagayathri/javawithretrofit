package com.test.myapplication.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import com.test.myapplication.R;
import com.test.myapplication.view.Model.DataItem;
import com.test.myapplication.view.View.Recy_Page;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    ArrayList<DataItem> arrayList;
    Context context;

    OnViewStatsClick onViewStatsClick;


    public DataAdapter(ArrayList<DataItem> contactList, Context context) {
        this.arrayList = contactList;
        this.context = context;
    }


    public interface OnViewStatsClick {
           void OnViewStatsClickListioner(int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.recy_child_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.txtName.setText(arrayList.get(position).getFirstName());
        holder.txtEmail.setText(arrayList.get(position).getEmail());
        holder.cardContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onViewStatsClick.OnViewStatsClickListioner(position);
            }
        });
        Picasso.get().load(arrayList.get(position).getAvatar()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(holder.imgContact);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgContact;
        TextView txtName;
        TextView txtEmail;
        CardView cardContact;

        public ViewHolder(View childView) {
            super(childView);
            imgContact = (ImageView) childView.findViewById(R.id.imgContact);
            txtName = (TextView) childView.findViewById(R.id.txtName);
            txtEmail = (TextView) childView.findViewById(R.id.txtEmail);
            cardContact = (CardView) childView.findViewById(R.id.cardContact);
        }
    }
    public void setOnViewStatsClick(OnViewStatsClick onViewStatsClick) {
        this.onViewStatsClick = onViewStatsClick;
    }

}

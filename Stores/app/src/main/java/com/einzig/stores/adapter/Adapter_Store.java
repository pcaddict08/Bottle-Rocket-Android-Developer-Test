package com.einzig.stores.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.einzig.stores.Constants;
import com.einzig.stores.R;
import com.einzig.stores.activities.StoreDetailsActivity;
import com.einzig.stores.objects.Store;

import org.w3c.dom.Text;

import java.util.List;

import static com.einzig.stores.helpers.LogHelper.log_debug;

public class Adapter_Store extends RecyclerView.Adapter<Adapter_Store.ViewHolder> {
    private List<Store> list;
    private Context context;

    public Adapter_Store(List<Store> _list, Context _context) {
        list = _list;
        context = _context;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView namelabel_storerow;
        TextView addresslabel_storerow;
        TextView phonelabel_storerow;
        TextView address2label_storerow;
        ImageView image_storerow;
        LinearLayout baselayout_storerow;
        ViewHolder(View v) {
            super(v);
            namelabel_storerow = v.findViewById(R.id.namelabel_storerow);
            image_storerow = v.findViewById(R.id.image_storerow);
            addresslabel_storerow = v.findViewById(R.id.addresslabel_storerow);
            phonelabel_storerow = v.findViewById(R.id.phonelabel_storerow);
            address2label_storerow = v.findViewById(R.id.address2label_storerow);
            baselayout_storerow = v.findViewById(R.id.baselayout_storerow);
        }
    }

    @Override
    public Adapter_Store.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_store, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Store store = list.get(position);
        holder.namelabel_storerow.setText(store.getName());
        holder.addresslabel_storerow.setText(store.getAddress());
        holder.address2label_storerow.setText(store.getAddress2());
        holder.phonelabel_storerow.setText(store.getPhone());
        Glide.with(context).load(store.getStoreLogoURL()).into(holder.image_storerow);
        holder.baselayout_storerow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailsIntent = new Intent(context, StoreDetailsActivity.class);
                detailsIntent.putExtra(Constants.KEY_STORE, store);
                context.startActivity(detailsIntent);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return list.size();
    }
}
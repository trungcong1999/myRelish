package com.vn.itplus.myrelish.viewAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.vn.itplus.myrelish.R;
import com.vn.itplus.myrelish.dto.ItemProductCard;

import java.util.List;

public class ListProductCardRecycleAdapter extends RecyclerView.Adapter<ListProductCardRecycleAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageProduct;
        private TextView textName;

        public ViewHolder(View view) {
            super(view);
            mapping(view);
        }

        private void mapping(View view) {
            imageProduct = view.findViewById(R.id.image_product);
            textName = view.findViewById(R.id.text_product_name);
        }

        public ImageView getImageProduct() {
            return imageProduct;
        }

        public TextView getTextName() {
            return textName;
        }
    }

    private List<ItemProductCard> listItemProductCard;

    public ListProductCardRecycleAdapter(List<ItemProductCard> listItemProductCard) {
        this.listItemProductCard = listItemProductCard;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemlist_product_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get().load(listItemProductCard.get(position).getImageUrl()).fit().centerCrop()
                .placeholder(R.drawable.loading_2)
                .error(R.drawable.set_video_game_doodle_6997_1231)
                .into(holder.getImageProduct());
        holder.getTextName().setText(listItemProductCard.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return listItemProductCard.size();
    }


}
package com.vn.itplus.myrelish.viewAdapter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.vn.itplus.myrelish.R;
import com.vn.itplus.myrelish.dto.ItemCriteriaReview;

import java.util.List;

public class ListCriteriaRecycleAdapter extends RecyclerView.Adapter<ListCriteriaRecycleAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textCriteria, textScore, textReview;

        public ViewHolder(View view) {
            super(view);
            mapping(view);
        }

        private void mapping(View view) {
            textCriteria = view.findViewById(R.id.text_criteria);
            textScore = view.findViewById(R.id.text_score);
            textReview = view.findViewById(R.id.text_review);
        }

        public TextView getCriteria() {
            return textCriteria;
        }

        public TextView getScore() {
            return textScore;
        }

        public TextView getReview() {
            return textReview;
        }
    }

    private List<ItemCriteriaReview> listItemCriteriaReview;

    public ListCriteriaRecycleAdapter(List<ItemCriteriaReview> listItemCriteriaReview) {
        this.listItemCriteriaReview = listItemCriteriaReview;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemlist_game_rate_criteria, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getCriteria().setText(listItemCriteriaReview.get(position).getCriteriaName()+": ");
        holder.getScore().setText(listItemCriteriaReview.get(position).getScore()+"");
        holder.getReview().setText(listItemCriteriaReview.get(position).getReview());
    }

    @Override
    public int getItemCount() {
        return listItemCriteriaReview.size();
    }
}

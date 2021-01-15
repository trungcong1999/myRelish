package com.vn.itplus.myrelish.viewAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vn.itplus.myrelish.R;
import com.vn.itplus.myrelish.dto.ItemCriteriaReview;
import com.vn.itplus.myrelish.dto.ItemReviewArticle;

import java.util.List;

public class ListReviewArticleRecycleAdapter extends RecyclerView.Adapter<ListReviewArticleRecycleAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textTitle, textLastModified, textSummary, textContent;

        public ViewHolder(View view) {
            super(view);
            mapping(view);
        }

        private void mapping(View view) {
            textTitle = view.findViewById(R.id.text_title);
            textLastModified = view.findViewById(R.id.text_last_modified);
            textSummary = view.findViewById(R.id.text_summary);
            textContent = view.findViewById(R.id.text_content);
        }

        public TextView getTextTitle() {
            return textTitle;
        }

        public TextView getTextLastModified() {
            return textLastModified;
        }

        public TextView getTextSummary() {
            return textSummary;
        }

        public TextView getTextContent() {
            return textContent;
        }
    }

    private List<ItemReviewArticle> listItemReviewArticle;

    public ListReviewArticleRecycleAdapter(List<ItemReviewArticle> listItemReviewArticle) {
        this.listItemReviewArticle = listItemReviewArticle;
    }

    @NonNull
    @Override
    public ListReviewArticleRecycleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemlist_game_review_article, parent, false);
        return new ListReviewArticleRecycleAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListReviewArticleRecycleAdapter.ViewHolder holder, int position) {
        holder.getTextTitle().setText(listItemReviewArticle.get(position).getTitle());
        holder.getTextLastModified().setText("Chỉnh sửa lần cuối: "+listItemReviewArticle.get(position).getLastModifiedTime());
        holder.getTextSummary().setText("Tóm tắt: "+listItemReviewArticle.get(position).getSummary());
        holder.getTextContent().setText(listItemReviewArticle.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return listItemReviewArticle.size();
    }
}
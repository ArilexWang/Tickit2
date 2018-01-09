package com.example.ricardo.tickit2.view.fragment.show;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ricardo.tickit2.R;
import com.example.ricardo.tickit2.data.model.Show;
import com.mancj.materialsearchbar.adapter.SuggestionsAdapter;

import java.util.ArrayList;

import kotlin.Unit;

/**
 * Created by yyh
 */

public class SearchSuggestionsAdapter extends SuggestionsAdapter<Show, SearchSuggestionsAdapter.SuggestionHolder> {

    public SearchSuggestionsAdapter(LayoutInflater inflater) {
        super(inflater);
    }

    @Override
    public int getSingleViewHeight() {
        return 80;
    }

    @Override
    public SuggestionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = getLayoutInflater().inflate(R.layout.item_custom_suggestion, parent, false);
        return new SuggestionHolder(view);
    }

    @Override
    public void onBindSuggestionHolder(Show suggestion, SuggestionHolder holder, int position) {
        holder.title.setText(suggestion.getName());

    }



    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                String term = constraint.toString();
                if(term.isEmpty())
                    suggestions = suggestions_clone;
                else {
                    suggestions = new ArrayList<>();
                    for (Show item: suggestions_clone)
                        if(item.getName().toLowerCase().contains(term.toLowerCase()))
                            suggestions.add(item);
                }
                results.values = suggestions;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                suggestions = (ArrayList<Show>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    static class SuggestionHolder extends RecyclerView.ViewHolder{
        protected TextView title;
        protected TextView subtitle;
        protected ImageView image;



        public SuggestionHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);

            subtitle = (TextView) itemView.findViewById(R.id.subtitle);
        }
    }

}

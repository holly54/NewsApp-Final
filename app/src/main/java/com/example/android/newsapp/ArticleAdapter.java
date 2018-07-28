package com.example.android.newsapp;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * An {@link ArticleAdapter} knows how to create a list item layout for each article
 * in the data source (a list of {@link Article} objects).
 * These list item layouts will be provided to an adapter view like ListView
 * to be displayed to the user.
 */
public class ArticleAdapter extends ArrayAdapter<Article> {

    public ArticleAdapter(Activity context, ArrayList<Article> articles) {
        super(context, 0, articles);
    }

    /**
     * To hold ArrayList Views.
     */

    static class ArticleViewHolder {
        private TextView title;
        private TextView author;
        private TextView section;
        private TextView date;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the {@link Article} object located at the current position.
        Article currentArticle = getItem(position);

        ArticleViewHolder holder;

        // Checks if the view is being reused, otherwise inflate.
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.article_list_item, parent, false);
            holder = new ArticleViewHolder();
            // Finds title, author, section and date TextViews.
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.author = (TextView) convertView.findViewById(R.id.author);
            holder.section = (TextView) convertView.findViewById(R.id.section);
            holder.date = (TextView) convertView.findViewById(R.id.date);
            convertView.setTag(holder);
        } else {
            holder = (ArticleViewHolder) convertView.getTag();
        }

        // Sets title to current Article object.
        holder.title.setText(currentArticle.getArticleTitle());

        // Do not show the textview if there is no author for the article.
        String authorText = currentArticle.getAuthorName();
        if (authorText == null) {
            holder.author.setVisibility(View.GONE);
        } else {
            holder.author.setText(authorText);
        }

        // Sets section to current Article object.
        holder.section.setText(currentArticle.getSectionTitle());

        String originalDate = currentArticle.getPublicationDate();
        String date = null;

        // Sets text of the date TextView.
        holder.date.setText(currentArticle.getPublicationDate());

        return convertView;
    }
}
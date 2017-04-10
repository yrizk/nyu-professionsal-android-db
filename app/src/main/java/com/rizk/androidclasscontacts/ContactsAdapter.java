package com.rizk.androidclasscontacts;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by rizk on 4/10/17.
 */
public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactViewHolder> {

    private String[] data = new String[]{"this", "is", "some", "data", "that", "should", "serve", "as", "dummy"};

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout,parent,false);
        return new ContactViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        holder.bind(data[position % (data.length - 1)], null);
    }

    @Override
    public int getItemCount() {
        return data.length * 2;
    }

    class ContactViewHolder extends RecyclerView.ViewHolder {
        protected TextView textView;

        public ContactViewHolder(View view) {
            super(view);
            this.textView = (TextView) view.findViewById(R.id.title);
        }

        void bind(String name, String phoneNumber) {
            textView.setText(name);
        }

        void unbind() {
            textView.setText("");
        }
    }
}

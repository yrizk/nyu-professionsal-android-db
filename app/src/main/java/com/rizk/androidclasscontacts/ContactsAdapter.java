package com.rizk.androidclasscontacts;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by rizk on 4/10/17.
 */
public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactViewHolder> {

    List<Contact> contactsList;


    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout,parent,false);
        return new ContactViewHolder(rowView);
    }

    public void setContactsList(List<Contact> list) {
        this.contactsList = list;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        holder.bind(contactsList.get(position));
    }

    @Override
    public int getItemCount() {
        return contactsList.size();
    }

    class ContactViewHolder extends RecyclerView.ViewHolder {
        private TextView nameView;
        private TextView phoneNoView;


        public ContactViewHolder(View view) {
            super(view);
            this.nameView = (TextView) view.findViewById(R.id.title);
            this.phoneNoView = (TextView) view.findViewById(R.id.phone_number);
        }

        void bind(Contact contact) {
            nameView.setText(contact.getName());
            phoneNoView.setText(contact.getFormattedDigits());
        }

        void unbind() {
            nameView.setText("");
        }
    }
}

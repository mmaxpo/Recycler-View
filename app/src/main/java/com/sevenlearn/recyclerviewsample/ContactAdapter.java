package com.sevenlearn.recyclerviewsample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    private static final String TAG = "ContactAdapter";
    private List<Contact> contacts = new ArrayList<>();
    private Context context;

    public ContactAdapter(Context context, List<Contact> contacts) {
        this.context = context;
        this.contacts = contacts;
    }

    public void addContact(Contact contact) {
        this.contacts.add(0, contact);
        notifyItemInserted(0);
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_contact, viewGroup, false);
        Log.i(TAG, "onCreateViewHolder is called ");
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder contactViewHolder, int position) {
        Log.i(TAG, "onBindViewHolder is called, position=>  " + position);
        contactViewHolder.bindContact(contacts.get(position));
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {
        private TextView firstCharacterTv;
        private TextView nameTv;
        private TextView phoneNumberTv;
        private View deleteButton;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            firstCharacterTv = itemView.findViewById(R.id.tv_contact_firstChar);
            nameTv = itemView.findViewById(R.id.tv_contact_name);
            phoneNumberTv = itemView.findViewById(R.id.tv_contact_phoneNumber);
            deleteButton = itemView.findViewById(R.id.iv_contact_delete);
        }

        public void bindContact(Contact contact) {
            firstCharacterTv.setText(contact.getName().substring(0, 1));
            nameTv.setText(contact.getName());
            phoneNumberTv.setText(contact.getPhoneNumber());
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    contacts.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                }
            });
        }
    }
}

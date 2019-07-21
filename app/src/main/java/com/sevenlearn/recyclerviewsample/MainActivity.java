package com.sevenlearn.recyclerviewsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView recyclerView = findViewById(R.id.rv_main_contacts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        List<Contact> contacts = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Contact contact = new Contact();
            contact.setName("Name " + (i + 1));
            contact.setPhoneNumber("PhoneNumber " + (i + 1));
            contacts.add(contact);
        }

        final ContactAdapter contactAdapter = new ContactAdapter(this, contacts);
        recyclerView.setAdapter(contactAdapter);

        final EditText nameEt = findViewById(R.id.et_main_name);
        final EditText phoneNumberEt = findViewById(R.id.et_main_phoneNumber);
        Button addContactBtn = findViewById(R.id.btn_main_addContact);
        addContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact contact = new Contact();
                contact.setName(nameEt.getText().toString());
                contact.setPhoneNumber(phoneNumberEt.getText().toString());
                contactAdapter.addContact(contact);
                recyclerView.smoothScrollToPosition(0);
            }
        });
    }
}

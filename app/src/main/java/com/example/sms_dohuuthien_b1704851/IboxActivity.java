package com.example.sms_dohuuthien_b1704851;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;

import com.example.sms_dohuuthien_b1704851.databinding.ActivityIboxBinding;

import java.util.ArrayList;
import java.util.List;

public class IboxActivity extends AppCompatActivity {
    ActivityIboxBinding binding;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(IboxActivity.this, R.layout.activity_ibox);

        list = getSMS();
        arrayAdapter = new ArrayAdapter<>(IboxActivity.this, android.R.layout.simple_list_item_1, list);
        //set adapter listview
        binding.list.setAdapter(arrayAdapter);

    }

    public ArrayList<String> getSMS() {
        ArrayList<String> sms = new ArrayList<String>();
        Uri uriSMSURI = Uri.parse("content://sms/inbox");
        Cursor cur = getContentResolver().query(uriSMSURI, null, null, null, null);

        while (cur != null && cur.moveToNext()) {
            String address = cur.getString(cur.getColumnIndex("address"));
            String body = cur.getString(cur.getColumnIndexOrThrow("body"));
            sms.add("Number: " + address + " .Message: " + body);
        }

        if (cur != null) {
            cur.close();
        }
        return sms;
    }


}
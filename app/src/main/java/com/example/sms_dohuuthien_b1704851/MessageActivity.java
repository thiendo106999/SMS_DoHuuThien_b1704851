package com.example.sms_dohuuthien_b1704851;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.example.sms_dohuuthien_b1704851.databinding.ActivityMessageBinding;

public class MessageActivity extends AppCompatActivity {
    ActivityMessageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(MessageActivity.this, R.layout.activity_message);

        binding.newMassage.setOnClickListener(v -> startActivity(new Intent(MessageActivity.this, NewMessageActivity.class)));
        binding.inbox.setOnClickListener(v -> startActivity(new Intent(MessageActivity.this, IboxActivity.class)));
        binding.exit.setOnClickListener(v -> System.exit(0));
    }
}
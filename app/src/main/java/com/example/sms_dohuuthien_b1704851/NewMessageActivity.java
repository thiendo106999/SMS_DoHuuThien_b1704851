package com.example.sms_dohuuthien_b1704851;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import com.example.sms_dohuuthien_b1704851.databinding.ActivityNewMessageBinding;

import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.List;

public class NewMessageActivity extends AppCompatActivity {
    ActivityNewMessageBinding binding;
    private String LOG_TAG = "SMS";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(NewMessageActivity.this,R.layout.activity_new_message);

       binding.send.setOnClickListener(v ->  sendMessage());
       binding.exitinbox.setOnClickListener(v -> finish());

        Handler handler = new Handler();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                getLastSMS();
                handler.postDelayed(this, 2000);
            }
        };
        handler.postDelayed(runnable, 500);
    }

    private void sendMessage() {
        String phone = binding.phone.getText().toString();
        String message = binding.message.getText().toString();
        try {
            SmsManager manager = SmsManager.getDefault();
            manager.sendTextMessage(phone, null, message, null, null);

            Log.i( LOG_TAG,"Your sms has successfully sent!");
            Toast.makeText(getApplicationContext(),"Your sms has successfully sent!",
                    Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Log.e( LOG_TAG,"Your sms has failed...", ex);
            Toast.makeText(getApplicationContext(),"Your sms has failed... " + ex.getMessage(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }

    }
    public void getLastSMS() {
        String lastMessage = "";
        Uri uriSMSURI = Uri.parse("content://sms/inbox");
        Cursor cur = getContentResolver().query(uriSMSURI, null, null, null, null);

        cur.moveToFirst();
               String body = cur.getString(cur.getColumnIndexOrThrow("body"));
               lastMessage = body;
               Log.e("TAG", "getLastSMS: "+ body );

        if ((lastMessage.equals("Bong den"))){
            binding.status.setText("dang bat");
        }else if (lastMessage.equals("Ban ui")) {
            binding.status.setText("Da tat");
        } else binding.status.setText("");
        if (cur != null) {
            cur.close();
        }
    }
}
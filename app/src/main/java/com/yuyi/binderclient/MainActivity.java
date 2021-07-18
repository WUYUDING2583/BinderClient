package com.yuyi.binderclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.yuyi.binderservice.MyAIdl;
import com.yuyi.binderservice.Person;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MyAIdl aIdl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindservice();
    }

    private void bindservice() {
        Intent intent = new Intent();
        intent.setComponent(
                new ComponentName(
                        "com.yuyi.binderservice",
                        "com.yuyi.binderservice.AIdlService"
                ));
        bindService(intent,conn, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection conn=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            aIdl=MyAIdl.Stub.asInterface(service);
            Log.e("CLIENT", "onServiceConnected: "+aIdl);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    public void click(View view) {
        try {
            aIdl.addPerson(new Person("yuyi",20));
            List<Person> people=aIdl.getPersonList();
            Toast.makeText(this, people.toString(),Toast.LENGTH_LONG).show();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
package com.example.rahulyiet.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    Button btn1,btn2,btn3,btn4;
    ListView listView;
    private BluetoothAdapter bluetoothAdapter;
    private Set<BluetoothDevice>PairedDevices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        bluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
        listView = findViewById(R.id.listView);
    }

    public void On(View view){
        if(!bluetoothAdapter.isEnabled()){
            Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(turnOn,0);
            Toast.makeText(getApplicationContext(),"Turned on",Toast.LENGTH_SHORT).show();

        }else {

            Toast.makeText(getApplicationContext(),"Already on",Toast.LENGTH_SHORT).show();
        }
    }

    public void off(View view){

        bluetoothAdapter.disable();
        Toast.makeText(getApplicationContext(),"Bluetooth off",Toast.LENGTH_SHORT).show();
    }

    public void Visible(View view){

        Intent getVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivityForResult(getVisible,0);
    }

    public void list(View view){

        PairedDevices = bluetoothAdapter.getBondedDevices();

        ArrayList list = new ArrayList();
        for (BluetoothDevice bluetoothDevice :PairedDevices)list.add(bluetoothDevice.getName());
        Toast.makeText(getApplicationContext(),"Show paired devices",Toast.LENGTH_SHORT).show();
        final ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);
    }
}

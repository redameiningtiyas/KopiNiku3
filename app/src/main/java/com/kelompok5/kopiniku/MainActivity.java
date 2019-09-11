package com.kelompok5.kopiniku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.CheckBox;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    EditText quantity_textview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Submitorder(View view) {
        EditText nameEditText=(EditText)findViewById(R.id.edt_name);
        quantity_textview = findViewById(R.id.quantity_textview);
        String name=nameEditText.getText().toString();
        String stringQuantity = quantity_textview.getText().toString();
        int quantity = Integer.parseInt(stringQuantity);
        Log.v("MainActivity","Nama:"+name);

        CheckBox whippedcreamChekBox= (CheckBox) findViewById(R.id.WhippedCream_checkbox);
        boolean haswhippedcream=whippedcreamChekBox.isChecked();//mengidentifikasi check
        Log.v("MainActivity","has whippedcream:"+haswhippedcream);

        CheckBox chocolateChekBox= (CheckBox) findViewById(R.id.Chocolate_checkbox);
        boolean haschocolate=chocolateChekBox.isChecked();//mengidentifikasi check
        Log.v("MainActivity","has whippedcream:"+haschocolate);

        int price=calculateprice(haswhippedcream,haschocolate,quantity);//memanggil method jumlah harga
        String pricemessage=createOrderSummary(price,name,haswhippedcream,haschocolate,quantity);


        displayMessage(pricemessage);

    }
    private int calculateprice(boolean addwhipedcream,boolean addchocolate, int quantity){//jumlah pesanan * harga
        int harga=5000;
        if(addwhipedcream){
            harga=harga+1000;//harga tambahan toping
        }

        if (addchocolate){
            harga=harga+2000;
        }

        return quantity * harga;
    }
    private String createOrderSummary(int price, String name, boolean addChocolate, boolean addWhippedCream, int quantity) {//hasil pemesanan
        String pricemessage=" Nama = "+name;
        pricemessage+="\n Tambahkan Coklat =" +addWhippedCream;
        pricemessage+="\n Tambahkan Krim =" +addChocolate;
        pricemessage+="\n Jumlah Pemesanan =" +quantity;
        pricemessage+="\n Total = Rp " +price;
        pricemessage+="\n Terimakasih";
        return  pricemessage;
    }

    //method ini untuk mencetak hasil perintah yang di tampilkan dengan inisial quantity_textview di textview 0
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_textview);
        priceTextView.setText(message);
    }
    private void display(double number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_textview);
        quantityTextView.setText("" + number);
    }
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_textview);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    public void info(View view) {
        Intent intent = new Intent(MainActivity.this, ActivityInfo.class);
        startActivity(intent);
    }
}

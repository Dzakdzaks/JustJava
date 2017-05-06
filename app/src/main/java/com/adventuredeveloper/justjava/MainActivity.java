package com.adventuredeveloper.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Method untuk menambahkan quantity
    public void increment(View view) {
        if (quantity == 100) {
            Toast.makeText(this, "You cannot have more than 100 coffes", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    //method untuk menampilkan quantity
    private void displayQuantity(int numberOfCoffes) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffes);
    }

    //method untuk mengurangi quantity
    public void decrement(View view) {
        if (quantity == 1) {
            Toast.makeText(this, "You cannot have less than 1 coffe", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /*fungsi untuk mengecek apakah isian kosong*/
    public static boolean isEmpty(EditText editText) {
        /*jika banyak huruf lebih dari 0*/
        if (editText.getText().toString().trim().length() > 0) {
            /*tidak dikembalikan*/
            return false;
        } else {
            /*kembalikan*/
            return true;
        }
    }


    //method untuk menampilkan orderan
    public void submitOrder(View view) {
        EditText nameField = (EditText) findViewById(R.id.name_field);
        if (isEmpty(nameField)) {
            nameField.setError("You Must Enter Yout Name");
            nameField.requestFocus();
        }
        String name = nameField.getText().toString();

        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_check_box);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_check_box);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        CheckBox chachaCheckBox = (CheckBox) findViewById(R.id.chacha_check_box);
        boolean hasChacha = chachaCheckBox.isChecked();

        CheckBox strawberryJamCheckBox = (CheckBox) findViewById(R.id.strawberry_jam_check_box);
        boolean hasStrawberryJam = strawberryJamCheckBox.isChecked();

        CheckBox milkCheckBox = (CheckBox) findViewById(R.id.milk_check_box);
        boolean hasMilk = milkCheckBox.isChecked();


        int price = calculatePrice(hasWhippedCream, hasChocolate, hasChacha, hasStrawberryJam, hasMilk);
        String priceMessage = createOrderSummary(name, price, hasWhippedCream, hasChocolate, hasChacha, hasStrawberryJam, hasMilk);
        displayMessage(priceMessage);

//        Intent intent = new Intent(Intent.ACTION_SENDTO);
//        intent.setData(Uri.parse("mailto:"));
//        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java Coffee Order For " + name);
//        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
//        if (intent.resolveActivity(getPackageManager()) != null){
//            startActivity(intent);
//        }
    }

    //method untuk menampilkan nama pemesan
    private void displayMessage(String message) {
        TextView orderSUmmaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSUmmaryTextView.setText(message);
    }

    //method untuk menghitung harga jika ada tambahan topping
    private int calculatePrice(boolean addWhispedCream, boolean addChocolate, boolean addChacha, boolean addStrawberryJam, boolean addMilk) {
        int basePrice = 5;

        if (addWhispedCream) {
            basePrice = basePrice + 1;
        }
        if (addChocolate){
            basePrice = basePrice + 2;
        }

        if (addChacha){
            basePrice = basePrice + 3;
        }

        if (addStrawberryJam){
            basePrice = basePrice + 4;
        }

        if (addMilk){
            basePrice = basePrice + 5;
        }
        return basePrice * quantity;
    }


    //Method untuk menampilkan hasil semuanya...
    public String createOrderSummary(String name, int price, boolean addWhippedCream, boolean addChocolate, boolean addChacha, boolean addStrawberryJam, boolean addMilk) {
        String priceMessage = "Name : " + name;
        priceMessage += "\nAdd Whipped Cream? " + addWhippedCream;
        priceMessage += "\nAdd Chocolate? " + addChocolate;
        priceMessage += "\nAdd Chacha? " + addChacha;
        priceMessage += "\nAdd Strawberry Jam? " + addStrawberryJam;
        priceMessage += "\nAdd Milk? " + addMilk;
        priceMessage += "\nQuantity : " + quantity;
        priceMessage += "\nTotal : $ " + price;
        priceMessage += "\nThanks You For Your Order!";

//        AlertDialog.Builder alert = new AlertDialog.Builder(this);
//        alert.setTitle("This is Your Order Summary");
//        alert.setMessage("Name : " + name + "\nAdd Whipped Cream? " + addWhippedCream + "\nAdd Chocolate? " + addChocolate + "\nAdd Chacha? " + addChacha + "\nAdd Strawberry Jam? " + addStrawberryJam + "\nAdd Milk? " + addMilk + "\nQuantity : " + quantity + "\nTotal : $" + price + "\nThank You For Your Order!");
//        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(MainActivity.this, "Your Order is been Deliveried", Toast.LENGTH_SHORT).show();
//            }
//        });
//        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        }).show();
        //Toast.makeText(this, "You has been order " + quantity + " Cup of coffee and you must paid $"+ price + ". Thank you for your order!!! See you in next order!!!", Toast.LENGTH_LONG).show();

        return priceMessage;
    }
}

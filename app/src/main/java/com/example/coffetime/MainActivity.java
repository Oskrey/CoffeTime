package com.example.coffetime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RadioGroup rg = findViewById(R.id.Coffe);
        rg.setOnCheckedChangeListener((group, checkedId) -> {
            if (rg.getCheckedRadioButtonId() == -1)
            {
                // no radio buttons are checked
            }
            else
            {
                for(int i =0; i<drinkNames.length;i++)
                {
                    if(checkedId == drinkNames[i])
                     price = drinkPrices[i];
                }
                RadioButton radioButton = findViewById(checkedId);
                x = (String) radioButton.getText();
            }

        });
    }
    public void make(View view)
    {}
    int price= 0;
    String x= "";
    int moneyAmount = 0;
    boolean firstCoffe=false;
    public void getMoney(View view)
    {
        firstCoffe = true;
        TextView tw = findViewById(R.id.balance);
        Random random = new Random();
        moneyAmount = random.nextInt(25)*10;
        tw.setText(getString(R.string.inserted_money)+ moneyAmount + "₽");
        coffe();
    }
    int[] drinkPrices = {130, 80, 100, 140, 250};
    int[] drinkNames = {R.id.Сappuccino, R.id.Espresso,R.id.Americano ,R.id.Latte , R.id.HotChocolate};
    public void coffe()
    {



        for (int i = 0; i < drinkNames.length; i++)
        {
            RadioButton radioButton = findViewById(drinkNames[i]);
            radioButton.setVisibility(View.INVISIBLE);
            radioButton.setChecked(false);
        }
        TextView tw = findViewById(R.id.textView3);
        tw.setText("");
        x = "";
        price = 0;

        boolean canBuyAnything = false;
        for (int i = 0; i < drinkPrices.length; i++) {
            if (moneyAmount >= drinkPrices[i]) {
            RadioButton radioButton = findViewById(drinkNames[i]);
            radioButton.setVisibility(View.VISIBLE);
                canBuyAnything = true;
            }
        }
        if (!canBuyAnything) {
            tw.setText("Недостаточно средств :( Изучайте Java и зарабатывайте много!))");

        }
    }
    public void brew(View view)
    {
        if (x == "")
        {
            Toast.makeText(getApplicationContext(), "Выберите кофе", Toast.LENGTH_SHORT).show();
        }
        else
        {
            if (firstCoffe)
            {
                TextView tw = findViewById(R.id.textView3);
                TextView twb = findViewById(R.id.balance);
                moneyAmount -= price;
                tw.setText("Ваш "+ x + " готов. \nСдача "+ moneyAmount+"₽" );
                twb.setText(getString(R.string.inserted_money)+ 0 + "₽");
                firstCoffe = false;
            }
            else {
                Toast.makeText(getApplicationContext(), "Внесите деньги", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
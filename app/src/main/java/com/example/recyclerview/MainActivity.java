package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    int count;
    double[] arr = new double[20];
    List<item> items;
    double startingValue;
    double jump;
    Button btnUpdate;
    RecyclerView rv;
    TextView index, sum;
    EditText startingVal, interval;
    ToggleButton tb;
    CustomAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        count = 0;
        items = new ArrayList<>();
        rv = findViewById(R.id.recyclerView);
        startingVal = findViewById(R.id.editText1);
        interval = findViewById(R.id.editText2);
        index = findViewById(R.id.textView1);
        sum = findViewById(R.id.textView2);
        tb = findViewById(R.id.toggleButton);
        btnUpdate = findViewById(R.id.btnUpdate);

        rv.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new CustomAdapter(getApplicationContext(), items, new ItemClickListener() {
            @Override
            public void onItemClick(int position)
            {
                if(count > 0)
                {
                    index.setText(Integer.toString(position + 1));
                    double doubleSum = arr[0];
                    for(int i = 0; i < position; i++)
                    {
                        doubleSum += arr[i];
                    }
                    sum.setText(Double.toString(doubleSum));
                }
            }
        });
        rv.setAdapter(myAdapter);
        initList();
    }

    private void initList()
    {
        for (int i = 0; i < 20 ; i++)
        {
            items.add(new item(""));
        }
    }

    public int e (String str)
    {
        int s = str.indexOf(".");
        int e = str.indexOf("E");
        if(e == -1)
        {
            return (str.substring(s + 1).length());
        }
        else
        {
            return (str.substring(s + 1).length() + Integer.parseInt(str.substring(e + 1)));
        }
    }

    @SuppressLint("DefaultLocale")
    public void updateList(View view)
    {
        myAdapter.notifyDataSetChanged();
        String startingValueStr = startingVal.getText().toString();
        String jumpStr = interval.getText().toString();
        if(startingValueStr.equals("") || startingValueStr.equals(".") || startingValueStr.equals("-") || startingValueStr.equals(" ") || startingValueStr.equals("-."))
        {
            Toast.makeText(this, "invalid starting value entered please enter and try again", Toast.LENGTH_SHORT).show();
        }
        else if(jumpStr.equals("") || jumpStr.equals(".") || jumpStr.equals("-") || jumpStr.equals(" ") || jumpStr.equals("-."))
        {
            Toast.makeText(this, "invalid adder/multiplier entered please enter and try again", Toast.LENGTH_SHORT).show();
        }
        else
        {

            startingValue = Double.parseDouble(startingValueStr);
            jump = Double.parseDouble(jumpStr);
            if(jump == 0)
            {
                Toast.makeText(this, "you cant have 0 as adder or multiplier please enter a new one", Toast.LENGTH_SHORT).show();
            }
            else if(tb.isChecked() && startingValue == 0)
            {
                Toast.makeText(this, "you can't have a starting value 0 on geometrical series", Toast.LENGTH_SHORT).show();
            }
            else
            {
                count++;
                String str = "";
                int check = 0;
                double num = 0;
                if (!tb.isChecked())
                {
                    for (int i = 0 ; i < 20; i++)
                    {
                        num = startingValue + (i * jump);
                        check = e(Double.toString(num));
                        arr[i] = num;
                        str = String.format("%.02f", arr[i]);
                        if(check > 2)
                        {
                            items.set(i, new item(str + "E" + Integer.toString(check - 2)));
                        }
                        else
                        {
                            items.set(i, new item(str));
                        }
                    }
                }
                else
                {
                    for (int i = 1; i < 20; i++)
                    {
                        num = startingValue * (Math.pow(jump, i));
                        arr[i] = num;
                        check = e(Double.toString(num));
                        str = String.format("%.02f", arr[i]);
                        if(check > 2)
                        {
                            items.set(i, new item(str + "E" + Integer.toString(check - 2)));
                        }
                        else
                        {
                            items.set(i, new item(str));
                        }
                    }
                }
            }
        }
    }
}
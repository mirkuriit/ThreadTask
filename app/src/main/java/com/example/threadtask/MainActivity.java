package com.example.threadtask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;




public class MainActivity extends AppCompatActivity {
    EditText a, b, c, d;
    Button doButton;
    TextView first_stream, second_stream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        a = findViewById(R.id.a);
        b = findViewById(R.id.b);
        c = findViewById(R.id.c);
        d = findViewById(R.id.d);
        doButton = findViewById(R.id.do_it);
        doButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a_int = Integer.parseInt(a.getText().toString());
                int b_int = Integer.parseInt(b.getText().toString());
                int c_int = Integer.parseInt(c.getText().toString());
                int d_int = Integer.parseInt(d.getText().toString());
                class AnotherThread extends Thread{
                    @Override
                    public void run() {
                        double D_ss = c_int*c_int-4*b_int*d_int;
                        double x1_ss = (-c_int-Math.sqrt(D_ss))/(2*b_int);
                        double x2_ss = (-c_int+Math.sqrt(D_ss))/(2*b_int);
                        second_stream = findViewById(R.id.second_stream);
                        if (x1_ss == x2_ss){
                            second_stream.setText("Второй поток x = " + x1_ss);
                        }else if (Double.toString(x1_ss) == "NaN" || Double.toString(x2_ss)  == "NaN"){
                            second_stream.setText("Второй поток: это нерешаемо");
                        } else
                            second_stream.setText("Второй поток: x1 = " + x1_ss + " и " + "x2 = "+ x2_ss);
                    }
                }
                AnotherThread second_stream = new AnotherThread();
                second_stream.run();
                double D_ss = b_int*b_int-4*a_int*c_int;
                double x1_ss = (-b_int-Math.sqrt(D_ss))/(2*a_int);
                double x2_ss = (-b_int+Math.sqrt(D_ss))/(2*a_int);
                first_stream = findViewById(R.id.first_stream);
                if (x1_ss == x2_ss){
                    first_stream.setText("Основной поток: x = " + x1_ss);
                }else if (Double.toString(x1_ss) == "NaN" || Double.toString(x2_ss)  == "NaN"){
                    first_stream.setText("Основной поток: это нерешаемо");
                } else
                    first_stream.setText("Основной поток: x1 = " + x1_ss + " и " + "x2 = "+ x2_ss);
            }
        });
    }
}
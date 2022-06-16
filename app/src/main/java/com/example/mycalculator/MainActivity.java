package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etN1, etN2, Operation;
    private TextView tvResult;
    private Button btnCalculate;
    private Toast toastError = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //it's our file in layout - activity_main.xml
        etN1 = findViewById(R.id.FirstNumberInput);
        etN2 = findViewById(R.id.SecondNumberInput);
        Operation = findViewById(R.id.OperationInput);
        tvResult = findViewById(R.id.ResultOutput);
        btnCalculate = findViewById(R.id.CalculateButton);
        btnCalculate.setOnClickListener(this); // we use MainActivity as listener for this button!
    }
    private void showToast(int resId) {
        int duration = Toast.LENGTH_SHORT;
        if (toastError != null) toastError.cancel();
        toastError = Toast.makeText(this, resId, duration);
        toastError.show();
    }
    @Override
    public void onClick(View view) {
        float n1, n2, result = 0;
        String operation = Operation.getText().toString();
        try {
            n1 = Float.parseFloat(etN1.getText().toString());
            n2 = Float.parseFloat(etN2.getText().toString());


        switch (operation) {
            case "+" : result = n1 + n2; break;
            case "-" : result = n1 - n2; break;
            case "*" : result = n1 * n2; break;
            case "/" : {
                if (n2 == 0) throw new ArithmeticException();
                result = n1 / n2;
            }
            default: break;
            }
        } catch (java.lang.NumberFormatException e) {
            showToast(R.string.wrong_data);
            return;
        } catch (ArithmeticException e) {
            showToast(R.string.divide_by_zero);
            return;
        }
        tvResult.setText (n1+ operation + n2 +" = "  + result);
    }
}
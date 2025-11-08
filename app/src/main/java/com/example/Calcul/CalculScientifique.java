package com.example.Calcul;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CalculScientifique extends AppCompatActivity {
    private Button button0, button1, button2, button3, button4, button5,
            button6, button7, button8, button9;
    private Button buttonPlus, buttonEqual, buttonDel, buttonMoin, buttonMult, buttonDiv,btnSin,btnCos,btnTan,btnLn,btnExp,btnCe,btnvirgule;
    private TextView textView, tv1;


    private double firstNumber = 0;
    private String operator = "";
    private boolean isNewOperation = true;
    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.calc_standard) {
            // 👇 this line opens the new page (ScientificActivity)
            Intent intent = new Intent( CalculScientifique.this,CalculActivity.class);
            startActivity(intent);
            return true;
        }
        if (item.getItemId() == R.id.cal_langeur) {
            // 👇 this line opens the new page (standartActivity)
            Intent intent = new Intent(CalculScientifique.this, longueur.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcul_scientifique);
        textView = findViewById(R.id.textView);
        tv1 = findViewById(R.id.textView1);

        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        buttonPlus = findViewById(R.id.buttonPlus);
        buttonEqual = findViewById(R.id.buttonEqual);
        buttonDel = findViewById(R.id.buttonDel);
        buttonMoin = findViewById(R.id.buttonMin);
        buttonMult = findViewById(R.id.buttonMult);
        buttonDiv = findViewById(R.id.buttonDiv);
        btnCos = findViewById(R.id.btnCos);
        btnSin = findViewById(R.id.btnSin);
        btnTan = findViewById(R.id.btnTan);
        btnLn= findViewById(R.id.btnLn);
        btnExp=findViewById(R.id.btnExp);
        btnCe=findViewById(R.id.buttonCe);
        btnvirgule = findViewById(R.id.buttonVirgule);

        // --- BOUTONS NUMÉRIQUES ---
        Button[] buttons = {button0, button1, button2, button3, button4, button5, button6, button7, button8, button9};
        for (int i = 0; i < buttons.length; i++) {
            int finalI = i;
            buttons[i].setOnClickListener(v -> {
                if (isNewOperation || textView.getText().toString().equals("0")) {
                    textView.setText(String.valueOf(finalI));
                    isNewOperation = false;
                } else {
                    textView.append(String.valueOf(finalI));
                }
            });
        }

        // --- BOUTON PLUS ---
        buttonPlus.setOnClickListener(v -> {
            prepareOperation("+");
        });

        // --- BOUTON MOINS ---
        buttonMoin.setOnClickListener(v -> {
            prepareOperation("-");
        });

        // --- BOUTON MULTIPLICATION ---
        buttonMult.setOnClickListener(v -> {
            prepareOperation("×");
        });

        // --- BOUTON DIVISION ---
        buttonDiv.setOnClickListener(v -> {
            prepareOperation("÷");
        });
        // --- BOUTON ln ---
        btnLn.setOnClickListener(v -> {
            String input = textView.getText().toString();
            if (input.isEmpty()) {
                textView.setText("Erreur");
                return;
            }

            try {
                double value = Double.parseDouble(input);

                if (value <= 0) {
                    textView.setText("Erreur");
                    tv1.setText("ln(" + value + ") impossible");
                    return;
                }

                double result = Math.log(value);
                tv1.setText("ln(" + value + ")");
                textView.setText(String.valueOf(result));
                isNewOperation = true;

            } catch (NumberFormatException e) {
                textView.setText("Erreur");
            }
        });
        btnCos.setOnClickListener(v -> {
            String input = textView.getText().toString();
            if (input.isEmpty()) {
                textView.setText("Erreur");
                return;
            }
            try {
                double value = Double.parseDouble(input);
                double result = Math.cos(value);
                tv1.setText("Cos(" + value + ")");
                textView.setText(String.valueOf(result));
                isNewOperation = true;
            } catch (NumberFormatException e) {
                textView.setText("Erreur");
            }
        });
        btnSin.setOnClickListener(v -> {
            String input = textView.getText().toString();
            if (input.isEmpty()) {
                textView.setText("Erreur");
                return;
            }
            try {
                double value = Double.parseDouble(input);
                double result = Math.sin(value);
                tv1.setText("Sin(" + value + ")");
                textView.setText(String.valueOf(result));
                isNewOperation = true;
            } catch (NumberFormatException e) {
                textView.setText("Erreur");
            }
        });
        btnTan.setOnClickListener(v -> {
            String input = textView.getText().toString();
            if (input.isEmpty()) {
                textView.setText("Erreur");
                return;
            }
            try {
                double value = Double.parseDouble(input);
                double result = Math.tan(value);
                tv1.setText("Tan(" + value + ")");
                textView.setText(String.valueOf(result));
                isNewOperation = true;
            } catch (NumberFormatException e) {
                textView.setText("Erreur");
            }
        });btnExp.setOnClickListener(v -> {
            String input = textView.getText().toString();
            if (input.isEmpty()) {
                textView.setText("Erreur");
                return;
            }
            try {
                double value = Double.parseDouble(input);
                double result = Math.exp(value);
                tv1.setText("exp(" + value + ")");
                textView.setText(String.valueOf(result));
                isNewOperation = true;
            } catch (NumberFormatException e) {
                textView.setText("Erreur");
            }
        });




        // --- BOUTON ÉGAL ---
        buttonEqual.setOnClickListener(v -> {
            double secondNumber = Double.parseDouble(textView.getText().toString());
            double result = 0;

            switch (operator) {
                case "+":
                    result = firstNumber + secondNumber;
                    break;
                case "-":
                    result = firstNumber - secondNumber;
                    break;
                case "×":
                    result = firstNumber * secondNumber;
                    break;
                case "÷":
                    if (secondNumber != 0) {
                        result = firstNumber / secondNumber;
                    } else {
                        textView.setText("Erreur");
                        tv1.setText(firstNumber + " ÷ " + secondNumber);
                        return;
                    }
                    break;
            }

            tv1.setText(firstNumber + " " + operator + " " + secondNumber + " =");
            textView.setText((result == (int) result) ? String.valueOf((int) result) : String.valueOf(result));

            firstNumber = result;
            operator = "";
            isNewOperation = true;
        });


        // --- BOUTON DEL ---
        buttonDel.setOnClickListener(v -> {
            textView.setText("0");
            tv1.setText("");
            firstNumber = 0;
            operator = "";
            isNewOperation = true;
        });
        btnCe.setOnClickListener(v -> {
            textView.setText("0");
            isNewOperation = true;
        });
        btnvirgule.setOnClickListener(v -> {
            String current = textView.getText().toString();
            if (!current.contains(".")) {
                textView.append(".");
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void prepareOperation(String newOperator) {
        double currentNumber = Double.parseDouble(textView.getText().toString());

        if (!operator.isEmpty()) {
            // Compute the previous operation first
            switch (operator) {
                case "+":
                    firstNumber = firstNumber + currentNumber;
                    break;
                case "-":
                    firstNumber = firstNumber - currentNumber;
                    break;
                case "×":
                    firstNumber = firstNumber * currentNumber;
                    break;
                case "÷":
                    if (currentNumber != 0) {
                        firstNumber = firstNumber / currentNumber;
                    } else {
                        textView.setText("Erreur");
                        tv1.setText(firstNumber + " ÷ " + currentNumber);
                        operator = "";
                        return;
                    }
                    break;
            }
        } else {
            // First time pressing an operator
            firstNumber = currentNumber;
        }

        operator = newOperator;
        tv1.setText(firstNumber + " " + operator);
        textView.setText("0");
        isNewOperation = true;
    }

}
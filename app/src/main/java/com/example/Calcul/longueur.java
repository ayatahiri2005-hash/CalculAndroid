package com.example.Calcul;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class longueur extends AppCompatActivity {
    private Button button0, button1, button2, button3, button4, button5,
            button6, button7, button8, button9, buttonDel, buttonCe,btnvirgule;
    private TextView metre, km, pouce, pied;

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.calc_standard) {
            startActivity(new Intent(longueur.this, CalculActivity.class));
            return true;
        } else if (item.getItemId() == R.id.calc_scientifique) {
            startActivity(new Intent(longueur.this, CalculScientifique.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Helper: safe parse double (returns 0 if not parseable)
    private double safeParse(TextView tv) {
        String s = tv.getText().toString().trim();
        if (s.isEmpty()) return 0d;
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e) {
            return 0d;
        }
    }

    // Helper: format double, remove trailing zeros
    private String fmt(double v) {
        // use 6 decimal places max, remove trailing zeros
        String s = String.format(Locale.US, "%.6f", v);
        // remove trailing zeros and possible trailing dot
        s = s.replaceAll("\\.?0+$", "");
        if (s.isEmpty()) s = "0";
        return s;
    }

    // Convert from the given unit to all others
    private void convertFrom(String unit, double value) {
        // unit: "m", "km", "in" (pouce), "ft" (pied)
        double m = 0d;
        switch (unit) {
            case "m":
                m = value;
                break;
            case "km":
                m = value * 1000.0; // 1 km = 1000 m
                break;
            case "in": // pouce
                m = value / 39.3701; // 1 m = 39.3701 in
                break;
            case "ft": // pied
                m = value / 3.28084; // 1 m = 3.28084 ft
                break;
        }
        double kmVal = m / 1000.0;
        double inVal = m * 39.3701;
        double ftVal = m * 3.28084;

        // Update TextViews, but avoid overwriting the active field's value (we still set them to formatted value)
        metre.setText(fmt(m));
        km.setText(fmt(kmVal));
        pouce.setText(fmt(inVal));
        pied.setText(fmt(ftVal));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_longueur);

        metre = findViewById(R.id.m);
        km = findViewById(R.id.km);
        pouce = findViewById(R.id.pouce);
        pied = findViewById(R.id.pied);

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
        buttonDel = findViewById(R.id.buttonDel);
        buttonCe = findViewById(R.id.buttonCe);
        btnvirgule =findViewById(R.id.buttonVirgule);

        // Ensure initial text is "0" (avoids parsing problems)
        metre.setText("0");
        km.setText("0");
        pouce.setText("0");
        pied.setText("0");

        // Track active TextView (which receives numeric input). Default = metre
        final TextView[] active = {metre};


        View.OnClickListener makeActive = v -> {
            // set active
            active[0] = (TextView) v;

            metre.setAlpha(active[0] == metre ? 1f : 0.7f);
            km.setAlpha(active[0] == km ? 1f : 0.7f);
            pouce.setAlpha(active[0] == pouce ? 1f : 0.7f);
            pied.setAlpha(active[0] == pied ? 1f : 0.7f);
        };

        metre.setOnClickListener(makeActive);
        km.setOnClickListener(makeActive);
        pouce.setOnClickListener(makeActive);
        pied.setOnClickListener(makeActive);

        // numeric buttons
        Button[] buttons = {button0, button1, button2, button3, button4, button5, button6, button7, button8, button9};
        for (int i = 0; i < buttons.length; i++) {
            int digit = i;
            buttons[i].setOnClickListener(v -> {
                TextView tv = active[0];
                String cur = tv.getText().toString();
                // if current is "0", replace it; otherwise append
                if (cur.equals("0")) {
                    tv.setText(String.valueOf(digit));
                } else {
                    tv.setText(cur + digit);
                }

                // convert immediately after updating the active field
                // determine which unit is active
                if (tv == metre) {
                    convertFrom("m", safeParse(metre));
                } else if (tv == km) {
                    convertFrom("km", safeParse(km));
                } else if (tv == pouce) {
                    convertFrom("in", safeParse(pouce));
                } else if (tv == pied) {
                    convertFrom("ft", safeParse(pied));
                }
            });
        }
        btnvirgule.setOnClickListener(v -> {
            TextView tv = active[0];
            String current = tv.getText().toString();
            if (!current.contains(".")) {
                tv.append(".");
            }
        });

        // DEL: delete last char
        buttonDel.setOnClickListener(v -> {
            TextView tv = active[0];
            String cur = tv.getText().toString();
            if (cur.length() <= 1) {
                tv.setText("0");
            } else {
                tv.setText(cur.substring(0, cur.length() - 1));
            }
            // convert
            if (tv == metre) convertFrom("m", safeParse(metre));
            else if (tv == km) convertFrom("km", safeParse(km));
            else if (tv == pouce) convertFrom("in", safeParse(pouce));
            else if (tv == pied) convertFrom("ft", safeParse(pied));
        });

        // CE: clear (reset active to 0)
        buttonCe.setOnClickListener(v -> {
            TextView tv = active[0];
            tv.setText("0");
            // convert
            if (tv == metre) convertFrom("m", 0);
            else if (tv == km) convertFrom("km", 0);
            else if (tv == pouce) convertFrom("in", 0);
            else if (tv == pied) convertFrom("ft", 0);
        });

        // Insets padding (unchanged)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        metre.setOnLongClickListener(v -> {
            convertFrom("m", safeParse(metre));
            return true;
        });
        km.setOnLongClickListener(v -> {
            convertFrom("km", safeParse(km));
            return true;
        });
        pouce.setOnLongClickListener(v -> {
            convertFrom("in", safeParse(pouce));
            return true;
        });
        pied.setOnLongClickListener(v -> {
            convertFrom("ft", safeParse(pied));
            return true;
        });
    }
}

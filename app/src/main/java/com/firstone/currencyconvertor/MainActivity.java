package com.firstone.currencyconvertor;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void calc(View view)
    {
        EditText dollar = (EditText) findViewById(R.id.dollar);
        double doll= Double.valueOf(dollar.getText().toString());
        doll=doll*71.0;
        String s =Double.toString(doll);
        Log.i("info",Double.toString(doll));

        Toast.makeText(MainActivity.this,String.format("%.2f",doll) , Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

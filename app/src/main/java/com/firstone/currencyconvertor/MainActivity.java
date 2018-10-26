package com.firstone.currencyconvertor;

        import android.os.AsyncTask;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.Toast;

        import org.json.JSONException;
        import org.json.JSONObject;

        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.net.HttpURLConnection;
        import java.net.URL;
        import java.util.concurrent.ExecutionException;


class DownloadTask extends AsyncTask<String, Void,String>{

            @Override
            protected String doInBackground(String... urls) {
                Log.i("content","In Asynctask");
                URL url;
                HttpURLConnection connection =null;
                String result="";
                try {
                    url = new URL(urls[0]);
                    connection=(HttpURLConnection) url.openConnection();
                    InputStream inputStream = connection.getInputStream();
                    InputStreamReader reader = new InputStreamReader(inputStream);
                    int data=reader.read();
                    while(data!=-1){
                        char ch=(char) data;
                        result = result + ch;
                        data=reader.read();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return result;
            }

}
public class MainActivity extends AppCompatActivity {
    String content;
    float val;
    public void calc(View view)
    {
        EditText rupee=findViewById(R.id.rupee);

        EditText dollar = (EditText) findViewById(R.id.dollar);
        double doll= Double.valueOf(dollar.getText().toString());
        doll=doll*val;
        String s;
        s=String.format("%.2f",doll);
        rupee.setText(s);

        Toast.makeText(MainActivity.this,s, Toast.LENGTH_LONG).show();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DownloadTask task =new DownloadTask();
        try {
            String content= task.execute("http://free.currencyconverterapi.com/api/v5/convert?q=USD_INR&compact=y").get();
            Log.i("content",content);
            try {
                JSONObject jsonObject=new JSONObject(content);
                String curr=jsonObject.getString("USD_INR");
                Log.i("content",curr);
                JSONObject jsonObject1=new JSONObject(curr);
                val=(float)jsonObject1.getDouble("val");
                Log.i("content",Double.toString(val));

            } catch (JSONException e) {
                e.printStackTrace();
                Log.i("content","error");
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}

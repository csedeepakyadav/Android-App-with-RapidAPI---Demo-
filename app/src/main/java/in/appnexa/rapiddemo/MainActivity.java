package in.appnexa.rapiddemo;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button getFactBtn = findViewById(R.id.btn);



        getFactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetYearFact();
            }
        });









    }


    public void GetYearFact()

    {

        EditText EnterYear = findViewById(R.id.yearFld);
        final String urlYear = EnterYear.getText().toString();


        String url = "https://numbersapi.p.rapidapi.com/"+urlYear+"/year?fragment=true&json=true";

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        String uri = Uri.parse(url)
                .buildUpon()
                .build().toString();

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, uri, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("VolleyResponse", "response: " + response);

              //  String result = response.toString();

                try {
                    JSONObject json= (JSONObject) new JSONTokener(response).nextValue();
                //    JSONObject json2 = json.getJSONObject("contact");

               //     String Year = (String) json.get("number");
              //      String Date = (String) json.get("date");
                    String Fact = (String) json.get("text");


                 //   String finalYear = "Year :"+"\n \n"+ Year;
                //    String finalDate = "Date :"+"\n \n"+ Date;
                    String finalFact = "Fact :"+"\n \n"+ Fact;

                    TextView factYear = findViewById(R.id.date);
                  //  TextView factDate = findViewById(R.id.year);
                    TextView factTxt = findViewById(R.id.fact);

                    factYear.setText(urlYear);
                  //  factDate.setText(finalDate);
                    factTxt.setText(finalFact);


                } catch (JSONException e) {
                    e.printStackTrace();
                }






            }
        }, new Response.ErrorListener() {


            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VolleyError", error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<>();
                params.put("X-RapidAPI-Host", "numbersapi.p.rapidapi.com");
                params.put("X-RapidAPI-Key", "090507531dmsha51977b3fa0abc2p1881d2jsnb302db3a7752");
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}

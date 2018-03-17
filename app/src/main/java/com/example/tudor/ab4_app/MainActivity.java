package com.example.tudor.ab4_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {

    Button dev_1, dev_2, dev_3, dev_4, dev_5, dev_6, dev_7, dev_8, dev_9 ,dev_10;
    ImageView img_1, img_2, img_3, img_4, img_5, img_6, img_7, img_8, img_9, img_10;
    String dev_name = "", dev_picture = "", dev_location = "", dev_badges = "";
    int button_clicked_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String BASE_URL = "https://api.stackexchange.com";
        String USER_INFO = "/2.2/users?order=desc&sort=reputation&site=stackoverflow";

        //initializare butoane si imagini
        dev_1 = (Button) findViewById(R.id.button1);
        dev_2 = (Button) findViewById(R.id.button2);
        dev_3 = (Button) findViewById(R.id.button3);
        dev_4 = (Button) findViewById(R.id.button4);
        dev_5 = (Button) findViewById(R.id.button5);
        dev_6 = (Button) findViewById(R.id.button6);
        dev_7 = (Button) findViewById(R.id.button7);
        dev_8 = (Button) findViewById(R.id.button8);
        dev_9 = (Button) findViewById(R.id.button9);
        dev_10 = (Button) findViewById(R.id.button);

        img_1 = (ImageView) findViewById(R.id.image1);
        img_2 = (ImageView) findViewById(R.id.image2);
        img_3 = (ImageView) findViewById(R.id.image3);
        img_4 = (ImageView) findViewById(R.id.image4);
        img_5 = (ImageView) findViewById(R.id.image5);
        img_6 = (ImageView) findViewById(R.id.image6);
        img_7 = (ImageView) findViewById(R.id.image7);
        img_8 = (ImageView) findViewById(R.id.image8);
        img_9 = (ImageView) findViewById(R.id.image9);
        img_10 = (ImageView) findViewById(R.id.image10);


        dev_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button_clicked_id = 1; //pentru identificarea developerului in urmatorul screen
                btnclick(view);
            }
        });
        dev_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button_clicked_id = 2;
                btnclick(view);
            }
        });
        dev_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button_clicked_id = 3;
                btnclick(view);
            }
        });
        dev_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button_clicked_id = 4;
                btnclick(view);
            }
        });
        dev_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button_clicked_id = 5;
                btnclick(view);
            }
        });
        dev_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button_clicked_id = 6;
                btnclick(view);
            }
        });
        dev_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button_clicked_id = 7;
                btnclick(view);
            }
        });
        dev_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button_clicked_id = 8;
                btnclick(view);
            }
        });
        dev_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button_clicked_id = 9;
                btnclick(view);

            }
        });
        dev_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button_clicked_id = 10;
                btnclick(view);
            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.GET,
                BASE_URL + USER_INFO,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONArray info_array = response.getJSONArray("items");
                            //parsarea obiectelor Json cu libraria Volley
                            //formez un string pentru fiecare tip de informatie
                            //si le despart prin caracterul "@"
                            for (int i = 0; i < 10; i++) {
                                JSONObject item = info_array.getJSONObject(i);
                                String display_name = item.getString("display_name");
                                dev_name = dev_name.concat(display_name + "@");
                                String profile_image = item.getString("profile_image");
                                dev_picture = dev_picture.concat(profile_image + "@");
                                String location = item.getString("location");
                                dev_location = dev_location.concat(location + "@");
                                String badge_counts = item.getString("badge_counts");
                                dev_badges = dev_badges.concat(badge_counts + "@");
                                Log.d("oooooo", dev_badges);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        String[] btn_text = parse_dev_info(dev_name);
                        //setez numele fiecarui utilizator in UI
                        dev_1.setText(btn_text[0]);
                        dev_2.setText(btn_text[1]);
                        dev_3.setText(btn_text[2]);
                        dev_4.setText(btn_text[3]);
                        dev_5.setText(btn_text[4]);
                        dev_6.setText(btn_text[5]);
                        dev_7.setText(btn_text[6]);
                        dev_8.setText(btn_text[7]);
                        dev_9.setText(btn_text[8]);
                        dev_10.setText(btn_text[9]);

                        String[] pictures_url = parse_dev_info(dev_picture);
                        //setez poza fiecarui utilizator in UI
                        Picasso.with(MainActivity.this).load(pictures_url[0]).into(img_1);
                        Picasso.with(MainActivity.this).load(pictures_url[1]).into(img_2);
                        Picasso.with(MainActivity.this).load(pictures_url[2]).into(img_3);
                        Picasso.with(MainActivity.this).load(pictures_url[3]).into(img_4);
                        Picasso.with(MainActivity.this).load(pictures_url[4]).into(img_5);
                        Picasso.with(MainActivity.this).load(pictures_url[5]).into(img_6);
                        Picasso.with(MainActivity.this).load(pictures_url[6]).into(img_7);
                        Picasso.with(MainActivity.this).load(pictures_url[7]).into(img_8);
                        Picasso.with(MainActivity.this).load(pictures_url[8]).into(img_9);
                        Picasso.with(MainActivity.this).load(pictures_url[9]).into(img_10);


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }


        ) {
            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                try {
                    //itializez cache-ul pe memoria ram
                    Cache.Entry cacheEntry = HttpHeaderParser.parseCacheHeaders(response);
                    if (cacheEntry == null) {
                        cacheEntry = new Cache.Entry();
                    }
                    final long cache_refresh = 3 * 60 * 1000; // odata la 3 minute refresh cache ram
                    final long cache_expired = 60 * 60 * 1000 / 6; // in 10 minute expira cache ram
                    long now = System.currentTimeMillis();
                    final long cache_refreshed_expiration = now + cache_refresh;
                    final long ttl = now + cache_expired;
                    cacheEntry.data = response.data;
                    cacheEntry.softTtl = cache_refreshed_expiration;
                    cacheEntry.ttl = ttl;
                    String header_value;
                    header_value = response.headers.get("Date");
                    if (header_value != null) {
                        cacheEntry.serverDate = HttpHeaderParser.parseDateAsEpoch(header_value);
                    }
                    header_value = response.headers.get("Last-Modified");
                    if (header_value != null) {
                        cacheEntry.lastModified = HttpHeaderParser.parseDateAsEpoch(header_value);
                    }
                    cacheEntry.responseHeaders = response.headers;
                    final String jsonString = new String(response.data,
                            HttpHeaderParser.parseCharset(response.headers));
                    return Response.success(new JSONObject(jsonString), cacheEntry);
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                } catch (JSONException e) {
                    return Response.error(new ParseError(e));
                }
            }


        };

        queue.add(objectRequest);

    }

    public void btnclick(View view) {
        //la  apasarea oricarui buton trimit utilizaorul la urmatorul screen
        //si transmit toate stringurile cu informatia parsata
        Intent screen2 = new Intent(MainActivity.this, InformationActivity.class);
        screen2.putExtra("name", dev_name);
        screen2.putExtra("picture", dev_picture);
        screen2.putExtra("location", dev_location);
        screen2.putExtra("badge", dev_badges);
        screen2.putExtra("dev_id", button_clicked_id);
        startActivity(screen2);
    }

    public String[] parse_dev_info(String info) {
        String string = info;
        String[] parts = string.split("@");
        return parts;
    }

}




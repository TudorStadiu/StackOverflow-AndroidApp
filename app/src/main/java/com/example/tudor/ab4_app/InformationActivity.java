package com.example.tudor.ab4_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class InformationActivity extends AppCompatActivity {
    String dev_name, dev_picture, dev_location, dev_badge;
    int dev_id;
    ImageView image;
    TextView name, location, badges;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        Bundle bundle = getIntent().getExtras();
         dev_name = bundle.getString("name");
         dev_picture = bundle.getString("picture");
         dev_location = bundle.getString("location");
         dev_badge = bundle.getString("badge");
         dev_id = bundle.getInt("dev_id");

        image = (ImageView) findViewById(R.id.d_image);
        name = (TextView) findViewById(R.id.text_name);
        location = (TextView) findViewById(R.id.text_location);
        badges = (TextView) findViewById(R.id.text_badge);

        String[] pictures_url = parse_dev_info(dev_picture);
        Picasso.with(InformationActivity.this).load(pictures_url[dev_id-1]).into(image);

        String[] btn_text_name = parse_dev_info(dev_name);
        name.append(btn_text_name[dev_id-1]);

        String[] btn_text_location = parse_dev_info(dev_location);
        location.append(btn_text_location[dev_id-1]);

        String bdg = parse_dev_badges(dev_badge);
        String[] btn_text_badge = parse_dev_info(bdg);
        if(dev_id == 1)
            badges.append("\n"+
                    "\n"+"Bronze: "+btn_text_badge[dev_id]+
                    "\n"+"Silver: "+btn_text_badge[dev_id+1]+
                    "\n"+"Gold: "+btn_text_badge[dev_id+2]);
        else
            badges.append("\n"+
                    "\n"+"Bronze: "+btn_text_badge[3*dev_id - 2]+
                    "\n"+"Silver: "+btn_text_badge[3*dev_id - 1]+
                    "\n"+"Gold: "+btn_text_badge[3*dev_id]);
    }

    public String[] parse_dev_info(String info) {
        String string = info;
        String[] parts = string.split("@");
        return parts;
    }

    public String parse_dev_badges(String info) {
        String string = info;
        string = string.replaceAll("\\D+","@");

       return string;
    }




}

package com.example.homepals_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.location.Address;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class add_page extends AppCompatActivity {
    private SupportMapFragment map;
    private GoogleMap gMap;
    private final int GALLERY_REQ_CODE = 1000;
    private static final int MAP_REQUEST_CODE = 2000;
    EditText Mylocation;
    Button getlocation;
    ImageView imagePet;
    Button location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_add_page);

        //gridlaout ส่วนหัวที่มี ปุ่ม X , newpost และ post
        TextView closeButton = findViewById(R.id.closeButton);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //ชื่อหน้า New Post
        TextView post = findViewById(R.id.newPost);

        //ยังไม่เสร็จต้องเอาไว้ทำเช็คว่าใส่ข้อความครบหรือไม่
        Button postButton = findViewById(R.id.postButton);
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //closelaout
        //ใส่ภาพจากโทรศัพธ์
        Button imageButton = findViewById(R.id.imageButton);
        imagePet = findViewById(R.id.imageInput);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gallery = new Intent(Intent.ACTION_PICK);
                gallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(gallery,GALLERY_REQ_CODE);
            }
        });

        EditText namePet = findViewById(R.id.namePet);
        namePet.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence text, int i, int i1, int i2) {
                // ตรวจสอบการเปลี่ยนแปลงของข้อความและนำชื่อที่ผู้ใช้ป้อนไปใช้งาน
                String petName = text.toString();
                Log.d("EditText", "Pet name: " + petName);
            }

            @Override
            public void onTextChanged(CharSequence text, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        EditText detailPet = findViewById(R.id.detailPet);
        detailPet.setFilters(new InputFilter[] {
                new InputFilter.LengthFilter(200) // จำกัดจำนวนคำที่พิมพ์ได้ไม่เกิน 200 คำ
        });
        detailPet.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        //เลื่อนบรรทัดให้ 2 บรรทัด
        detailPet.setMaxLines(2);
        detailPet.setVerticalScrollBarEnabled(true);
        //การเลื่อนบรรทัดเมื่อพิมพ์ครบขอบเขตของ EditText จะเป็นการใช้ android:scrollbars
        detailPet.setMovementMethod(new ScrollingMovementMethod());
        detailPet.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        // add location
        TextView mylo = findViewById(R.id.mylo);
        Mylocation = findViewById(R.id.Mylocation);
        getlocation = findViewById(R.id.getlocation);

        getlocation.setOnClickListener(view -> {
            String userLocation = Mylocation.getText().toString();

            if(userLocation.equals("")){
                Toast.makeText(this,"Please enter your location", Toast.LENGTH_SHORT).show();
            } else {
                getYourLo(userLocation);
            }
        });


        //เรียก Button location ให้ไปโชว์แผนที่
        Button addLocationButton = findViewById(R.id.locationButton);
        addLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mapIntent = new Intent(add_page.this, showMap_page.class);
                startActivityForResult(mapIntent, MAP_REQUEST_CODE);
            }
        });

        // Initialize the map fragment
        map = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        //mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        if (map != null) {
            map.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    // ทำสิ่งที่คุณต้องการเมื่อแผนที่พร้อมใช้งาน
                }
            });
        }


        //closelaout
        // add Location
        location = findViewById(R.id.locationButton);
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent map = new Intent(add_page.this, showMap_page.class);
                startActivity(map);
            }
        });

        TextView gender = findViewById(R.id.genderPet);
        gender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //gridlaout
        CheckBox male = findViewById(R.id.malePet);
        CheckBox female = findViewById(R.id.femalePet);

        male.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    // ถ้าเลือก male ให้เคลียร์ femaleCheckBox
                    female.setChecked(false);
                }
            }
        });

        female.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    // ถ้าเลือก female ให้เคลียร์ maleCheckBox
                    male.setChecked(false);
                }
            }
        });

        //closelaout
        EditText color = findViewById(R.id.colorPet);
        color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        EditText breed = findViewById(R.id.breedPet);
        breed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        EditText weight = findViewById(R.id.weightPet);
        weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    //getYourLo
    private void getYourLo(String from){
        try {
            Uri uri = Uri.parse("https://www.google.com/maps/dir/" + from);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage("com.google.android.apps.maps");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivityForResult(intent, MAP_REQUEST_CODE);
        }catch (ActivityNotFoundException exception) {
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps" + from);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivityForResult(intent, MAP_REQUEST_CODE);
        }
    }
    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQ_CODE && resultCode == RESULT_OK && data != null) {
            imagePet.setImageURI(data.getData());
        }

        if (resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            imagePet.setImageURI(selectedImage);

            // Add the marker to the map at the selected location
            if (gMap != null) {
                LatLng location = new LatLng(13.7563, 100.5018); // Example location (Bangkok, Thailand)
                gMap.clear();
                gMap.addMarker(new MarkerOptions().position(location).title("Marker"));
                gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 10));
            }
        }

        if (requestCode == MAP_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // รับข้อมูลที่อยู่จากแผนที่ที่ส่งกลับมา
                String address = data.getStringExtra("address");

                // นำที่อยู่ที่ได้มาแสดงใน EditText Mylocation
                Mylocation.setText(address);
            }
        }

    }
}
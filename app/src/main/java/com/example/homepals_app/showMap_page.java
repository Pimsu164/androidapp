package com.example.homepals_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.SearchView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class showMap_page extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap gMap;
    private SearchView mapSearchView;
    Button addLocationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_map_page);

        //map = findViewById(R.id.map);
        mapSearchView = findViewById(R.id.mapSearch);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        //mapFragment.getMapAsync(this);
        //เรียกใช้ getMapAsync() และกำหนด OnQueryTextListener สำหรับ SearchView
        mapFragment.getMapAsync(showMap_page.this);

        mapSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // ดำเนินการค้นหาและเพิ่มตำแหน่งลงในแผนที่
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // ดำเนินการเมื่อเปลี่ยนข้อความในช่องค้นหา
                return false;
            }
        });

        //Search Map
        mapSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                String location = mapSearchView.getQuery().toString();
                List<Address> addressList = null;

                if(location != null){
                    Geocoder geocoder = new Geocoder(showMap_page.this);

                    try {
                        addressList = geocoder.getFromLocationName(location, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    List<Object> myList = new ArrayList<>();
                    Address address = addressList.get(0);
                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                    gMap.addMarker(new MarkerOptions().position(latLng).title(location));
                    gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));

                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        mapFragment.getMapAsync(showMap_page.this);

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.gMap = googleMap;

        // เมื่อคลิกที่แผนที่เพื่อเลือกที่อยู่ใน PageShowMap
        gMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                // รับที่อยู่จากตำแหน่งที่คลิกบนแผนที่
                String address = getAddressFromLatLng(latLng);

                // ส่งที่อยู่กลับไปใน MainActivity
                Intent resultIntent = new Intent();
                resultIntent.putExtra("address", address);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });

        //ใส่ตำแหน่งของ ประเทศไทย Thailand coordinates
        //LatLng mapThai = new LatLng(15.8700,100.9925);
        //this.gMap.addMarker(new MarkerOptions().position(mapThai).title("Marker in Thailand"));
        //this.gMap.moveCamera(CameraUpdateFactory.newLatLng(mapThai));
    }
    private String getAddressFromLatLng(LatLng latLng) {
        Geocoder geocoder = new Geocoder(this);
        String address = "";

        try {
            List<Address> addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            if (addresses != null && addresses.size() > 0) {
                Address returnedAddress = addresses.get(0);
                StringBuilder sb = new StringBuilder("");
                for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    sb.append(returnedAddress.getAddressLine(i));
                    if (i < returnedAddress.getMaxAddressLineIndex())
                        sb.append(", ");
                }
                address = sb.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return address;
    }
}
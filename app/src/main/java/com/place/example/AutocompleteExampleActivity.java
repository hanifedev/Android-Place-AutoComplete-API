package com.place.example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.Arrays;

public class AutocompleteExampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autocomplete_example);
        String apiKey = getString(R.string.google_maps_key);
        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), apiKey);
        }
        PlacesClient placesClient = Places.createClient(this);

        //autocompletefragment nesnesi oluşturur
        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);
        //döndürülecek yer verisi türlerini belirtir
        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));

        //selected olayı gerçekleştiğinde kod bloğu çalıştırmaya yarar
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {

            //selected işlemi gerçekleşince çalışacak kod bloğu
            @Override
            public void onPlaceSelected(Place place) {
                Toast.makeText(getApplicationContext(), "Place: " + place.getName() + ", " + place.getId(),Toast.LENGTH_LONG).show();
            }

            //hata oluştuğunda çalışacak kod bloğu
            @Override
            public void onError(@NonNull Status status) {
                Toast.makeText(getApplicationContext(),"An error occurred: " + status,Toast.LENGTH_LONG).show();
            }
        });
    }
}

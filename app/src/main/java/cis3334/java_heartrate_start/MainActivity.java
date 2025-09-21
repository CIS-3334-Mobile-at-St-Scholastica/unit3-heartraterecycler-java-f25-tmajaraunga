package cis3334.java_heartrate_start;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cis3334.java_heartrate_start.Heartrate;
import cis3334.java_heartrate_start.MainViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editTextPulse;
    EditText editTextAge;
    EditText editTextDisplay;           // used to display the heart rates from the databas
    // TODO: In Unit 5 will will replace the editText with a RecycleView
    Button buttonInsert;
    MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        editTextAge = findViewById(R.id.editTextAge);
        editTextPulse = findViewById(R.id.editTextPulse);
        editTextDisplay = findViewById(R.id.editTextDisplay);

        setupInsertButton();            // Set up the OnClickListener for the insert button
        setupLiveDataObserver();
    }

    private void setupLiveDataObserver() {
        Log.d("CIS 3334", "MainActivity: setupLiveDataObserver started");
        // Assuming mainViewModel.getAllHeartrates() returns LiveData<List<Heartrate>>
        if (mainViewModel.getAllHeartrates() == null) {
            Log.e("CIS 3334", "MainActivity: mainViewModel.getAllHeartrates() returned NULL!");
            return;
        }

        mainViewModel.getAllHeartrates().observe(this, allHeartrates -> {
            Log.d("CIS 3334", "MainActivity: LiveData observer triggered for allHeartrates.");
            if (allHeartrates != null) {
                Log.d("CIS 3334", "MainActivity: Observed " + allHeartrates.size() + " heartrates.");
                // Pass the observed allHeartrates to the heartrateAdapter
                if (heartrateAdapter != null) {
                    heartrateAdapter.heartrateDataUpdated(allHeartrates); // Call the new method
                } else {
                    Log.w("CIS 3334", "MainActivity: heartrateAdapter is null in observer, cannot update.");
                }
            } else {
                Log.w("CIS 3334", "MainActivity: Observed null list for allHeartrates.");
                // Optionally, clear the adapter or show an empty state
                if (heartrateAdapter != null) {
                    heartrateAdapter.heartrateDataUpdated(new ArrayList<>()); // Pass an empty list
                }
            }
        });
        Log.d("CIS 3334", "MainActivity: LiveData observer set up.");
    }


    /**
     *  Set up the Insert Heartrate button so it adds a new heart rate reading to the database
     */
    private void setupInsertButton() {
        buttonInsert = findViewById(R.id.buttonInsert);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer pulse = Integer.parseInt(editTextPulse.getText().toString());
                Integer age = Integer.parseInt(editTextAge.getText().toString());
                mainViewModel.insert(pulse, age);
            }
        });
    }
}
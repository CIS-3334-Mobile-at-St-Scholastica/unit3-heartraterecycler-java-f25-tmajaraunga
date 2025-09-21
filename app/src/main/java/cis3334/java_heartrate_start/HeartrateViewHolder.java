package cis3334.java_heartrate_start;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

// Assuming your R class is in this package or imported correctly
// If not, you might need: import your.package.name.R;

public class HeartrateViewHolder extends RecyclerView.ViewHolder {

    // Variables for the three TextView widgets
    public TextView pulseValueTextView;
    public TextView rangeValueTextView;
    public TextView descriptionTextView;

    // Constructor
    public HeartrateViewHolder(@NonNull View itemView) {
        super(itemView);

        // Initialize the TextView widgets using itemView.findViewById()
        // Note: I'm using the IDs from the layout XML you provided.
        // If you used textViewPulseLabel for the value by mistake, adjust accordingly.
        // Assuming the value TextViews are:
        // textViewPulseValue
        // textViewRangeValue
        // textViewDescription

        pulseValueTextView = itemView.findViewById(R.id.textViewPulseValue);
        rangeValueTextView = itemView.findViewById(R.id.textViewRangeValue);
        descriptionTextView = itemView.findViewById(R.id.textViewDescription);

        // It seems you also had label TextViews, but the request was for the three
        // TextViews that display the actual data. If you also need references to:
        // TextView pulseLabelTextView = itemView.findViewById(R.id.textViewPulseLabel);
        // TextView rangeLabelTextView = itemView.findViewById(R.id.textViewRangeLabel);
        // ... you can add them here. For now, sticking to the three requested value fields.
    }
}


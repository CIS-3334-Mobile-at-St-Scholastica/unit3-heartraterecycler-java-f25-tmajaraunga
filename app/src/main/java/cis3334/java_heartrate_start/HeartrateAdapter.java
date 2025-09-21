package cis3334.java_heartrate_start;

import android.app.Application;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cis3334.java_heartrate_start.R;
import cis3334.java_heartrate_start.Heartrate;
import cis3334.java_heartrate_start.MainViewModel;

public class HeartrateAdapter extends RecyclerView.Adapter<HeartrateViewHolder> {

    private Application application;
    private Heartrate heartrate;;
    private List<Heartrate> localHeartrateList;

    // Constructor
    public HeartrateAdapter(Application application, Heartrate heartrate) {
        this.application = application;
        this.heartrate = heartrate;
    }

    @NonNull
    @Override
    public HeartrateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        // See: https://developer.android.com/develop/ui/views/layout/recyclerview#implement-adapter
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.heartrate_row, parent, false);
        return new HeartrateViewHolder(view);
    }
    
    public void heartrateDataUpdated(List<Heartrate> newHeartrates) {
        this.localHeartrateList = newHeartrates;
        androidx.media3.common.util.Log.d("CIS 3334", "HeartrateAdapter: Data updated, new size: " + (newHeartrates != null ? newHeartrates.size() : 0) + ". Notifying change.");
        notifyDataSetChanged(); // Tell the RecyclerView to re-bind and re-draw
    }

// ... (rest of the class)

    @Override
    public void onBindViewHolder(@NonNull HeartrateViewHolder holder, int position) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        // Use mainViewModel to get the heartrate with the correct position
        Heartrate currentHeartrate = heartrate.getHeartrate(position);

        if (currentHeartrate != null) {
            // Use the heartrate to set the widgets in the HeartrateViewHolder
            holder.pulseValueTextView.setText(String.valueOf(currentHeartrate.getPulse()));
            holder.rangeValueTextView.setText(currentHeartrate.getRangeName());
            holder.descriptionTextView.setText(currentHeartrate.getRangeDescription());
        } else {
            // Handle the case where currentHeartrate might be null, if applicable
            holder.pulseValueTextView.setText("N/A");
            holder.rangeValueTextView.setText("N/A");
            holder.descriptionTextView.setText("Data not available");
        }
    }

    @Override
    public int getItemCount() {
        // Return the size of your dataset (invoked by the layout manager)
        // Use mainViewModel to get the number of heartrates stored so far.
        return Heartrate.getHeartrateCount();
    }

    // Optional: A method to notify the adapter that the underlying data in the ViewModel has changed.
    // This is useful if the ViewModel's data is updated and you need to refresh the RecyclerView.
    public void notifyDataChanged() {
        notifyDataSetChanged();
    }
}

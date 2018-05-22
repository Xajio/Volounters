package ru.unturn.iksa.volountersevent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class EventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        Event event = (Event) getIntent().getParcelableExtra(Event.class.getCanonicalName());
        ((TextView)findViewById(R.id.textEventName)).setText(event.eventName);
        ((TextView)findViewById(R.id.dateEvent)).setText(event.startOfEvent);
    }
}

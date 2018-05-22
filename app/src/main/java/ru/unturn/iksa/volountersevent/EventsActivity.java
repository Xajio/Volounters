package ru.unturn.iksa.volountersevent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class EventsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        Event[] events1 = RequestsForServer.getEvents("ufa");
        final EventsAdapter adapter = new EventsAdapter(this, events1);
        ListView lv = (ListView) findViewById(R.id.listview);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Event event = adapter.getItem(pos);
                Intent intent = new Intent(EventsActivity.this, EventActivity.class);
                intent.putExtra(Event.class.getCanonicalName(), event);
                startActivity(intent);
            }
        });
    }
}

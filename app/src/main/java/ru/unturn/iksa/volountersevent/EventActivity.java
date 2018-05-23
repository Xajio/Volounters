package ru.unturn.iksa.volountersevent;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class EventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        final Event event = (Event) getIntent().getParcelableExtra(Event.class.getCanonicalName());
        ((TextView)findViewById(R.id.textEventName)).setText(event.name);
        ((TextView)findViewById(R.id.dateEvent)).setText(event.startOfEvent);
        final Button enterButton = (Button)findViewById(R.id.enterToEvent);
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sPref = getPreferences(MODE_PRIVATE);
                if(RequestsForServer.enterToEvent(sPref.getString("email_login", ""), event.id)){
                    Toast.makeText(EventActivity.this, "You`re successfull writed.", Toast.LENGTH_LONG).show();
                };
                enterButton.setEnabled(false);
            }
        });
    }
}

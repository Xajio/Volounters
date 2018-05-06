package ru.unturn.iksa.volountersevent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Iksa on 08.04.2018.
 */

public class EventsAdapter extends ArrayAdapter<Event> {
    public EventsAdapter(Context context, Event[] arr) {
        super(context, R.layout.adapters_list, arr);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Event event = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapters_list, null);
        }

// Заполняем адаптер
        ((TextView) convertView.findViewById(R.id.eventname)).setText(event.eventName + "\n" + event.ownerOfEvent);
        ((TextView) convertView.findViewById(R.id.startofevent)).setText(event.startOfEvent);
        return convertView;
    }
}

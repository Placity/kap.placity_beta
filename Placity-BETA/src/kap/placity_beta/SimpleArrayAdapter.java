package kap.placity_beta;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
//import android.widget.ImageView;
import android.widget.TextView;

public class SimpleArrayAdapter extends BaseAdapter{
	
	private final ArrayList<HashMap<String,String>>  values;
	private final LayoutInflater inflater;

	public SimpleArrayAdapter(Context context, ArrayList<HashMap<String,String>>  values) {
		this.values = values;
		this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = inflater.inflate(R.layout.custom_list_row, parent, false);
		
		TextView headline = (TextView) rowView.findViewById(R.id.text1);
		TextView description = (TextView) rowView.findViewById(R.id.text2);
		//ImageView thumbnail = (ImageView) rowView.findViewById(R.id.imageView1);
		
		HashMap<String,String> data = new HashMap<String,String>();
		data = values.get(position);
		
		if (data.get("KEY_NAME") != null) {
			headline.setText(data.get("KEY_NAME"));
		} else {
			headline.setText(R.string.no_gamename);
		}
		if (data.get("KEY_DESC") != null) {
			description.setText(data.get("KEY_DESC"));
		} else {
			description.setText(R.string.no_description);
		}
		if (data.get("KEY_IMG") != null) {
			//TODO: Set thumbnail here
		} else {
			//TODO: Set default here
		}
		
		return rowView;
	}

	public int getCount() {
		return values.size();
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}
	

}

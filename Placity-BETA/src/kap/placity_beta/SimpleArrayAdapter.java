package kap.placity_beta;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SimpleArrayAdapter extends ArrayAdapter<String>{
	
	private final Context context;
	private final String[] values;

	public SimpleArrayAdapter(Context context, String[] values) {
		super(context, R.layout.custom_list_row, values);
		this.context = context;
		this.values = values;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.custom_list_row, parent, false);
		TextView head = (TextView) rowView.findViewById(R.id.text1);
		TextView description = (TextView) rowView.findViewById(R.id.text2);
		//ImageView thumbnail = (ImageView) rowView.findViewById(R.id.imageView1);
		head.setText(values[position]);
		description.setText(R.string.no_description);
		
		return rowView;
	}
	

}

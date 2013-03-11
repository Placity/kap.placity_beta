package kap.placity_beta;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class ChooseGame extends ListActivity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_game);//Init
        
        String[] folders = getApplicationContext().getDir("games", Context.MODE_PRIVATE).list(); //get list of folders in the main directory
        ArrayList<String> folderlist = new ArrayList<String>();
        
        //folderlist.add("Demo Game");
        
        try {
        	for (int i = 0; i <= folders.length; i++) {
        		folderlist.add(folders[i]); //TODO: read the game names instead of id with xmlpullparser
        	}
        } catch (IndexOutOfBoundsException e) {
        	System.out.println(e);
        }
        
        String[] games = folderlist.toArray(new String[folderlist.size()]);
        
        ArrayAdapter<String> listAdapter = new SimpleArrayAdapter(this, games); //create List entries
        setListAdapter(listAdapter); //push entries into listview        
	}
	
	public void onListItemClick(ListView l, View v, int position, long id)
	{
			super.onListItemClick(l, v, position, id);
			
			String name = this.getListAdapter().getItem(position).toString(); //get clicked game id
			System.out.println(name);

			Intent i = new Intent(getApplicationContext(), MainActivity.class);
			i.putExtra("ID", name);
			i.putExtra("sender", "chooseGame");
			i.putExtra("loading", false);
			startActivity(i); //start main activity with game id and sender
	}
	

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_choose_game, menu);
        return true;
    }
}

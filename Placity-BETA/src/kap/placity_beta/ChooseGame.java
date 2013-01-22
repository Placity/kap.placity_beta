package kap.placity_beta;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ChooseGame extends Activity {

    private ListView gameList;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_game);//Init
        
        gameList = (ListView) findViewById(R.id.listView1);
        String[] games = getApplicationContext().getDir("games", Context.MODE_PRIVATE).list(); //get list of games in the main directory
        
        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, games); //create List entries
        gameList.setAdapter(listAdapter); //push entries into listview        
        
        gameList.setOnItemClickListener(new OnItemClickListener() //execute on click
        {
        	public void onItemClick(AdapterView<?> arg0, View v, int position, long id)
        	{
        			String name = gameList.getItemAtPosition(position).toString(); //get clicked game id
        
        			Intent i = new Intent(getApplicationContext(), MainActivity.class);
        			i.putExtra("ID", name);
        			i.putExtra("sender", "chooseGame");
        			i.putExtra("loading", false);
        			startActivity(i); //start main activity with game id and sender
        	}
        });
	}
	

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_choose_game, menu);
        return true;
    }
}

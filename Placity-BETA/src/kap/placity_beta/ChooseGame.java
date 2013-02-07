package kap.placity_beta;

import java.io.File;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ChooseGame extends Activity {

    private ListView gameList;
	private String name;

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
        			name = gameList.getItemAtPosition(position).toString(); //get clicked game id
        
        	}
        });
	}
	
	public void startGame() {
		if (name != null) {
		Intent i = new Intent(getApplicationContext(), MainActivity.class);
		i.putExtra("ID", name);
		i.putExtra("sender", "chooseGame");
		i.putExtra("loading", false);
		startActivity(i); //start main activity with game id and sender
		}
		else {
			Toast.makeText(this, "Wähle ein Game aus!", Toast.LENGTH_LONG).show();
		}
	}
	
	public void deleteGame() {
		if (name != null) {
			new AlertDialog.Builder(this)
			.setTitle("Warnung")
			.setMessage("Game unwiederruflich löschen?")
			.setPositiveButton("Löschen", new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					File dir = new File(getApplicationContext().getDir("games", Context.MODE_PRIVATE).getAbsolutePath() + File.separator + name);
					dir.delete();
				}
			})
			.setNegativeButton("Abbrechen", null)
			.show();
		}
		else {
			Toast.makeText(this, "Wähle ein Game aus!", Toast.LENGTH_LONG).show();
		}
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_choose_game, menu);
        return true;
    }
}

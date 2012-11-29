package kap.placity_beta;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
        setContentView(R.layout.activity_choose_game);
        
        gameList = (ListView) findViewById(R.id.listView1); 
        String[] games = getDir("games", Context.MODE_PRIVATE).list();
        
        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, games);
        gameList.setAdapter(listAdapter);        
        
        gameList.setOnItemClickListener(new OnItemClickListener()
        {
        public void onItemClick(AdapterView<?> arg0, View v, int position, long id)
        {
        String name = gameList.getItemAtPosition(position).toString();
        
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        i.putExtra("ID", name);
        i.putExtra("sender", "chooseGame");
        startActivity(i);
        }
        });
	}
	

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_choose_game, menu);
        return true;
    }
}

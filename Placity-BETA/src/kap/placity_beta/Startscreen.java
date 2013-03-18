package kap.placity_beta;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

public class Startscreen extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_startscreen);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_startscreen, menu);
        return true;
    }
	
    public void startOffline(View view) {
    	//Toast toast = Toast.makeText(getApplicationContext(), "Not yet available", Toast.LENGTH_SHORT);
    	//toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 0);
    	//toast.show();
    	Intent i = new Intent(this, ChooseGame.class);
    	startActivity(i);
//    	Intent i = new Intent(getApplicationContext(), MainActivity.class);
//		i.putExtra("ID", "nothing");
//		i.putExtra("sender", "chooseGame");
//		i.putExtra("loading", false);
//		startActivity(i);
    }
    
    public void startScan(View view) {
    	Intent i = new Intent(this, ScanCode.class);
    	startActivity(i);
    }
    
    public void showInfo(View view) {
    	LayoutInflater inflater = getLayoutInflater();
    	View view2 = inflater.inflate(R.layout.toast_info, (ViewGroup) findViewById(R.id.toastLayout));
    	Toast toast = new Toast(this);
    	toast.setView(view2);
    	toast.setDuration(Toast.LENGTH_LONG);
    	toast.show();
    }
	
}

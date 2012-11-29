package kap.placity_beta;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

public class ScanCode extends Activity {

    private EditText id;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_scan_code);
        
        id = (EditText)findViewById(R.id.editText1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_scan_code, menu);
        return true;
    }
    
    public void startGame(View view) {
    	Intent download = new Intent (this, DownloadService.class);
    	download.putExtra("ID", id.getText().toString());
    	startService(download);
    	
    	Intent i = new Intent (this, MainActivity.class);
    	i.putExtra("ID", id.getText().toString());
    	i.putExtra("sender", "scanCode");
    	startActivity(i);
    }
}


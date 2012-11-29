package kap.placity_beta;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class JavaScriptInterface{
	
	private Context jsContext;

    /** Instantiate the interface and set the context */
    JavaScriptInterface(Context c) {
        jsContext = c;
    }
    
    public int get_qrcode(int number) {
    	return number; 
    }
    
    public void End() {
    	Log.v("end", "end");
    	Intent i = new Intent (jsContext, Startscreen.class);
    	jsContext.startActivity(i);
    }
    
    public void alert() {
    	Log.v("tag", "message");
    }
}

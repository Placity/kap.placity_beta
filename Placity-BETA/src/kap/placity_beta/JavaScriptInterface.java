package kap.placity_beta;
import android.content.Context;

public class JavaScriptInterface {
    Context jsContext;

    /** Instantiate the interface and set the context */
    JavaScriptInterface(Context c) {
        jsContext = c;
    }
    
    public int get_qrcode(int number) {
    	return number; 
    }
    
    public void End() {

    }
}

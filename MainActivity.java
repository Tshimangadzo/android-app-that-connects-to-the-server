package com.example.lab9;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
	
	TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView1);
        
        Button btn = (Button) findViewById(R.id.button1);

        btn.setOnClickListener( new OnClickListener(){        	
        	public void onClick(View v) {
             		
	AsyncHttpPost asyncHttpPost = new AsyncHttpPost();       
	asyncHttpPost.execute("http://lamp.ms.wits.ac.za/~800361/Cars.php");
        		        
        	}
        });
    }

    public class AsyncHttpPost extends AsyncTask<String, String, String> {
    	public AsyncHttpPost(){
    	}
    	    @Override
    	    protected String doInBackground(String... params) {
    	     byte[] result = null;
    	      String str = "";
    	      HttpClient client = new DefaultHttpClient();
    	      HttpPost post = new HttpPost(params[0]);// in this case, params[0] is URL
    	      try {
    	      HttpResponse response = client.execute(post);
    	      StatusLine statusLine = response.getStatusLine();
    	      if(statusLine.getStatusCode() == HttpURLConnection.HTTP_OK){
    	      result = EntityUtils.toByteArray(response.getEntity());
    	      str = new String(result, "UTF-8");
    	         }
    	     }
    	      catch (UnsupportedEncodingException e) {
    	       e.printStackTrace();
    	     }
    	    catch (Exception e) {
    	    }
    	   return str;
    	  }
    	@Override
    	protected void onPostExecute(String output) {
    	// do something with the string returned earlier
        textView.setText(output);
    	System.out.println(output);
    	}
    	}   
}

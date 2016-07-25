 package asus.exercise01.base;

 import android.graphics.Color;
 import android.os.Bundle;

 import org.apache.cordova.CordovaActivity;
 import org.apache.cordova.CordovaWebView;
 import org.apache.cordova.CordovaWebViewImpl;
 import org.apache.cordova.engine.SystemWebView;
 import org.apache.cordova.engine.SystemWebViewEngine;

 import asus.exercise01.R;

 public class WebActivity extends CordovaActivity {

     /** Called when the activity is first created. */
     @Override
     public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_web);
         super.init();
         // Load your application
         // launchUrl = "file:///android_asset/www/index.html"
         launchUrl = "file:///android_asset/www/index.html";
         loadUrl(launchUrl);
     }
     @Override
     protected CordovaWebView makeWebView() {
         SystemWebView webView = (SystemWebView)findViewById(R.id.cordovaWebView);
         return new CordovaWebViewImpl(new SystemWebViewEngine(webView));
     }
     @Override
     protected void createViews() {
         //Why are we setting a constant as the ID? This should be investigated
//        appView.getView().setId(100);
//        appView.getView().setLayoutParams(new FrameLayout.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.MATCH_PARENT));
//
//        setContentView(appView.getView());
         if (preferences.contains("BackgroundColor")) {
             int backgroundColor = preferences.getInteger("BackgroundColor", Color.BLACK);
             // Background of activity:
             appView.getView().setBackgroundColor(backgroundColor);
         }
         appView.getView().requestFocusFromTouch();
     }
 }
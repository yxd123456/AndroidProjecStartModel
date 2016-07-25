/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
*/

package org.apache.cordova.engine;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaWebViewEngine;

/**
 * Custom WebView subclass that enables us to capture events needed for Cordova.
 */
public class SystemWebView extends WebView implements CordovaWebViewEngine.EngineView {
    private SystemWebViewClient viewClient;
    SystemWebChromeClient chromeClient;
    private SystemWebViewEngine parentEngine;
    private CordovaInterface cordova;
    private Context context;
    private AlertDialog.Builder builder;
    private AlertDialog dialog;
    private ProgressBar progressBar;

    public SystemWebView(Context context) {
        this(context, null);
    }

    public SystemWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        progressBar = new ProgressBar(context);
        progressBar.setIndeterminate(true);
        progressBar.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100));
        builder = new AlertDialog.Builder(context).setTitle("加载中...").setView(progressBar);

        dialog = builder.create();
        dialog.setCancelable(false);
    }

    // Package visibility to enforce that only SystemWebViewEngine should call this method.
    void init(SystemWebViewEngine parentEngine, CordovaInterface cordova) {
        this.cordova = cordova;
        this.parentEngine = parentEngine;

        if (this.viewClient == null) {
            setWebViewClient(new SystemWebViewClient(parentEngine){

                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    dialog.show();
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    dialog.dismiss();
                }
            });
        }

        if (this.chromeClient == null) {
            setWebChromeClient(new SystemWebChromeClient(parentEngine){

                @Override
                public void onProgressChanged(WebView view, int newProgress) {

                }
            });
        }
    }

    @Override
    public CordovaWebView getCordovaWebView() {
        return parentEngine != null ? parentEngine.getCordovaWebView() : null;
    }

    @Override
    public void setWebViewClient(WebViewClient client) {
        viewClient = (SystemWebViewClient)client;
        super.setWebViewClient(client);
    }

    @Override
    public void setWebChromeClient(WebChromeClient client) {
        chromeClient = (SystemWebChromeClient)client;
        super.setWebChromeClient(client);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        Boolean ret = parentEngine.client.onDispatchKeyEvent(event);
        if (ret != null) {
            return ret.booleanValue();
        }
        return super.dispatchKeyEvent(event);
    }
}

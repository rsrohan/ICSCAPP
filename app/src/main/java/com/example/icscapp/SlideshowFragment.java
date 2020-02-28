package com.example.icscapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.icscapp.R;

public class SlideshowFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);

        final WebView myWebView = (WebView) root.findViewById(R.id.webview);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAppCacheEnabled(true);

        root.findViewById(R.id.internal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myWebView.loadUrl("https://docs.google.com/forms/d/1Yx9z-MbqYGErJsNUF9NqPp0Xe7O-Tf7xfcf-00TpAg0/viewform?edit_requested=true");

                myWebView.setVisibility(View.VISIBLE);
            }
        });
        root.findViewById(R.id.external).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myWebView.loadUrl("https://docs.google.com/forms/d/1oM9M6mBF9TP3rE2obD4wo5kMzlL_v493F4uD4GGSgEM/viewform?edit_requested=true");

                myWebView.setVisibility(View.VISIBLE);
            }
        });
        return root;
    }
}
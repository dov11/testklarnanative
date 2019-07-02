package com.example.testklarna;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.klarna.checkout.KlarnaCheckout;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
  private KlarnaCheckout mKlarnaCheckout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mKlarnaCheckout = new KlarnaCheckout(this, "com.example.testklarna");

    // read starting snippet from assets
    String snippet;
    try {
      InputStream is = getAssets().open("snippet.html");
      int size = is.available();

      byte[] buffer = new byte[size];
      is.read(buffer);
      is.close();

      snippet = new String(buffer);
    } catch (IOException e) {
      Log.e("klarnaTest", e.getMessage());
      snippet = "error";
    }

    mKlarnaCheckout.setSnippet(snippet);
    final View checkoutView = mKlarnaCheckout.getView();
    final ViewGroup container = findViewById(R.id.checkoutContainer);
    container.addView(checkoutView);
  }

  @Override
  protected void onDestroy() {
    if (mKlarnaCheckout != null) {
      mKlarnaCheckout.destroy();
    }
    super.onDestroy();
  }
}

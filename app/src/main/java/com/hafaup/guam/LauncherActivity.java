package com.hafaup.guam;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.browser.trusted.TrustedWebActivityIntentBuilder;

public class LauncherActivity extends android.app.Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Uri targetUrl = Uri.parse("https://hafaup.com/app.html");

        Intent intent = getIntent();
        String action = intent != null ? intent.getAction() : null;
        String data = intent != null && intent.getData() != null ? intent.getData().toString() : null;

        if (Intent.ACTION_VIEW.equals(action) && data != null && data.contains("hafaup.com")) {
            targetUrl = Uri.parse(data);
        }
        else if (intent != null && intent.getStringExtra("shortcut_target") != null) {
            String tgt = intent.getStringExtra("shortcut_target");
            targetUrl = Uri.parse("https://hafaup.com/app.html?tab=" + tgt);
        }
        else if (Intent.ACTION_SEND.equals(action) || Intent.ACTION_SEND_MULTIPLE.equals(action)) {
            String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
            String sharedSubject = intent.getStringExtra(Intent.EXTRA_SUBJECT);
            StringBuilder sb = new StringBuilder("https://hafaup.com/app.html?share=1");
            if (!TextUtils.isEmpty(sharedSubject)) {
                sb.append("&title=").append(Uri.encode(sharedSubject));
            }
            if (!TextUtils.isEmpty(sharedText)) {
                sb.append("&body=").append(Uri.encode(sharedText));
            }
            targetUrl = Uri.parse(sb.toString());
        }

        TrustedWebActivityIntentBuilder builder = new TrustedWebActivityIntentBuilder(targetUrl);
        CustomTabsIntent customTabsIntent = builder.buildCustomTabsIntent();
        customTabsIntent.launchUrl(this, targetUrl);
        finish();
    }
}

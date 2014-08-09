package com.commonsware.empublite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {


    private ViewPager pager = null;
    private ContentsAdapter adapter = null;

    public static final String MODEL = "model";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        pager = (ViewPager) findViewById(R.id.pager);
        findViewById(R.id.progressBar1).setVisibility(View.GONE);
        pager.setVisibility(View.VISIBLE);

        ModelFragment mfrag = (ModelFragment) getFragmentManager().findFragmentByTag(MODEL);

        if (mfrag == null) {
            getFragmentManager().beginTransaction().add(new ModelFragment(), MODEL).commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                return true;
            case R.id.about:

                Intent i = new Intent(this, SimpleContentActivity.class);
                i.putExtra(SimpleContentActivity.EXTRA_FILE, "file:///android_asset/misc/about.html");

                startActivity(i);

                return true;

            case R.id.help:

                i = new Intent(this, SimpleContentActivity.class);
                i.putExtra(SimpleContentActivity.EXTRA_FILE, "file:///android_asset/misc/help.html");

                startActivity(i);
                break;


        }

        return super.onOptionsItemSelected(item);
    }
}

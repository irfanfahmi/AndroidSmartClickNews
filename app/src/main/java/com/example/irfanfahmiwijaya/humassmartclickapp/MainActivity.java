package com.example.irfanfahmiwijaya.humassmartclickapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private ActionBarDrawerToggle drawerToggle;
    private long backPres;
    private Toast backToast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer);
        drawerToggle = setupDrawerToggle();
        toolbar = (Toolbar) findViewById(R.id.toolbarid);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDrawer.addDrawerListener(drawerToggle);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        loadFragment(new fragment_home());



    }


    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.open,  R.string.close);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the togg le state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {


        if(backPres + 2000 > System.currentTimeMillis()){
            backToast.cancel();
            super.finish();
            return;
        }else {
            backToast =  Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPres = System.currentTimeMillis();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container,fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.nav_home:
                fragment = new fragment_home();
                break;
            case R.id.nav_notification:
                fragment = new fragment_notification();
                break;
            case R.id.nav_profile:
               fragment = new fragment_profile();
                break;
        }
        return loadFragment(fragment);
    }

    public void klikbupati(MenuItem item) {
        Intent klikbupati = new Intent(MainActivity.this,modul_bupati.class);
        klikbupati.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        klikbupati.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(klikbupati);
    }

    public void klikwakilbupati(MenuItem item) {
        Intent klikwakilbupati = new Intent(MainActivity.this,modul_wakil_bupati.class);
        klikwakilbupati.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        klikwakilbupati.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(klikwakilbupati);
    }

    public void klikunduh(MenuItem item) {
        Intent klikunduh = new Intent(MainActivity.this,modul_unduh_naskah.class);
        startActivity(klikunduh);
    }

    public void klikpemerintah(MenuItem item) {
        Intent klikpemerintah = new Intent(MainActivity.this,modul_pemerintah.class);
        startActivity(klikpemerintah);
    }

    public void klikhome(MenuItem item) {
        Intent klikhome = new Intent(MainActivity.this,MainActivity.class);
        startActivity(klikhome);
    }

    public void klikgaleri(MenuItem item) {
        Intent klikgaleri= new Intent(MainActivity.this,modul_galeri.class);
        startActivity(klikgaleri);
    }

    public void klikgalerifoto(MenuItem item) {
        Intent klikgalerifoto= new Intent(MainActivity.this,modul_galerifoto.class);
        startActivity(klikgalerifoto);
    }

    public void klikgalerivideo(MenuItem item) {
        Intent klikgalerivideo= new Intent(MainActivity.this,modul_galerivideo.class);
        startActivity(klikgalerivideo);
    }

    public void klikcalendar(MenuItem item) {
        Intent klikkalender = new Intent(MainActivity.this,modul_kalender.class);
        startActivity(klikkalender);
    }
}

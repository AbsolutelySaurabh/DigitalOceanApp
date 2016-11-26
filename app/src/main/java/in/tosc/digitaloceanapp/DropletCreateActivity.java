package in.tosc.digitaloceanapp;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import in.tosc.digitaloceanapp.fragments.AdditionalDetailsFragment;
import in.tosc.digitaloceanapp.fragments.SelectImageFragment;
import in.tosc.digitaloceanapp.fragments.SelectSizeFragment;
import in.tosc.doandroidlib.objects.Droplet;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class DropletCreateActivity extends AppCompatActivity {

    Droplet droplet;
    public static int count = 1;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        droplet = new Droplet();
        setContentView(R.layout.activity_droplet_create);
    }

    private void removeFragment(int count) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (count){
            case 3 :
                fragmentTransaction.remove(fragmentManager.findFragmentByTag("ADDITIONAL_DETAILS")).commit();
                break;
            case 2 :
                fragmentTransaction.remove(fragmentManager.findFragmentByTag("SELECT_SIZE")).commit();
                break;
            case 1 :
                this.finish();
        }
    }

    private void addFragment(int count) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch(count){
            case 2 :
                SelectSizeFragment selectSizeFragment = new SelectSizeFragment();
                fragmentTransaction.add(R.id.fragmentHolder,selectSizeFragment,"SELECT_SIZE");
                fragmentTransaction.commit();
                break;
            case 3 :
                AdditionalDetailsFragment additionalDetailsFragment = new AdditionalDetailsFragment();
                fragmentTransaction.add(R.id.fragmentHolder,additionalDetailsFragment,"ADDITIONAL_DETAILS");
                fragmentTransaction.commit();
                break;
            case 4 :
                createDroplet(droplet);
        }
    }

    private void createDroplet(Droplet droplet) {
        //TODO Make network call to create a droplet
        this.finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        SelectImageFragment selectImageFragment = new SelectImageFragment();
        fragmentTransaction.add(R.id.fragmentHolder,selectImageFragment,"CREATE_DROPLET");
        fragmentTransaction.commit();
    }
}
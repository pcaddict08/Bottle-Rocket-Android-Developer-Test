package com.einzig.stores.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.einzig.stores.Constants;
import com.einzig.stores.R;
import com.einzig.stores.StoreAct;
import com.einzig.stores.helpers.AlertHelper;
import com.einzig.stores.helpers.MiscHelper;
import com.einzig.stores.helpers.ThemeHelper;
import com.einzig.stores.objects.Store;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.einzig.stores.helpers.LogHelper.log_debug;

public class StoreDetailsActivity extends StoreAct {

    Store store;

    @BindView(R.id.image_storedetailsactivity)
    ImageView image_storedetailsactivity;
    @BindView(R.id.namelabel_storedetailsactivity)
    TextView namelabel_storedetailsactivity;
    @BindView(R.id.storenum_storedetailsactivity)
    TextView storenum_storedetailsactivity;
    @BindView(R.id.phonelabel_storedetailsactivity)
    TextView phonelabel_storedetailsactivity;
    @BindView(R.id.addresslabel_storedetailsactivity)
    TextView addresslabel_storedetailsactivity;
    @BindView(R.id.address2label_storedetailsactivity)
    TextView address2label_storedetailsactivity;
    @BindView(R.id.latlon_storedetailsactivity)
    TextView latlon_storedetailsactivity;
    @BindView(R.id.mapimage_storedetailsactivity)
    ImageView mapimage_storedetailsactivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_details);
        ButterKnife.bind(this);
        store = getIntent().getParcelableExtra(Constants.KEY_STORE);
        if (store != null) {
            ThemeHelper.initActionBar(getSupportActionBar(), "Details - " + store.getName());
            Glide.with(this).load(store.getStoreLogoURL()).into(image_storedetailsactivity);
            namelabel_storedetailsactivity.setText(store.getName());
            storenum_storedetailsactivity.setText(String.valueOf(store.getStoreID()));
            phonelabel_storedetailsactivity.setText(store.getPhone());
            addresslabel_storedetailsactivity.setText(store.getAddress());
            address2label_storedetailsactivity.setText(store.getAddress2());
            latlon_storedetailsactivity.setText(store.getLatLngStr());
            loadMapImage();
            log_debug("HAS MAPS INSTALLED: " + MiscHelper.hasMapsInstalled(this));
        } else
            finish();
    }

    public void loadMapImage() {
        String url = "http://maps.google.com/maps/api/staticmap?center=" + store.getLatitude() + "," + store.getLongitude() + "&zoom=15&size=400x400&sensor=true&markers=color:red|label:S|" + store.getLatitude() + "," + store.getLongitude();
        Glide.with(this).load(url).into(mapimage_storedetailsactivity);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_storedetails, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.share_storedetailsmenu:
                shareText(Constants.SHARESUBJ, store.getShareString());
                return true;
            case R.id.nav_storedetailsmenu:
                navToStore();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void shareText(String subject, String body) {
        try {
            Intent txtIntent = new Intent(android.content.Intent.ACTION_SEND);
            txtIntent.setType("text/plain");
            txtIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
            txtIntent.putExtra(android.content.Intent.EXTRA_TEXT, body);
            startActivity(Intent.createChooser(txtIntent, "Share Store Info"));
        } catch (Exception e) {
            e.printStackTrace();
            AlertHelper.showToast(this, "Error Sharing Store. Try again.", true, R.drawable.ic_warning);
        }
    }

    private void navToStore() {
        try {
            String url = "https://www.google.com/maps/dir/?api=1&destination=" + store.getLatitude() + "," + store.getLongitude();
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        } catch (Exception e) {
            e.printStackTrace();
            AlertHelper.showToast(this, "Error Navigating. Try again", true, R.drawable.ic_warning);
        }
    }
}

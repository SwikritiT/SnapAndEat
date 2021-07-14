package ellere.com.snapandeat.activity;

/**
 * Created by swikriti on 3/15/2020.
 */




        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.support.annotation.NonNull;
        import android.support.design.widget.BottomNavigationView;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentTransaction;
        import android.support.v7.app.ActionBar;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.text.Html;
        import android.view.MenuItem;
        import android.widget.Toolbar;

        import ellere.com.snapandeat.R;
        import ellere.com.snapandeat.fragments.AddFragment;
        import ellere.com.snapandeat.fragments.HomeFragment;
        import ellere.com.snapandeat.fragments.ProfileFragment;


public class FeedActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    private ActionBar toolbar;
    SharedPreferences prf;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        toolbar = getSupportActionBar();
        openFragment(new HomeFragment());


        bottomNavigationView=(BottomNavigationView) findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        toolbar.setTitle(Html.fromHtml("<font color='#000000'>SnapAndEat</font>"));
        //toolbar.setTitle("SnapAndEat");
        //prf = getSharedPreferences("user_details",MODE_PRIVATE);
        //intent = new Intent(FeedActivity.this,LoginActivity.class);


    }
    private final BottomNavigationView.OnNavigationItemSelectedListener navListener=new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment fragment;
            switch (menuItem.getItemId()){

                case R.id.item_home:
                    toolbar.setTitle(Html.fromHtml("<font color='#000000'>SnapAndEat</font>"));
                    fragment= new HomeFragment();
                    openFragment(fragment);
                    return true;

                case R.id.item_add:
                    toolbar.setTitle(Html.fromHtml("<font color='#000000'>Gallery</font>"));
                    fragment= new AddFragment();
                    openFragment(fragment);
                    return true;

                case R.id.item_profile:
                    toolbar.setTitle(Html.fromHtml("<font color='#000000'>Profile</font>"));
                    fragment=new ProfileFragment();
                    openFragment(fragment);
                    return true;

            }
            return false;
        }
    };
    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}


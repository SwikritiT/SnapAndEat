package ellere.com.snapandeat;

/**
 * Created by swikriti on 3/15/2020.
 */



        import android.content.Intent;
        import android.support.annotation.NonNull;
        import android.support.design.widget.BottomNavigationView;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentTransaction;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.MenuItem;
        import ellere.com.snapandeat.fragments.AddFragment;
        import ellere.com.snapandeat.fragments.HomeFragment;
        import ellere.com.snapandeat.fragments.ProfileFragment;


public class FeedActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        openFragment(new HomeFragment());

        bottomNavigationView=(BottomNavigationView) findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

    }
    private   BottomNavigationView.OnNavigationItemSelectedListener navListener=new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment fragment;
            switch (menuItem.getItemId()){

                case R.id.item_home:
                    fragment= new HomeFragment();
                    openFragment(fragment);

                    return true;
                case R.id.item_add:
                    fragment= new AddFragment();
                    openFragment(fragment);

                    return true;
                case R.id.item_profile:
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


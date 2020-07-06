package ellere.com.snapandeat.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ellere.com.snapandeat.Constants;
import ellere.com.snapandeat.R;
import ellere.com.snapandeat.activity.EditProfileActivity;
import ellere.com.snapandeat.activity.FeedActivity;
import ellere.com.snapandeat.activity.LoginActivity;
import ellere.com.snapandeat.adapter.ProfileBottomAdapter;
import ellere.com.snapandeat.adapter.ProfileTopAdapter;
import ellere.com.snapandeat.adapter.RecyclerViewAdapterFeed;
import ellere.com.snapandeat.model.FeedModel;
import ellere.com.snapandeat.model.ProfileBtmModel;
import ellere.com.snapandeat.model.ProfileTopModel;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by DELL on 4/24/2020.
 */

public class ProfileFragment extends Fragment {
    RecyclerView recyclerViewtop,recyclerViewBtm;
    SharedPreferences prf;

    ArrayList<FeedModel> btmModelArrayList=new ArrayList<>();
    ArrayList<ProfileTopModel> topModelArrayList=new ArrayList<>();
    //ArrayList<ProfileBtmModel> btmModelArrayList=new ArrayList<>();
    ProfileTopAdapter profileTopAdapter;
    RecyclerViewAdapterFeed profileBottomAdapter;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_profile, container, false);
        recyclerViewtop= view.findViewById(R.id.recy_proftop);

        prf = this.getActivity().getSharedPreferences("user_details", MODE_PRIVATE);

        SharedPreferences.Editor editor = prf.edit();


        //recyclerview for top part of the profile
        RecyclerView.LayoutManager layoutManager2=new LinearLayoutManager(getActivity());
        recyclerViewtop.setLayoutManager(layoutManager2);
        profileTopAdapter=new ProfileTopAdapter(getActivity(),topModelArrayList);
        recyclerViewtop.setAdapter(profileTopAdapter);
        populateTop();
        populateBottom();
        return view;
    }

    private  void  populateTop(){
        ProfileTopModel topModel=new ProfileTopModel(R.drawable.baseline_account_circle_black_18dp,"Swikriti","3","0","0");
        topModelArrayList.add(topModel);
    }
    private void populateBottom(){
        StringRequest stringRequest= new StringRequest(Request.Method.GET, Constants.IMAGES_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    final String result = response.toString();
                    if (result.equals("")) {
                        Toast.makeText(getActivity(), "No Picture Found", Toast.LENGTH_SHORT).show();
                    } else {
                        int i = 0;
                        Log.d("response", "result1: " + result);
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("response_list");
                        while (i < jsonArray.length()) {

                            //getting product object from json array
                            JSONObject recipes = jsonArray.getJSONObject(i);

                            String image_path = recipes.getString("path");
                            FeedModel feedModel = new FeedModel("Swikriti ", "Muna", "Bbq Chicken Pizza-250 cal",
                                    1, R.drawable.baseline_account_circle_black_18dp, R.drawable.baseline_account_circle_black_18dp, R.drawable.baseline_account_circle_black_18dp, image_path);

                            btmModelArrayList.add(new FeedModel("Swikriti ", "Muna", "Bbq Chicken Pizza-250 cal",
                                    1, R.drawable.baseline_account_circle_black_18dp, R.drawable.baseline_account_circle_black_18dp, R.drawable.baseline_account_circle_black_18dp, image_path));
                            i++;

                        }
                    }
                    //RecyclerView for bottom part of the profile
                      recyclerViewBtm=(RecyclerView)getView().findViewById(R.id.recyprof_btm);
                    RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
                    //layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerViewBtm.setHasFixedSize(true);
                    recyclerViewBtm.setLayoutManager(layoutManager);
                    profileBottomAdapter=new RecyclerViewAdapterFeed(getActivity(),btmModelArrayList);
                    recyclerViewBtm.setAdapter(profileBottomAdapter);


                }
                catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        volleyError.printStackTrace();
                        volleyError.getMessage();

                    }
                }
        );
        Volley.newRequestQueue(getActivity()).add(stringRequest);




    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.logout, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                SharedPreferences.Editor editor = prf.edit();
                editor.clear();
                editor.commit();

                Intent intent=new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
                break;


        }
        return true;

    }
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}

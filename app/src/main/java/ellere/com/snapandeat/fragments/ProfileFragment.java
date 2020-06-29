package ellere.com.snapandeat.fragments;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

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

/**
 * Created by DELL on 4/24/2020.
 */

public class ProfileFragment extends Fragment {
    RecyclerView recyclerViewtop, recyclerViewBtm;


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
        recyclerViewBtm=view.findViewById(R.id.recyprof_btm);
//        editprofile=view.findViewById(R.id.editProfile);
//        editprofile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//            }
//        });
        //RecyclerView for bottom part of the profile
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerViewBtm.setLayoutManager(layoutManager);
        profileBottomAdapter=new RecyclerViewAdapterFeed(getActivity(),btmModelArrayList);
        recyclerViewBtm.setAdapter(profileBottomAdapter);

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
        ProfileTopModel topModel=new ProfileTopModel(R.drawable.propic1,"Swikriti","3","0","0");
        topModelArrayList.add(topModel);
    }
    private void populateBottom(){
        FeedModel feedModel=new FeedModel("Swikriti ","2 HOURS AGO","Isha","Bbq Chicken Pizza-250 cal",
                87,R.drawable.propic2,R.drawable.propic6,R.drawable.propic1,R.drawable.postpic1);

        btmModelArrayList.add(feedModel);

        feedModel=new FeedModel("Yamuna","25 MINUTES AGO","Swikriti","Cheese Pizza-200 cal",
                92,R.drawable.propic2,R.drawable.propic5,R.drawable.propic2,R.drawable.postpic2);

        btmModelArrayList.add(feedModel);

        feedModel=new FeedModel("Shardool","50 MINUTES AGO","Yamuna","Mexican Pizza-220 cal",
                82,R.drawable.propic2,R.drawable.propic4,R.drawable.propic3,R.drawable.postpic3);

        btmModelArrayList.add(feedModel);

        feedModel=new FeedModel("Isha","5 HOURS AGO","Swikriti","Pepperoni Pizza-215 cal",
                76,R.drawable.propic2,R.drawable.propic3,R.drawable.propic4,R.drawable.postoic4);

        btmModelArrayList.add(feedModel);

        feedModel=new FeedModel("Santosh","36 MINUTES AGO","Isha","Margherita Pizza- 240 cal",
                97,R.drawable.propic2,R.drawable.propic2,R.drawable.propic5,R.drawable.postpic5);

        btmModelArrayList.add(feedModel);

        feedModel=new FeedModel("Sandhya","8 HOURS AGO","Shardool","Pepperoni Pizza - 215 cal",
                56,R.drawable.propic2,R.drawable.propic1,R.drawable.propic6,R.drawable.postpic6);

        btmModelArrayList.add(feedModel);

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

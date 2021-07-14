package ellere.com.snapandeat.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import ellere.com.snapandeat.R;
import ellere.com.snapandeat.model.ProfileBtmModel;
import ellere.com.snapandeat.model.ProfileTopModel;

/**
 * Created by DELL on 6/24/2020.
 */

public class ProfileBottomAdapter extends RecyclerView.Adapter<ProfileBottomAdapter.MyViewHolder> {
    private final Context mContext;
    private final List<ProfileBtmModel> profileList;

    public ProfileBottomAdapter(Context mContext, List<ProfileBtmModel> profileList){
        this.mContext=mContext;
        this.profileList=profileList;
    }

    @NonNull
    @Override
    public ProfileBottomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.profilebtm_posts_temp,parent,false);
        return new ProfileBottomAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileBottomAdapter.MyViewHolder myViewHolder, int position) {
        final ProfileBtmModel model=profileList.get(position);
        Glide.with(mContext).load(model.getPostedPictures()).into(myViewHolder.posts);

    }

    @Override
    public int getItemCount() {
        return profileList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView posts;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            posts=(ImageView)itemView.findViewById(R.id.posts_photos);
        }
    }
}

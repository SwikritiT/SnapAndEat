package ellere.com.snapandeat.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import ellere.com.snapandeat.R;
import ellere.com.snapandeat.model.ProfileTopModel;

/**
 * Created by DELL on 6/24/2020.
 */

public class ProfileTopAdapter extends RecyclerView.Adapter<ProfileTopAdapter.MyViewHolder> {
    private Context mContext;
    private List<ProfileTopModel> profileList;
    public ProfileTopAdapter(Context mContext, List<ProfileTopModel> profileList){
        this.mContext=mContext;
        this.profileList=profileList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.profiletop_template,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        final ProfileTopModel model=profileList.get(position);
        Glide.with(mContext).load(model.getProfilePic()).into(myViewHolder.profilePic);
        myViewHolder.userName.setText(model.getUserName());
        myViewHolder.noOfPosts.setText(model.getNoOfPosts());
        myViewHolder.noOfFollowings.setText(model.getNoFollowing());
        myViewHolder.noOfFollowers.setText(model.getNoFollowers());

    }

    @Override
    public int getItemCount() {
        return profileList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView profilePic;
        TextView userName,noOfPosts,noOfFollowers,noOfFollowings;
        Button editProfile;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            profilePic=(CircleImageView)itemView.findViewById(R.id.profile_pic);
            userName=(TextView)itemView.findViewById(R.id.user_name);
            noOfFollowers=(TextView)itemView.findViewById(R.id.nooffollowers);
            noOfFollowings=(TextView)itemView.findViewById(R.id.nooffollowings);
            noOfPosts=(TextView)itemView.findViewById(R.id.noofpost);
            editProfile=(Button)itemView.findViewById(R.id.editProfile);
        }
    }
}

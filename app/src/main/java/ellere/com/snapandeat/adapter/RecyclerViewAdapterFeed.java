package ellere.com.snapandeat.adapter;

/**
 * Created by swikriti on 3/15/2020.
 */


        import android.content.Context;
        import android.support.annotation.NonNull;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;

        import ellere.com.snapandeat.R;
        import ellere.com.snapandeat.model.FeedModel;

        import com.bumptech.glide.Glide;
        import com.bumptech.glide.RequestManager;


        import java.util.List;

        import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapterFeed extends RecyclerView.Adapter<RecyclerViewAdapterFeed.MyViewHolder> {

    private Context context;
    private List<FeedModel> mData;
    RequestManager glide;

    public RecyclerViewAdapterFeed(Context context, List<FeedModel> mData) {
        this.context = context;
        this.mData = mData;
        this.glide = Glide.with(context);
    }

    @NonNull
    @Override
    public RecyclerViewAdapterFeed.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater=LayoutInflater.from(context);
        view=inflater.inflate(R.layout.instagram_feed,parent,false);

        return new RecyclerViewAdapterFeed.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterFeed.MyViewHolder holder, final int position) {

        holder.uploadername.setText(mData.get(position).getName());
        holder.likername.setText(mData.get(position).getLikedBy());
        holder.posttime.setText(mData.get(position).getTime());
        holder.likes.setText(mData.get(position).getLikes()+" others");
        holder.captionnames.setText(mData.get(position).getName());
        holder.tags.setText(mData.get(position).getTags());

        glide.load(mData.get(position).getUploaderPic()).into(holder.uploader);
        glide.load(mData.get(position).getProPic()).into(holder.userpic);
        glide.load(mData.get(position).getLikerPic()).into(holder.liker);
        glide.load(mData.get(position).getPostPic()).into(holder.post);


    }

    @Override
    public int getItemCount() {
        return  mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {

        TextView uploadername,likername,posttime,likes,captionnames,tags;
        CircleImageView uploader,userpic,liker;
        ImageView post;

        public MyViewHolder(@NonNull View mView) {
            super(mView);

            uploadername=(TextView) mView.findViewById(R.id.tv_uploader_name);
            likername=(TextView) mView.findViewById(R.id.liker_name);
            posttime=(TextView) mView.findViewById(R.id.tv_time);
            likes=(TextView) mView.findViewById(R.id.tv_likes);
            captionnames=(TextView) mView.findViewById(R.id.tv_uploader_name_caption);
            tags=(TextView) mView.findViewById(R.id.tv_tags);

            uploader=(CircleImageView) mView.findViewById(R.id.uploader_pro_pic);
            userpic=(CircleImageView) mView.findViewById(R.id.user_pro_pic);
            liker=(CircleImageView) mView.findViewById(R.id.liker_pro_pic);

            post=(ImageView) mView.findViewById(R.id.post_pic);
        }
    }

}

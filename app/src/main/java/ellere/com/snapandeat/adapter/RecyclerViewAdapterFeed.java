package ellere.com.snapandeat.adapter;

/**
 * Created by swikriti on 3/15/2020.
 */


        import android.content.Context;
        import android.support.annotation.NonNull;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.MenuItem;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageButton;
        import android.widget.ImageView;
        import android.widget.PopupMenu;
        import android.widget.TextView;
        import android.widget.Toast;
        import ellere.com.snapandeat.Constants;
        import ellere.com.snapandeat.R;
        import ellere.com.snapandeat.model.FeedModel;

        import com.bumptech.glide.Glide;
        import com.bumptech.glide.RequestManager;
        import com.bumptech.glide.load.engine.DiskCacheStrategy;
        import com.bumptech.glide.request.RequestOptions;


        import java.util.List;

        import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapterFeed extends RecyclerView.Adapter<RecyclerViewAdapterFeed.MyViewHolder> {

    static Context context;
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
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.instagram_feed,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterFeed.MyViewHolder holder, final int position) {
        FeedModel feedModel=mData.get(position);
        holder.uploadername.setText(mData.get(position).getName());
        holder.likername.setText(mData.get(position).getLikedBy());
//        holder.posttime.setText(mData.get(position).getTime());
        holder.likes.setText(mData.get(position).getLikes()+" others");
        holder.captionnames.setText(mData.get(position).getName());
        holder.tags.setText(mData.get(position).getTags());

        glide.load(mData.get(position).getUploaderPic()).into(holder.uploader);
        glide.load(mData.get(position).getProPic()).into(holder.userpic);
        glide.load(mData.get(position).getLikerPic()).into(holder.liker);
//        glide.load(mData.get(position).getPostPic()).into(holder.post);

        glide.load(feedModel.getPostPic()).apply(new RequestOptions().override(300, 300)).into(holder.post);


    }

    @Override
    public int getItemCount() {
        return  mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        TextView uploadername,likername,posttime,likes,captionnames,tags;
        CircleImageView uploader,userpic,liker;
        ImageView post;
        ImageView like,comment,share,bookmark;
        ImageButton dots;
        boolean isClicked=true;


        public MyViewHolder(@NonNull View mView) {
            super(mView);

            uploadername=(TextView) mView.findViewById(R.id.tv_uploader_name);
            likername=(TextView) mView.findViewById(R.id.liker_name);
            //posttime=(TextView) mView.findViewById(R.id.tv_time);
            likes=(TextView) mView.findViewById(R.id.tv_likes);
            captionnames=(TextView) mView.findViewById(R.id.tv_uploader_name_caption);
            tags=(TextView) mView.findViewById(R.id.tv_tags);

            uploader=(CircleImageView) mView.findViewById(R.id.uploader_pro_pic);
            userpic=(CircleImageView) mView.findViewById(R.id.user_pro_pic);
            liker=(CircleImageView) mView.findViewById(R.id.liker_pro_pic);
            like=mView.findViewById(R.id.iv_like);
            comment=mView.findViewById(R.id.iv_comment);
            share=mView.findViewById(R.id.iv_share);
            bookmark=mView.findViewById(R.id.iv_bookmark);
            dots=mView.findViewById(R.id.btn_dotsfeed);

            post=(ImageView) mView.findViewById(R.id.post_pic);
            like.setOnClickListener(this);
            comment.setOnClickListener(this);
            share.setOnClickListener(this);
             bookmark.setOnClickListener(this);
             dots.setOnClickListener(new View.OnClickListener() {
                  @Override
                     public void onClick(View v) {
                        //Creating the instance of PopupMenu
                        PopupMenu popup = new PopupMenu(context.getApplicationContext(), dots);
                       //Inflating the Popup using xml file
                        popup.getMenuInflater().inflate(R.menu.dots_menu, popup.getMenu());

                        //registering popup with OnMenuItemClickListener
                        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            public boolean onMenuItemClick(MenuItem item) {
                                switch (item.getItemId()) {
                                    case R.id.item_report_pic:
                                        Toast.makeText(context.getApplicationContext(), "Report selected", Toast.LENGTH_SHORT).show();
                                        return true;
                                    case R.id.item_copy_link:
                                        Toast.makeText(context, "copy link selected", Toast.LENGTH_SHORT).show();
                                        return true;
                                    case R.id.item_unfollow:
                                        Toast.makeText(context, "unfollow selected", Toast.LENGTH_SHORT).show();
                                        return true;
                                    default:
                                        return true;
                                }


                            }
                        });

                        popup.show();//showing popup menu

                  }
             });


        }

            @Override
    public void onClick(View v) {
        if (v == like) {

            if(isClicked){
            Constants.LIKE_COUNT=Constants.LIKE_COUNT+1;
            like.setImageResource(R.drawable.favourite);
            isClicked=false;
            }
            else{
                Constants.LIKE_COUNT=Constants.LIKE_COUNT-1;
                like.setImageResource(R.drawable.ic_like);
                isClicked=true;
            }
            //Toast.makeText(getActivity(), "Comment selected", Toast.LENGTH_SHORT).show();
        }
        if (v == comment){
            Toast.makeText(context, "Comment selected", Toast.LENGTH_SHORT).show();
        }
        if (v == share){
            Toast.makeText(context, "Share selected", Toast.LENGTH_SHORT).show();
        }
        if (v == bookmark){
            Toast.makeText(context, "bookmark selected", Toast.LENGTH_SHORT).show();
        }




    }
    }

}

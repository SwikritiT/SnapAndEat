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
        import android.widget.TextView;

        import ellere.com.snapandeat.R;
        import ellere.com.snapandeat.model.StoriesModel;
        import com.bumptech.glide.Glide;
        import com.bumptech.glide.RequestManager;


        import java.util.List;

        import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapterStories  extends RecyclerView.Adapter<RecyclerViewAdapterStories.MyViewHolder> {

    private Context context;
    private List<StoriesModel> mData;
    RequestManager glide;

    public RecyclerViewAdapterStories(Context context, List<StoriesModel> mData) {
        this.context = context;
        this.mData = mData;
        this.glide = Glide.with(context);
    }

    @NonNull
    @Override
    public RecyclerViewAdapterStories.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater=LayoutInflater.from(context);
        view=inflater.inflate(R.layout.stories_feed,parent,false);

        return new RecyclerViewAdapterStories.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterStories.MyViewHolder holder, int position) {

        holder.storiesname.setText(mData.get(position).getStoriesName());
        glide.load(mData.get(position).getStoriesPic()).into(holder.stories);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {

        TextView storiesname;
        CircleImageView stories;

        public MyViewHolder(@NonNull View mView) {
            super(mView);

            storiesname=(TextView) mView.findViewById(R.id.stories_name);
            stories=(CircleImageView) mView.findViewById(R.id.stories_pic);

        }
    }
}

package ru.vsu.alexey.surfandroidschool;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.List;

import ru.vsu.alexey.surfandroidschool.contract.MemContract;
import ru.vsu.alexey.surfandroidschool.model.MemResponse;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<MemResponse> memesList;
    private OnItemListener onItemListener;

    public RecyclerViewAdapter(Context context, List<MemResponse> memesList) {


        this.context = context;
        this.memesList = memesList;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view;
        view = LayoutInflater.from(context).inflate(R.layout.memes_item_view,viewGroup,false);

        ViewHolder viewHolder = new ViewHolder(view, onItemListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.memTitle.setText(memesList.get(i).getTitle());

        Glide.with(context)
                .load(memesList.get(i).getPhotoUrl())
                .placeholder(R.drawable.ic_launcher_background)
                .into(viewHolder.memeImage);
        Log.d("СТРОКА ССЫЛКИ",memesList.get(i).getPhotoUrl().toString());


    }

    @Override
    public int getItemCount() {
        return memesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private RelativeLayout item_memes;
        private TextView memTitle;
        private ImageView memeImage;
        OnItemListener onItemListener;


        public ViewHolder(@NonNull View itemView, OnItemListener onItemListener) {
            super(itemView);

            item_memes = (RelativeLayout) itemView.findViewById(R.id.memes_item_id);
            memTitle = (TextView)itemView.findViewById(R.id.memes_title);
            memeImage = (ImageView)itemView.findViewById(R.id.image_item_view);
            this.onItemListener = onItemListener;
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {

            //onItemListener.OnItemClick(getAdapterPosition());
            if(onItemListener != null){
                onItemListener.OnItemClick(v,getPosition());
            }
        }
        }
    public interface OnItemListener{
        void OnItemClick(View view, int position);
    }
    public void setClickListener(OnItemListener onItemListener){
        this.onItemListener = onItemListener;
    }
}

package com.atomasg.randomapiconsumer.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.atomasg.randomapiconsumer.R;
import com.atomasg.randomapiconsumer.model.Contact;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

    private List<Contact> mItems;
    private Context mContext;
    private PostItemListener mItemListener;
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView nameTextView;
        public ImageView contactImage;
        PostItemListener mItemListener;
        public ViewHolder(View itemView, PostItemListener postItemListener) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.tvContactName);
            contactImage = (ImageView) itemView.findViewById(R.id.contactImage);
            this.mItemListener = postItemListener;
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            Contact item = getItem(getAdapterPosition());
            this.mItemListener.onPostClick(item.getId().getValue());
            notifyDataSetChanged();
        }
    }
    public ContactsAdapter(Context context, List<Contact> posts, PostItemListener itemListener) {
        mItems = posts;
        mContext = context;
        mItemListener = itemListener;
    }
    @Override
    public ContactsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View postView = inflater.inflate(R.layout.list_user_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(postView, this.mItemListener);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(ContactsAdapter.ViewHolder holder, int position) {
        Contact item = mItems.get(position);
        TextView textView = holder.nameTextView;
        ImageView imageView = holder.contactImage;
        textView.setText(item.getName().getFirst());
        Picasso.get().load(item.getPicture().getMedium()).into(imageView);
    }
    @Override
    public int getItemCount() {
        return mItems.size();
    }
    public void updateContacts(List<Contact> items) {
        mItems = items;
        notifyDataSetChanged();
    }
    private Contact getItem(int adapterPosition) {
        return mItems.get(adapterPosition);
    }
    public interface PostItemListener {
        void onPostClick(String id);
    }
}

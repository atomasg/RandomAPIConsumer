package com.atomasg.randomapiconsumer.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.recyclerview.widget.RecyclerView;

import com.atomasg.randomapiconsumer.databinding.ListUserItemBinding;
import com.atomasg.randomapiconsumer.model.Contact;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> implements Filterable {

    private List<Contact> mItems;
    private PostItemListener mItemListener;
    private List<Contact> originalList;

    public ContactsAdapter(List<Contact> posts, PostItemListener itemListener) {
        mItems = posts;
        mItemListener = itemListener;
    }
    @Override
    public ContactsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(ListUserItemBinding.inflate(LayoutInflater.from(parent.getContext())), mItemListener);
    }
    @Override
    public void onBindViewHolder(ContactsAdapter.ViewHolder holder, int position) {
        Contact item = mItems.get(position);
        holder.bind(item);
    }
    @Override
    public int getItemCount() {
        return mItems.size();
    }
    public void updateContacts(List<Contact> items) {
        originalList = items;
        mItems = items;
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mItems = (List<Contact>) results.values;
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<Contact> filteredResults = null;
                if (constraint.length() == 0) {
                    filteredResults = originalList;
                } else {
                    filteredResults = getFilteredResults(constraint.toString().toLowerCase());
                }

                FilterResults results = new FilterResults();
                results.values = filteredResults;

                return results;
            }
        };
    }


    protected List<Contact> getFilteredResults(String constraint) {
        List<Contact> results = new ArrayList<>();

        for (Contact item : originalList) {
            if (item.getGender().toLowerCase().equals(constraint)) {
                results.add(item);
            }
        }
        return results;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ListUserItemBinding binding;
        PostItemListener mItemListener;
        public ViewHolder(ListUserItemBinding binding, PostItemListener mItemListener) {
            super(binding.getRoot());
            this.mItemListener = mItemListener;
            this.binding = binding;
        }

        void bind(Contact item){
            binding.getRoot().setOnClickListener(v -> mItemListener.onPostClick(item.getLogin().getUuid()));
            binding.tvContactName.setText(item.getName().toString());
            Picasso.get().load(item.getPicture().getMedium()).into(binding.contactImage);
        }

    }

    private Contact getItem(int adapterPosition) {
        return mItems.get(adapterPosition);
    }
    public interface PostItemListener {
        void onPostClick(String id);
    }
}

package com.sola.testing.solatesting.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.sola.testing.solatesting.recycleview.utils.IRecycleListItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * <p/>
 * author: Sola
 * 2015/9/25
 */
public class RecycleViewAdapter<Param extends IRecycleListItem>
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    protected List<Param> cacheList;

    protected final Context mContext;

    // ===========================================================
    // Constructors
    // ===========================================================

    public RecycleViewAdapter(Context mContext, List<Param> cacheList) {
        refreshList(cacheList);
        this.mContext = mContext;
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================


    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return cacheList.get(viewType).getHolder(mContext);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        cacheList.get(position).refreshView(holder);

    }

    @Override
    public int getItemCount() {
        return cacheList == null ? 0 : cacheList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    // ===========================================================
    // Methods
    // ===========================================================

    public void refreshList(List<Param> params) {
        if (params == null || params.size() == 0) {
            cacheList = new ArrayList<>();
        } else {
            if (cacheList == null)
                cacheList = new ArrayList<>();
            cacheList.clear();
            cacheList.addAll(params);
        }
    }

    public void addItem(int position, Param item) {
        cacheList.add(position, item);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        cacheList.remove(position);
        notifyItemRemoved(position);
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================


}

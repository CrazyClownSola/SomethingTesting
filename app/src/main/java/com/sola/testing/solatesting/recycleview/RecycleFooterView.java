package com.sola.testing.solatesting.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sola.testing.solatesting.R;
import com.sola.testing.solatesting.recycleview.utils.IRecycleExtraItem;
import com.sola.testing.solatesting.view.load_more.RecycleLoadMoreContainerBase;
import com.sola.testing.solatesting.view.load_more.interfaces.IRecycleLoadMoreContainer;
import com.sola.testing.solatesting.view.load_more.interfaces.IRecycleLoadMoreUIHandler;

/**
 * Description:
 * <p/>
 * author: Sola
 * 2015/10/13
 */
public class RecycleFooterView implements IRecycleExtraItem, IRecycleLoadMoreUIHandler {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    ViewHolder mHolder;

    // ===========================================================
    // Constructors
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================
    @Override
    public View getView(Context context, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.recycle_footer_view,
                parent, false);
        return v;
    }

    @Override
    public RecyclerView.ViewHolder getHolder(Context context, ViewGroup parent) {
        mHolder = new ViewHolder(getView(context, parent));
        return mHolder;
    }

    @Override
    public void refreshView(Context context, RecyclerView.ViewHolder holder) {
//        mHolder.id_footer_text.setVisibility(View.INVISIBLE);
//        mHolder.id_footer_progress.setVisibility(View.INVISIBLE);
    }

    @Override
    public int getViewType() {
        return 0x0301;
    }


    @Override
    public void onLoading(IRecycleLoadMoreContainer container) {
        mHolder.id_footer_text.setVisibility(View.VISIBLE);
        mHolder.id_footer_text.setText("努力加载中，请稍后");
        mHolder.id_footer_progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoadFinish(IRecycleLoadMoreContainer container, boolean empty, boolean hasMore) {
        if (!hasMore) {
//            mHolder.id_footer_text.setVisibility(View.VISIBLE);
            mHolder.id_footer_text.setText("╮(╯_╰)╭ 再怎么加载也没有了");
        } else
            mHolder.id_footer_text.setVisibility(View.INVISIBLE);
        mHolder.id_footer_progress.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onWaitToLoadMore(IRecycleLoadMoreContainer container) {

    }

    @Override
    public void onLoadError(IRecycleLoadMoreContainer container, int errorCode, String errorMessage) {
        mHolder.id_footer_text.setText("(╯‵□′)╯︵┻━┻ 拿不到数据");
        mHolder.id_footer_progress.setVisibility(View.INVISIBLE);
    }

   
    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView id_footer_text;
        ProgressBar id_footer_progress;

        public ViewHolder(
                View v) {
            super(v);
            id_footer_text = (TextView) v.findViewById(R.id.id_footer_text);
            id_footer_progress = (ProgressBar) v.findViewById(R.id.id_footer_progress);
        }
    }
}

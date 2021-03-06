/**
 *   Copyright(c) 2013 DuoKan TV Group
 *    
 *   LoadingListView.java
 *
 *   @author xuanmingliu(liuxuanming@duokan.com)
 *
 *   2013-3-29
 */

package com.miui.video.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListAdapter;

/**
 * @author xuanmingliu
 * 
 */

public class LoadingListView extends FrameLayout {

	private Context mContext;
	private ListViewEx mListView;
	private View mLoadingView;
	private View mEmptyView;
	private boolean mIsLoading;
	
	private LayoutParams mParams;

	public LoadingListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public LoadingListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public LoadingListView(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context) {
		this.mContext = context;
		mListView = new ListViewEx(this.mContext);
		mParams = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT,
				FrameLayout.LayoutParams.MATCH_PARENT);
		mListView.setLayoutParams(mParams);
		addView(mListView);
		setWillNotDraw(false);
	}
	
	public void setListViewMargin(int left, int top, int right, int bottom) {
		mParams.leftMargin = left;
		mParams.topMargin = top;
		mParams.rightMargin = right;
		mParams.bottomMargin = bottom;
		mListView.setLayoutParams(mParams);
	}

	public ListViewEx getListView() {
		return mListView;
	}
	
	public void setLoadingView(View loadingView, int topMargin) {
		if (loadingView == null || this.mLoadingView == loadingView){
			return;
		}
		if (mLoadingView != null){
			removeView(mLoadingView);
		}
		mLoadingView = loadingView;
		FrameLayout.LayoutParams ltParams = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.WRAP_CONTENT,
				FrameLayout.LayoutParams.WRAP_CONTENT);
		ltParams.gravity = Gravity.CENTER_HORIZONTAL;
		ltParams.topMargin = topMargin;
		loadingView.setLayoutParams(ltParams);
		loadingView.setVisibility(View.INVISIBLE);
		addView(loadingView);
	}

	public void setLoadingView(View loadingView) {
		if (loadingView == null || this.mLoadingView == loadingView){
			return;
		}
		if (mLoadingView != null){
			removeView(mLoadingView);
		}
		mLoadingView = loadingView;
		FrameLayout.LayoutParams ltParams = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.WRAP_CONTENT,
				FrameLayout.LayoutParams.WRAP_CONTENT);
		ltParams.gravity = Gravity.CENTER;
		loadingView.setLayoutParams(ltParams);
		loadingView.setVisibility(View.INVISIBLE);
		addView(loadingView);
	}

	public View getLoadingView() {
		return mLoadingView;
	}

	public void setShowLoading(boolean bLoading) {
		this.mIsLoading = bLoading;
		if (mLoadingView == null){
			return;
		}
		mLoadingView.setVisibility(bLoading ? View.VISIBLE : View.INVISIBLE);
	}

	public boolean isLoading() {
		return mIsLoading;
	}
	
	public void setEmptyView(View emptyView, int topMargin) {
		if (emptyView == null){
		    if(mEmptyView != null){
		        removeView(mEmptyView);
		    }
		    mEmptyView = null;
			return;
		}
		if(mEmptyView == emptyView){
		    return;
		}
		if (mEmptyView != null){
			removeView(mEmptyView);
		}
		this.mEmptyView = emptyView;
	      FrameLayout.LayoutParams ltParams = new FrameLayout.LayoutParams(
	                FrameLayout.LayoutParams.WRAP_CONTENT,
	                FrameLayout.LayoutParams.WRAP_CONTENT);
		if(topMargin > 0){
	        ltParams.gravity = Gravity.CENTER_HORIZONTAL;
	        ltParams.topMargin = topMargin;
		}else{
		      ltParams.gravity = Gravity.CENTER;
		}
		emptyView.setLayoutParams(ltParams);
		emptyView.setVisibility(View.INVISIBLE);
		addView(emptyView);
	}

	public void setEmptyView(View emptyView) {
	    setEmptyView(emptyView, 0);
	}

	public View getEmptyView() {
		return mEmptyView;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		if(mIsLoading){
			hideView(mEmptyView);
			showView(mLoadingView);
		}else{
			hideView(mLoadingView);
			ListAdapter adapter = mListView.getAdapter();
			if(adapter == null || 
					(adapter.getCount() - mListView.getHeaderViewsCount() - mListView.getFooterViewsCount()) == 0){
				showView(mEmptyView);
			}else{
				hideView(mEmptyView);
			}
		}
	}
	
	private void showView(View view){
		if(view != null){
			if(view.getVisibility() != View.VISIBLE){
				view.setVisibility(View.VISIBLE);
			}
		}
	}
	
	private void hideView(View view){
		if(view != null){
			if(view.getVisibility() == View.VISIBLE){
				view.setVisibility(View.INVISIBLE);
			}
		}
	}
	
//	public void setLoadingResultView(View loadingResultView) {
//		if( loadingResultView == null || 
//				    this.loadingResultView == loadingResultView)
//			return;
//
//		if( this.loadingResultView != null)
//			removeView(this.loadingResultView);
//		this.loadingResultView = loadingResultView;
//		FrameLayout.LayoutParams ltParams = new 
//				FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,
//						         	FrameLayout.LayoutParams.WRAP_CONTENT);
//		ltParams.gravity = Gravity.CENTER;
//		loadingResultView.setLayoutParams(ltParams);
//		loadingResultView.setVisibility(View.INVISIBLE);
//		addView(loadingResultView);
//	}
//	public View getLoadingResultView() {
//		return loadingResultView;
//	}
//	public void setShowLoadingResult(boolean bShowResult) {
//		if( loadingResultView == null)
//			return;
//		loadingResultView.setVisibility(bShowResult ? View.VISIBLE : View.INVISIBLE);
//	}
//	public void setEmptyView(boolean bShowResult) {
//		if (mEmptyView == null)
//			return;
//		mEmptyView.setVisibility(bShowResult ? View.VISIBLE : View.INVISIBLE);
//	}
}

package com.tongji.myrunninghelper;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements OnClickListener {
	
	//页面起始位置(first=1,second=2,third=3)
	private final int StartPage = 2;
	
	private ViewPager mPager;// 页卡内容
	private List<View> listViews = new ArrayList<View>(); // Tab页面列表
	private TextView Textview;// 页卡头标
	private int currpage =StartPage-1;
	private int[] TextViewList = { R.id.text1, R.id.text2, R.id.text3 };
	private int[] LayViewList = { R.layout.config, R.layout.main, R.layout.history };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}

	private void init() {
		InitTextView();
		InitViewPager();
	}

	private void InitTextView() {
		for (int element : TextViewList) {
			Textview = (TextView) findViewById(element);
			Textview.setOnClickListener(this);
		}
	}

	private void InitViewPager() {
		LayoutInflater mInflater = getLayoutInflater();
		mPager = (ViewPager) findViewById(R.id.vPager);
		for (int element : LayViewList) {
			listViews.add(mInflater.inflate(element, null));
		}

		mPager.setAdapter(new MyPagerAdapter(listViews));
		mPager.setCurrentItem(currpage);
		mPager.setOnPageChangeListener(new MyOnPageChangeListener());
		
		Textview = (TextView) findViewById(TextViewList[currpage]);
		Textview.setTextColor(getResources().getColor(R.color.TitleSelected));
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.text1:
			mPager.setCurrentItem(0);
			break;
		case R.id.text2:
			mPager.setCurrentItem(1);
			break;
		case R.id.text3:
			mPager.setCurrentItem(2);
		}
	}

	public class MyOnPageChangeListener implements OnPageChangeListener {
		@Override
		public void onPageSelected(int arg0) {
			
			Textview = (TextView) findViewById(TextViewList[currpage]);
			Textview.setTextColor(getResources().getColor(R.color.TitleName));
			
			Textview = (TextView) findViewById(TextViewList[arg0]);
			Textview.setTextColor(getResources().getColor(R.color.TitleSelected));
			currpage=arg0;
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
	}

	public class MyPagerAdapter extends PagerAdapter {
		public List<View> mListViews;

		public MyPagerAdapter(List<View> mListViews) {
			this.mListViews = mListViews;
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView(mListViews.get(arg1));
		}

		@Override
		public void finishUpdate(View arg0) {
		}

		@Override
		public int getCount() {
			return mListViews.size();
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			((ViewPager) arg0).addView(mListViews.get(arg1), 0);
			return mListViews.get(arg1);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == (arg1);
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {
		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View arg0) {
		}
	}
}

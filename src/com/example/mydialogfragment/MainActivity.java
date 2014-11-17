package com.example.mydialogfragment;

import android.R.integer;
import android.app.Activity;
import android.app.ActionBar;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.os.Build;

public class MainActivity extends Activity {
	
	public void buttonMethod(View button){
		showDialog(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Holo_Dialog);
	}
	
	private void showDialog(int style, int theme){
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		DialogFragment fragment = MyDialogFragment.newInstance(style, theme);
		fragment.show(ft, "dialog"); // ※FragmentTransactionを渡した場合、show内で自動的にcommitされる。
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		/*
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class MyDialogFragment extends DialogFragment {

		// private static int msStyle = 0;
		// private static int msTheme = 0;
		public static MyDialogFragment newInstance(int style, int theme){
			MyDialogFragment f = new MyDialogFragment();
			// 引数の保持
			// msStyle = style;
			// msTheme = theme;
			Bundle args = new Bundle();
			args.putInt("style", style);
			args.putInt("theme", theme);
			f.setArguments(args);
			// 結果
			return f;
		}
		
		@Override
		public void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			
			// スタイルとテーマ
			int style = getArguments().getInt("style");
			int theme = getArguments().getInt("theme");
			
			// 設定
			setStyle(style, theme);
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// タイトル設定
			getDialog().setTitle("Dialog test");
			
			// Fragment用レイアウトをセット
			View v = inflater.inflate(
				R.layout.fragment_main,
				container,
				false // attach to root (今回はrootにはアタッチしない)
			);
			
			// ボタンイベント
			Button btn1 = (Button)v.findViewById(R.id.button1);
			btn1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					close();
				}
			});
			
			return v;
		}
		
		private void close(){
			this.dismiss();
		}

		public MyDialogFragment() {
		}
		

		/*
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}*/
	}
}

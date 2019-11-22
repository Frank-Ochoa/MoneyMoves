package com.example.moneymoves.Pages;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.moneymoves.R;

public class StartController extends AppCompatActivity
{

	@Override protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		SharedPreferences pref = getSharedPreferences("prefs",MODE_PRIVATE);
		boolean flag = pref.getBoolean("firstStart",true);
	if(flag){
		Intent intent = new Intent(this, IncomePage.class);
		startActivity(intent);
	}
	else{
		//sub for main page
		Intent intent = new Intent(this,
				ExpensesPage.class); //an action something thats going to happen
		startActivity(intent);
	}


	}

	public void incomePage(View view)
	{
		Intent intent = new Intent(this,
				IncomePage.class); //an action something thats going to happen
		startActivity(intent);
	}

	@Override public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings)
		{
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}

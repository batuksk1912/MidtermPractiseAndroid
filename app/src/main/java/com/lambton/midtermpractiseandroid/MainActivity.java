package com.lambton.midtermpractiseandroid;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import com.lambton.midtermpractiseandroid.adapters.UserAdapter;
import com.lambton.midtermpractiseandroid.models.User;
import com.lambton.midtermpractiseandroid.models.Company;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    ArrayList<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        /*int listSize = users.size();

        for (int i = 0; i<listSize; i++){
            Log.i("Member name: ", users.get(i).getName());
        }*/
        processJSON();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new UserAdapter(this, users);
        recyclerView.setAdapter(adapter);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, UserActivity.class));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mMenuInflater = getMenuInflater();
        mMenuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void processJSON() {
        users = new ArrayList<>();
        String jsonString = this.loadJSONFromAsset();
        if (jsonString != null) {
            try {
                JSONArray mJSONArray = new JSONArray(jsonString);
                for (int i = 0; i < mJSONArray.length(); i++) {

                    User mUser = this.getUserObjectFromJSON(mJSONArray.getJSONObject(i));
                    users.add(mUser);

                    /*JSONObject mJSONObject = mJSONArray.getJSONObject(i);

                    if(mJSONObject.has("id"))
                    {
                        int id = mJSONObject.getInt("id");
                    }

                    String name = mJSONObject.getString("name");

                    Log.d("-- JSON -- ", name);

                    //Read Address JSON Object
                    JSONObject mAddress = mJSONObject.getJSONObject("address");

                    String city = mAddress.getString("city");
                    Log.d("-- JSON -- ", name + " : " + city);*/

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private User getUserObjectFromJSON(JSONObject userJsonObject) throws JSONException {
        long id = userJsonObject.getLong(User.KEY_ID);
        String name = userJsonObject.getString("name");
        String username = userJsonObject.getString("username");
        String email = userJsonObject.getString("email");
        String phone = userJsonObject.getString("phone");
        String website = userJsonObject.getString("website");

        JSONObject company = new JSONObject(userJsonObject.getJSONObject("company").toString());
        String companyName = company.getString("name");
        String catchPhrase = company.getString("catchPhrase");
        String bs = company.getString("bs");

        //Start Creating User Object
        User mUser = new User();
        mUser.setId(id);
        mUser.setName(name);
        mUser.setUsername(username);
        mUser.setEmail(email);
        mUser.setPhone(phone);
        mUser.setWebsite(website);

        Company mCompany = new Company();
        mCompany.setName(companyName);
        mCompany.setCatchPhrase(catchPhrase);
        mCompany.setBs(bs);
        mUser.setCompany(mCompany);

        return mUser;
    }

    private String loadJSONFromAsset() {
        String json;
        try {
            InputStream is = getAssets().open("UserList.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            int count = is.read(buffer);
            is.close();
            Log.d("-- COUNT --", String.format("%d Bytes", count));
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}

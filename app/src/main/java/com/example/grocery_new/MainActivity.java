package com.example.grocery_new;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    //for fetching data
    SharedPreferences sharedPreferences;
    //so create a sharedpreferences name and also create key name
    private static final String SHARED_PREF_NAME = "my_pref";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASS = "password";
    private static final String KEY_NAME="name";
    private static final String KEY_CONTACT="contact";
    private static final String KEY_ADDRESS="address";
    String image;
    private static final String KEY_IMAGE="image";
    TextView hometxt,weltxt,searchbtn;
    Button favobtn;
    private List<MovieModel> movieList = new ArrayList<>();
    private MoviesAdapter mAdapter;
    private List<MovieModel> movieList2 = new ArrayList<>();
    private MoviesAdapter mAdapter2;
    private List<MovieModel> movieList3 = new ArrayList<>();
    private MoviesAdapter mAdapter3;
    private List<MovieModel> movieList4 = new ArrayList<>();
    private MoviesAdapter mAdapter4;
    private List<MovieModel> movieList5 = new ArrayList<>();
    private MoviesAdapter mAdapter5;
    private List<MovieModel> movieList6 = new ArrayList<>();
    private MoviesAdapter mAdapter6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        //sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String email = sharedPreferences.getString(KEY_EMAIL, null);
        String password = sharedPreferences.getString(KEY_PASS, null);
        String name = sharedPreferences.getString(KEY_NAME, null);
        String contact = sharedPreferences.getString(KEY_CONTACT, null);
        String address = sharedPreferences.getString(KEY_ADDRESS, null);
         image = sharedPreferences.getString(KEY_IMAGE, null);
        Glide.with(this).load(image).into((CircleImageView)findViewById(R.id.profile_next));
        //Toast.makeText(MainActivity.this," "+email+" "+password,Toast.LENGTH_SHORT).show();
        weltxt=findViewById(R.id.weltxt);
        if(name.contains(" ")) {
            String[] parts=name.split(" ");
            weltxt.setText("Welcome " + parts[0]);
        }else{
            weltxt.setText("Welcome " + name);
        }
        findViewById(R.id.profile_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,profile.class);
                startActivity(i);
                finish();
            }
        });
        findViewById(R.id.searchbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               show_msg();
            }
        });
        findViewById(R.id.favobtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_msg();
            }
        });
        int location[]={R.id.recyclerView1,R.id.recyclerView2,R.id.recyclerView3,R.id.recyclerView4,R.id.recyclerView5,R.id.recyclerView6};
        RecyclerView recyclerView1,recyclerView2,recyclerView3,recyclerView4,recyclerView5,recyclerView6;
        //for (int i=0;i<location.length;i++){

            mAdapter = new MoviesAdapter(movieList);
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            try {
                recyclerView1 = findViewById(R.id.recyclerView1);
                recyclerView1.setLayoutManager(mLayoutManager);
                recyclerView1.setItemAnimator(new DefaultItemAnimator());
                recyclerView1.setAdapter(mAdapter);
                prepareMovieData1("biscuits_and_chocolates");

                mAdapter2 = new MoviesAdapter(movieList2);
                LinearLayoutManager mLayoutManager2 = new LinearLayoutManager(getApplicationContext());
                mLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView2 = findViewById(R.id.recyclerView2);
                recyclerView2.setLayoutManager(mLayoutManager2);
                recyclerView2.setItemAnimator(new DefaultItemAnimator());
                recyclerView2.setAdapter(mAdapter2);
                prepareMovieData2("breakfast_and_instantfood");

                mAdapter3 = new MoviesAdapter(movieList3);
                LinearLayoutManager mLayoutManager3 = new LinearLayoutManager(getApplicationContext());
                mLayoutManager3.setOrientation(LinearLayoutManager.HORIZONTAL);
                   recyclerView3 = findViewById(R.id.recyclerView3);
                   recyclerView3.setLayoutManager(mLayoutManager3);
                   recyclerView3.setItemAnimator(new DefaultItemAnimator());
                   recyclerView3.setAdapter(mAdapter3);
                   prepareMovieData3("breverges");

                mAdapter4 = new MoviesAdapter(movieList4);
                LinearLayoutManager mLayoutManager4 = new LinearLayoutManager(getApplicationContext());
                mLayoutManager4.setOrientation(LinearLayoutManager.HORIZONTAL);
                   recyclerView4 = findViewById(R.id.recyclerView4);
                   recyclerView4.setLayoutManager(mLayoutManager4);
                   recyclerView4.setItemAnimator(new DefaultItemAnimator());
                   recyclerView4.setAdapter(mAdapter4);
                   prepareMovieData4("fruit_and_vegetables");

                mAdapter5 = new MoviesAdapter(movieList5);
                LinearLayoutManager mLayoutManager5 = new LinearLayoutManager(getApplicationContext());
                mLayoutManager5.setOrientation(LinearLayoutManager.HORIZONTAL);
                   recyclerView5 = findViewById(R.id.recyclerView5);
                   recyclerView5.setLayoutManager(mLayoutManager5);
                   recyclerView5.setItemAnimator(new DefaultItemAnimator());
                   recyclerView5.setAdapter(mAdapter5);
                   prepareMovieData5("grocery_and_staples");

                mAdapter6 = new MoviesAdapter(movieList6);
                LinearLayoutManager mLayoutManager6 = new LinearLayoutManager(getApplicationContext());
                mLayoutManager6.setOrientation(LinearLayoutManager.HORIZONTAL);
                   recyclerView6 = findViewById(R.id.recyclerView6);
                   recyclerView6.setLayoutManager(mLayoutManager6);
                   recyclerView6.setItemAnimator(new DefaultItemAnimator());
                   recyclerView6.setAdapter(mAdapter6);
                   prepareMovieData6("household_accessories");
            }catch (Exception e){
                Toast.makeText(MainActivity.this," "+e.toString(),Toast.LENGTH_SHORT).show();
            }

        //}
       /* mAdapter = new MoviesAdapter(movieList);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        prepareMovieData();*/
    }
    private void prepareMovieData1(String t_name) {
        String[] table={"biscuits_and_chocolates","breakfast_and_instantfood","breverges","fruit_and_vegetables","grocery_and_staples","household_accessories"};
        String url = "https://grocerryproject.000webhostapp.com/androidfiles/display_product.php";
        StringBuffer buffer = new StringBuffer();
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        if (response != null) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                JSONArray jsonArray = jsonObject.getJSONArray("result");
                                int records = jsonArray.length();
                                if (records >0) {
                                    String imgadd="https://grocerryproject.000webhostapp.com/androidfiles/img/";
                                    for(int j=0;j<jsonArray.length();j++) {
                                        JSONObject obj = jsonArray.getJSONObject(j);
                                        String img=obj.getString("p_img");
                                        String name=obj.getString("p_name");
                                        String price=obj.getString("p_price");
                                        String quantity=obj.getString("p_quantity");
                                        String description=obj.getString("p_description");
                                        MovieModel movie = new MovieModel(imgadd+img, name, price, quantity, description, MainActivity.this);
                                        movieList.add(movie);

                                    }
                                    mAdapter.notifyDataSetChanged();

                                } else {
                                    /*buffer.append("Record Does Not Exists,Please Cheak Entered Details");
                                    builder.setCancelable(true);
                                    //builder.setTitle("Entries");
                                    builder.setMessage(buffer.toString());
                                    builder.show();*/
                                }
                                //responsetxt.setText(" "+records);
                            } catch (JSONException e) {
                                buffer.append("ERROR OCCURED!!!!...");
                                builder.setCancelable(true);
                                //builder.setTitle("Entries");
                                builder.setMessage(buffer.toString());
                                builder.show();
                            }
                        }
                               /* buffer.append(response);
                                AlertDialog.Builder builder=new AlertDialog.Builder(SlideshowFragment.super.getContext());
                                builder.setCancelable(true);
                                //builder.setTitle("Entries");
                                builder.setMessage(buffer.toString());
                                builder.show();*/

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                buffer.append("Cann't Load Product.\nPlease Check Your Internet Connection");
                builder.setCancelable(true);
                //builder.setTitle("Entries");
                builder.setMessage(buffer.toString());
                builder.show();

            }

        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("table", t_name);
                return data;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        //ending

        //mAdapter.notifyDataSetChanged();
    }
    private void prepareMovieData2(String t_name) {
        String[] table={"biscuits_and_chocolates","breakfast_and_instantfood","breverges","fruit_and_vegetables","grocery_and_staples","household_accessories"};
        String url = "https://grocerryproject.000webhostapp.com/androidfiles/display_product.php";
        StringBuffer buffer = new StringBuffer();
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        if (response != null) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                JSONArray jsonArray = jsonObject.getJSONArray("result");
                                int records = jsonArray.length();
                                if (records >0) {
                                    String imgadd="https://grocerryproject.000webhostapp.com/androidfiles/img/";
                                    for(int j=0;j<jsonArray.length();j++) {
                                        JSONObject obj = jsonArray.getJSONObject(j);
                                        String img=obj.getString("p_img");
                                        String name=obj.getString("p_name");
                                        String price=obj.getString("p_price");
                                        String quantity=obj.getString("p_quantity");
                                        String description=obj.getString("p_description");
                                        MovieModel movie = new MovieModel(imgadd+img, name, price, quantity, description, MainActivity.this);
                                        movieList2.add(movie);

                                    }
                                    mAdapter2.notifyDataSetChanged();

                                } else {
                                    /*buffer.append("Record Does Not Exists,Please Cheak Entered Details");
                                    builder.setCancelable(true);
                                    //builder.setTitle("Entries");
                                    builder.setMessage(buffer.toString());
                                    builder.show();*/
                                }
                                //responsetxt.setText(" "+records);
                            } catch (JSONException e) {
                                buffer.append("ERROR OCCURED!!!!...");
                                builder.setCancelable(true);
                                //builder.setTitle("Entries");
                                builder.setMessage(buffer.toString());
                                builder.show();
                            }
                        }
                               /* buffer.append(response);
                                AlertDialog.Builder builder=new AlertDialog.Builder(SlideshowFragment.super.getContext());
                                builder.setCancelable(true);
                                //builder.setTitle("Entries");
                                builder.setMessage(buffer.toString());
                                builder.show();*/

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                buffer.append("Cann't Load Product.\nPlease Check Your Internet Connection");
                builder.setCancelable(true);
                //builder.setTitle("Entries");
                builder.setMessage(buffer.toString());
                builder.show();

            }

        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("table", t_name);
                return data;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        //ending

        //mAdapter.notifyDataSetChanged();
    }
    private void prepareMovieData3(String t_name) {
        String[] table={"biscuits_and_chocolates","breakfast_and_instantfood","breverges","fruit_and_vegetables","grocery_and_staples","household_accessories"};
        String url = "https://grocerryproject.000webhostapp.com/androidfiles/display_product.php";
        StringBuffer buffer = new StringBuffer();
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        if (response != null) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                JSONArray jsonArray = jsonObject.getJSONArray("result");
                                int records = jsonArray.length();
                                if (records >0) {
                                    String imgadd="https://grocerryproject.000webhostapp.com/androidfiles/img/";
                                    for(int j=0;j<jsonArray.length();j++) {
                                        JSONObject obj = jsonArray.getJSONObject(j);
                                        String img=obj.getString("p_img");
                                        String name=obj.getString("p_name");
                                        String price=obj.getString("p_price");
                                        String quantity=obj.getString("p_quantity");
                                        String description=obj.getString("p_description");
                                        MovieModel movie = new MovieModel(imgadd+img, name, price, quantity, description, MainActivity.this);
                                        movieList3.add(movie);

                                    }
                                    mAdapter3.notifyDataSetChanged();

                                } else {
                                    /*buffer.append("Record Does Not Exists,Please Cheak Entered Details");
                                    builder.setCancelable(true);
                                    //builder.setTitle("Entries");
                                    builder.setMessage(buffer.toString());
                                    builder.show();*/
                                }
                                //responsetxt.setText(" "+records);
                            } catch (JSONException e) {
                                buffer.append("ERROR OCCURED!!!!...");
                                builder.setCancelable(true);
                                //builder.setTitle("Entries");
                                builder.setMessage(buffer.toString());
                                builder.show();
                            }
                        }
                               /* buffer.append(response);
                                AlertDialog.Builder builder=new AlertDialog.Builder(SlideshowFragment.super.getContext());
                                builder.setCancelable(true);
                                //builder.setTitle("Entries");
                                builder.setMessage(buffer.toString());
                                builder.show();*/

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                buffer.append("Cann't Load Product.\nPlease Check Your Internet Connection");
                builder.setCancelable(true);
                //builder.setTitle("Entries");
                builder.setMessage(buffer.toString());
                builder.show();

            }

        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("table", t_name);
                return data;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        //ending

        //mAdapter.notifyDataSetChanged();
    }
    private void prepareMovieData4(String t_name) {
        String[] table={"biscuits_and_chocolates","breakfast_and_instantfood","breverges","fruit_and_vegetables","grocery_and_staples","household_accessories"};
        String url = "https://grocerryproject.000webhostapp.com/androidfiles/display_product.php";
        StringBuffer buffer = new StringBuffer();
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        if (response != null) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                JSONArray jsonArray = jsonObject.getJSONArray("result");
                                int records = jsonArray.length();
                                if (records >0) {
                                    String imgadd="https://grocerryproject.000webhostapp.com/androidfiles/img/";
                                    for(int j=0;j<jsonArray.length();j++) {
                                        JSONObject obj = jsonArray.getJSONObject(j);
                                        String img=obj.getString("p_img");
                                        String name=obj.getString("p_name");
                                        String price=obj.getString("p_price");
                                        String quantity=obj.getString("p_quantity");
                                        String description=obj.getString("p_description");
                                        MovieModel movie = new MovieModel(imgadd+img, name, price, quantity, description, MainActivity.this);
                                        movieList4.add(movie);

                                    }
                                    mAdapter4.notifyDataSetChanged();

                                } else {
                                    /*buffer.append("Record Does Not Exists,Please Cheak Entered Details");
                                    builder.setCancelable(true);
                                    //builder.setTitle("Entries");
                                    builder.setMessage(buffer.toString());
                                    builder.show();*/
                                }
                                //responsetxt.setText(" "+records);
                            } catch (JSONException e) {
                                buffer.append("ERROR OCCURED!!!!...");
                                builder.setCancelable(true);
                                //builder.setTitle("Entries");
                                builder.setMessage(buffer.toString());
                                builder.show();
                            }
                        }
                               /* buffer.append(response);
                                AlertDialog.Builder builder=new AlertDialog.Builder(SlideshowFragment.super.getContext());
                                builder.setCancelable(true);
                                //builder.setTitle("Entries");
                                builder.setMessage(buffer.toString());
                                builder.show();*/

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                buffer.append("Cann't Load Product.\nPlease Check Your Internet Connection");
                builder.setCancelable(true);
                //builder.setTitle("Entries");
                builder.setMessage(buffer.toString());
                builder.show();

            }

        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("table", t_name);
                return data;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        //ending

        //mAdapter.notifyDataSetChanged();
    }
    private void prepareMovieData5(String t_name) {
        String[] table={"biscuits_and_chocolates","breakfast_and_instantfood","breverges","fruit_and_vegetables","grocery_and_staples","household_accessories"};
        String url = "https://grocerryproject.000webhostapp.com/androidfiles/display_product.php";
        StringBuffer buffer = new StringBuffer();
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        if (response != null) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                JSONArray jsonArray = jsonObject.getJSONArray("result");
                                int records = jsonArray.length();
                                if (records >0) {
                                    String imgadd="https://grocerryproject.000webhostapp.com/androidfiles/img/";
                                    for(int j=0;j<jsonArray.length();j++) {
                                        JSONObject obj = jsonArray.getJSONObject(j);
                                        String img=obj.getString("p_img");
                                        String name=obj.getString("p_name");
                                        String price=obj.getString("p_price");
                                        String quantity=obj.getString("p_quantity");
                                        String description=obj.getString("p_description");
                                        MovieModel movie = new MovieModel(imgadd+img, name, price, quantity, description, MainActivity.this);
                                        movieList5.add(movie);

                                    }
                                    mAdapter5.notifyDataSetChanged();

                                } else {
                                    /*buffer.append("Record Does Not Exists,Please Cheak Entered Details");
                                    builder.setCancelable(true);
                                    //builder.setTitle("Entries");
                                    builder.setMessage(buffer.toString());
                                    builder.show();*/
                                }
                                //responsetxt.setText(" "+records);
                            } catch (JSONException e) {
                                buffer.append("ERROR OCCURED!!!!...");
                                builder.setCancelable(true);
                                //builder.setTitle("Entries");
                                builder.setMessage(buffer.toString());
                                builder.show();
                            }
                        }
                               /* buffer.append(response);
                                AlertDialog.Builder builder=new AlertDialog.Builder(SlideshowFragment.super.getContext());
                                builder.setCancelable(true);
                                //builder.setTitle("Entries");
                                builder.setMessage(buffer.toString());
                                builder.show();*/

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                buffer.append("Cann't Load Product.\nPlease Check Your Internet Connection");
                builder.setCancelable(true);
                //builder.setTitle("Entries");
                builder.setMessage(buffer.toString());
                builder.show();

            }

        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("table", t_name);
                return data;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        //ending

        //mAdapter.notifyDataSetChanged();
    }
    private void prepareMovieData6(String t_name) {
        String[] table={"biscuits_and_chocolates","breakfast_and_instantfood","breverges","fruit_and_vegetables","grocery_and_staples","household_accessories"};
        String url = "https://grocerryproject.000webhostapp.com/androidfiles/display_product.php";
        StringBuffer buffer = new StringBuffer();
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        if (response != null) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                JSONArray jsonArray = jsonObject.getJSONArray("result");
                                int records = jsonArray.length();
                                if (records >0) {
                                    String imgadd="https://grocerryproject.000webhostapp.com/androidfiles/img/";
                                    for(int j=0;j<jsonArray.length();j++) {
                                        JSONObject obj = jsonArray.getJSONObject(j);
                                        String img=obj.getString("p_img");
                                        String name=obj.getString("p_name");
                                        String price=obj.getString("p_price");
                                        String quantity=obj.getString("p_quantity");
                                        String description=obj.getString("p_description");
                                        MovieModel movie = new MovieModel(imgadd+img, name, price, quantity, description, MainActivity.this);
                                        movieList6.add(movie);

                                    }
                                    mAdapter6.notifyDataSetChanged();

                                } else {
                                   /* buffer.append("Record Does Not Exists,Please Cheak Entered Details");
                                    builder.setCancelable(true);
                                    //builder.setTitle("Entries");
                                    builder.setMessage(buffer.toString());
                                    builder.show();*/
                                }
                                //responsetxt.setText(" "+records);
                            } catch (JSONException e) {
                                buffer.append("ERROR OCCURED!!!!...");
                                builder.setCancelable(true);
                                //builder.setTitle("Entries");
                                builder.setMessage(buffer.toString());
                                builder.show();
                            }
                        }
                               /* buffer.append(response);
                                AlertDialog.Builder builder=new AlertDialog.Builder(SlideshowFragment.super.getContext());
                                builder.setCancelable(true);
                                //builder.setTitle("Entries");
                                builder.setMessage(buffer.toString());
                                builder.show();*/

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                buffer.append("Cann't Load Product.\nPlease Check Your Internet Connection");
                builder.setCancelable(true);
                //builder.setTitle("Entries");
                builder.setMessage(buffer.toString());
                builder.show();

            }

        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("table", t_name);
                return data;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        //ending

        //mAdapter.notifyDataSetChanged();
    }

    public void onBackPressed() {
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("EXIT:-");
        builder.setMessage("DO YOU WANT TO EXIT?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Toast.makeText(MainActivity.this,"yes button",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Toast.makeText(MainActivity.this,"no button",Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });
        builder.create();
        builder.show();
    }
    public void show_msg(){
        Toast.makeText(MainActivity.this," Still Working",Toast.LENGTH_SHORT).show();
    }
}
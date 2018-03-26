package com.debajit.volley.moviedirectory.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.debajit.volley.moviedirectory.Model.Movie;
import com.debajit.volley.moviedirectory.R;
import com.debajit.volley.moviedirectory.Util.Constants;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MovieDetailActivity extends AppCompatActivity {

    private Movie movie;
    private TextView movieTitle;
    private ImageView movieImage;
    private TextView movieYear;
    private TextView director;
    private TextView actors;
    private TextView category;
    private TextView rating;
    private TextView writers;
    private TextView plot;
    private TextView boxOffice;
    private TextView runtime;

    private RequestQueue queue;
    private String movieId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        queue = Volley.newRequestQueue(this);

        movie = (Movie) getIntent().getSerializableExtra("movie");
        movieId = movie.getImdbId();

        setUpUI();
        getMoviedetails(movieId);
       // Log.d("idmb: ",movie.getImdbId().toString());


    }

    private void setUpUI() {
        movieTitle = (TextView) findViewById(R.id.movieTitleIDDet);
        movieImage = (ImageView) findViewById(R.id.movieImageIDDet);
        movieYear = (TextView) findViewById(R.id.movieReleaseIDDet);
        director = (TextView) findViewById(R.id.directedByDet);
        actors = (TextView) findViewById(R.id.actorsDet);
        category = (TextView) findViewById(R.id.movieCatIDDet);
        rating = (TextView) findViewById(R.id.movieRatingIDDet);
        writers = (TextView) findViewById(R.id.writersDet);
        plot = (TextView) findViewById(R.id.plotDet);
        boxOffice = (TextView) findViewById(R.id.boxOfficeDet);
        runtime = (TextView) findViewById(R.id.runtimeDet);
    }

    private void getMoviedetails(String id) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Constants.URL + id + Constants.URL_END, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try{

                    if(response.has("Ratings")){
                        JSONArray ratings = response.getJSONArray("Ratings");

                        String source = null;
                        String value = null;

                        if(ratings.length()>0){
                            JSONObject mRatings = ratings.getJSONObject(ratings.length()-1);
                            source = mRatings.getString("Source");
                            value = mRatings.getString("Value");
                            rating.setText("Ratings:\n" + source + " : " + value);
                        }else{
                            rating.setText("Ratings : N/A");
                        }

                        movieTitle.setText(response.getString("Title"));
                        category.setText("Category: "+response.getString("Type"));
                        movieYear.setText("Released: "+response.getString("Released"));
                        director.setText("Director: "+response.getString("Director"));
                        writers.setText("Writers: "+response.getString("Writer"));
                        plot.setText("Plot: "+response.getString("Plot"));
                        runtime.setText("Runtime: "+response.getString("Runtime"));
                        actors.setText("Actors: "+response.getString("Actors"));

                        Picasso.with(getApplicationContext()).load(response.getString("Poster")).into(movieImage);

                        boxOffice.setText("Box Office: "+response.getString("BoxOffice"));
                        //Log.d("Title: ", response.getString("Title"));

                    }
                    /*else{

                        JSONArray ratings = response.getJSONArray("Ratings");

                        movieTitle.setText(response.getString("Title"));
                        movieYear.setText("Released: "+response.getString("Released"));
                        director.setText("Director: "+response.getString("Director"));
                        writers.setText("Writers: "+response.getString("Writer"));
                        plot.setText("Plot: "+response.getString("Plot"));
                        runtime.setText("Runtime: "+response.getString("Runtime"));
                        actors.setText("Actors: "+response.getString("Actors"));

                        Picasso.with(getApplicationContext()).load(response.getString("Poster")).into(movieImage);

                        boxOffice.setText("Box Office: "+response.getString("BoxOffice"));
                    }*/

                }catch (JSONException e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                VolleyLog.d("Error: ",error.getMessage());

            }
        });
        queue.add(jsonObjectRequest);

    }

}

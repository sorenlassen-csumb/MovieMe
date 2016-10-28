package com.movie.me.android;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.movie.me.android.util.FetchMovieTask;


public class SearchMovie extends AppCompatActivity implements View.OnClickListener{
    private EditText movieSearchField;
    private Button movieSearchButton;

    private String movieInput = new String();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movie);

        movieSearchField = (EditText) findViewById(R.id.search_movie_text);
        movieSearchButton = (Button) findViewById(R.id.search_movie_button);

        movieSearchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                movieInput = movieSearchField.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        movieSearchButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.search_movie_button) {
            searchResults();
        }
    }

    public void searchResults() {
        if(movieInput.length() != 0) {
            new FetchMovieTask(this).execute(movieInput);
        }
    }

}

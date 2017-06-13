package id.sch.smktelkom_mlg.privateassignment.xirpl303.cinemaxxiv;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassignment.xirpl303.cinemaxxiv.adapter.NowPlaying;
import id.sch.smktelkom_mlg.privateassignment.xirpl303.cinemaxxiv.model.Results;
import id.sch.smktelkom_mlg.privateassignment.xirpl303.cinemaxxiv.model.ResultsResponse;
import id.sch.smktelkom_mlg.privateassignment.xirpl303.cinemaxxiv.service.GsonGetRequest;
import id.sch.smktelkom_mlg.privateassignment.xirpl303.cinemaxxiv.service.VolleySingleton;


/**
 * A simple {@link Fragment} subclass.
 */
public class TeaterFragment extends Fragment {
    ArrayList<Results> mlist = new ArrayList<>();
    NowPlaying teaterfragment;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_teater2, container, false);

        RecyclerView recyclerView = (RecyclerView) rootview.findViewById(R.id.RecyclerView);
        recyclerView.setHasFixedSize(true);
        teaterfragment = new NowPlaying(this, mlist, getContext());
        recyclerView.setAdapter(teaterfragment);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(llm);

        downloadDataResource();
        return rootview;
    }
    private void downloadDataResource() {
        String url = "https://api.themoviedb.org/3/movie/top_rated?api_key=c29e2e9238f22ef49f7484758fab4ac4&";

        GsonGetRequest<ResultsResponse> myRequest = new GsonGetRequest<ResultsResponse>
                (url, ResultsResponse.class, null, new Response.Listener<ResultsResponse>() {

                    @Override
                    public void onResponse(ResultsResponse response) {
                        Log.d("FLOW", "onResponse: " + (new Gson().toJson(response)));
                        mlist.addAll(response.results);
                        teaterfragment.notifyDataSetChanged();
                    }

                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("FLOW", "onErrorResponse: ", error);
                    }
                });
        VolleySingleton.getInstance(getActivity()).addToRequestQueue(myRequest);
    }

}

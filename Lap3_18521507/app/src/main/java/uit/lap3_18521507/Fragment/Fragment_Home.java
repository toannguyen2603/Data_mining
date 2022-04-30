package uit.lap3_18521507.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uit.lap3_18521507.Adapter.HomeAdapter;
import uit.lap3_18521507.Model.Article;
import uit.lap3_18521507.Model.News;
import uit.lap3_18521507.R;
import uit.lap3_18521507.Service.APIService;
import uit.lap3_18521507.Service.DataService;

public class Fragment_Home extends Fragment {


    View view;
    RecyclerView recyclerViewNews;
    HomeAdapter homeAdapter;
    ArrayList<Article> articles;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home,container, false);
        //       get layout
        recyclerViewNews = view.findViewById(R.id.recyclerViewNews);
        GetData();
        return view;
    }

    private void GetData() {
//        get service
        DataService dataService = APIService.getService();
//        get list data through the service
        Call<News> callback = dataService.GetDataNews();

        callback.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {

                  articles = (ArrayList<Article>) response.body().getArticles();

                  homeAdapter = new HomeAdapter(getActivity(), articles);

                  LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                  linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                  recyclerViewNews.setLayoutManager(linearLayoutManager);

                  recyclerViewNews.setAdapter(homeAdapter);

            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {

            }
        });



    }
}

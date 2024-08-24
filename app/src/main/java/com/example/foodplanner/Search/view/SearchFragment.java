package com.example.foodplanner.Search.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.foodplanner.Favorite.view.FavoriteAdapter;
import com.example.foodplanner.R;
import com.example.foodplanner.Search.Repo.SearchRemoteRepoImpl;
import com.example.foodplanner.Search.presenter.SearchPresenter;
import com.example.foodplanner.network.model.Meal;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchFragment extends Fragment implements ISearch{

    SearchPresenter presenter;
    ChipGroup chipGroup;
    SearchAdapter adapter;
    String chipText;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new SearchPresenter(SearchRemoteRepoImpl.getInstance(),this);
        chipText="";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText searchTxt =  view.findViewById(R.id.searchTxt);
        chipGroup = view.findViewById(R.id.chipGroup);
        ///
        RecyclerView recyclerView = view.findViewById(R.id.searchRV);
        LinearLayoutManager manager = new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter = new SearchAdapter(getContext());
        recyclerView.setAdapter(adapter);
        /////
        chipGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId != -1) {
                Chip selectedChip = group.findViewById(checkedId);
                chipText = selectedChip.getText().toString();
            }
        });
        ////
        Observable.create(emitter -> {
                    searchTxt.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            /*chipGroup.setOnCheckedChangeListener((group, checkedId) -> {
                                if (checkedId != -1) {
                                    Chip selectedChip = group.findViewById(checkedId);
                                    String chipText = selectedChip.getText().toString();
                                    Log.i("STAG", "onTextChanged: checked id = "+checkedId);
                                    // Handle the selected chip action here
                                    if(checkedId==0){
                                        emitter.onNext(s);
                                    } else if (checkedId == 1) {
                                        emitter.onNext(s);
                                    }else {
                                        emitter.onNext(s);
                                    }
                                }
                            });*/

                            List<String> newNames = new ArrayList<>();
                            newNames.add(s.toString().toLowerCase());
                            emitter.onNext(newNames);
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                }).debounce(1000, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s->searchData((List<String>) s));/*s -> adapter.setList((ArrayList<String>) s)*/ //s-> Log.i("STAG", "onViewCreated: "+s));


    }
    public void searchData(List<String> data){
        Log.i("STAG", "searchData: "+data);
        // Handle the selected chip action here
        if(chipText.equals("Area")){
            presenter.filterByArea(data.get(0).toLowerCase());
        } else if (chipText.equals("Ingredient")) {
            presenter.filterByIngredient(data.get(0).toLowerCase());
        }else if(chipText.equals("Category")){
            presenter.filterByCategory(data.get(0).toLowerCase());
        }else{
            presenter.getMealByName(data.get(0));
        }
    }

    @Override
    public void setSearchResult(Meal[] meals) {
        adapter.setMealsList(Arrays.asList(meals));
    }
}
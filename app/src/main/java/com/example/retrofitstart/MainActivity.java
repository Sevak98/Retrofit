package com.example.retrofitstart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private List<Item> data = new ArrayList<>();
    private RecyclerView dataList;
    private ItemAdapter adapter;
    private ProgressDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadingDialog = new ProgressDialog(MainActivity.this);
        loadingDialog.setMessage("Please wait loading ...");
        loadingDialog.show();


        GetDataService service = ConnectRetrofit.getRetrofit().create(GetDataService.class);
        Call<List<Item>> call = service.getAllPhotos();

        call.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                loadingDialog.dismiss();
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                loadingDialog.dismiss();
                Toast.makeText(MainActivity.this ,"Failed tto get data" , Toast.LENGTH_SHORT ).show();
            }
        });
    }

    public  void generateDataList(List<Item> photoList){
        dataList = findViewById(R.id.dataList);
        adapter = new ItemAdapter(this, photoList);
        dataList.setAdapter(adapter);
        dataList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }
}
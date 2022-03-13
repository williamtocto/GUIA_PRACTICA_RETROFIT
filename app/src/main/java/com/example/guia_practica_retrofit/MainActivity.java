package com.example.guia_practica_retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView mJsonview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mJsonview=findViewById(R.id.jsonText);
        getPost();
    }
    private void getPost(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://androidtutorials.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InterfaceRetro json=retrofit.create(InterfaceRetro.class);
        Call<List<Modelo>> call= json.getPosts();
        call.enqueue(new Callback<List<Modelo>>() {
            @Override
            public void onResponse(Call<List<Modelo>> call, Response<List<Modelo>> response) {
                if (!response.isSuccessful()){
                    mJsonview.setText("Codigo: "+response.code());
                    return;
                }
                List<Modelo> post=response.body();

                for (Modelo m: post){
                    String content="";
                    content += "Id: "+ m.getId()+ "\n";
                    content += "Nombre: "+ m.getName() + "\n";
                    content += "Apellido: "+ m.getLastName() + "\n";
                    content += "NickName: "+ m.getNickName() + "\n\n";
                    mJsonview.append(content);

                }
            }
            @Override
            public void onFailure(Call<List<Modelo>> call, Throwable t) {

            }
        });


    }


   }



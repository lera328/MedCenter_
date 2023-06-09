package com.example.medcenter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class Fragment_anPopular extends Fragment {



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public List<Analis> analisList;

    public Fragment_anPopular() {
        // Required empty public constructor
    }




    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment Fragment_anPopular.
     */
    // TODO: Rename and change types and number of parameters
   //public Fragment_anPopular newInstance(String param1, String param2) {
   //    Fragment_anPopular fragment = new Fragment_anPopular();
   //    Bundle args = new Bundle();
   //    args.putString(ARG_PARAM1, param1);
   //    args.putString(ARG_PARAM2, param2);
   //    fragment.setArguments(args);
   //    return fragment;
   //}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_an_popular, container, false);
        //List<cardAnalisModel> cardList = new ArrayList<>();
        analisList = new ArrayList();
        //String base_url="https://medic.madskill.ru/api/";
        String base_url= "https://5cad-95-189-162-4.ngrok-free.app/api/";
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InterfaceApi interfaceApi=retrofit.create(InterfaceApi.class);
        Call<AnalisResult> call=interfaceApi.getListAnalises();
        call.enqueue(new Callback<AnalisResult>() {
            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onResponse(Call<AnalisResult> call, Response<AnalisResult> response) {
                if (response.isSuccessful())
                {
                    List<Analis> analisResponseList = response.body().getAnalyses();
                    for (Analis analis: analisResponseList){
                        analis.setPrice(analis.getPrice().replace(".00",""));
                        if (analis.getCategory().contains("1")) analisList.add(analis);
                    }
                    cardAnalisAdapterNew adapter = new cardAnalisAdapterNew(analisList, getActivity(), (cardAnalisAdapterNew.OnCardClickListener) getActivity());
                    recyclerView = v.findViewById(R.id.recyclerView2);
                    recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext(), LinearLayoutManager.VERTICAL, false));
                    recyclerView.setAdapter(adapter);
                } else {
                    ErrorMessage errorDialog = new ErrorMessage();
                    errorDialog.show(getChildFragmentManager(),"Error message");}
            }
            @Override
            public void onFailure(Call<AnalisResult> call, Throwable t) {
                ErrorMessage errorDialog = new ErrorMessage();
                errorDialog.show(getChildFragmentManager(),"Error message");
            }
        });
        return v;
    }


}
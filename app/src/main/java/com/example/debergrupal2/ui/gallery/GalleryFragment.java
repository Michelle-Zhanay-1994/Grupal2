package com.example.debergrupal2.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.debergrupal2.R;
import com.example.debergrupal2.adapter.ListAdapterP;
import com.example.debergrupal2.database.Persona;
import com.example.debergrupal2.database.PersonaLab;
import com.example.debergrupal2.ui.home.HomeViewModel;
import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private ListAdapterP listItemAdapter;
    private ArrayList<Persona> listaNombres=new ArrayList<>();
    private ListView listView;
    private PersonaLab mPersonaLab;
    private GalleryViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        //final TextView textView = root.findViewById(R.id.text_gallery);
        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
        //        textView.setText(s);
            }});

        super.onCreate(savedInstanceState);
        mPersonaLab=new PersonaLab(getActivity().getApplicationContext());
        listView=root.findViewById(R.id.list);

        getAllPersonas();
        listItemAdapter=new ListAdapterP(getActivity().getApplicationContext(),listaNombres);
        listView.setAdapter(listItemAdapter);
        return root;
    }
    public void getAllPersonas(){
        listaNombres.clear();
        listaNombres.addAll(mPersonaLab.getPersonas());
    }
}
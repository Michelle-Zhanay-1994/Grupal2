package com.example.debergrupal2.ui.home;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.debergrupal2.ui.home.HomeFragment;
import com.example.debergrupal2.R;
import com.example.debergrupal2.adapter.ListAdapterP;
import com.example.debergrupal2.database.Persona;
import com.example.debergrupal2.database.PersonaLab;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private HomeViewModel homeViewModel;
    private ListAdapterP listItemAdapter;
    private ArrayList<Persona> listaNombres=new ArrayList<>();
    private ListView listView;
    private PersonaLab mPersonaLab;
    private Persona mPersona;
    private TextView guardar;
    private Button bguardar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.editTextTextPersonName);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);

            }
        });
        super.onCreate(savedInstanceState);
        mPersonaLab=new PersonaLab(getActivity().getApplicationContext());

        listView=root.findViewById(R.id.list);
        guardar=root.findViewById(R.id.editTextTextPersonName);
        bguardar=root.findViewById(R.id.buttonGuardar);
        bguardar.setOnClickListener(this);

        getAllPersonas();
        listItemAdapter=new ListAdapterP(getActivity().getApplicationContext(),listaNombres);
        listView.setAdapter(listItemAdapter);
        return root;}

    public void insertPersonas() {
        mPersona=new Persona();
        mPersona.setNombre(guardar.getText().toString());
        mPersonaLab.addPersona(mPersona);
        guardar.setText(" ");}


    public void getAllPersonas(){
        listaNombres.clear();
        listaNombres.addAll(mPersonaLab.getPersonas()); }

    @Override
    public void onClick(View view) {
        if (view==bguardar) {
            insertPersonas();
            getAllPersonas();
            listItemAdapter.notifyDataSetChanged(); }
    }
}

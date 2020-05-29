package com.example.finalproject.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.finalproject.Item;
import com.example.finalproject.ItemAdapter;
import com.example.finalproject.R;

import java.util.Objects;


public class TrainingFragment extends Fragment{

    private TextView money;
    private int current_money = 0;
    private static final String APP_PREFERENCES = "mySettings";
    private static final String currentMoneySaver = "counter";
    private SharedPreferences mSettings;
    private int PowerAfterShop = 0

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_training, container, false);
        Item[] a = new Item[0];
        ItemAdapter itemAdapter = new ItemAdapter(getContext(), a);
        int MoneyAfterShop = itemAdapter.getMoneyAfterShop(Objects.requireNonNull(getContext()));
        NegativeResultOfFightFragment negativeResultOfFightFragment = new NegativeResultOfFightFragment();
        PositiveResultOfFightFragment positiveResultOfFightFragment = new PositiveResultOfFightFragment();
        int MoneyAfterFightPositive = positiveResultOfFightFragment.getMoneyAfterFight(getContext());
        int MoneyAfterFight = negativeResultOfFightFragment.getMoneyAfterFight(getContext());
        PowerAfterShop = itemAdapter.getPowerAfterShopFromShop(getContext());

        mSettings = Objects.requireNonNull(this.getActivity()).getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        money = v.findViewById(R.id.money);
        int mCounter = mSettings.getInt(currentMoneySaver, 0);
        current_money = mCounter;
        if(MoneyAfterShop != current_money) current_money = MoneyAfterShop;
        if(MoneyAfterFight != current_money) current_money = MoneyAfterFight;
        if(MoneyAfterFightPositive != current_money) current_money = MoneyAfterFightPositive;
        money.setText(Integer.toString(mCounter));
        v.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint({"ClickableViewAccessibility", "ApplySharedPref"})
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    //первый спрайт
                    money.setText(Integer.toString(current_money + getPowerAfterShop()));
                    current_money += getPowerAfterShop();
                    SharedPreferences.Editor editor = mSettings.edit();
                    editor.putInt(currentMoneySaver, current_money);
                    editor.commit();
                }
            return true;
            }
        });
        return v;
    }

    private int getPowerAfterShop() {
        return PowerAfterShop;
    }

    public int getMoney(Context ctx){
        mSettings = ctx.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        return mSettings.getInt(currentMoneySaver, 0);
    }

}

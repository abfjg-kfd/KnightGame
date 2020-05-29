package com.example.finalproject.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.finalproject.Item;
import com.example.finalproject.ItemAdapter;
import com.example.finalproject.R;

public class ShopFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_shop, container, false);

        final ListView List = v.findViewById(R.id.listView);
        final ItemAdapter weaponAdapter = new ItemAdapter(getContext(), createWeapon());
        final ItemAdapter chestAdapter = new ItemAdapter(getContext(), createChest());
        final ItemAdapter bootsAdapter = new ItemAdapter(getContext(), createBoots());
        final ItemAdapter headsAdapter = new ItemAdapter(getContext(), createHeads());
        final Button weapon_list = v.findViewById(R.id.weapon);
        final Button chest_list = v.findViewById(R.id.stomach);
        final Button boots_list = v.findViewById(R.id.shoes);
        final Button heads_list = v.findViewById(R.id.head);
        weapon_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List.setAdapter(weaponAdapter);
            }
        });
        chest_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List.setAdapter(chestAdapter);
            }
        });
        boots_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List.setAdapter(bootsAdapter);
            }
        });
        heads_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List.setAdapter(headsAdapter);
            }
        });
        return v;
    }


    private Item[] createWeapon() {
        Item[] Weapon = new Item[5];
        String[] names = {"Сломанный меч", "Меч палача", "Хороший гладиус", "Элитный клеймор", "Лучший меч короля"};
        int[] prices = {20, 200, 2000, 20000, 200000};
        int[] powers = {2, 20, 200, 2000, 2000000};
        String[] descriptions = {"Даже у моего дяди лучше", "Может срубить голову, проверено",
                "Прям как у настоящего легионера", "За такой и умереть можно", "Где ты его вообще взял?"};
        for (int i = 0; i < Weapon.length; i++) {
            Item weapon = new Item();
            weapon.name = names[i];
            weapon.price = prices[i];
            weapon.power = powers[i];
            weapon.description = descriptions[i];
            Weapon[i] = weapon;
        }
        return Weapon;
    }
    private Item[] createChest(){
        Item[] Chest = new Item[5];
        String[] names = {"Старая кольчуга", "Поношенный нагрудник", "Прочная кольчуга с пластинами",
                "Полный  доспех", "Королевский доспех"};
        int[] prices = {40, 400, 4000, 40000, 400000};
        int[] armors = {4, 40, 400, 4000, 4000000};
        String[] descriptions = {"Починке не подлежит", "Бывший рыцарь продал со скидкой",
                "Стандартный набор", "Хороший дом стоит чуть меньше", "Где ты его вообще взял?"};
        for(int i = 0; i < Chest.length; i++){
            Item chest = new Item();
            chest.name = names[i];
            chest.price = prices[i];
            chest.power = armors[i];
            chest.description = descriptions[i];
            Chest[i] = chest;
        }
        return Chest;
    }
    private Item[] createBoots() {
        Item[] Boots = new Item[5];
        String[] names = {"Старые ботинки", "Поношенные саббатоны", " Хорошие саббатоны и наголенники",
                "Хороший комплект брони на ноги", "Королевский комплект брони на ноги"};
        int[] prices = {10, 100, 1000, 10000, 100000};
        int[] armors = {1, 10, 100, 1000, 1000000};
        String[] descriptions = {"Можно получить грибок", "Уже хоть что то", "Теперь не прострелят колено",
                "Ни одной бреши в броне", "Где ты ее вообще взял?"};
        for(int i = 0; i < Boots.length; i++){
            Item boot = new Item();
            boot.name = names[i];
            boot.price = prices[i];
            boot.power = armors[i];
            boot.description = descriptions[i];
            Boots[i] = boot;
        }
        return Boots;
    }
    private Item[] createHeads() {
        Item[] Hands = new Item[5];
        String[] names = {"Старый шлем", "Шлем с подкладкой", "Хороший бувигер", "Дорогой комплект брони на голову",
                "Королевский комплект брони на голову"};
        int[] prices = {30, 300, 3000, 30000, 300000};
        int[] armor = {3, 30, 300, 3000, 3000000};
        String[] descriptions = {"Стучит по голове больнее чем враги", "Хоть немного комфорта", "Наконец то чувство безопасности",
                "Выглядит устрашающе", "Где ты его вообще взял?"};
        for(int i = 0; i < Hands.length; i++){
            Item hand = new Item();
            hand.name = names[i];
            hand.price = prices[i];
            hand.power = armor[i];
            hand.description = descriptions[i];
            Hands[i] = hand;
        }
        return Hands;
    }
}
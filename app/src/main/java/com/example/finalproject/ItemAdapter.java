package com.example.finalproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;


import com.example.finalproject.Fragments.TrainingFragment;


public class ItemAdapter extends ArrayAdapter {


    public ItemAdapter(Context context, Item[] arr) {
        super(context, R.layout.fragment_shop, arr);
    }

    private static final String APP_PREFERENCES = "mySettings";
    private static final String APower = "power";
    private static final String AWeaponName = "AnameWeapon";
    private static final String AChestName = "AnameChest";
    private static final String ABootsName = "AnameBoots";
    private static final String AHeadName = "AnameHead";
    private static final String AChestPower = "ApowerChest";
    private static final String ABootsPower = "ApowerBoots";
    private static final String AHeadPower = "ApowerHead";
    private static final String weaponName = "nameOfWeapon";
    private static final String chestName = "nameOfChest";
    private static final String bootsName = "nameOfBoots";
    private static final String headName = "nameOfHead";
    private static final String chestPower = "powerOfChest";
    private static final String bootsPower = "powerOfBoots";
    private static final String headPower = "powerOfHead";

    private static final String afterShopMoneySaver = "counter";
    private static final String afterShopPowerSaver = "counter";
    private SharedPreferences mSettings;
    private SharedPreferences mPower;
    private SharedPreferences mWeaponName;
    private SharedPreferences mChestName;
    private SharedPreferences mBootsName;
    private SharedPreferences mHeadName;
    private SharedPreferences mChestPower;
    private SharedPreferences mBootsPower;
    private SharedPreferences mHeadPower;


    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Item item = (Item) getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.shop_layout_item, null);
        }

        assert item != null;
        ((TextView)convertView.findViewById(R.id.name)).setText(item.name);
        ((TextView)convertView.findViewById(R.id.price)).setText(Integer.toString(item.price));
        ((TextView)convertView.findViewById(R.id.power)).setText(Integer.toString(item.power));
        ((TextView)convertView.findViewById(R.id.desc)).setText(item.description);
        mChestPower = this.getContext().getSharedPreferences(AChestPower, Context.MODE_PRIVATE);
        mBootsPower = this.getContext().getSharedPreferences(ABootsPower, Context.MODE_PRIVATE);
        mHeadPower = this.getContext().getSharedPreferences(AHeadPower, Context.MODE_PRIVATE);
        mWeaponName = this.getContext().getSharedPreferences(AWeaponName, Context.MODE_PRIVATE);
        mChestName = this.getContext().getSharedPreferences(AChestName, Context.MODE_PRIVATE);
        mBootsName = this.getContext().getSharedPreferences(ABootsName, Context.MODE_PRIVATE);
        mHeadName = this.getContext().getSharedPreferences(AHeadName, Context.MODE_PRIVATE);
        mSettings = this.getContext().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        mPower = this.getContext().getSharedPreferences(APower, Context.MODE_PRIVATE);
        final Button b = convertView.findViewById(R.id.pressToBuy);
        String name = "";
        String type = "";
        switch (item.name){
            case "Сломанный меч": name = "Сломанный меч";
                                  type = "Оружие";
                                  break;
            case "Меч палача": name = "Меч палача";
                                  type = "Оружие";
                                  break;
            case "Хороший гладиус": name = "Хороший гладиус";
                                  type = "Оружие";
                                  break;
            case "Элитный клеймор": name = "Элитный клеймор";
                                  type = "Оружие";
                                  break;
            case "Лучший меч короля": name = "Лучший меч короля";
                                  type = "Оружие";
                                  break;
            case "Старая кольчуга": name = "Старая кольчуга";
                                  type = "Нагрудник";
                                  break;
            case "Поношенный нагрудник": name = "Поношенный нагрудник";
                                  type = "Нагрудник";
                                  break;
            case "Прочная кольчуга с пластинами": name = "Прочная кольчуга с пластинами";
                                  type = "Нагрудник";
                                  break;
            case "Полный  доспех": name = "Полный  доспех";
                                  type = "Нагрудник";
                                  break;
            case "Королевский доспех": name = "Королевский доспех";
                                  type = "Нагрудник";
                                  break;
            case "Старые ботинки": name = "Старые ботинки";
                                  type = "Ботинки";
                                  break;
            case "Поношенные саббатоны": name = "Поношенные саббатоны";
                                  type = "Ботинки";
                                  break;
            case "Хорошие саббатоны и наголенники": name = "Хорошие саббатоны и наголенники";
                                  type = "Ботинки";
                                  break;
            case "Хороший комплект брони на ноги": name = "Хороший комплект брони на ноги";
                                  type = "Ботинки";
                                  break;
            case "Королевский комплект брони на ноги": name = "Королевский комплект брони на ноги";
                                  type = "Ботинки";
                                  break;
            case "Старый шлем": name = "Старый шлем";
                                  type = "Шлем";
                                  break;
            case "Шлем с подкладкой": name = "Шлем с подкладкой";
                                  type = "Шлем";
                                  break;
            case "Хороший бувигер": name = "Хороший бувигер";
                                  type = "Шлем";
                                  break;
            case "Дорогой комплект брони на голову": name = "Дорогой комплект брони на голову";
                                  type = "Шлем";
                                  break;
            case "Королевский комплект брони на голову": name = "Королевский комплект брони на голову";
                                  type = "Шлем";
                                  break;
        }
        TrainingFragment trainingExample = new TrainingFragment();
        final int[] money = {trainingExample.getMoney(getContext())};
        final String finalName = name;
        final String finalType = type;
        b.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ApplySharedPref")
            @Override
            public void onClick(View v) {
                if(finalType.equals("Оружие") && money[0] >= item.price) {
                    money[0] -= item.price;
                    SharedPreferences.Editor money_editor = mSettings.edit();
                    money_editor.putInt(afterShopMoneySaver, money[0]);
                    money_editor.commit();
                    SharedPreferences.Editor power_editor = mPower.edit();
                    power_editor.putInt(afterShopPowerSaver, item.power);
                    power_editor.commit();
                    SharedPreferences.Editor weapon_name_editor = mWeaponName.edit();
                    weapon_name_editor.putString(weaponName, item.name);
                    weapon_name_editor.commit();
                }

                if(finalType.equals("Нагрудник") && money[0] >= item.price){
                    money[0] -= item.price;
                    SharedPreferences.Editor money_editor = mSettings.edit();
                    money_editor.putInt(afterShopMoneySaver, money[0]);
                    money_editor.commit();
                    SharedPreferences.Editor power_editor = mChestPower.edit();
                    power_editor.putInt(chestPower, item.power);
                    power_editor.commit();
                    SharedPreferences.Editor chest_editor = mChestName.edit();
                    chest_editor.putString(chestName, item.name);
                    chest_editor.commit();
                }

                if(finalType.equals("Ботинки") && money[0] >= item.price){
                    money[0] -= item.price;
                    SharedPreferences.Editor money_editor = mSettings.edit();
                    money_editor.putInt(afterShopMoneySaver, money[0]);
                    money_editor.commit();
                    SharedPreferences.Editor power_editor = mBootsPower.edit();
                    power_editor.putInt(bootsPower, item.power);
                    power_editor.commit();
                    SharedPreferences.Editor boots_editor = mBootsName.edit();
                    boots_editor.putString(bootsName, item.name);
                    boots_editor.commit();
                }

                if(finalType.equals("Шлем") && money[0] >= item.price){
                    money[0] -= item.price;
                    SharedPreferences.Editor money_editor = mSettings.edit();
                    money_editor.putInt(afterShopMoneySaver, money[0]);
                    money_editor.commit();
                    SharedPreferences.Editor power_editor = mHeadPower.edit();
                    power_editor.putInt(headPower, item.power);
                    power_editor.commit();
                    SharedPreferences.Editor head_editor = mHeadName.edit();
                    head_editor.putString(headName, item.name);
                    head_editor.commit();
                }

                }
        });
        return convertView;
    }

    public int getPowerAfterShopFromShop(Context ctx){
        mPower = ctx.getSharedPreferences(APower, Context.MODE_PRIVATE);
        return mPower.getInt(afterShopPowerSaver, 1);
    }

    public int getMoneyAfterShop(Context ctx){
        mSettings = ctx.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        return mSettings.getInt(afterShopMoneySaver, 0);
    }

    public String getAWeaponName(Context ctx){
        mWeaponName = ctx.getSharedPreferences(AWeaponName, Context.MODE_PRIVATE);
        return mWeaponName.getString(weaponName, "Начальный меч");
    }

    public String getAChestName(Context ctx){
        mChestName = ctx.getSharedPreferences(AChestName, Context.MODE_PRIVATE);
        return mChestName.getString(chestName, "Начальная броня");
    }

    public String getABootsName(Context ctx){
        mBootsName = ctx.getSharedPreferences(ABootsName, Context.MODE_PRIVATE);
        return mBootsName.getString(bootsName, "Начальные ботинки");
    }

    public String getAHeadName(Context ctx){
        mHeadName = ctx.getSharedPreferences(AHeadName, Context.MODE_PRIVATE);
        return mHeadName.getString(headName, "Начальный Шлем");
    }

    public int getAChestPower(Context ctx){
        mChestPower = ctx.getSharedPreferences(AChestPower, Context.MODE_PRIVATE);
        return mChestPower.getInt(chestPower, 0);
    }

    public int getABootsPower(Context ctx){
        mBootsPower = ctx.getSharedPreferences(ABootsPower, Context.MODE_PRIVATE);
        return mBootsPower.getInt(bootsPower, 0);
    }

    public int getAHeadPower(Context ctx){
        mHeadPower = ctx.getSharedPreferences(AHeadPower, Context.MODE_PRIVATE);
        return mHeadPower.getInt(headPower, 0);
    }

}

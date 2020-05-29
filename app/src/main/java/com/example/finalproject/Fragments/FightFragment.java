package com.example.finalproject.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.finalproject.Item;
import com.example.finalproject.ItemAdapter;
import com.example.finalproject.R;


import java.util.Objects;

public class FightFragment extends Fragment {


    @SuppressLint("SetTextI18n")
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fight, container, false);
        Item[] a = new Item[1];
        final TextView WeaponPowerTextView = v.findViewById(R.id.power_of_weapon);
        TextView ChestPowerTextView = v.findViewById(R.id.protection_of_chest);
        TextView BootsPowerTextView = v.findViewById(R.id.protection_of_boots);
        TextView HeadPowerTextView = v.findViewById(R.id.protection_of_head);
        final TextView fightDescriptionOfEnemy = v.findViewById(R.id.fight_description_of_enemy);
        final TextView fightDescriptionOfHero = v.findViewById(R.id.fight_description_of_hero);
        ItemAdapter itemAdapter = new ItemAdapter(getContext(), a);
        final DialogFragment dialogFragment = new PositiveResultOfFightFragment();
        final DialogFragment dialogFragment1 = new NegativeResultOfFightFragment();
        final int Weapon = itemAdapter.getPowerAfterShopFromShop(Objects.requireNonNull(getContext()));
        final int Chest = itemAdapter.getAChestPower(getContext());
        final int Boots = itemAdapter.getABootsPower(getContext());
        final int Head = itemAdapter.getAHeadPower(getContext());
        final int SummPower = Weapon + Chest + Boots + Head;
        final int[] OpponentProtections = {SummPower / 3, SummPower / 4, SummPower / 5, SummPower / 6};
        final TextView EnemyHealth = v.findViewById(R.id.myOpponentHealth);
        final TextView MyHealth = v.findViewById(R.id.myHealth);

        final TextView EnemyWeaponPower = v.findViewById(R.id.power_of_weapon_of_enemy);
        final TextView EnemyChestPower = v.findViewById(R.id.protection_of_chest_of_enemy);
        final int EnemyWeaponProtection = Weapon + (SummPower + Weapon) / 4;
        final int EnemyChestProtection = (int)Math.floor(Math.random() * OpponentProtections.length);
        final int EnemyBootsProtection = (int)Math.floor(Math.random() * OpponentProtections.length);
        final int EnemyHeadProtection = (int)Math.floor(Math.random() * OpponentProtections.length);
        final int[] EnemyHealthInt = new int[1];
        final int[] MyHealthInt = new int[1];
        final String[] ChosenPlace = new String[1];
        EnemyHealthInt[0] = 100;
        MyHealthInt[0] = 100;
        final TextView EnemyBootsPower = v.findViewById(R.id.protection_of_boots_of_enemy);
        final TextView EnemyHeadPower = v.findViewById(R.id.protection_of_head_of_enemy);
        Button inChest = v.findViewById(R.id.AttackChest);
        Button inWeapon = v.findViewById(R.id.AttackWeapon);
        Button inBoots = v.findViewById(R.id.AttackLegs);
        Button inHead = v.findViewById(R.id.AttackHead);
        final String[] MyProtections = new String[]{"Weapon", "Chest", "Boots", "Head"};
        final double[] max = {100};
        final int[] breakCounterHero = {0};
        final int[] breakCounterEnemy = {0};
        final int[] lose = {0};

        WeaponPowerTextView.setText("Урон оружия: " + Weapon);
        ChestPowerTextView.setText("Защита тела: " + Chest);
        BootsPowerTextView.setText("Защита ног: " + Boots);
        HeadPowerTextView.setText("Защита головы: " + Head);

        inChest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (EnemyChestPower.getText().equals("Защита тела врага: ?")) {
                    EnemyChestPower.setText("Защита тела врага: " + EnemyChestProtection);
                }
                if (EnemyHealthInt[0] - (Weapon - EnemyChestProtection) > 0 && breakCounterHero[0] != 1 && breakCounterEnemy[0] != 1 && lose[0] != 1) {
                    if (Weapon - EnemyChestProtection > 0) {
                        fightDescriptionOfHero.setText("Вы нанесли " + (Weapon - EnemyChestProtection) + " Урона");
                        EnemyHealth.setText("Здоровье врага: " + (EnemyHealthInt[0] - (Weapon - EnemyChestProtection)));
                        EnemyHealthInt[0] -= (Weapon - EnemyChestProtection);
                    } else fightDescriptionOfHero.setText("Броня врага слишком сильна!");
                }
                else{
                    if(lose[0] == 0) {
                        assert getFragmentManager() != null;
                        dialogFragment.show(getFragmentManager(), "victory");
                    }
                    lose[0] = 1;
                }

                if (EnemyWeaponPower.getText().equals("Урон оружия врага: ?")) {
                    EnemyWeaponPower.setText("Урон оружия врага: " + EnemyWeaponProtection);
                }
                if (breakCounterHero[0] != 1 && breakCounterEnemy[0] != 1 && lose[0] != 1) {
                    ChosenPlace[0] = Double.toString(Math.floor(Math.random() * MyProtections.length));
                    if (ChosenPlace[0].equals("1.0")) {
                        double chance = Math.random() * ++max[0];
                        if (chance < 5) {
                            fightDescriptionOfEnemy.setText("Ваше оружие сломано! Вы проиграли!");
                            breakCounterHero[0] = 1;
                            lose[0] = 1;
                            assert getFragmentManager() != null;
                            dialogFragment1.show(getFragmentManager(), "Поражение");
                        } else
                            fightDescriptionOfEnemy.setText("Враг попытался сломать ваше оружие, но у него ничего не вышло");
                    }
                    if (ChosenPlace[0].equals("2.0")) {
                        if (MyHealthInt[0] - (EnemyWeaponProtection - Chest) > 0) {
                            if (EnemyWeaponProtection > Chest) {
                                fightDescriptionOfEnemy.setText("Вам ударили в тело и нанесли " + (EnemyWeaponProtection - Chest));
                                MyHealth.setText("Мое здоровье: " + (MyHealthInt[0] - (EnemyWeaponProtection - Chest)));
                                MyHealthInt[0] -= EnemyWeaponProtection - Chest;
                            } else
                                fightDescriptionOfEnemy.setText("Ваша броня смогла поглотить весь урон!");
                        }
                        else{
                            lose[0] = 1;
                            assert getFragmentManager() != null;
                            dialogFragment1.show(getFragmentManager(), "Поражение");
                        }
                    }

                    if (ChosenPlace[0].equals("3.0")) {
                        if (MyHealthInt[0] - (EnemyWeaponProtection - Boots) > 0) {
                            if (EnemyWeaponProtection > Boots) {
                                fightDescriptionOfEnemy.setText("Вам ударили в ноги и нанесли " + (EnemyWeaponProtection - Boots));
                                MyHealth.setText("Мое здоровье: " + (MyHealthInt[0] -
                                        (EnemyWeaponProtection - Boots)));
                                MyHealthInt[0] -= EnemyWeaponProtection - Chest;
                            } else
                                fightDescriptionOfEnemy.setText("Ваша броня смогла поглотить весь урон!");
                        }
                        else{
                            lose[0] = 1;
                            assert getFragmentManager() != null;
                            dialogFragment1.show(getFragmentManager(), "Поражение");
                        }
                    }
                    if (ChosenPlace[0].equals("0.0")) {
                        if (MyHealthInt[0] - (EnemyBootsProtection - Head) > 0) {
                            if (EnemyWeaponProtection > Head) {
                                fightDescriptionOfEnemy.setText("Вам ударили в голову и нанесли " + (EnemyWeaponProtection - Head));
                                MyHealth.setText("Мое здоровье: " + (MyHealthInt[0] -
                                        (EnemyWeaponProtection - Head)));
                                MyHealthInt[0] -= EnemyWeaponProtection - Chest;
                            } else
                                fightDescriptionOfEnemy.setText("Ваша броня смогла поглотить весь урон!");
                        }
                        else{
                            lose[0] = 1;
                            assert getFragmentManager() != null;
                            dialogFragment1.show(getFragmentManager(), "Поражение");
                        }
                    }
                }
            }
        });

        inBoots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (EnemyBootsPower.getText().equals("Защита ног врага: ?")) {
                    EnemyBootsPower.setText("Защита ног врага: " + EnemyBootsProtection);
                }
                if (EnemyHealthInt[0] - (Weapon - EnemyBootsProtection) > 0 && breakCounterHero[0] != 1 && breakCounterEnemy[0] != 1 && lose[0] != 1) {
                    if (Weapon - EnemyBootsProtection > 0) {
                        fightDescriptionOfHero.setText("Вы нанесли " + (Weapon - EnemyBootsProtection) + " Урона");
                        EnemyHealth.setText("Здоровье врага: " + (EnemyHealthInt[0] - (Weapon - EnemyBootsProtection)));
                        EnemyHealthInt[0] -= (Weapon - EnemyBootsProtection);
                    } else fightDescriptionOfHero.setText("Броня врага слишком сильна!");
                }
                else{
                    if(lose[0] == 0) {
                        assert getFragmentManager() != null;
                        dialogFragment.show(getFragmentManager(), "victory");
                    }
                    lose[0] = 1;
                }

                if (EnemyWeaponPower.getText().equals("Урон оружия врага: ?")) {
                    EnemyWeaponPower.setText("Урон оружия врага: " + EnemyWeaponProtection);
                }
                if (breakCounterHero[0] != 1 && breakCounterEnemy[0] != 1 && lose[0] != 1) {
                    ChosenPlace[0] = Double.toString(Math.floor(Math.random() * MyProtections.length));
                    if (ChosenPlace[0].equals("1.0")) {
                        double chance = Math.random() * ++max[0];
                        if (chance < 5) {
                            fightDescriptionOfEnemy.setText("Ваше оружие сломано! Вы проиграли!");
                            breakCounterHero[0] = 1;
                            lose[0] = 1;
                            assert getFragmentManager() != null;
                            dialogFragment1.show(getFragmentManager(), "Поражение");
                        } else
                            fightDescriptionOfEnemy.setText("Враг попытался сломать ваше оружие, но у него ничего не вышло");
                    }
                    if (ChosenPlace[0].equals("2.0")) {
                        if (MyHealthInt[0] - (EnemyWeaponProtection - Chest) > 0) {
                            if (EnemyWeaponProtection > Chest) {
                                fightDescriptionOfEnemy.setText("Вам ударили в тело и нанесли " + (EnemyWeaponProtection - Chest));
                                MyHealth.setText("Мое здоровье: " + (MyHealthInt[0] - (EnemyWeaponProtection - Chest)));
                                MyHealthInt[0] -= EnemyWeaponProtection - Chest;
                            } else
                                fightDescriptionOfEnemy.setText("Ваша броня смогла поглотить весь урон!");
                        }
                        else{
                            lose[0] = 1;
                            assert getFragmentManager() != null;
                            dialogFragment1.show(getFragmentManager(), "Поражение");
                        }
                    }

                    if (ChosenPlace[0].equals("3.0")) {
                        if (MyHealthInt[0] - (EnemyWeaponProtection - Boots) > 0) {
                            if (EnemyWeaponProtection > Boots) {
                                fightDescriptionOfEnemy.setText("Вам ударили в ноги и нанесли " + (EnemyWeaponProtection - Boots));
                                MyHealth.setText("Мое здоровье: " + (MyHealthInt[0] -
                                        (EnemyWeaponProtection - Boots)));
                                MyHealthInt[0] -= EnemyWeaponProtection - Chest;
                            } else
                                fightDescriptionOfEnemy.setText("Ваша броня смогла поглотить весь урон!");
                        }
                        else{
                            lose[0] = 1;
                            assert getFragmentManager() != null;
                            dialogFragment1.show(getFragmentManager(), "Поражение");
                        }
                    }
                    if (ChosenPlace[0].equals("4.0")) {
                        if (MyHealthInt[0] - (EnemyBootsProtection - Head) > 0) {
                            if (EnemyWeaponProtection > Head) {
                                fightDescriptionOfEnemy.setText("Вам ударили в голову и нанесли " + (EnemyWeaponProtection - Head));
                                MyHealth.setText("Мое здоровье: " + (MyHealthInt[0] -
                                        (EnemyWeaponProtection - Head)));
                                MyHealthInt[0] -= EnemyWeaponProtection - Chest;
                            } else
                                fightDescriptionOfEnemy.setText("Ваша броня смогла поглотить весь урон!");
                        }
                        else{
                            lose[0] = 1;
                            assert getFragmentManager() != null;
                            dialogFragment1.show(getFragmentManager(), "Поражение");
                        }
                    }
                }
            }
        });

        inHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (EnemyHeadPower.getText().equals("Защита головы врага: ?")) {
                    EnemyHeadPower.setText("Защита головы врага: " + EnemyHeadProtection);
                }
                if (EnemyHealthInt[0] - (Weapon - EnemyHeadProtection) > 0 && breakCounterHero[0] != 1 && breakCounterEnemy[0] != 1 && lose[0] != 1) {
                    if (Weapon - EnemyHeadProtection > 0) {
                        fightDescriptionOfHero.setText("Вы нанесли " + (Weapon - EnemyHeadProtection) + " Урона");
                        EnemyHealth.setText("Здоровье врага: " + (EnemyHealthInt[0] - (Weapon - EnemyHeadProtection)));
                        EnemyHealthInt[0] -= (Weapon - EnemyHeadProtection);
                    }
                    else fightDescriptionOfHero.setText("Броня врага слишком сильна!");
                }
                else{
                    if(lose[0] == 0) {
                        assert getFragmentManager() != null;
                        dialogFragment.show(getFragmentManager(), "victory");
                    }
                    lose[0] = 1;
                }

                if (EnemyWeaponPower.getText().equals("Урон оружия врага: ?")) {
                    EnemyWeaponPower.setText("Урон оружия врага: " + EnemyWeaponProtection);
                }
                if (breakCounterHero[0] != 1 && breakCounterEnemy[0] != 1 && lose[0] != 1) {
                    ChosenPlace[0] = Double.toString(Math.floor(Math.random() * MyProtections.length));
                    if (ChosenPlace[0].equals("1.0")) {
                        double chance = Math.random() * ++max[0];
                        if (chance < 5) {
                            fightDescriptionOfEnemy.setText("Ваше оружие сломано! Вы проиграли!");
                            breakCounterHero[0] = 1;
                            lose[0] = 1;
                            assert getFragmentManager() != null;
                            dialogFragment1.show(getFragmentManager(), "Поражение");
                        } else
                            fightDescriptionOfEnemy.setText("Враг попытался сломать ваше оружие, но у него ничего не вышло");
                    }
                    if (ChosenPlace[0].equals("2.0")) {
                        if (MyHealthInt[0] - (EnemyWeaponProtection - Chest) > 0) {
                            if (EnemyWeaponProtection > Chest) {
                                fightDescriptionOfEnemy.setText("Вам ударили в тело и нанесли " + (EnemyWeaponProtection - Chest));
                                MyHealth.setText("Мое здоровье: " + (MyHealthInt[0] - (EnemyWeaponProtection - Chest)));
                                MyHealthInt[0] -= EnemyWeaponProtection - Chest;
                            } else
                                fightDescriptionOfEnemy.setText("Ваша броня смогла поглотить весь урон!");
                        }
                        else{
                            lose[0] = 1;
                            assert getFragmentManager() != null;
                            dialogFragment1.show(getFragmentManager(), "Поражение");
                        }
                    }

                    if (ChosenPlace[0].equals("3.0")) {
                        if (MyHealthInt[0] - (EnemyWeaponProtection - Boots) > 0) {
                            if (EnemyWeaponProtection > Boots) {
                                fightDescriptionOfEnemy.setText("Вам ударили в ноги и нанесли " + (EnemyWeaponProtection - Boots));
                                MyHealth.setText("Мое здоровье: " + (MyHealthInt[0] -
                                        (EnemyWeaponProtection - Boots)));
                                MyHealthInt[0] -= EnemyWeaponProtection - Chest;
                            } else
                                fightDescriptionOfEnemy.setText("Ваша броня смогла поглотить весь урон!");
                        }
                        else{
                            lose[0] = 1;
                            assert getFragmentManager() != null;
                            dialogFragment1.show(getFragmentManager(), "Поражение");
                        }
                    }
                    if (ChosenPlace[0].equals("4.0")) {
                        if (MyHealthInt[0] - (EnemyBootsProtection - Head) > 0) {
                            if (EnemyWeaponProtection > Head) {
                                fightDescriptionOfEnemy.setText("Вам ударили в голову и нанесли " + (EnemyWeaponProtection - Head));
                                MyHealth.setText("Мое здоровье: " + (MyHealthInt[0] -
                                        (EnemyWeaponProtection - Head)));
                                MyHealthInt[0] -= EnemyWeaponProtection - Chest;
                            } else
                                fightDescriptionOfEnemy.setText("Ваша броня смогла поглотить весь урон!");
                        }
                        else{
                            lose[0] = 1;
                            assert getFragmentManager() != null;
                            dialogFragment1.show(getFragmentManager(), "Поражение");
                        }
                    }
                }
            }
        });

        inWeapon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(breakCounterHero[0] != 1 && breakCounterEnemy[0] != 1 && lose[0] != 1){
                double chance = Math.random() * ++max[0];
                if (chance < 50) {
                    fightDescriptionOfHero.setText("Вы смогли выбить оружие врага!");
                    breakCounterEnemy[0] = 1;
                    lose[0] = 1;
                    assert getFragmentManager() != null;
                    dialogFragment.show(getFragmentManager(), "victory");
                } else {
                    fightDescriptionOfHero.setText("Вы попытались выбить оружие врага, но у вас ничего не вышло");
                }

                    if (EnemyWeaponPower.getText().equals("Урон оружия врага: ?")) {
                        EnemyWeaponPower.setText("Урон оружия врага: " + EnemyWeaponProtection);
                    }
                    if (breakCounterHero[0] != 1 && breakCounterEnemy[0] != 1 && lose[0] != 1) {
                        ChosenPlace[0] = Double.toString(Math.floor(Math.random() * MyProtections.length));
                        if (ChosenPlace[0].equals("1.0")) {
                            double chance1 = Math.random() * ++max[0];
                            if (chance1 < 5) {
                                fightDescriptionOfEnemy.setText("Ваше оружие сломано! Вы проиграли!");
                                breakCounterHero[0] = 1;
                                lose[0] = 1;
                                assert getFragmentManager() != null;
                                dialogFragment1.show(getFragmentManager(), "Поражение");
                            } else
                                fightDescriptionOfEnemy.setText("Враг попытался сломать ваше оружие, но у него ничего не вышло");
                        }
                        if (ChosenPlace[0].equals("2.0")) {
                            if (MyHealthInt[0] - (EnemyWeaponProtection - Chest) > 0) {
                                if (EnemyWeaponProtection > Chest) {
                                    fightDescriptionOfEnemy.setText("Вам ударили в тело и нанесли " + (EnemyWeaponProtection - Chest));
                                    MyHealth.setText("Мое здоровье: " + (MyHealthInt[0] - (EnemyWeaponProtection - Chest)));
                                    MyHealthInt[0] -= EnemyWeaponProtection - Chest;
                                } else
                                    fightDescriptionOfEnemy.setText("Ваша броня смогла поглотить весь урон!");
                            }
                            else{
                                lose[0] = 1;
                                assert getFragmentManager() != null;
                                dialogFragment1.show(getFragmentManager(), "Поражение");
                            }
                        }

                        if (ChosenPlace[0].equals("3.0")) {
                            if (MyHealthInt[0] - (EnemyWeaponProtection - Boots) > 0) {
                                if (EnemyWeaponProtection > Boots) {
                                    fightDescriptionOfEnemy.setText("Вам ударили в ноги и нанесли " + (EnemyWeaponProtection - Boots));
                                    MyHealth.setText("Мое здоровье: " + (MyHealthInt[0] -
                                            (EnemyWeaponProtection - Boots)));
                                    MyHealthInt[0] -= EnemyWeaponProtection - Chest;
                                } else
                                    fightDescriptionOfEnemy.setText("Ваша броня смогла поглотить весь урон!");
                            }
                            else{
                                lose[0] = 1;
                                assert getFragmentManager() != null;
                                dialogFragment1.show(getFragmentManager(), "Поражение");
                            }
                        }
                        if (ChosenPlace[0].equals("4.0")) {
                            if (MyHealthInt[0] - (EnemyBootsProtection - Head) > 0) {
                                if (EnemyWeaponProtection > Head) {
                                    fightDescriptionOfEnemy.setText("Вам ударили в голову и нанесли " + (EnemyWeaponProtection - Head));
                                    MyHealth.setText("Мое здоровье: " + (MyHealthInt[0] -
                                            (EnemyWeaponProtection - Head)));
                                    MyHealthInt[0] -= EnemyWeaponProtection - Chest;
                                } else
                                    fightDescriptionOfEnemy.setText("Ваша броня смогла поглотить весь урон!");
                            }
                            else{
                                lose[0] = 1;
                                assert getFragmentManager() != null;
                                dialogFragment1.show(getFragmentManager(), "Поражение");
                            }
                        }
                    }
                }
            }
        });
        return v;
    }
}

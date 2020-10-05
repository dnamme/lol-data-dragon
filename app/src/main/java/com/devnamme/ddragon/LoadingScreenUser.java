package com.devnamme.ddragon;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoadingScreenUser extends AppCompatActivity {

    EditText username = null;
    EditText club = null;

    Switch isBlue = null;

    Spinner champion = null;
    ImageView champion_preview = null;
    Spinner skin = null;
    ImageView skin_preview = null;

    Spinner summoner_left = null;
    ImageView summoner_left_preview = null;
    Spinner summoner_right = null;
    ImageView summoner_right_preview = null;

    EditText icon = null;
    ImageView icon_preview = null;

    Spinner rune_primary = null;
    ImageView rune_primary_preview = null;
    Spinner rune_secondary = null;
    ImageView rune_secondary_preview = null;


    ArrayList<String> selectedChampionSkinList = new ArrayList<>();
    ArrayList<Integer> selectedChampionSkinNum = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_loading_screen_user);

        username = findViewById(R.id.username_field);
        club = findViewById(R.id.club_field);

        isBlue = findViewById(R.id.side_switch);

        champion = findViewById(R.id.champion_spinner);
        champion_preview = findViewById(R.id.champion_preview);

        skin = findViewById(R.id.skin_spinner);
        skin_preview = findViewById(R.id.skin_preview);

        summoner_left = findViewById(R.id.spell_left_spinner);
        summoner_left_preview = findViewById(R.id.spell_left_preview);
        summoner_right = findViewById(R.id.spell_right_spinner);
        summoner_right_preview = findViewById(R.id.spell_right_preview);

        icon = findViewById(R.id.icon_field);
        icon_preview = findViewById(R.id.icon_preview);

        rune_primary = findViewById(R.id.rune_primary_spinner);
        rune_primary_preview = findViewById(R.id.rune_primary_preview);
        rune_secondary = findViewById(R.id.rune_secondary_spinner);
        rune_secondary_preview = findViewById(R.id.rune_secondary_preview);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new Util.Download(
                        LoadingScreenUser.this,
                        DataDragon.getVersionUrl() + "/data/en_US/champion.json",
                        "v/data/en_US",
                        "champion.json",
                        new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    DataDragon.championNameList.clear();
                                    DataDragon.championKeyList.clear();

                                    JSONObject data = Util.readJSONObjectFile(new File(DataDragon.dir + "/v/data/en_US/champion.json"))
                                            .getJSONObject("data");

                                    Iterator<String> championNames = data.keys();

                                    while(championNames.hasNext()) {
                                        String championKey = championNames.next();
                                        JSONObject championData = data.getJSONObject(championKey);

                                        DataDragon.championNameList.add(championData.getString("name"));
                                        DataDragon.championKeyList.add(championData.getString("id"));
                                    }

                                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getBaseContext(), R.layout.simple_spinner_item, DataDragon.championNameList);
                                    adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                                    champion.setAdapter(adapter);
                                } catch(Exception e) {
                                    Toast.makeText(LoadingScreenUser.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                ).execute();

                new Util.Download(
                        LoadingScreenUser.this,
                        DataDragon.getVersionUrl() + "/data/en_US/summoner.json",
                        "v/data/en_US",
                        "summoner.json",
                        new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    DataDragon.summonerNameList.clear();
                                    DataDragon.summonerKeyList.clear();

                                    JSONObject data = Util.readJSONObjectFile(new File(DataDragon.dir + "/v/data/en_US/summoner.json"))
                                            .getJSONObject("data");

                                    Iterator<String> summonerKeys = data.keys();

                                    while(summonerKeys.hasNext()) {
                                        String summonerKey = summonerKeys.next();
                                        JSONObject summonerData = data.getJSONObject(summonerKey);

                                        DataDragon.summonerNameList.add(summonerData.getString("name"));
                                        DataDragon.summonerKeyList.add(summonerData.getString("id"));
                                        DataDragon.summonerImageList.add(
                                                summonerData.getJSONObject("image")
                                                .getString("full")
                                        );
                                    }

                                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getBaseContext(), R.layout.simple_spinner_item, DataDragon.summonerNameList);
                                    adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);

                                    summoner_left.setAdapter(adapter);
                                    summoner_right.setAdapter(adapter);
                                } catch(Exception e) {
                                    Toast.makeText(LoadingScreenUser.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                ).execute();

                new Util.Download(
                        LoadingScreenUser.this,
                        DataDragon.getVersionUrl() + "/data/en_US/profileicon.json",
                        "v/data/en_US",
                        "profileicon.json",
                        new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    DataDragon.iconMin = 0;
                                    DataDragon.iconMax = 0;

                                    JSONObject data = Util.readJSONObjectFile(new File(DataDragon.dir + "/v/data/en_US/profileicon.json"))
                                            .getJSONObject("data");

                                    Iterator<String> ids = data.keys();

                                    while(ids.hasNext()) {
                                        String keyId = ids.next();
                                        JSONObject iconData = data.getJSONObject(keyId);

                                        int id = iconData.getInt("id");
                                        if(id > DataDragon.iconMax) DataDragon.iconMax = id;
                                    }
                                } catch(Exception e) {
                                    Toast.makeText(LoadingScreenUser.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                ).execute();

                new Util.Download(
                        LoadingScreenUser.this,
                        DataDragon.getVersionUrl() + "/data/en_US/runesReforged.json",
                        "v/data/en_US",
                        "runesReforged.json",
                        new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    DataDragon.keystoneNameList.clear();
                                    DataDragon.keystoneDirList.clear();
                                    DataDragon.secondaryNameList.clear();
                                    DataDragon.secondaryDirList.clear();

                                    JSONArray data = Util.readJSONArrayFile(new File(DataDragon.dir + "/v/data/en_US/runesReforged.json"));

                                    for(int i = 0; i < data.length(); i++) {
                                        JSONObject subdata = data.getJSONObject(i);

                                        DataDragon.secondaryNameList.add(subdata.getString("key"));
                                        DataDragon.secondaryDirList.add(subdata.getString("icon"));

                                        JSONArray runes = subdata.getJSONArray("slots")
                                                .getJSONObject(0)
                                                .getJSONArray("runes");

                                        for(int ii = 0; ii < runes.length(); ii++) {
                                            JSONObject rune = runes.getJSONObject(ii);

                                            DataDragon.keystoneNameList.add(rune.getString("name"));
                                            DataDragon.keystoneDirList.add(rune.getString("icon"));
                                        }
                                    }

                                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getBaseContext(), R.layout.simple_spinner_item, DataDragon.keystoneNameList);
                                    adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                                    rune_primary.setAdapter(adapter);

                                    ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getBaseContext(), R.layout.simple_spinner_item, DataDragon.secondaryNameList);
                                    adapter1.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                                    rune_secondary.setAdapter(adapter1);
                                } catch(Exception e) {
                                    Toast.makeText(LoadingScreenUser.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                ).execute();

                champion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                        new Util.Download(
                                LoadingScreenUser.this,
                                "https://ddragon.leagueoflegends.com/cdn/" + DataDragon.version + "/data/en_US/champion/" + DataDragon.championKeyList.get(position) + ".json",
                                "v/data/en_US/champion",
                                DataDragon.championKeyList.get(position) + ".json",
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            selectedChampionSkinList.clear();
                                            selectedChampionSkinNum.clear();

                                            JSONArray skins = Util.readJSONObjectFile(new File(DataDragon.getChampionFullJSONDir(DataDragon.championKeyList.get(position))))
                                                    .getJSONObject("data")
                                                    .getJSONObject(DataDragon.championKeyList.get(position))
                                                    .getJSONArray("skins");


                                            for(int i = 0; i < skins.length(); i++) {
                                                JSONObject obj = skins.getJSONObject(i);

                                                selectedChampionSkinList.add(obj.getString("name"));
                                                selectedChampionSkinNum.add(obj.getInt("num"));
                                            }

                                            ArrayAdapter<String> adapter = new ArrayAdapter<>(getBaseContext(), R.layout.simple_spinner_item, selectedChampionSkinList);
                                            adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                                            skin.setAdapter(adapter);
                                        } catch(Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                        ).execute();

                        new Util.LoadURLToImage(champion_preview).execute(DataDragon.getChampionPreviewImageUrl(position));
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {}
                });

                skin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        new Util.LoadURLToImage(skin_preview).execute(DataDragon.getChampionSkinPreviewImageUrl(champion.getSelectedItemPosition(), selectedChampionSkinNum.get(position)));
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {}
                });

                icon.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) { }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if(s.toString().length() == 0) return;

                        int index = Integer.parseInt(s.toString());

                        if(index >= DataDragon.iconMin && index <= DataDragon.iconMax) {
                            new Util.LoadURLToImage(icon_preview).execute(DataDragon.getIconImageUrl(index));
                        }
                    }
                });

                summoner_left.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        new Util.LoadURLToImage(summoner_left_preview).execute(DataDragon.getSummonerSpellImageUrl(position));
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {}
                });

                summoner_right.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        new Util.LoadURLToImage(summoner_right_preview).execute(DataDragon.getSummonerSpellImageUrl(position));
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {}
                });

                rune_primary.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        new Util.LoadURLToImage(rune_primary_preview).execute(DataDragon.getKeystoneImageUrl(position));
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) { }
                });

                rune_secondary.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        new Util.LoadURLToImage(rune_secondary_preview).execute(DataDragon.getSecondaryImageUrl(position));
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) { }
                });
            }
        });
    }

    public void generateLoadingScreenUser(View v) {
        Intent intent = new Intent(this, LoadingScreenUserGen.class);

        intent.putExtra("_username", username.getText().toString());
        intent.putExtra("_club", club.getText().toString());
        intent.putExtra("_isBlue", !isBlue.isChecked());
        intent.putExtra("_spell_left", summoner_left.getSelectedItemPosition());
        intent.putExtra("_spell_right", summoner_right.getSelectedItemPosition());
        intent.putExtra("_champion_index", champion.getSelectedItemPosition());
        intent.putExtra("_champion_skin_index", selectedChampionSkinNum.get(skin.getSelectedItemPosition()));
        intent.putExtra("_champion_skin_name", skin.getSelectedItemPosition() == 0 ? champion.getSelectedItem().toString() : skin.getSelectedItem().toString());
        intent.putExtra("_icon", icon.getText().toString().length() == 0 ? "0" : icon.getText().toString());
        intent.putExtra("_rune_primary", rune_primary.getSelectedItemPosition());
        intent.putExtra("_rune_secondary", rune_secondary.getSelectedItemPosition());

        startActivity(intent);
    }
}

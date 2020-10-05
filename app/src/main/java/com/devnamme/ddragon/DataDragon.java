package com.devnamme.ddragon;

import java.lang.reflect.Array;
import java.util.ArrayList;

class DataDragon {
    static String dir;

    static String version = "10.11.1";

    static final String url = "https://ddragon.leagueoflegends.com";

    static ArrayList<String> championNameList = new ArrayList<>();
    static ArrayList<String> championKeyList = new ArrayList<>();
    static ArrayList<String> summonerNameList = new ArrayList<>();
    static ArrayList<String> summonerKeyList = new ArrayList<>();
    static ArrayList<String> summonerImageList = new ArrayList<>();
    static ArrayList<String> keystoneNameList = new ArrayList<>();
    static ArrayList<String> keystoneDirList = new ArrayList<>();
    static ArrayList<String> secondaryNameList = new ArrayList<>();
    static ArrayList<String> secondaryDirList = new ArrayList<>();

    static int iconMin = 0;
    static int iconMax = 0;

    static String getChampionFullListJSONDir() { return dir + "/v/data/en_US/champion.json"; }

    private static String getCDN() { return url + "/cdn"; }
    static String getVersionUrl() { return getCDN() + "/" + version; }


    static String getChampionFullJSONDir(String championKey) { return dir + "/v/data/en_US/champion/" + championKey + ".json"; }
    static String getChampionPreviewImageUrl(int index) { return getVersionUrl() + "/img/champion/" + championKeyList.get(index) + ".png"; } // square pv
    static String getChampionSkinPreviewImageUrl(int championIndex, int skinIndex) { return getCDN() + "/img/champion/tiles/" + championKeyList.get(championIndex) + "_" + skinIndex + ".jpg"; }
    static String getChampionSkinImageUrl(int championIndex, int skinIndex) { return getCDN() + "/img/champion/loading/" + championKeyList.get(championIndex) + "_" + skinIndex + ".jpg"; }

    static String getSummonerSpellImageUrl(int index) { return getVersionUrl() + "/img/spell/" + summonerImageList.get(index); }


    static String getIconImageUrl(int index) { return getVersionUrl() + "/img/profileicon/" + index + ".png"; }


    static String getKeystoneImageUrl(int index) { return getCDN() + "/img/" + keystoneDirList.get(index); }
    static String getSecondaryImageUrl(int index) { return getCDN() + "/img/" + secondaryDirList.get(index); }
}

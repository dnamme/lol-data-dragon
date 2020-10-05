package com.devnamme.ddragon;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.util.AttributeSet;

import java.io.InputStream;
import java.net.URL;

import androidx.core.content.res.ResourcesCompat;

public class LoadingScreenUserView extends android.view.View {
    Paint paint = new Paint();


    private String username = "";
    private String club = "";
    private boolean isBlue = false;
    private int spellLeft = 0;
    private int spellRight = 1;
    private int championIndex = 0;
    private int championSkinIndex = 0;
    private String championSkinName = "Default";
    private String icon = "0";
    private int runePrimary = 0;
    private int runeSecondary = 1;


    private boolean maxedWidth = false;
    private float scale = 0.0f;

    private final float TextScale = 1.10f;


    private Typeface font = null;


    private Bitmap ChampionBitmap = null;
    private Rect ChampionRect = null;


    private LinearGradient ShadowGradient = null;
    private final int ShadowGradient_Start_Y = 340;
    private final int ShadowGradient_End_Y = 432;
    private final int ShadowColor = Color.parseColor("#020b15");
    private Rect ShadowGradientRect = null;


    private final int Border_Width = 257;
    private final int Border_Height = 476;
    private Bitmap BorderBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.blue); // dev TODO remove - this is for loading purposes only
//    private Bitmap BorderBitmap = null;
    private Rect BorderRect = null;


//    private final int HR_Color1 = Color.parseColor("#");
//    private final int HR_Color2 = Color.parseColor("#");
//    private final int HR_Color3 = Color.parseColor("#");


//    private final int ChampionSkinName_Start_Y = 371;
    private final int ChampionSkinName_End_Y = 384;
    private final int ChampionSkinNameTextSize = 384 - 371;
    private final int ChampionSkinNameTextColor = Color.parseColor("#ffffff");


    private final int SummonerBackground_Start_Y = 412;
    private final int SummonerBackground_End_Y = 442;
    private final int SummonerBackground_Left_Start_X = 17;
    private final int SummonerBackground_Left_End_X = 47;
    private final int SummonerBackground_Right_Start_X = 49; // was 48 (+1)
    private final int SummonerBackground_Right_End_X = 79;
    private final int SummonerBackground_Weight = 2;
    private Rect SummonerBackground_Left_Rect = null;
    private Rect SummonerBackground_Right_Rect = null;
    private final int SummonerBackgroundColor = Color.parseColor("#504f4a");
    private final int Summoner_Start_Y = 414;
    private final int Summoner_End_Y = 440;
    private final int Summoner_Left_Start_X = 19;
    private final int Summoner_Left_End_X = 45;
    private final int Summoner_Right_Start_X = 51; // was 50 (+1)
    private final int Summoner_Right_End_X = 77;
    private Rect Summoner_Left_Rect = null;
    private Rect Summoner_Right_Rect = null;
    private Bitmap Summoner_Left_Bitmap = null;
    private Bitmap Summoner_Right_Bitmap = null;


//    private final int IconBackground_Diameter = 50;
//    private final int IconBackground_Start_Y = 396;
//    private final int IconBackground_End_Y = 446;
    private final int IconBackground_Radius = 50 / 2;
    private final int IconBackground_Center_Y = (396 + 446) / 2;
    private final int IconBackground_Weight = 2;
//    private final int Icon_Diameter = 46;
    private final int Icon_Radius = 46 / 2;
    private final int Icon_Start_Y = 398;
    private final int Icon_End_Y = 444;
    private final int IconBackgroundColor = Color.parseColor("#b88f36");
    private Rect IconRect = null;
    private Bitmap IconBitmap = null;


//    private final int RunePrimaryBackground_Diameter = 40;
    // private final int RunePrimaryBackground_Start_X = 181;
    // private final int RunePrimaryBackground_Start_Y = 398;
    // private final int RunePrimaryBackground_End_X = 221;
    // private final int RunePrimaryBackground_End_Y = 438;
    private final int RunePrimaryBackground_Radius = 40 / 2;
    private final int RunePrimary_Center_X = (181 + 221) / 2;
    private final int RunePrimary_Center_Y = (405 + 445) / 2;
    private final int RunePrimaryBackground_Weight = 2;
    // private final int RunePrimary_Diameter = 36;
    private final int RunePrimary_Radius = 36 / 2;
    private final int RunePrimary_Start_X = 181; // was 183
    private final int RunePrimary_Start_Y = 405; // was 407
    private final int RunePrimary_End_X = 221; // was 219
    private final int RunePrimary_End_Y = 445; // was 443
    private Rect RunePrimary_Rect = null;
    private Bitmap RunePrimary_Bitmap = null;

//    private final int RuneSecondaryBackground_Diameter = 36;
//    private final int RuneSecondaryBackground_Start_X = 212;
//    private final int RuneSecondaryBackground_Start_Y = 423;
//    private final int RuneSecondaryBackground_End_X = 248;
//    private final int RuneSecondaryBackground_End_Y = 459;
    private final int RuneSecondaryBackground_Radius = 26 / 2;
    private final int RuneSecondary_Center_X = (212 + 238) / 2;
    private final int RuneSecondary_Center_Y = (423 + 449) / 2;
    private final int RuneSecondaryBackground_Weight = 2;
    // private final int RuneSecondary_Diameter = 32;
    private final int RuneSecondary_Radius = 22 / 2;
    private final int RuneSecondary_Start_X = 216; // was 214
    private final int RuneSecondary_Start_Y = 427; // was 425
    private final int RuneSecondary_End_X = 234; // was 236
    private final int RuneSecondary_End_Y = 445; // was 447
    private Rect RuneSecondary_Rect = null;
    private Bitmap RuneSecondary_Bitmap = null;

    private final int RuneBackgroundColor = Color.parseColor("#493c21");


//    private final int Username_Start_Y = 450;
    private int Username_Center_X = 0;
    private final int Username_End_Y = 461;
    private final int UsernameTextSize = 461 - 450;
    private final int UsernameTextColor = Color.parseColor("#e2cfad"); // white
    private final int UsernameDefaultTextColor = Color.parseColor("#ebab08"); // yellow


    private int Club_Center_X = 0;
    private final int ClubTextColor = Color.parseColor("#bc9995");



    public LoadingScreenUserView(Context context) {
        super(context);
    }
    public LoadingScreenUserView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public LoadingScreenUserView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private void init() {
        int l, t, r, b;

        // ratio 308 560 loading
        // ratio 257 476 border

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        float _w = (float) width / 257.0f; //308.0f;
        float _h = (float) height / 476.0f; //560.0f;

        if(_w < _h) {
            l = 0;
            r = width;
            t = (int) (height - (476.0f * _w)) / 2;
            b = height - t;
            maxedWidth = true;
            scale = _w;
        } else {
            l = (int) (width - (257.0f * _h)) / 2;
            r = width - l;
            t = 0;
            b = height;
            maxedWidth = false;
            scale = _h;
        }

        try {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.P)
                font = Typeface.create(ResourcesCompat.getFont(getContext(), R.font.beaufort_regular), 1000, false);
            else
                font = Typeface.create(ResourcesCompat.getFont(getContext(), R.font.beaufort_regular), Typeface.BOLD);
        } catch(Exception ignored) {}

        BorderRect = new Rect(l, t, r, b);

        ChampionRect = new Rect(l, t, r, (int) (t + (560.0f * (r - l) / 308.0f)));

        ShadowGradient = new LinearGradient(0, posY(ShadowGradient_Start_Y), 0, posY(ShadowGradient_End_Y), Color.TRANSPARENT, ShadowColor, Shader.TileMode.CLAMP);
        ShadowGradientRect = new Rect(l, posY(ShadowGradient_Start_Y), r, posY(ShadowGradient_End_Y));

        SummonerBackground_Left_Rect = new Rect(posX(SummonerBackground_Left_Start_X), posY(SummonerBackground_Start_Y), posX(SummonerBackground_Left_End_X), posY(SummonerBackground_End_Y));
        SummonerBackground_Right_Rect = new Rect(posX(SummonerBackground_Right_Start_X), posY(SummonerBackground_Start_Y), posX(SummonerBackground_Right_End_X), posY(SummonerBackground_End_Y));
        Summoner_Left_Rect = new Rect(posX(Summoner_Left_Start_X), posY(Summoner_Start_Y), posX(Summoner_Left_End_X), posY(Summoner_End_Y));
        Summoner_Right_Rect = new Rect(posX(Summoner_Right_Start_X), posY(Summoner_Start_Y), posX(Summoner_Right_End_X), posY(Summoner_End_Y));

        IconRect = new Rect((int) ((width / 2) - (Icon_Radius * scale)), posY(Icon_Start_Y), (int) ((width / 2) + (Icon_Radius * scale)), posY(Icon_End_Y));

        RunePrimary_Rect = new Rect(posX(RunePrimary_Start_X), posY(RunePrimary_Start_Y), posX(RunePrimary_End_X), posY(RunePrimary_End_Y));
        RuneSecondary_Rect = new Rect(posX(RuneSecondary_Start_X), posY(RuneSecondary_Start_Y), posX(RuneSecondary_End_X), posY(RuneSecondary_End_Y));




        paint.reset();
        paint.setTextScaleX(TextScale);
        paint.setTextSize(ChampionSkinNameTextSize * scale);
        if(font != null)
            paint.setTypeface(font);

        final float championSkinName_width = paint.measureText(championSkinName);


        paint.setTextSize(UsernameTextSize * scale);

        final float username_width = paint.measureText(username);
        final float club_width = club.length() == 0 ? 0.0f : paint.measureText(" ".concat(club));


        Username_Center_X = (int) ((width / 2) - ((username_width + club_width) / 2) + (username_width / 2));
        Club_Center_X = (int) ((width / 2) + ((username_width + club_width) / 2) - (club_width / 2));
    }

    public void setData(String username,
                        String club,
                        boolean isBlue,
                        int spellLeft,
                        int spellRight,
                        final int championIndex,
                        final int championSkinIndex,
                        String championSkinName,
                        String icon,
                        int runePrimary,
                        int runeSecondary) {
        this.username = username;
        this.club = club;
        this.isBlue = isBlue;
        this.spellLeft = spellLeft;
        this.spellRight = spellRight;
        this.championIndex = championIndex;
        this.championSkinIndex = championSkinIndex;
        this.championSkinName = championSkinName;
        this.icon = icon;
        this.runePrimary = runePrimary;
        this.runeSecondary = runeSecondary;



        BorderBitmap = BitmapFactory.decodeResource(getResources(), isBlue ? R.drawable.blue : R.drawable.red);




        new Load().execute();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(BorderRect == null) init();

//        paint.setColor(Color.GRAY);
//        canvas.drawRect(BorderRect, paint);


        // draw loading splash art
//        paint.reset();
//        paint.setColor(Color.WHITE);
//        canvas.drawRect(ChampionRect, paint);
        if(ChampionBitmap != null)
            canvas.drawBitmap(ChampionBitmap, getRectFromBitmap(ChampionBitmap), ChampionRect, paint);

        // draw shadow
        paint.reset();
        paint.setShader(ShadowGradient);
        canvas.drawRect(ShadowGradientRect, paint);

        // draw shadow bottom
        paint.reset();
        paint.setColor(ShadowColor);
        canvas.drawRect(ShadowGradientRect.left, ShadowGradientRect.bottom, ShadowGradientRect.right, BorderRect.bottom, paint);
//        canvas.drawRect(ShadowGradRect.left, ShadowGradRect.bottom, ShadowGradRect.right, ChampionRect.bottom, paint);

        // draw RB border
        paint.reset();
        paint.setAntiAlias(false);
        if(BorderBitmap != null)
            canvas.drawBitmap(BorderBitmap, getRectFromBitmap(BorderBitmap), BorderRect, paint);
//            canvas.drawBitmap(BorderBitmap, getRectFromBitmap(BorderBitmap), ChampionRect, null);

        // draw hr

        // draw champion skin name
        paint.reset();
        paint.setColor(Color.WHITE);
        paint.setTextScaleX(TextScale);
        paint.setTextSize(ChampionSkinNameTextSize * scale);
        paint.setTextAlign(Paint.Align.CENTER);
        if(font != null)
            paint.setTypeface(font);
        canvas.drawText(championSkinName, (float) getWidth() / 2, posYf(ChampionSkinName_End_Y), paint);
//        canvas.drawText(championSkinName, (float) getWidth() / 2, ChampionRect.top + (0.80f * ChampionRect.height()), paint);

        // draw summoner background
        paint.reset();
        paint.setColor(SummonerBackgroundColor);
        canvas.drawRect(SummonerBackground_Left_Rect, paint);
        canvas.drawRect(SummonerBackground_Right_Rect, paint);

        // draw summoner left, right
        paint.reset();
        if(Summoner_Left_Bitmap != null)
            canvas.drawBitmap(Summoner_Left_Bitmap, getRectFromBitmap(Summoner_Left_Bitmap), Summoner_Left_Rect, null);
        if(Summoner_Right_Bitmap != null)
            canvas.drawBitmap(Summoner_Right_Bitmap, getRectFromBitmap(Summoner_Right_Bitmap), Summoner_Right_Rect, null);

        // draw icon background
        paint.reset();
        paint.setColor(IconBackgroundColor);
        canvas.drawCircle((float) getWidth() / 2, posYf(IconBackground_Center_Y), (float) IconBackground_Radius * scale, paint);

        // draw icon
        if(IconBitmap != null)
            canvas.drawBitmap(IconBitmap, getRectFromBitmap(IconBitmap), IconRect, null);

        // draw keystone ring
        paint.reset();
        paint.setColor(RuneBackgroundColor);
        canvas.drawCircle(posXf(RunePrimary_Center_X), posYf(RunePrimary_Center_Y), (float) RunePrimaryBackground_Radius * scale, paint);

        // draw keystone background
        paint.reset();
        paint.setColor(ShadowColor);
        canvas.drawCircle(posXf(RunePrimary_Center_X), posYf(RunePrimary_Center_Y), (float) RunePrimary_Radius * scale, paint);

        // draw keystone
        if(RunePrimary_Bitmap != null)
            canvas.drawBitmap(RunePrimary_Bitmap, getRectFromBitmap(RunePrimary_Bitmap), RunePrimary_Rect, null);

        // draw secondary ring
        paint.reset();
        paint.setColor(RuneBackgroundColor);
        canvas.drawCircle(posXf(RuneSecondary_Center_X), posYf(RuneSecondary_Center_Y), (float) RuneSecondaryBackground_Radius * scale, paint);

        // draw secondary background
        paint.reset();
        paint.setColor(ShadowColor);
        canvas.drawCircle(posXf(RuneSecondary_Center_X), posYf(RuneSecondary_Center_Y), (float) RuneSecondary_Radius * scale, paint);

        // draw secondary
        if(RuneSecondary_Bitmap != null)
            canvas.drawBitmap(RuneSecondary_Bitmap, getRectFromBitmap(RuneSecondary_Bitmap), RuneSecondary_Rect, null);

        // draw username
        paint.reset();
        paint.setColor(UsernameTextColor);
        paint.setTextScaleX(TextScale);
        paint.setTextSize(UsernameTextSize * scale);
        paint.setTextAlign(Paint.Align.CENTER);
        if(font != null)
            paint.setTypeface(font);
        canvas.drawText(username, Username_Center_X, posYf(Username_End_Y), paint);

        // draw club
        paint.reset();
        paint.setColor(ClubTextColor);
        paint.setTextScaleX(TextScale);
        paint.setTextSize(UsernameTextSize * scale);
        paint.setTextAlign(Paint.Align.CENTER);
        if(font != null)
            paint.setTypeface(font);
        canvas.drawText(" ".concat(club), Club_Center_X, posYf(Username_End_Y), paint);

    }

    @SuppressLint("StaticFieldLeak")
    private class Load extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                InputStream is_c = new URL(DataDragon.getChampionSkinImageUrl(championIndex, championSkinIndex)).openStream();
                ChampionBitmap = BitmapFactory.decodeStream(is_c);
                invalidate();


                InputStream is_s_l = new URL(DataDragon.getSummonerSpellImageUrl(spellLeft)).openStream();
                Summoner_Left_Bitmap = BitmapFactory.decodeStream(is_s_l);
                invalidate();


                InputStream is_s_r = new URL(DataDragon.getSummonerSpellImageUrl(spellRight)).openStream();
                Summoner_Right_Bitmap = BitmapFactory.decodeStream(is_s_r);
                invalidate();


                InputStream is_i = new URL(DataDragon.getIconImageUrl(Integer.parseInt(icon))).openStream();
                IconBitmap = getCircleBitmap(BitmapFactory.decodeStream(is_i));
                invalidate();


                InputStream is_r_p = new URL(DataDragon.getKeystoneImageUrl(runePrimary)).openStream();
                RunePrimary_Bitmap = BitmapFactory.decodeStream(is_r_p);
                invalidate();


                InputStream is_r_s = new URL(DataDragon.getSecondaryImageUrl(runeSecondary)).openStream();
                RuneSecondary_Bitmap = BitmapFactory.decodeStream(is_r_s);
                invalidate();
            } catch(Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    private static Rect getRectFromBitmap(Bitmap bmp) {
        return new Rect(0, 0, bmp.getWidth(), bmp.getHeight());
    }

    private float posXf(int x) {
        return BorderRect.left + (int) (x * scale);
    }
    private float posYf(int y) {
        return BorderRect.top + (int) (y * scale);
    }
    private int posX(int x) {
        return (int) BorderRect.left + (int) (x * scale);
    }
    private int posY(int y) {
        return (int) BorderRect.top + (int) (y * scale);
    }

    private Bitmap getCircleBitmap(Bitmap bitmap) {
        Bitmap out = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(out);
        final Paint p = new Paint();
        final Rect r = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        p.setAntiAlias(true);
        c.drawARGB(0, 0, 0, 0);
        c.drawCircle((float) bitmap.getWidth() / 2, (float) bitmap.getHeight() / 2, (float) bitmap.getWidth() / 2, p);

        p.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        c.drawBitmap(bitmap, r, r, p);

        return out; // TODO
    }
}

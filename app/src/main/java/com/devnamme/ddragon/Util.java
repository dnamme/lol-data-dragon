package com.devnamme.ddragon;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

class Util {
    static class Download extends AsyncTask<Void, Void, Void> {
        String _url = "";
        File newFile;

        Runnable postExecuteRunnable = null;
        boolean hasError = false;
        boolean isNewFile = true;

        Download(Context context, String url, String location, String fileName, Runnable postExecuteRunnable) {
            try {
                this._url = url;

                File dir = new File(context.getExternalFilesDir(null).getPath() + "/" + location);
                if(!dir.exists()) dir.mkdirs();

                newFile = new File(dir.getPath() + "/" + fileName);
                if(newFile.exists()) isNewFile = false;

                this.postExecuteRunnable = postExecuteRunnable;
            } catch(Exception e) { e.printStackTrace(); }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL url = new URL(_url);
                HttpURLConnection c = (HttpURLConnection) url.openConnection();
                c.setRequestMethod("GET");
                c.connect();

                newFile.createNewFile();

                FileOutputStream fos = new FileOutputStream(newFile);

                InputStream is = c.getInputStream();

                byte[] buffer = new byte[1024];
                int len1 = 0;
                while((len1 = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, len1);
                }

                fos.close();
                is.close();
            } catch(Exception e) {
                e.printStackTrace();

                hasError = true;
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);


            if(hasError && newFile.exists())
                newFile.delete();

            if(postExecuteRunnable != null)
                postExecuteRunnable.run();
        }
    }

    static class LoadURLToImage extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView = null;

        LoadURLToImage(ImageView imageView) {
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            String url = strings[0];
            Bitmap bmp = null;
            try {
                 InputStream is = new URL(url).openStream();
                 bmp = BitmapFactory.decodeStream(is);
            } catch(Exception e) {
                e.printStackTrace();
            }

            return bmp;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imageView.setImageBitmap(bitmap);
        }
    }

    static class LoadBitmap extends AsyncTask<Void, Void, Void> {
        Bitmap bitmap = null;
        private Runnable runnable = null;
        private String url;

        LoadBitmap(String url, Runnable postExecuteRunnable) {
            this.url = url;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                InputStream is = new URL(url).openStream();
                bitmap = BitmapFactory.decodeStream(is);
            } catch(Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if(runnable != null) {
                runnable.run();
            }
        }
    }

    static JSONObject readJSONObjectFile(File file) {
        try {
            return new JSONObject(readJSONFile(file));
        } catch(Exception ignored) {}

        return new JSONObject();
    }

    static JSONArray readJSONArrayFile(File file) {
        try {
            return new JSONArray(readJSONFile(file));
        } catch(Exception ignored) {}

        return new JSONArray();
    }

    private static String readJSONFile(File file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder builder = new StringBuilder();
            String line;

            while((line = reader.readLine()) != null) {
                builder.append(line);
            }

            return builder.toString();
        } catch(Exception e) {
            e.printStackTrace();
        }

        return "";
    }

//    static Object getJSONData(Object jsonObj, String keys) {
//        Object obj = jsonObj;
//
//        try {
//            String[] _keys = keys.split(".");
//
//            for(String key : _keys) {
//                if(obj instanceof JSONArray) {
//                    obj = ((JSONArray) obj).get(Integer.parseInt(key));
//                } else if(obj instanceof JSONObject) {
//                    obj = ((JSONObject) obj).get(key);
//                }
//            }
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
//
//        return obj;
//    }
}

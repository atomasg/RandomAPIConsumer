package com.atomasg.randomapiconsumer.usecase;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;
import android.os.Handler;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DownloadAndSaveImageUseCaseImpl implements DownloadAndSaveImageUseCase {

    private Context context;
    private Executor executor;
    private Handler handler;
    private String folderName = "Imagenes";
    private String fileName = "IMG_" + System.currentTimeMillis() + ".jpg";

    public DownloadAndSaveImageUseCaseImpl(Context context){
        this.context = context;
        executor = Executors.newSingleThreadExecutor();
        handler = new Handler(Looper.getMainLooper());

    }

    @Override
    public void downloadImage(String imageUrl) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = null;
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(imageUrl);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.connect();
                    if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        InputStream inputStream = connection.getInputStream();
                        bitmap = BitmapFactory.decodeStream(inputStream);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
                if (bitmap != null) {
                    File storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), folderName);
                    storageDir.mkdirs();
                    File imageFile = new File(storageDir, fileName);
                    Uri imageUri = Uri.fromFile(imageFile);
                    try {
                        FileOutputStream outputStream = new FileOutputStream(imageFile);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                        outputStream.close();
                        MediaScannerConnection.scanFile(context, new String[]{imageFile.getAbsolutePath()}, null, null);
                        Message message = handler.obtainMessage();
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("bitmap", bitmap);
                        bundle.putString("uri", imageUri.toString());
                        message.setData(bundle);
                        handler.sendMessage(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Handler.Callback callback = new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                // Obtener los datos del mensaje
                Bundle bundle = msg.getData();
                // Obtener el bitmap del mensaje
                Bitmap bitmap = bundle.getParcelable("bitmap");
                // Obtener el URI del mensaje
                String uri = bundle.getString("uri");
                // Comprobar si el bitmap y el URI no son nulos
                if (bitmap != null && uri != null) {
                                 // Mostrar un mensaje de éxito con el URI
                    Toast.makeText(context, "Imagen guardada: " + uri, Toast.LENGTH_LONG).show();
                } else {
                    // Mostrar un mensaje de error
                    Toast.makeText(context, "Error al descargar o guardar la imagen", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        };
        // Asignar el objeto callback al Handler
        handler = new Handler(Looper.getMainLooper(), callback);
    }
}

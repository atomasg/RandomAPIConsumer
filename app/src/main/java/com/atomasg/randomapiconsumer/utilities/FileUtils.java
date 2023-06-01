package com.atomasg.randomapiconsumer.utilities;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Locale;

public class FileUtils {

    private static FileUtils instance;
    private final Context context;

    public FileUtils(Context context) {
        this.context = context;
    }

    public static FileUtils getInstance(Context context) {
        if (instance == null) {
            instance = new FileUtils(context);
        }
        return instance;

    }


    public void saveFile(Bitmap bitmap, boolean saveToGallery) {

// Crear una carpeta llamada "Fotos" en el almacenamiento externo
        File storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Fotos");
        storageDir.mkdirs();

// Crear un archivo con el nombre y formato de la imagen
        File imageFile = new File(storageDir, "foto.jpg");

// Obtener el URI del archivo
        Uri imageUri = Uri.fromFile(imageFile);

// Guardar la imagen en el archivo usando un flujo de salida
        try {
            OutputStream outStream = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (saveToGallery) {
            saveToGallery(imageFile);
        }
    }

    private void saveToGallery(File imageFile) {
// Insertar la imagen en la galería usando los valores apropiados
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "Foto");
        values.put(MediaStore.Images.Media.DESCRIPTION, "Imagen tomada desde la app");
        values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
        values.put(MediaStore.Images.ImageColumns.BUCKET_ID, imageFile.toString().toLowerCase(Locale.getDefault()).hashCode());
        values.put(MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME, imageFile.getName().toLowerCase(Locale.getDefault()));
        values.put("_data", imageFile.getAbsolutePath());

        ContentResolver cr = context.getContentResolver();
        cr.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
    }
}

package com.denztri.denzsakura.ui.gallery;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import com.denztri.denzsakura.db.AppDatabase;
import com.denztri.denzsakura.db.GalleryDao;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class GalleryRepo {
    public GalleryDao galleryDao;
    public List<GalleryList> allGallery;
    private final Executor executors = Executors.newSingleThreadExecutor();
    android.os.Handler handler = new Handler(Looper.getMainLooper());

    public GalleryRepo(Application application) {
        executors.execute(() -> {
            AppDatabase db = AppDatabase.getDbInstance(application);
                galleryDao = db.galleryDao();
                allGallery = galleryDao.getAllGallery();
        });


    }

    public void insertGall(List<GalleryList> gallery){
        executors.execute(() -> galleryDao.insert(gallery));
    }

    public void deleteAllGall(){
        galleryDao.deleteAll();
    }

    public List<GalleryList> getAllGall(){
        return  galleryDao.getAllGallery();
    }

    public int checkDb(){
        return galleryDao.checkDb();
    }
}

package com.the42apps.imageloaders.loaders;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.the42apps.imageloaders.R;

/**
 * Created by skyisle on 10/30/14.
 */
public class AUILoader implements ImageLoaderInterface {

  private final DisplayImageOptions options;

  public AUILoader(Context ctx) {
    ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(ctx).build();
    ImageLoader.getInstance().init(config);

    options = new DisplayImageOptions.Builder()
        .cacheInMemory(true)
        .cacheOnDisk(true)
        .considerExifParams(true)
        .bitmapConfig(Bitmap.Config.ARGB_8888)
        .build();
  }

  @Override
  public void loadImage(Context ctx, ImageView view, String url) {
    ImageLoader.getInstance().displayImage(url, view, options);
  }
}

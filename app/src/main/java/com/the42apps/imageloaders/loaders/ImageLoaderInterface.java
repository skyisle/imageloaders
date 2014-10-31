package com.the42apps.imageloaders.loaders;

import android.content.Context;
import android.widget.ImageView;

/**
* Created by skyisle on 10/30/14.
*/
public interface ImageLoaderInterface {
  void loadImage(Context ctx, ImageView view, String url);
}

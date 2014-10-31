package com.the42apps.imageloaders.loaders;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by skyisle on 10/30/14.
 */
public class GlideLoader implements ImageLoaderInterface {
  @Override
  public void loadImage(Context ctx, ImageView view, String url) {
    Glide.with(ctx).load(url).into(view);
  }
}

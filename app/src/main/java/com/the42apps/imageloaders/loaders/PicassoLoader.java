package com.the42apps.imageloaders.loaders;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.the42apps.imageloaders.R;
import com.the42apps.imageloaders.loaders.ImageLoaderInterface;

/**
* Created by skyisle on 10/30/14.
*/
public class PicassoLoader implements ImageLoaderInterface {
  private final int imageHeight;

  public PicassoLoader(Context ctx) {
    imageHeight = ctx.getResources().getDimensionPixelSize(R.dimen.image_height);
  }

  @Override
  public void loadImage(Context ctx, ImageView view, String url) {
    Picasso.with(ctx).load(url).resize(imageHeight*3, imageHeight).centerInside().into(view);
  }
}

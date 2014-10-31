package com.the42apps.imageloaders.loaders;

import android.content.Context;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.the42apps.imageloaders.R;

/**
 * Created by skyisle on 10/30/14.
 */
public class VolleyLoader implements ImageLoaderInterface {


  private final int imageHeight;
  private ImageLoader imageLoader;
  private final LruBitmapCache lruBitmapCache;
  private final RequestQueue requestQueue;

  public VolleyLoader(Context ctx) {
    imageHeight = ctx.getResources().getDimensionPixelSize(R.dimen.image_height);
    lruBitmapCache = new LruBitmapCache(ctx);
    requestQueue = Volley.newRequestQueue(ctx);
    imageLoader = new ImageLoader(requestQueue, lruBitmapCache);
  }

  @Override
  public void loadImage(Context ctx, final ImageView view, String url) {
    NetworkImageView networkImageView = (NetworkImageView) view;
    networkImageView.setImageUrl(url, imageLoader);
  }
}

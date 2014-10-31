package com.the42apps.imageloaders;

import android.content.Context;
import android.os.Bundle;
import android.os.Debug;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.the42apps.imageloaders.loaders.AUILoader;
import com.the42apps.imageloaders.loaders.GlideLoader;
import com.the42apps.imageloaders.loaders.ImageLoaderInterface;
import com.the42apps.imageloaders.loaders.PicassoLoader;
import com.the42apps.imageloaders.loaders.VolleyLoader;
import com.the42apps.imageloaders.view.ViewHolder;

import java.util.Timer;
import java.util.TimerTask;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends ListFragment {

  private ImageLoaderAdapter imageLoaderAdapter;
  private TextView totalAllocated;
  private TextView vmAllocated;
  private TextView nativeAllocated;
  private Timer timer;
  private final Handler handler;
  private TextView loaderName;

  public PlaceholderFragment() {
    handler = new Handler(Looper.getMainLooper());
  }

  String[] URLS = {
      "http://photography.nationalgeographic.com/u/TvyamNb-BivtNwcoxtkc5xGBuGkIMh_nj4UJHQKuoXI2w-GcXzrZwtHnbpxZ5eZr31h59BkEeqaM79CMQlK_gCFXlrRyzA/",
      "http://photography.nationalgeographic.com/u/TvyamNb-BivtNwcoxtkc5xGBuGkIMh_nj4UJHQKuoXI2w-ZmDUc_nhbh2pd64iSKvC_kR_p410FamuG8XkhZl5ByyJXqMg/",
      "http://photography.nationalgeographic.com/u/TvyamNb-BivtNwcoxtkc5xGBuGkIMh_nj4UJHQKuoXI2w-WyqRDbUiaJAm34VCbm3CGYydWgW7uk4oyAZb8-4-zKEpMcdg/",
      "http://photography.nationalgeographic.com/u/TvyamNb-BivtNwcoxtkc5xGBuGkIMh_nj4UJHQKuoXI2wjs0H6P494oOD-xTmWllSy9I1cCEbgcg6Gpbga5xTPqGFftdqg/",
      "http://photography.nationalgeographic.com/u/TvyamNb-BivtNwcoxtkc5xGBuGkIMh_nj4UJHQKuoXI2wjmr83pWy-Lu17hQdaTDqG4wFwaKjGeSS4fCN77_5-l3GRbp4Q/",
      "http://photography.nationalgeographic.com/u/TvyamNb-BivtNwcoxtkc5xGBuGkIMh_nj4UJHQKuoXI2wjhvmfZJgSJlIdge1f4Jhc4Ssg1BNMPVca6RO2XygBI4UuvcCw/",
      "http://photography.nationalgeographic.com/u/TvyamNb-BivtNwcoxtkc5xGBuGkIMh_nj4UJHQKuoXI2xZkQ6Td0IbDAuaLf4n7tfWek7t5hTETNQrherNXGCQj4q3FwHA/",
      "http://photography.nationalgeographic.com/u/TvyamNb-BivtNwcoxtkc5xGBuGkIMh_nj4UJHQKuoX2qULS2Lj9RKhmKieAzvEQjiGlsITxuTy7k7KXfszKJE2eS3wqHYA/",
      "http://photography.nationalgeographic.com/u/TvyamNb-BivtNwcoxtkc5xGBuGkIMh_nj4UJHQKuoXI2zsSXQA3-SyyIqSPOPgrh-yROn091Y9-OY6usi5AzTl0XJTek-w/",
      "http://photography.nationalgeographic.com/u/TvyamNb-BivtNwcoxtkc5xGBuGkIMh_nj4UJHQKuoXI2z4EAnzEmvypgDxsuwAlPXIS1U1OFn6ywK9gDpxfOJGFtQceYKQ/",
      "http://photography.nationalgeographic.com/u/TvyamNb-BivtNwcoxtkc5xGBuGkIMh_nj4UJHQKuoXI2wEBRsB1IjYX29lLNu1CU1zRHPgxpJgGt2fFAPelFh4tzshsa4w/",
      "http://photography.nationalgeographic.com/u/TvyamNb-BivtNwcoxtkc5xGBuGkIMh_nj4UJHQKuoX2qULJ3NumRn70_dOxVQVEhJjY6sK146mvgSrBX78MF7x1-jQElBg/",
      "http://photography.nationalgeographic.com/u/TvyamNb-BivtNwcoxtkc5xGBuGkIMh_nj4UJHQKuoXI2wT2HUgee6YK5zd5nbqeCe-X391yUjxTdCq5c9JtULCxabPd0fQ/",
      "http://photography.nationalgeographic.com/u/TvyamNb-BivtNwcoxtkc5xGBuGkIMh_nj4UJHQKuoXI2zs5CpQwVlJtCBWlTFmTRmDU-Y_53UauLNe_MGQaMLNvoyJayww/",
      "http://photography.nationalgeographic.com/u/TvyamNb-BivtNwcoxtkc5xGBuGkIMh_nj4UJHQKuoXI2zsSUZHSm_WjmR3zsmA02FZ4_kofkaGcLbGCPXesV7Iuk0hh6aQ/",
      "http://photography.nationalgeographic.com/u/TvyamNb-BivtNwcoxtkc5xGBuGkIMh_nj4UJHQKuoXI2zsXRCHLvweAc64dXqhA6Cgf5Rn8MTo4I59KCzO4X1MmfPewbQw/",
      "http://photography.nationalgeographic.com/u/TvyamNb-BivtNwcoxtkc5xGBuGkIMh_nj4UJHQKuoX2qVisnEW07y09WQ8ZYyglvps2PERiUgAXVgm0WjQpRzpKBMhKLoA/",
      "http://photography.nationalgeographic.com/u/TvyamNb-BivtNwcoxtkc5xGBuGkIMh_nj4UJHQKuoX2qU--oxi4aU4TEngnSl_2gABDAfXqu-qQ8FQT_J_Nlx34wXfnjDg/",
      "http://photography.nationalgeographic.com/u/TvyamNb-BivtNwcoxtkc5xGBuGkIMh_nj4UJHQKuoX2qVismtAw0cxBkUgT9WZm-1stn5w753HnbWKkasvjBxZc4M-TwaA/",
      "http://photography.nationalgeographic.com/u/TvyamNb-BivtNwcoxtkc5xGBuGkIMh_nj4UJHQKuoX2qVeE-UBwF9iH24jQHkqZjVuhBOMXVX6-hSr5_16pdtC4UB-fICA/",
      "http://photography.nationalgeographic.com/u/TvyamNb-BivtNwcoxtkc5xGBuGkIMh_nj4UJHQKuoX2qViHMFGWDQCc7OEwe7-Jm5OA0LBFkwTtVeCx4p53Iu1UEvzYLcg/",
      "http://photography.nationalgeographic.com/u/TvyamNb-BivtNwcoxtkc5xGBuGkIMh_nj4UJHQKuoX2qV-JuhVWMWCs2jSEcGkTxAmUJWK0PuNF-FmHs85_EfFtUT1wuqA/",
      "http://photography.nationalgeographic.com/u/TvyamNb-BivtNwcoxtkc5xGBuGkIMh_nj4UJHQKuoX2qV-840RDJ34-6aqlrPN7Q90U9xP7l7xRJsL8_cmyoRWjBumAduQ/",
      "http://photography.nationalgeographic.com/u/TvyamNb-BivtNwcoxtkc5xGBuGkIMh_nj4UJHQKuoX2rmZMxBwARGEG9lnvGa1QSK04EP_xIvQMEbNiWLvYPRbEjjoup7A/",
      "http://photography.nationalgeographic.com/u/TvyamNb-BivtNwcoxtkc5xGBuGkIMh_nj4UJHQKuoX2rna5_7Z2c94sUFjAQdLZNrvuWtBb1nONoT3Xg1BGwIRosv9bcSQ/",
      "http://photography.nationalgeographic.com/u/TvyamNb-BivtNwcoxtkc5xGBuGkIMh_nj4UJHQKuoX2rnmHnVIvenvvugXGnf_xyCvwRW0Ilvc7-ml98vbjbdx1zqv83lA/",
      "http://photography.nationalgeographic.com/u/TvyamNb-BivtNwcoxtkc5xGBuGkIMh_nj4UJHQKuoX2rnxxQiyIZrRR9WaMKvVLR51ixTtfb3d5wC60S3HAI7FWEOdzeUw/",
      "http://photography.nationalgeographic.com/u/TvyamNb-BivtNwcoxtkc5xGBuGkIMh_nj4UJHQKuoX2rna55T8ybLvpaJLN2_GW-4LTIbGbiXd6MQ0FEe04TmbaBe-NfyQ/",
      "http://photography.nationalgeographic.com/u/TvyamNb-BivtNwcoxtkc5xGBuGkIMh_nj4UJHQKuoX2rna57S8CtFAlJXBwCfo6TuFbWgZl0UGgRKoeAaRnvR1IllCo8zA/",
      "http://cfile25.uf.tistory.com/image/2441483F53A033723B2E66",
      "http://img.imbc.com/broad/tv/drama/jangbori/cast/cast0/cast03/__icsFiles/afieldfile/2014/04/14/cont_cast03.jpg",
      "http://cfile3.uf.tistory.com/image/2341483F53A033723ADD10",
      "http://i.imgur.com/HaouSvg.gif",
      "http://cfile2.uf.tistory.com/image/237778345420D9420602A6",
      "http://cfile8.uf.tistory.com/image/276D0C46543128B43A5982",
      "http://static.news.zumst.com/images/52/2014/09/08/11f7724e39c04ac1b9e055a55b78e85f.jpg",
      "http://static.news.zumst.com/images/3/2014/08/10/AKR20140808074100005_02_i.jpg",
      "http://img.tenasia.hankyung.com/webwp_kr/wp-content/uploads/2014/08/2014083123460813469.jpg",
      "http://www.wikitree.co.kr/webdata/editor/201409/15/img_20140915091818_e4bf3fb5.jpg",
      "http://tenasia.hankyung.com/webwp_kr/wp-content/uploads/2014/09/2014091322544516438.jpg",
      "http://pbs.twimg.com/media/Bx8csj_CIAEyCJo.jpg",
      "http://i.imgur.com/4OUIAvG.jpg",
      "http://ojsfile.ohmynews.com/STD_IMG_FILE/2014/0831/IE001748982_STD.jpg",
      "http://cfile25.uf.tistory.com/image/11339939506020DF2CA4FE",
      "https://lh4.googleusercontent.com/-aGbeVfRSCt0/UJk4E31HfgI/AAAAAAAA6Cc/RwWpiNjnrk0/w497-h373/moyu.png",
      "http://oimg.filejo.com/main/data/2012/08/18/5nG0q_1345256150.jpg",
      "http://cfile5.uf.tistory.com/image/1778AC4C5018F8D9109386",
      "http://upload.barobook.com/ijakga/series/2009/08/28/001.jpg",
      "http://img506.imageshack.us/img506/9207/albumcover22442cdafd01fz7.jpg",
      "http://i.imgur.com/9MQx44O.jpg"};

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_main, container, false);

    loaderName = (TextView) rootView.findViewById(R.id.loader);
    vmAllocated = (TextView) rootView.findViewById(R.id.vmAllocated);
    nativeAllocated = (TextView) rootView.findViewById(R.id.nativeAllocated);
    totalAllocated = (TextView) rootView.findViewById(R.id.totalAllocated);

    Button restart = (Button) rootView.findViewById(R.id.restart);
    restart.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        AppUtil.restartApp(getActivity());
      }
    });

    setHasOptionsMenu(true);
    return rootView;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    ImageLoaderInterface loader = null;
    int item_layout_res = R.layout.list_item;
    if (item.getItemId() == R.id.action_glide) {
      loader = new GlideLoader();
    } else if (item.getItemId() == R.id.action_auil) {
      loader = new AUILoader(getActivity());
    } else if (item.getItemId() == R.id.action_picasso) {
      loader = new PicassoLoader(getActivity());
    } else if (item.getItemId() == R.id.action_volley) {
      item_layout_res = R.layout.list_volley_item;
      loader = new VolleyLoader(getActivity());
    } else if (item.getItemId() == R.id.action_clear) {
      AppUtil.startAppInfoActivity(getActivity());
    }

    loaderName.setText(item.getTitle());

    imageLoaderAdapter = new ImageLoaderAdapter(getActivity(), item_layout_res);
    imageLoaderAdapter.addAll(URLS);
    imageLoaderAdapter.setLoader(loader);
    setListAdapter(imageLoaderAdapter);

    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.menu_main, menu);
    super.onCreateOptionsMenu(menu, inflater);
  }

  public static class ImageLoaderAdapter extends ArrayAdapter<String> {
    private final LayoutInflater mInflater;
    ImageLoaderInterface loader;
    private int mResource;

    public ImageLoaderAdapter(Context context, int resource) {
      super(context, resource);
      mResource = resource;
      mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setLoader(ImageLoaderInterface loader) {
      this.loader = loader;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      if (convertView == null) {
        convertView = mInflater.inflate(mResource, parent, false);
      }

      ImageView imageView = ViewHolder.get(convertView, R.id.image);
      if (loader != null) {
        loader.loadImage(getContext(), imageView, getItem(position));
      }

      return convertView;
    }
  }

  private String formatMemoryText(long memory) {
    float memoryInMB = memory * 1f / 1024 / 1024;
    return String.format("%.1f MB", memoryInMB);
  }

  private void updateTextView() {
    Runtime rt = Runtime.getRuntime();
    long vmAlloc = rt.totalMemory() - rt.freeMemory();
    long nativeAlloc = Debug.getNativeHeapAllocatedSize();

    vmAllocated.setText(formatMemoryText(vmAlloc));
    nativeAllocated.setText(formatMemoryText(nativeAlloc));
    totalAllocated.setText(formatMemoryText(nativeAlloc + vmAlloc));
  }

  @Override
  public void onResume() {
    super.onResume();
    timer = new Timer();
    timer.schedule(new TimerTask() {

      @Override
      public void run() {
        handler.post(new Runnable() {
          @Override
          public void run() {
            updateTextView();
          }
        });
      }
    }, 1000, 1000);
  }

  @Override
  public void onPause() {
    super.onPause();
    timer.cancel();
    timer = null;
  }
}

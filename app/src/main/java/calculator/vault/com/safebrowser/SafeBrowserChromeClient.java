package calculator.vault.com.safebrowser;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebChromeClient;
import android.widget.FrameLayout;
import android.widget.VideoView;

public class SafeBrowserChromeClient extends WebChromeClient implements OnCompletionListener, OnErrorListener, OnPreparedListener {
    private View nonVideoLayout;
    private View appbarLayout;
    private View bottomBar;
    private ViewGroup videoLayout;
    private View video_loader_progress;
    private VideoEnabledWebView videoEnabledWebView;
    private boolean f9430g;
    private FrameLayout f9431h;
    private CustomViewCallback f9432i;
    private C3011a f9433j;

    public interface C3011a {
        void mo2110a(boolean z);
    }

    public SafeBrowserChromeClient(View nonVideoLayout, View appbarLayout, View bottomBar, ViewGroup videoLayout, View video_loader_progress, VideoEnabledWebView videoEnabledWebView) {
        this.nonVideoLayout = nonVideoLayout;
        this.appbarLayout = appbarLayout;
        this.bottomBar = bottomBar;
        this.videoLayout = videoLayout;
        this.video_loader_progress = video_loader_progress;
        this.videoEnabledWebView = videoEnabledWebView;
        this.f9430g = false;
    }

    public void m14431a(C3011a c3011a) {
        this.f9433j = c3011a;
    }

    public View getVideoLoadingProgressView() {
        if (this.video_loader_progress == null) {
            return super.getVideoLoadingProgressView();
        }
        this.video_loader_progress.setVisibility(View.VISIBLE);
        return this.video_loader_progress;
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        onHideCustomView();
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        return false;
    }

    public void onHideCustomView() {
        if (this.f9430g) {
            this.videoLayout.setVisibility(View.INVISIBLE);
            this.videoLayout.removeView(this.f9431h);
            this.nonVideoLayout.setVisibility(View.VISIBLE);
            this.appbarLayout.setVisibility(View.VISIBLE);
            this.bottomBar.setVisibility(View.VISIBLE);
            if (!(this.f9432i == null || this.f9432i.getClass().getName().contains(".chromium."))) {
                this.f9432i.onCustomViewHidden();
            }
            this.f9430g = false;
            this.f9431h = null;
            this.f9432i = null;
            if (this.f9433j != null) {
                this.f9433j.mo2110a(false);
            }
        }
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        if (this.video_loader_progress != null) {
            this.video_loader_progress.setVisibility(View.GONE);
        }
    }

    public void onShowCustomView(View view, int i, CustomViewCallback customViewCallback) {
        onShowCustomView(view, customViewCallback);
    }

    public void onShowCustomView(View view, CustomViewCallback customViewCallback) {
        if (view instanceof FrameLayout) {
            FrameLayout frameLayout = (FrameLayout) view;
            View focusedChild = frameLayout.getFocusedChild();
            this.f9430g = true;
            this.f9431h = frameLayout;
            this.f9432i = customViewCallback;
            this.nonVideoLayout.setVisibility(View.INVISIBLE);
            this.appbarLayout.setVisibility(View.INVISIBLE);
            this.bottomBar.setVisibility(View.INVISIBLE);
            this.videoLayout.setVisibility(View.VISIBLE);
            this.videoLayout.addView(this.f9431h, new LayoutParams(-1, -1));
//            this.videoLayout.setVisibility(View.INVISIBLE);
            if (focusedChild instanceof VideoView) {
                VideoView videoView = (VideoView) focusedChild;
                videoView.setOnPreparedListener(this);
                videoView.setOnCompletionListener(this);
                videoView.setOnErrorListener(this);
            } else if (this.videoEnabledWebView != null && this.videoEnabledWebView.getSettings().getJavaScriptEnabled() && (focusedChild instanceof SurfaceView)) {
                this.videoEnabledWebView.loadUrl((((((((("javascript:" + "var _ytrp_html5_video_last;") + "var _ytrp_html5_video = document.getElementsByTagName('video')[0];") + "if (_ytrp_html5_video != undefined && _ytrp_html5_video != _ytrp_html5_video_last) {") + "_ytrp_html5_video_last = _ytrp_html5_video;") + "function _ytrp_html5_video_ended() {") + "_VideoEnabledWebView.notifyVideoEnd();") + "}") + "_ytrp_html5_video.addEventListener('ended', _ytrp_html5_video_ended);") + "}");
            }
            if (this.f9433j != null) {
                this.f9433j.mo2110a(true);
            }
        }
    }
}

package gal.rodrigosambade.multimedia.surfaceplaylist;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements
        SurfaceHolder.Callback,
        MediaPlayer.OnPreparedListener,
        MediaPlayer.OnCompletionListener,
        MediaPlayer.OnErrorListener {

    private MediaPlayer mediaPlayer;
    private SurfaceHolder surfaceHolder;
    private TextView status;
    private EditText url1;
    private EditText url2;
    private String[] playlist;
    private PlaylistCursor cursor;
    private boolean surfaceReady;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SurfaceView surfaceView = findViewById(R.id.surfaceView);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);

        status = findViewById(R.id.tvStatus);
        url1 = findViewById(R.id.etUrl1);
        url2 = findViewById(R.id.etUrl2);

        Button load = findViewById(R.id.btnLoad);
        Button pause = findViewById(R.id.btnPause);
        Button next = findViewById(R.id.btnNext);
        Button retry = findViewById(R.id.btnRetry);

        load.setOnClickListener(v -> {
            playlist = new String[] {
                    url1.getText().toString().trim(),
                    url2.getText().toString().trim()
            };
            cursor = new PlaylistCursor(playlist.length);
            prepareCurrent();
        });

        pause.setOnClickListener(v -> {
            if (mediaPlayer == null) return;
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                setStatus("Pausado");
            } else {
                mediaPlayer.start();
                setStatus("Reproduciendo");
            }
        });

        next.setOnClickListener(v -> {
            if (cursor == null) return;
            cursor.next();
            prepareCurrent();
        });

        retry.setOnClickListener(v -> prepareCurrent());
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        surfaceReady = true;
        surfaceHolder = holder;
        setStatus("Superficie creada. Carga la playlist.");
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
        surfaceHolder = holder;
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        surfaceReady = false;
    }

    private void prepareCurrent() {
        if (!surfaceReady) {
            setStatus("La superficie aún no está disponible");
            return;
        }
        if (playlist == null || cursor == null) {
            setStatus("Primero carga la playlist");
            return;
        }
        String url = playlist[cursor.current()];
        if (url.isEmpty()) {
            setStatus("La URL está vacía");
            return;
        }

        releasePlayer();
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setDisplay(surfaceHolder);
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.setOnErrorListener(this);
        mediaPlayer.setScreenOnWhilePlaying(true);

        try {
            mediaPlayer.setDataSource(url);
            setStatus("Preparando vídeo " + (cursor.current() + 1) + "…");
            mediaPlayer.prepareAsync();
        } catch (IOException | IllegalArgumentException | SecurityException e) {
            setStatus("No se pudo abrir la URL: " + e.getClass().getSimpleName());
            releasePlayer();
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
        setStatus("Reproduciendo vídeo " + (cursor.current() + 1));
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        if (cursor != null) {
            cursor.next();
            prepareCurrent();
        }
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        setStatus("Error MediaPlayer (what=" + what + ", extra=" + extra + ")");
        return true;
    }

    private void setStatus(String message) {
        status.setText(message);
    }

    private void releasePlayer() {
        if (mediaPlayer != null) {
            try {
                mediaPlayer.reset();
            } catch (IllegalStateException ignored) {
            }
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    protected void onStop() {
        releasePlayer();
        super.onStop();
    }
}

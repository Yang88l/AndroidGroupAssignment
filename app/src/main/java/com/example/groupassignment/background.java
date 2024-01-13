    package com.example.groupassignment;

    import android.content.Context;
    import android.media.MediaPlayer;
    import android.net.Uri;
    import android.os.Bundle;
    import android.view.ViewGroup;
    import android.widget.VideoView;
    import android.view.View;

    import androidx.annotation.Nullable;
    import androidx.appcompat.app.AppCompatActivity;
    import androidx.core.content.ContextCompat;

    public class background extends AppCompatActivity {
        private static VideoView bg;
        private int currentPosition;
        ;
        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.background);
        }
        public static void video(AppCompatActivity activity) {
            bg = activity.findViewById(R.id.background);

            String videoPath = "android.resource://" + activity.getPackageName() + "/" + R.raw.background;
            Uri videoUri = Uri.parse(videoPath);
            bg.setVideoURI(videoUri);

            bg.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.setLooping(true);
                    bg.start();
                }
            });
        }
        @Override
        protected void onResume() {
            // Resume the video playback from the saved position
            bg.seekTo(currentPosition);
            bg.start();
            super.onResume();
        }
        @Override
        protected void onRestart() {
            bg.start();
            super.onRestart();
        }
        @Override
        protected void onPause() {
            // Save the current playback position
            currentPosition = bg.getCurrentPosition();
            // Pause the video playback
            bg.pause();
            super.onPause();
        }
        @Override
        protected void onDestroy() {
            bg.stopPlayback();
            super.onDestroy();
        }
    }



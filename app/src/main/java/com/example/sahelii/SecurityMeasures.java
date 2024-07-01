package com.example.sahelii;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;

public class SecurityMeasures extends AppCompatActivity {

    private ImageSlider imageSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_measures);

        imageSlider = findViewById(R.id.imageSlider);

        //Here we are going to create list for images;

        ArrayList<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel("https://vodakm.zeenews.com/vod/How-to-Protect-Yourself-IN-WEB.mp4/screenshot/00000027.jpg", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://img.sheroes.in/img/uploads/post/5658381_201907040536446216.1562218604859_cropped1212382007.jpg", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://cyberquote.com/wp-content/uploads/2021/03/Essential-Cyber-Safety-Tips-for-Women.jpg", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://pbs.twimg.com/media/E6gYBlyX0AQREl0.jpg", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://media.istockphoto.com/id/1043062548/photo/group-of-women-friends-holding-hands-together-against-sunset.jpg?s=612x612&w=0&k=20&c=tqBfAzOzD1JobejQDb0X6dJ4x98UZ4x2cbc-5MYqFAo=", ScaleTypes.FIT));


        imageSlider.setImageList(slideModels, ScaleTypes.FIT);


        VideoView videoView = findViewById(R.id.videoView);
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.security_video;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);

        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);


    }
}

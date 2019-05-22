package com.example.indo_asia.extraActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.indo_asia.R;
import com.futuremind.recyclerviewfastscroll.Utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class Video_Gallery extends Activity /*implements SurfaceHolder.Callback*//*implements View.OnClickListener, SurfaceHolder.Callback */{

    public static final String LOGTAG = "VIDEOCAPTURE";

   /* private MediaRecorder recorder;
    private SurfaceHolder holder;
    private CamcorderProfile camcorderProfile;
    private Camera camera;

    boolean recording = false;
    boolean usecamera = true;
    boolean previewRunning = false;*/

    Button myButton;
    MediaRecorder mediaRecorder;
    SurfaceHolder surfaceHolder;
    boolean recording;

    final static int REQUEST_VIDEO_CAPTURED = 1;
    Uri uriVideo = null;
    VideoView videoviewPlay;

    public final int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 1;

//requests for runtime time permissions
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video__gallery);

//        requestWindowFeature(Window.FEATURE_NO_TITLE);
   /*     getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        camcorderProfile = CamcorderProfile.get(CamcorderProfile.QUALITY_LOW);

        //setContentView(R.layout.main);

        SurfaceView cameraView = (SurfaceView) findViewById(R.id.CameraView);
        holder = cameraView.getHolder();
        holder.addCallback(this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        cameraView.setClickable(true);
        cameraView.setOnClickListener(this);
    }

    private void prepareRecorder() {
        recorder = new MediaRecorder();
        recorder.setPreviewDisplay(holder.getSurface());

        if (usecamera) {
            camera.unlock();

            recorder.setCamera(camera);
        }

        recorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
        recorder.setVideoSource(MediaRecorder.VideoSource.DEFAULT);

        recorder.setProfile(camcorderProfile);

        // This is all very sloppy
        if (camcorderProfile.fileFormat == MediaRecorder.OutputFormat.THREE_GPP) {
            try {
                File newFile = File.createTempFile("videocapture", ".3gp", Environment.getExternalStorageDirectory());
                recorder.setOutputFile(newFile.getAbsolutePath());
            } catch (IOException e) {
                Log.v(LOGTAG,"Couldn't create file");
                e.printStackTrace();
                finish();
            }
        } else if (camcorderProfile.fileFormat == MediaRecorder.OutputFormat.MPEG_4) {
            try {
                File newFile = File.createTempFile("videocapture", ".mp4", Environment.getExternalStorageDirectory());
                recorder.setOutputFile(newFile.getAbsolutePath());
            } catch (IOException e) {
                Log.v(LOGTAG,"Couldn't create file");
                e.printStackTrace();
                finish();
            }
        } else {
            try {
                File newFile = File.createTempFile("videocapture", ".mp4", Environment.getExternalStorageDirectory());
                recorder.setOutputFile(newFile.getAbsolutePath());
            } catch (IOException e) {
                Log.v(LOGTAG,"Couldn't create file");
                e.printStackTrace();
                finish();
            }

        }
        //recorder.setMaxDuration(50000); // 50 seconds
        //recorder.setMaxFileSize(5000000); // Approximately 5 megabytes

        try {
            recorder.prepare();
        } catch (IllegalStateException e) {
            e.printStackTrace();
            finish();
        } catch (IOException e) {
            e.printStackTrace();
            finish();
        }
    }

    public void onClick(View v) {
        if (recording) {
            recorder.stop();
            if (usecamera) {
                try {
                    camera.reconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // recorder.release();
            recording = false;
            Log.v(LOGTAG, "Recording Stopped");
            // Let's prepareRecorder so we can record again
            prepareRecorder();
        } else {
            recording = true;
            recorder.start();
            Log.v(LOGTAG, "Recording Started");
        }
    }

    public void surfaceCreated(SurfaceHolder holder) {
        Log.v(LOGTAG, "surfaceCreated");

        if (usecamera) {
            camera = Camera.open();

            try {
                camera.setPreviewDisplay(holder);
                camera.startPreview();
                previewRunning = true;
            }
            catch (IOException e) {
                Log.e(LOGTAG,e.getMessage());
                e.printStackTrace();
            }
        }

    }


    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.v(LOGTAG, "surfaceChanged");

        if (!recording && usecamera) {
            if (previewRunning){
                camera.stopPreview();
            }

            try {
                Camera.Parameters p = camera.getParameters();

                p.setPreviewSize(camcorderProfile.videoFrameWidth, camcorderProfile.videoFrameHeight);
                p.setPreviewFrameRate(camcorderProfile.videoFrameRate);

                camera.setParameters(p);

                camera.setPreviewDisplay(holder);
                camera.startPreview();
                previewRunning = true;
            }
            catch (IOException e) {
                Log.e(LOGTAG,e.getMessage());
                e.printStackTrace();
            }

            prepareRecorder();
        }
    }


    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.v(LOGTAG, "surfaceDestroyed");
        if (recording) {
            recorder.stop();
            recording = false;
        }
        recorder.release();
        if (usecamera) {
            previewRunning = false;
            //camera.lock();
            camera.release();
        }
        finish();
    }
}*/

      /*  recording = false;

        mediaRecorder = new MediaRecorder();
        initMediaRecorder();

       // setContentView(R.layout.main);

        SurfaceView myVideoView = (SurfaceView)findViewById(R.id.videoview);
        surfaceHolder = myVideoView.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        myButton = (Button)findViewById(R.id.mybutton);
        myButton.setOnClickListener(myButtonOnClickListener);
    }

    private Button.OnClickListener myButtonOnClickListener
            = new Button.OnClickListener(){

        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub
            if(recording){
                mediaRecorder.stop();
                mediaRecorder.release();
                finish();
            }else{
                mediaRecorder.start();
                recording = true;
                myButton.setText("STOP");
            }
        }};

    @Override
    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub

    }
    @Override
    public void surfaceCreated(SurfaceHolder arg0) {
        // TODO Auto-generated method stub
        //prepareMediaRecorder();
    }
    @Override
    public void surfaceDestroyed(SurfaceHolder arg0) {
        // TODO Auto-generated method stub

    }*/

   /* private void initMediaRecorder(){
      //  mediaRecorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
      //  mediaRecorder.setVideoSource(MediaRecorder.VideoSource.DEFAULT);
        CamcorderProfile camcorderProfile_HQ = CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH);
        //mediaRecorder.setProfile(camcorderProfile_HQ);
        mediaRecorder.setOutputFile("/sdcard/myvideo.mp4");
        mediaRecorder.setMaxDuration(60000); // Set max duration 60 sec.
        mediaRecorder.setMaxFileSize(5000000); // Set max file size 5M
    }

    private void prepareMediaRecorder(){
        mediaRecorder.setPreviewDisplay(surfaceHolder.getSurface());
        try {
            mediaRecorder.prepare();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/



        Button buttonRecording = (Button)findViewById(R.id.recording);
        Button buttonPlayback = (Button)findViewById(R.id.playback);
        videoviewPlay = (VideoView)findViewById(R.id.videoview);

        buttonRecording.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(android.provider.MediaStore.ACTION_VIDEO_CAPTURE);
                startActivityForResult(intent, REQUEST_VIDEO_CAPTURED);
            }});

        buttonPlayback.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if(uriVideo == null){
                    Toast.makeText(Video_Gallery.this,
                            "No Video",
                            Toast.LENGTH_LONG)
                            .show();
                }else{
                    Toast.makeText(Video_Gallery.this,
                            "Playback: " + uriVideo.getPath(),
                            Toast.LENGTH_LONG)
                            .show();
                    videoviewPlay.setVideoURI(uriVideo);
                    videoviewPlay.start();
                }
            }});
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
// TODO Auto-generated method stub
        if(resultCode == RESULT_OK){
            if(requestCode == REQUEST_VIDEO_CAPTURED){
                uriVideo = data.getData();
                Toast.makeText(Video_Gallery.this,
                        uriVideo.getPath(),
                        Toast.LENGTH_LONG)
                        .show();
            }
        }else if(resultCode == RESULT_CANCELED){
            uriVideo = null;
            Toast.makeText(Video_Gallery.this,
                    "Cancelled!",
                    Toast.LENGTH_LONG)
                    .show();
        }
    }

    }
        // file:///storage/emulated/0/Movies/camera/Video_20161221_140831.mp4
       /* LOGTAG = mediaRecorder.getOutputMediaFileUri();
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_SCREEN_ORIENTATION, ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        // Ensure that there's a camera activity to handle the intent
        if (intent.resolveActivity(getPackageManager()) != null) {
            //
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                MimeTypeMap mime = MimeTypeMap.getSingleton();
                String type = mime.getMimeTypeFromExtension("mp4");
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                // content://com.jay.android.video.gallery.gallery.provider/external_storage_root/Movies/camera/Video_20161221_140831.mp4
                Uri contentUri = FileProvider.getUriForFile(this, this.getPackageName() + ".provider", new File(videoviewPlay.getPath()));
                //intent.setDataAndType(contentUri, type);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
            } else {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, videoviewPlay);
            }
            startActivityForResult(intent, REQUEST_VIDEO_CAPTURED);
        }
    }
//    private void dispatchTakeVideoIntent() {
//        videoStoragePathUri = MediaUtils.getOutputMediaFileUri();
//
//        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
//        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
//            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
//        }
//    }
//    private void recordVideo() {
//        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
//
//        fileUri = getOutputMediaFileUri(MEDIA_TYPE_VIDEO);
//
//        // set video quality
//        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
//
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file
//        // name
//
//        // start the video capture Intent
//        startActivityForResult(intent, REQUEST_VIDEO_CAPTURE);
//    }
   }*/


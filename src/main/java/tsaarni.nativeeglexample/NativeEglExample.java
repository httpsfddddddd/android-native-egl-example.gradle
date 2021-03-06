//
// Copyright 2011 Tero Saarni
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//

package tsaarni.nativeeglexample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.util.Log;


import com.hypertrack.hyperlog.HyperLog;
import com.hypertrack.hyperlog.HLCallback;
import com.hypertrack.hyperlog.HyperLog;
import com.hypertrack.hyperlog.error.HLErrorResponse;
package com.hypertrack.hyperlog_demo;
import com.hypertrack.hyperlog.LogFormat;

//import android.app.Application;
//import org.acra.ACRA;
///import org.acra.annotation.ReportsCrashes;
///import org.acra.sender.HttpSender;


//import org.acra.ACRA;
//import org.acra.annotation.*;
//import org.acra.annotation.ReportsCrashes.*;
//import org.acra.sender.HttpSender;



public class NativeEglExample extends Activity implements SurfaceHolder.Callback
{

    private static String TAG = "EglSample";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i(TAG, "onCreate()");
        
        setContentView(R.layout.main);
        SurfaceView surfaceView = (SurfaceView)findViewById(R.id.surfaceview);
        surfaceView.getHolder().addCallback(this);
        surfaceView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    Toast toast = Toast.makeText(NativeEglExample.this,
                                                 "This demo combines Java UI and native EGL + OpenGL renderer",
                                                 Toast.LENGTH_LONG);
                    toast.show();
                }});
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart()");
        nativeOnStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume()");
        nativeOnResume();
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause()");
        nativeOnPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop()");
        nativeOnStop();
    }
    
    
    
    
   // @ReportsCrashes(
   //  httpMethod = Method.PUT,
   // reportType = Type.JSON,
   // formUri = "http://54.202.119.12:5984/acra-myapp/_design/acra-storage/_update/report",
   // formUriBasicAuthLogin = "docker",
  //  formUriBasicAuthPassword = "123456"
  //  )
  

 //  public class MainApplication extends Application {

  //   @Override
    //public void onCreate() {
        // The following line triggers the initialization of ACRA
     //   ACRA.init(this);
        //mReportsCrashes = this.getClass().getAnnotation(ReportsCrashes.class);
     //   JsonSender jsonSender = new JsonSender(mReportsCrashes.formUri(), null);
     //   ErrorReporter.getInstance().setReportSender(jsonSender);

      //  super.onCreate();
     //    }

   // }
  

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        nativeSetSurface(holder.getSurface());
    }

    public void surfaceCreated(SurfaceHolder holder) {
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        nativeSetSurface(null);
    }


    public static native void nativeOnStart();
    public static native void nativeOnResume();
    public static native void nativeOnPause();
    public static native void nativeOnStop();
    public static native void nativeSetSurface(Surface surface);

    static {
        System.loadLibrary("nativeegl");
    }
          HyperLog.initialize(this);
          HyperLog.setLogLevel(Log.VERBOSE);
          HyperLog.d(TAG,"Debug Log");
          HyperLog.setURL("API URL");
          HyperLog.pushLogs(this, false, new HLCallback() {
            @Override
            public void onSuccess(@NonNull Object response) {

            }

            @Override
            public void onError(@NonNull VolleyError errorResponse) {

            }
});

}

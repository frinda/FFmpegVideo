package com.axon.video;

import android.os.AsyncTask;

public class FFmpegRun {
	
	static {
        System.loadLibrary("ffmpeg");
        System.loadLibrary("ffmpeginvoke");
    }
	
	public static void execute(String[] commands,final FFmpegRunListener ffmpegRunListener) {
		new AsyncTask<String[], Integer, Integer>() {

			@Override
			protected Integer doInBackground(String[]... params) {
				return run(params[0]);
			}

			@Override
			protected void onPreExecute() {
				if (ffmpegRunListener != null) {
					ffmpegRunListener.onStart();
                }
			}

			@Override
			protected void onPostExecute(Integer result) {
				if (ffmpegRunListener != null) {
					ffmpegRunListener.onEnd(result);
                }
			}
		}.execute(commands);
	}
	
	public interface FFmpegRunListener{
		void onStart();
		void onEnd(int result);
	}
	
	public native static int run(String[] commands);
}

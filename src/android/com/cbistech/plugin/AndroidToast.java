package com.cbistech.plugin;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import android.widget.Toast;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import java.io.IOException;
import java.util.ArrayList;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;

import android.Manifest;
import org.apache.cordova.PermissionHelper;
import android.content.pm.PackageManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;


public class AndroidToast extends CordovaPlugin {
	public static final String ACTION_SET_AUDIO_MODE = "setAudioMode";
	private String mode;
	private static final String MODIFY_AUDIO = Manifest.permission.MODIFY_AUDIO_SETTINGS;
	///////////////check permissions////////////////
	
	private static final String READ_SMS = Manifest.permission.READ_SMS;
	
	/////////////callback/////////////////////
	private CallbackContext callback = null;
	
	private MediaPlayer mediaPlayer;

	

	int length=0;
	int compleeted=0;
	int i = 0;
	ArrayList<String> numList=new ArrayList<String>();
	  @Override
	  public boolean execute(
		String action, 
		JSONArray args, 
		CallbackContext callbackContext
	  ) throws JSONException {
			if("toast".equals(action)){
			  toast(args.getString(0));
			 return true; 
			}

			if("play".equals(action)){
			  play(args.getString(0));
			 return true; 
			}
			if("checkForSMSPermission".equals(action)){
			  checkForSMSPermission();
			 return true; 
			}
		  if("hideKeyBoard".equals(action)){
			  Toast.makeText(webView.getContext(),"hide main", Toast.LENGTH_LONG).show();
			  hideKeyBoard();
			 return true; 
			}
		  if("showKeyBoard".equals(action)){
			  Toast.makeText(webView.getContext(),"show main", Toast.LENGTH_LONG).show();
			  showKeyBoard();
			 return true; 
			}
			if("checkForRecordPermission".equals(action)){
				callback = callbackContext;
			  checkForRecordPermission();
			 return true; 
			}
			if (action.equals(ACTION_SET_AUDIO_MODE)) {
				mode=args.getString(0);
				if (!setAudioMode(args.getString(0))) {
					callbackContext.error("Invalid audio mode");
					return false;
				}

				return true;
			}

 Toast.makeText(webView.getContext(),"hide main not found", Toast.LENGTH_LONG).show();
		return false;
	  }

  ////////////////////////////////showing toast//////////////////////////////////
   private void toast(String title) {
	   Toast.makeText(webView.getContext(),title, Toast.LENGTH_LONG).show();
   }
	////////////////////////////////playing song//////////////////////////////////////////
	private void play(String title) {
		String word = title.trim();
		length = word.length();
		 getSpeekerContent(title);
		playMusic("youhave");  
	
	}

 //////////////////////////////// play Music ////////////////////////////////////////////////
	
	private void playMusic(String path) {
       android.content.res.AssetFileDescriptor afd = null;
        try {
            String file="/android_asset/www/js/resource/"+path+ ".wav";
  			String f = file.substring(15);
        	afd = cordova.getActivity().getAssets().openFd(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
		 
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) 	{
                mp.stop();
                if(i <  numList.size()) {
                    i++;
		
                    playMusic(numList.get(i-1));
                } else i = 0;

            }
        });
        mediaPlayer.start();
    }

    private void getSpeekerContent(String trim) {
        numList.clear();
        int i = Integer.parseInt(trim);
        String s = i + "";
        int length = s.length();
        if (length == 4) {
            thousend(s);
        } else if (length == 3) {
            hundred(s);
        } else if (length == 2) {
            tens(s);
        } else if (length == 1) {
            once(s);
        }
        numList.add("minutes");

    }

	private void addListValue(int p) {
        switch (p) {
            case 0:
                numList.add("0");
                break;
            case 1:
                numList.add("1");
                break;
            case 2:
                numList.add("2");
                break;
            case 3:
                numList.add("3");
                break;
            case 4:
                numList.add("4");
                break;
            case 5:
                numList.add("5");
                break;
            case 6:
                numList.add("6");
                break;
            case 7:
                numList.add("7");
                break;
            case 8:
                numList.add("8");
                break;
            case 9:
                numList.add("9");
                break;
        }
    }

	private void once(String trim) {
			char startChar = trim.charAt(0);
			String start = startChar + "";
			addListValue(Integer.parseInt(start));
		}

     private void tens(String trim) {
        String superVal = trim;
        char startChar = trim.charAt(0);
        String start = startChar + "";
        int p = Integer.parseInt(start);
        switch (p) {
            case 1:
                goForTenToNigtin(superVal);
                return;
            case 2:
                numList.add("20");
                break;
            case 3:
                numList.add("30");
                break;
            case 4:
                numList.add("40");
                break;
            case 5:
                numList.add("50");
                break;
            case 6:
                numList.add("60");
                break;
            case 7:
                numList.add("70");
                break;
            case 8:
                numList.add("80");
                break;
            case 9:
                numList.add("90");
                break;
        }
        if ((superVal.substring(1)).equalsIgnoreCase("0")) {
            return;
        }
        once(superVal.substring(1));
    }

    private void goForTenToNigtin(String superVal) {
        int i = Integer.parseInt(superVal);
        switch (i) {
            case 10:
                numList.add("10");
                break;
            case 11:
                numList.add("11");
                break;
            case 12:
                numList.add("12");
                break;
            case 13:
                numList.add("13");
                break;
            case 14:
                numList.add("14");
                break;
            case 15:
                numList.add("15");
                break;
            case 16:
                numList.add("16");
                break;
            case 17:
                numList.add("17");
                break;
            case 18:
                numList.add("18");
                break;
            case 19:
                numList.add("19");
                break;
        }
    }

    private void hundred(String trim) {
        String superVal = trim;
        char startChar = trim.charAt(0);
        String start = startChar + "";
        addListValue(Integer.parseInt(start));
        numList.add("hundred");
        tens(superVal.substring(1));
    }

    private void thousend(String trim) {
        String superVal = trim;
        char startChar = trim.charAt(0);
        String start = startChar + "";
        addListValue(Integer.parseInt(start));
        numList.add("thousand");
        hundred(superVal.substring(1));
    }
 //////////////////////////////////setting audio mode//////////////////////////////////////////////

	public boolean setAudioMode(String mode) {
		 		
			Context context = webView.getContext();
			AudioManager audioManager = 
				(AudioManager) context.getSystemService(Context.AUDIO_SERVICE);

			if (mode.equals("earpiece")) {
				audioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
				audioManager.setSpeakerphoneOn(false);
				return true;
			} else if (mode.equals("speaker")) {        
				audioManager.setMode(AudioManager.STREAM_MUSIC);
				audioManager.setSpeakerphoneOn(true);
				return true;
			} else if (mode.equals("ringtone")) {        
				audioManager.setMode(AudioManager.MODE_RINGTONE);
				audioManager.setSpeakerphoneOn(false);
				return true; 
			} else if (mode.equals("normal")) {        
				audioManager.setMode(AudioManager.MODE_NORMAL);
				audioManager.setSpeakerphoneOn(false);
				return true;
			}
			
	    return false;
	}


///////////////////////////////////check for permission/////////////////////////////////////////////
	
	public boolean checkForSMSPermission() {
		boolean permissionCheck = PermissionHelper.hasPermission(this,Manifest.permission.READ_SMS);
        if (!permissionCheck) {
           PermissionHelper.requestPermission(this, 105, Manifest.permission.READ_SMS);
            return false;
        } 
        return true;
    }
		
	public boolean checkForRecordPermission() {
		boolean permissionCheck = PermissionHelper.hasPermission(this,Manifest.permission.RECORD_AUDIO);
        if (!permissionCheck) {
           PermissionHelper.requestPermission(this, 104, Manifest.permission.RECORD_AUDIO);
            return false;
        } 
        return true;
    }
		
	 public void onRequestPermissionResult(int requestCode, String[] permissions,
                                             int[] grantResults) throws JSONException {
		 
		 //handle denied callback
        for(int r:grantResults)
        {
            if(r == PackageManager.PERMISSION_DENIED)
            {
                Toast.makeText(webView.getContext(),"you denied permission,so app will not work perfectly", Toast.LENGTH_SHORT).show();
                return;
            }
        }
		 
		 //user permit permission
		 
        switch(requestCode){
            case 105:
           		 Toast.makeText(webView.getContext(),"request permit", Toast.LENGTH_SHORT).show();
                break; 
			
			case 102:
           		setAudioMode(mode);
                break;
			case 104:
				
				if(callback != null) {
            try {
                JSONObject parameter = new JSONObject();
                parameter.put("state", "1");
                parameter.put("index", "2");
                //callback.success(parameter);
				
				PluginResult result = new PluginResult(PluginResult.Status.OK, parameter);
                result.setKeepCallback(true);
                callback.sendPluginResult(result);
				
            } catch (JSONException e) {
               
            }
        }
				
           		 Toast.makeText(webView.getContext(),"request permit", Toast.LENGTH_SHORT).show();
                break;
		
          }
    }
	
//////////////////////////////////// Hide keyboard ////////////////////////////////////////////
	public void hideKeyBoard() {
		Toast.makeText(webView.getContext(),"1", Toast.LENGTH_SHORT).show();
		
				View view =  cordova.getActivity().getCurrentFocus();
				if (view != null) {
					InputMethodManager imm = (InputMethodManager)webView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
				}
	
    }
	
//////////////////////////////////// Show keyboard ////////////////////////////////////////////
	  public void showKeyBoard() {
		    Toast.makeText(webView.getContext(),"hide main", Toast.LENGTH_LONG).show();
     
 InputMethodManager imm = (InputMethodManager)webView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);

   }
////////////////////////////////////////////////////////////////////////////////////////
}  



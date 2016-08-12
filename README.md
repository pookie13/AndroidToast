# IonicPlug
This is simple cordova plugin for android which does following things: 
1. Playing IVR sound.
2. Set Audio mode.
3. showing toast.

##STEPS TO USE THIS PLUGIN

1. Download the plugin

  `git clone https://github.com/pookie13/IonicPlug`

2. Add this plugin to your cordova project

  `ionic plugin add "path of downloaded plugin"`
3. Call functions

###  for TTS

`AndroidToast.play("12");`  

### Set Audio Mode to EARPIECE

`AndroidToast.setAudioMode(AndroidToast.EARPIECE);`

### Set Audio Mode to SPEAKER

 `AndroidToast.setAudioMode(AndroidToast.SPEAKER);`
 
### Showing Toast

`AndroidToast.toast("text to toast");`

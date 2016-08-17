var exec = require('cordova/exec');

exports.SPEAKER = 'speaker';
exports.EARPIECE = 'earpiece';
exports.NORMAL = 'normal';
exports.RINGTONE = 'ringtone';

exports.SMS = 'Manifest.permission.READ_SMS';
exports.AUDIO_TOGEL = 'Manifest.permission.MODIFY_AUDIO_SETTING';



exports.toast = function(arg0) {
    exec(null, null, "AndroidToast", "toast", [arg0]);
};


exports.play = function(arg0) {
    exec(null, null, "AndroidToast", "play", [arg0]);
};


exports.setAudioMode = function (mode) {
	cordova.exec(null, null, 'AndroidToast', 'setAudioMode', [mode]);
};

exports.checkForSMSPermission = function () {
	cordova.exec(null, null, 'AndroidToast', 'checkForSMSPermission', []);
};
exports.checkForRecordPermission = function () {
	cordova.exec(null, null, 'AndroidToast', 'checkForRecordPermission', []);
};

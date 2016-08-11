var exec = require('cordova/exec');

exports.SPEAKER = 'speaker';
exports.EARPIECE = 'earpiece';
exports.NORMAL = 'normal';
exports.RINGTONE = 'ringtone';

exports.toast = function(arg0) {
    exec(null, null, "AndroidToast", "toast", [arg0]);
};


exports.play = function(arg0) {
    exec(null, null, "AndroidToast", "play", [arg0]);
};


exports.setAudioMode = function (mode) {
	cordova.exec(null, null, 'AndroidToast', 'setAudioMode', [mode]);
};

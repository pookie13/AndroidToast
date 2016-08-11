var exec = require('cordova/exec');

exports.toast = function(arg0) {
    exec(null, null, "AndroidToast", "toast", [arg0]);
};


exports.play = function(arg0) {
    exec(null, null, "AndroidToast", "play", [arg0]);
};

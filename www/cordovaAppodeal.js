    var exec = require('cordova/exec');

    exports.initialize = function (success, error) {
        exec(success, error, 'cordovaAppodeal','initialize');
    };
    exports.signInToGooglePlayGames = function (success, error) {
        exec(success, error, 'cordovaAppodeal','signInToGooglePlayGames');
    };
    exports.showAchievements = function (success, error) {
        exec(success, error, 'cordovaAppodeal','showAchievements');
    };
    exports.submitScoreForLeaderboards = function (ID, POINTS, success, error) {
        exec(success, error, 'cordovaAppodeal','submitScoreForLeaderboards',[ID,POINTS]);
    };
    exports.showLeaderboards = function ( LeaderboardID, success, error) {
        exec(success, error, 'cordovaAppodeal','showLeaderboards',[LeaderboardID]);
    };
    exports.unlockAchievements = function (AchievementID, type, incrementValue, success, error) {
        exec(success, error, 'cordovaAppodeal','unlockAchievements',[AchievementID, type, incrementValue]);
    }

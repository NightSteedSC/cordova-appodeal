package org.apache.cordova.cordovaAppodeal;

import android.content.Intent;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Switch;
import androidx.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaWebView;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.Native;
import com.appodeal.ads.NativeAd;
import com.appodeal.ads.NativeAdView;
import com.appodeal.ads.UserSettings;
import com.explorestack.consent.Consent;
import com.explorestack.consent.ConsentForm;
import com.explorestack.consent.ConsentFormListener;
import com.explorestack.consent.ConsentInfoUpdateListener;
import com.explorestack.consent.ConsentManager;
import com.explorestack.consent.exception.ConsentManagerException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

////////////////////////////////////////////////////
public class cordovaAppodeal extends CordovaPlugin  {

    private static final String TAG = "SignInActivity";
    private static String APP_KEY = "a9d23e2e749eb878c8d4f530e9b5d2c2c650835536c1651c";

    private static final String CONSENT = "consent";

    private List<NativeAd> nativeAds = new ArrayList<>();
    String placementName = "default";
    boolean consent;
    private Switch consentSwitch;
    @Nullable
    private ConsentForm consentForm;

    /////////////////////////////////////////////////////////////////////////////
    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView){
        super.initialize(cordova, webView);
        Log.w(TAG, "*** MAIN initialize 0");

        ConsentManager.getInstance(cordova.getContext())
                .requestConsentInfoUpdate(APP_KEY, new ConsentInfoUpdateListener() {
                    @Override
                    public void onConsentInfoUpdated(Consent consent) {
                        // User's consent status successfully updated.
                        // Initialize the Appodeal SDK with the received Consent object here or show consent window.
                    }

                    @Override
                    public void onFailedToUpdateConsentInfo(ConsentManagerException exception) {
                        // User's consent status failed to update.
                        int errorCode = exception.getCode();
                        String reason = exception.getReason();
                        // Initialize the Appodeal SDK with default params.
                    }
                });

    }

    /////////////////////////////////////////////////////////////////////////////
    @Override//funkcja która łączy się z JS
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("signInToGooglePlayGames")) {Log.d("log","***signInToGooglePlayGames");
//            signInToGooglePlayGames();
        }
        else if (action.equals("initialize")) {Log.d("log","***initialize");
            initialize();
        } else if (action.equals("showBanner")) {
            showBanner();
        }
//        else if (action.equals("showAchievements")) {Log.d("log","***showAchievements");
//            showAchievements();
//        } else if (action.equals("submitScoreForLeaderboards")) {Log.d("log","***submitScoreForLeaderboards");
//            submitScoreForLeaderboards(callbackContext, args);
//        } else if (action.equals("showLeaderboards")) {Log.d("log","***showLeaderboards");
//            showLeaderboards(callbackContext, args);
//        }else if (action.equals("unlockAchievements")) {Log.d("log","***unlockAchievements");
//            unlockAchievements(callbackContext,args);
//        }

        return false;  // Returning false results in a "MethodNotFound" error.
    }

    private void initialize() {
        Appodeal.initialize(cordova.getActivity(), APP_KEY, Appodeal.INTERSTITIAL, consent);
    }

    public void showBanner(){
        Appodeal.show(cordova.getActivity(), Appodeal.INTERSTITIAL);
    }

//

    @Override
    public void onStart() {
        super.onStart();
//        googleSignInAccount = GoogleSignIn.getLastSignedInAccount(this.cordova.getActivity());
//        Log.w(TAG, "*** OnStart: " + googleSignInAccount);
        //updateUI(account);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }


    private void onDisconnected() {
        Log.d(TAG, "onDisconnected()");
    }
//    private boolean isSignedIn() {
//
//        return googleSignInAccount != null;
//
//    }

    @Override
    public void onResume(boolean multitasking) {
        super.onResume(multitasking);
        Log.d(TAG, "onResume()");
    }

    public void goToUrl(String url){
        cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                webView.loadUrl(url);
            }
        });
    }
}
package me.mattlewis.isdebug;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.provider.Settings.Global;
import android.provider.Settings.Secure;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;

public class IsDebug extends CordovaPlugin {

    private static Context ctx;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        ctx = this.cordova.getActivity().getApplicationContext();

        if (action.equals("getIsDebug")) {
            callbackContext.success(this.isDebug() ? 1 : 0);
        } else {
            return false;
        }
        return true;
    }

    private boolean isDebug() {
        try {
          	if (Global.getInt(ctx.getContentResolver(), Global.ADB_ENABLED, 0) == 1) {
			   return true;
			}
			
        } catch (Exception e){
            // do nothing
        }
        return false;
    }
}
package com.roil.cordova.plugin.associatefiletype;

import android.content.Intent;
import android.net.Uri;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class AssociateFileTypePlugin extends CordovaPlugin {

    private CallbackContext handleContext;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("setFileHandler")) {
            setFileHandler(callbackContext);
            return true;
        } else {
            return false;
        }
    }

    private void setFileHandler(CallbackContext handlerContext) {
        this.handleContext = handlerContext;
    }

    @Override
    public void onNewIntent(Intent intent) {
        checkIntentForFile(intent);
    }

    @Override
    public void onResume(boolean multitasking) {
        checkIntentForFile(cordova.getActivity().getIntent());
    }

    private void checkIntentForFile(Intent intent) {
        try {
            if (Objects.equals(intent.getAction(), Intent.ACTION_VIEW) &&
                    intent.getData() != null && handleContext != null) {
                Uri fileUri = intent.getData();
                InputStream stream = cordova.getActivity().getContentResolver()
                        .openInputStream(fileUri);

                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                int nRead;
                byte[] data = new byte[1024];
                while ((nRead = stream.read(data, 0, data.length)) != -1) {
                    buffer.write(data, 0, nRead);
                }

                buffer.flush();
                byte[] byteArray = buffer.toByteArray();

                PluginResult result = new PluginResult(PluginResult.Status.OK, byteArray);
                result.setKeepCallback(true);

                handleContext.sendPluginResult(result);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.mxcompany.identityexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.combateafraude.identity.authenticationmethods.faceauthentication.input.FaceAuthenticator;
import com.combateafraude.identity.input.Identity;
import com.combateafraude.identity.input.VerifyPolicyListener;
import com.combateafraude.identity.output.Failure;

public class MainActivity extends AppCompatActivity {
    final String YOUR_MOBILE_TOKEN = "";
    final String YOUR_PERSON_ID = "";
    final String YOUR_POLICY_ID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startIdentity(View view) {

        Identity identity = new Identity.Builder(YOUR_MOBILE_TOKEN, view.getContext())
                //.setStage() -> You can use this method to set which stage are you going to define our API's, example, PROD or BETA
                //.setEmailUrl() -> You can use this parameter to change the user email inside your api (use a url for a services that you provide with your services)
                //.setPhoneUrl() -> You can use this parameter to change the user phone inside your api (use a url for a services that you provide with your services)
                //.setFaceAuthenticatorConfiguration(configureFaceAuth()) -> Used to configure the FaceAuthenticator that is inside the authentications, you can check this method to understand all the parameters
                .build();

        identity.verifyPolicy(YOUR_PERSON_ID, YOUR_POLICY_ID, new VerifyPolicyListener() {
            @Override
            public void onSuccess(boolean isAuthorized, String attestation) {
                if(isAuthorized){
                    // Authorized user
                    // Send attestation to your backend and perform validation
                    System.out.println("User isAuthorized");
                }else{
                    // Unauthorized user
                    System.out.println("User Unauthorized");
                }
            }
            @Override
            public void onPending(boolean b, String s) {
                System.out.println("User is pending approval in caf panel");
            }

            @Override
            public void onError(Failure failure) {
                // Error when running the SDK
                System.out.println("An error occurred, check it out for more information \nError: " + failure);
            }
        });
    }

    /*Those are all the parameters you can use to customize your faceAuthenticator builder, you can check all the
    functions here: https://docs.caf.io/sdks/android/getting-started/faceauthenticator*/

    /* private FaceAuthenticator.Builder configureFaceAuth(){
        return new FaceAuthenticator.Builder()
                .setStage()
                .setAudioSettings()
                .setCaptureSettings()
                .setEyesClosedSettings()
                .setNetworkSettings()
                .setMessageSettings()
                .setLayout()
                .setMask()
                .setStyle()
                .setUseRoot()
                .setUseEmulator()
                .setUseAdb()
                .setUseDeveloperMode()
                .setUseDebug()
    } */
}
package recyclerviewcheckbox.com.facebookintegerationexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public class FacebookButtonActivity extends AppCompatActivity {

    private CallbackManager callbackManager;
    private LoginButton fbLoginButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_facebook_button );


        fbLoginButton = (LoginButton) findViewById( R.id.fbLoginButton );
        callbackManager = CallbackManager.Factory.create();

        // Callback registration
        fbLoginButton.registerCallback( callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(final LoginResult loginResult) {
                Log.e( "loginResult", " ==> " + loginResult );
                if (loginResult.getAccessToken() != null) {
                    final GraphRequest request = GraphRequest.newMeRequest( loginResult.getAccessToken(),
                            new GraphRequest.GraphJSONObjectCallback() {
                                @Override
                                public void onCompleted(final JSONObject object, GraphResponse response) {
                                    try {
//                                        Log.e( "FACEBOOK RESPONSE ", " ==>" + object );
                                        String strLastName = object.getString( "last_name");
                                        String strFirstName = object.getString( "first_name");

                                        JSONObject objectPictur = object.getJSONObject( "picture" );
                                        JSONObject objectData = objectPictur.getJSONObject( "data" );
                                        String strUrl = objectData.getString(  "url");

                                        Log.e( "strUrl "," ==> "+strUrl );
                                        Log.e( "strLastName"," ==> "+strLastName );
                                        Log.e( "strFirstName"," ==> "+strFirstName );
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            });

                    Bundle parameters = new Bundle();
                    parameters.putString( "fields", "id,name,email,last_name,picture,first_name" );

                    request.setParameters( parameters );
                    request.executeAsync();
                }

            }

            @Override
            public void onCancel() {
                // App code
                Toast.makeText( FacebookButtonActivity.this, R.string.cancel, Toast.LENGTH_LONG ).show();
            }

            @Override
            public void onError(final FacebookException exception) {
                // App code
                Toast.makeText( FacebookButtonActivity.this, R.string.error, Toast.LENGTH_LONG ).show();
            }
        } );

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

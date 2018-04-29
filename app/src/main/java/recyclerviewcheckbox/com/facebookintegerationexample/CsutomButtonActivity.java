package recyclerviewcheckbox.com.facebookintegerationexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public class CsutomButtonActivity extends AppCompatActivity {

    private Button btnLogin;
    private CallbackManager callbackManager;
    private List<String> permissionNeeds = Arrays.asList( "public_profile,email" );


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_csutom_button );

        btnLogin = findViewById( R.id.btnLogin );
        btnLogin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                callbackManager = CallbackManager.Factory.create();
                LoginManager.getInstance().logInWithReadPermissions( CsutomButtonActivity.this, permissionNeeds );
                LoginManager.getInstance().registerCallback( callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(final LoginResult loginResults) {

                        if (loginResults.getAccessToken() != null) {
                            final GraphRequest request = GraphRequest.newMeRequest( loginResults.getAccessToken(),
                                    new GraphRequest.GraphJSONObjectCallback() {
                                        @Override
                                        public void onCompleted(final JSONObject object, GraphResponse response) {
                                            try {
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
                                    } );


                            Bundle parameters = new Bundle();
                            parameters.putString( "fields", "id,name,email,last_name,picture,first_name" );
                            request.setParameters( parameters );
                            request.executeAsync();
                        }
                    }

                    @Override
                    public void onCancel() {
                    }

                    @Override
                    public void onError(FacebookException e) {
                    }

                } );


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

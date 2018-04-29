package recyclerviewcheckbox.com.facebookintegerationexample;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    private Button btnLoginViaButton,btnLoginViaFacebook;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        btnLoginViaFacebook = findViewById( R.id.btnLoginViaFacebook );
        btnLoginViaButton = findViewById( R.id.btnLoginViaButton );

        btnLoginViaFacebook.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent( MainActivity.this,FacebookButtonActivity.class );
                startActivity( intent );
            }
        } );

        btnLoginViaButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( MainActivity.this,CsutomButtonActivity.class );
                startActivity( intent );
            }
        } );

    }
}

package krast.overwatchapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.security.cert.CertificateException;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText editTxtTag;
    Spinner regionSpinner;
    Spinner platformSpinner;
    Button btnGo;
    ProgressBar progressBar;
    TextView txtFail;

    private final String URL = "https://api.lootbox.eu";

    private Retrofit retrofit;
    private OverwatchApi service;

    private static OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            OkHttpClient okHttpClient = builder.build();
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getUnsafeOkHttpClient())
                .build();

        txtFail = (TextView) findViewById(R.id.txtFail);
        txtFail.setVisibility(View.INVISIBLE);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        editTxtTag = (EditText) findViewById(R.id.editTxtTab);
        regionSpinner = (Spinner) findViewById(R.id.regionSpinner);
        platformSpinner = (Spinner) findViewById(R.id.platformSpinner);
        btnGo = (Button) findViewById(R.id.btnGo);
        btnGo.setOnClickListener(btnGoClickListener);


        //Setting Spinners content
        ArrayAdapter<CharSequence> regionAdapter = ArrayAdapter.createFromResource(this, R.array.region_array,
               android.R.layout.simple_spinner_item);

        regionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        regionSpinner.setAdapter(regionAdapter);

        ArrayAdapter<CharSequence> platformAdapter = ArrayAdapter.createFromResource(this, R.array.platform_array,
                android.R.layout.simple_list_item_1);

        platformAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);

        platformSpinner.setAdapter(platformAdapter);

        //Creation of interface for work with API
        service = retrofit.create(OverwatchApi.class);
    }

    View.OnClickListener btnGoClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String userTag = editTxtTag.getText().toString();
            userTag = userTag.replace("#", "-");
            String userPlatfrom = platformSpinner.getSelectedItem().toString();

            switch (userPlatfrom){
                case "xbox":
                    userPlatfrom = "xbl";
                    break;
                case "ps":
                    userPlatfrom = "psn";
                    break;
                case "pc":
                    break;
            }
            String userRegion = regionSpinner.getSelectedItem().toString();

            btnGo.setEnabled(false);
            editTxtTag.setEnabled(false);
            progressBar.setVisibility(View.VISIBLE);
            txtFail.setVisibility(View.INVISIBLE);
            Call<Example> getUserData = service.getUserProfile(userPlatfrom, userRegion, userTag);
            getUserData.enqueue(new Callback<Example>() {
                @Override
                public void onResponse(Call<Example> call, Response<Example> response) {
                    try{
                        Example result = response.body();
                        if (result.getData() == null){
                            btnGo.setEnabled(true);
                            editTxtTag.setEnabled(true);
                            progressBar.setVisibility(View.INVISIBLE);
                            txtFail.setVisibility(View.VISIBLE);
                        } else {
                            btnGo.setEnabled(true);
                            editTxtTag.setEnabled(true);
                            DataCore.setInstance(result);
                            progressBar.setVisibility(View.INVISIBLE);
                            txtFail.setVisibility(View.INVISIBLE);
                            startProfileActivity();
                        }
                    }catch (Exception ex) {
                        progressBar.setVisibility(View.INVISIBLE);
                        btnGo.setEnabled(true);
                        editTxtTag.setEnabled(true);
                    }
                }

                @Override
                public void onFailure(Call<Example> call, Throwable t) {
                    btnGo.setEnabled(true);
                    progressBar.setVisibility(View.INVISIBLE);
                    editTxtTag.setEnabled(true);

                }
            });

        }
    };

    private void startProfileActivity(){
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first
        DataCore.clearInstance();
    }
}

package krast.overwatchapi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Turchyn on 27.12.2016.
 */
public interface OverwatchApi {
    @GET("/{platform}/{region}/{tag}/profile")
    Call<Example> getUserProfile(@Path("platform") String userPlatform, @Path("region") String userRegion,
                        @Path("tag") String userTag);

}

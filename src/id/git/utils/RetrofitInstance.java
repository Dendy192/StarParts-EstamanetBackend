package id.git.utils;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

public class RetrofitInstance {
	
	private static String BASE_URL="http://103.18.133.223/SPM_eStatement/api/";
//	private static String VERSION=ParamWa.getVersion();

	private static String username = "admin";
	private static String password = "Spm#159yes";
	private static Retrofit retrofit;
	
	public static Retrofit getRetrofitClient() {
		System.out.println(BASE_URL);
		if(retrofit == null) {

			retrofit=new Retrofit.Builder()
					.baseUrl(BASE_URL)
					.addConverterFactory(GsonConverterFactory.create())
					.client(new OkHttpClient().newBuilder()
							.connectTimeout(100, TimeUnit.SECONDS)
							.readTimeout(100,TimeUnit.SECONDS)
							.addInterceptor(new MyIntercepter(username, password)).build())
					.build();
			
		}
		return retrofit;
	}
}

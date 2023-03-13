package id.git.api;

import id.git.api.model.*;
import retrofit2.Call;
import retrofit2.http.*;

public interface IApiService {
    @GET("eStatement_KonfirmasiHutang")
    Call<ResponseGetInvoice> getInvoice (@Query(value = "id",encoded = true) String id);


    @PUT("eStatement_Customers")
    Call<UpdateCustomerRespons> updateCustomer(@Query(value = "id",encoded = true) String id,
                                               @Body UpdateCustomer updateCustomer);

    @GET("eStatement_Customers")
    Call<ResponseGetCustomer>getAllCustomerAPI();

    @GET("eStatement_Customers")
    Call<ResponseGetCustomer> getCustomerAPI (@Query(value = "id",encoded = true) String id);
}


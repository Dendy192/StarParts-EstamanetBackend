package id.git.api.service;

import id.git.api.IApiService;
import id.git.api.model.*;
import id.git.utils.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetAPIData {

    public HashMap<String , Object[]>getInvoice(String id){
        HashMap<String,Object[]> result = new HashMap<>();

        try{
            IApiService api =RetrofitInstance.getRetrofitClient().create(IApiService.class);
            Call<ResponseGetInvoice> call =  api.getInvoice(id);
            Response<ResponseGetInvoice> response = call.execute();
            ResponseGetInvoice rgi = response.body();
//            System.out.println(response.body());
          int i = 0;
          List<DataItem> lidi = rgi.getData();
          for(DataItem di : lidi){
//        	  System.out.println(di);
             List<ListDetailItem> lidim = di.getListDetail();
             if(lidim.isEmpty()) {
//            	 System.out.println("masuk sini");
            	 result.put("kosong", new Object[]{});
             }
             else {
            	 for(ListDetailItem ldi : lidim){
//                	 System.out.println("Masuk sini: "+ldi);
                     result.put(String.valueOf(i++),new Object[]{rgi.getMessage(),rgi.getError(),di.getCOMPANYNAME(),di.getCOMPANYADDRESS(),
                             di.getOUTLET_ID(),di.getOUTLETNAME(),di.getOUTLETADDRESS(),di.getTTDFADMGR(),ldi.getINVOICE_DATE(),  ldi.getBRAND(),ldi.getINVOICE_DUE(),
                             ldi.getINVOICE_NO(), ldi.getAMOUNT(), ldi.getREMAINING_AMOUNT(), ldi.getTOTAL()});
                 }
//            	 System.out.println("di sini");
             }
//             System.out.println(lidim);
             
          }
            
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public Map<String, String> UpdateCustomerData(String id, String email, String phone){
//        String result = "";
        Map<String, String> result = new HashMap<>();
//        String tes = "BDG002898";
        try{
            UpdateCustomer uc = new UpdateCustomer();
            uc.setE_Mail(email);
            uc.setPHONE(phone);
            IApiService api = RetrofitInstance.getRetrofitClient().create(IApiService.class);
            Call<UpdateCustomerRespons> call = api.updateCustomer(id, uc);
            Response<UpdateCustomerRespons> response = call.execute();
            System.out.println(response.body().toString());
             

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("masuk sini: "+e.getMessage());
        }

        return result;
    }
    public Map<String,String[]> GetCustomerData(String id){
        Map<String,String[]> result = new HashMap<>();
        try{
            IApiService api = RetrofitInstance.getRetrofitClient().create(IApiService.class);
            Call<ResponseGetCustomer> call = api.getCustomerAPI(id);
            Response<ResponseGetCustomer> response = call.execute();
            ResponseGetCustomer rgc = response.body();
            List<DataItemCustomer> dicList = rgc.getData();
            int i = 0;
            for(DataItemCustomer dic : dicList){

                result.put(String.valueOf(i++),new String[]{dic.getCOMPANYID(), dic.getCOMPANYNAME(),dic.getEMAIL(),dic.getPHONE()});
            }
            
            
        }catch (Exception e){
            e.printStackTrace();

        }
        return result;
    }
    public HashMap<String,String[]> GetCustomerDataAll(){
    	HashMap<String,String[]> result = new HashMap<>();
        try{
            IApiService api = RetrofitInstance.getRetrofitClient().create(IApiService.class);
            Call<ResponseGetCustomer> call = api.getAllCustomerAPI();
            Response<ResponseGetCustomer> response = call.execute();
            ResponseGetCustomer rgc = response.body();
            List<DataItemCustomer> dicList = rgc.getData();
            int i = 0;
            for(DataItemCustomer dic : dicList){
//                System.out.println(dic.getCOMPANYID());
//                System.out.println(dic.getCOMPANYNAME());
//                System.out.println(dic.getEMAIL());
//                System.out.println(dic.getPHONE());
//                System.out.println("===================");
//
                result.put(String.valueOf(i++),new String[]{dic.getCOMPANYID(), dic.getCOMPANYNAME(),dic.getEMAIL(),dic.getPHONE()});
            }
            System.out.println(rgc.getMessage()+" : "+rgc.getError());
//            System.out.println(response.body());
        }catch (Exception e){
            e.printStackTrace();

        }
        return result;
    }
//    public static void main (String[]args){
//        GetAPIData gad = new GetAPIData();
//        gad.GetCustomerDataAll();
//    }
}

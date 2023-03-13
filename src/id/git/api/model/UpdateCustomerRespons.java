package id.git.api.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class UpdateCustomerRespons{

	@SerializedName("Message")
	private String message;

	@SerializedName("Error")
	private int error;

	@SerializedName("Data")
	private DataCustomer data;

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setError(int error){
		this.error = error;
	}

	public int getError(){
		return error;
	}

	public void setData(DataCustomer data){
		this.data = data;
	}

	public DataCustomer getData(){
		return data;
	}

	@Override
 	public String toString(){
		return 
			"UpdateCustomerRespons{" + 
			"message = '" + message + '\'' + 
			",error = '" + error + '\'' + 
			",data = '" + data + '\'' + 
			"}";
		}
}
package id.git.api.model;

import java.util.List;

public class ResponseGetCustomer{

	private String Message;

	private int Error;

	private List<DataItemCustomer> Data;

	public void setMessage(String message){
		this.Message = message;
	}

	public String getMessage(){
		return Message;
	}

	public void setError(int Error){
		this.Error = Error;
	}

	public int getError(){
		return Error;
	}

	public void setData(List<DataItemCustomer> Data){
		this.Data = Data;
	}

	public List<DataItemCustomer> getData(){
		return Data;
	}

	@Override
 	public String toString(){
		return 
			"ResponseGetCustomer{" + 
			"message = '" + Message + '\'' +
			",error = '" + Error + '\'' +
			",data = '" + Data + '\'' +
			"}";
		}
}
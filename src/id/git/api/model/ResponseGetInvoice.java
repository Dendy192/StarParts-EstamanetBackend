package id.git.api.model;

import java.util.List;

public class ResponseGetInvoice {
	private String Message;
	private int Error;
	private List<DataItem> Data;

	public void setMessage(String Message){
		this.Message = Message;
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

	public void setData(List<DataItem> Data){
		this.Data = Data;
	}

	public List<DataItem> getData(){
		return Data;
	}
}
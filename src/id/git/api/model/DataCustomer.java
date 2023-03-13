package id.git.api.model;

import com.google.gson.annotations.SerializedName;

public class DataCustomer {

	@SerializedName("COMPANY_NAME")
	private String cOMPANYNAME;

	@SerializedName("PHONE")
	private String pHONE;

	@SerializedName("E_MAIL")
	private String eMAIL;

	@SerializedName("COMPANY_ID")
	private String cOMPANYID;

	public void setCOMPANYNAME(String cOMPANYNAME){
		this.cOMPANYNAME = cOMPANYNAME;
	}

	public String getCOMPANYNAME(){
		return cOMPANYNAME;
	}

	public void setPHONE(String pHONE){
		this.pHONE = pHONE;
	}

	public String getPHONE(){
		return pHONE;
	}

	public void setEMAIL(String eMAIL){
		this.eMAIL = eMAIL;
	}

	public String getEMAIL(){
		return eMAIL;
	}

	public void setCOMPANYID(String cOMPANYID){
		this.cOMPANYID = cOMPANYID;
	}

	public String getCOMPANYID(){
		return cOMPANYID;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"cOMPANY_NAME = '" + cOMPANYNAME + '\'' + 
			",pHONE = '" + pHONE + '\'' + 
			",e_MAIL = '" + eMAIL + '\'' + 
			",cOMPANY_ID = '" + cOMPANYID + '\'' + 
			"}";
		}
}
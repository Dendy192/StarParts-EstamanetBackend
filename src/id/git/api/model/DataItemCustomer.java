package id.git.api.model;


public class DataItemCustomer {

	private String COMPANY_NAME;

	private String PHONE;

	private String E_MAIL;

	private String COMPANY_ID;

	public void setCOMPANYNAME(String COMPANY_NAME){
		this.COMPANY_NAME = COMPANY_NAME;
	}

	public String getCOMPANYNAME(){
		return COMPANY_NAME;
	}

	public void setPHONE(String PHONE){
		this.PHONE = PHONE;
	}

	public String getPHONE(){
		return PHONE;
	}

	public void setEMAIL(String E_MAIL){
		this.E_MAIL = E_MAIL;
	}

	public String getEMAIL(){
		return E_MAIL;
	}

	public void setCOMPANYID(String COMPANY_ID){
		this.COMPANY_ID = COMPANY_ID;
	}

	public String getCOMPANYID(){
		return COMPANY_ID;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"cOMPANY_NAME = '" + COMPANY_NAME + '\'' +
			",pHONE = '" + PHONE + '\'' +
			",e_MAIL = '" + E_MAIL + '\'' +
			",cOMPANY_ID = '" + COMPANY_ID + '\'' +
			"}";
		}
}
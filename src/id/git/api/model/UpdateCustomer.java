package id.git.api.model;

public class UpdateCustomer{
	private String PHONE;
	private String E_Mail;

	public void setPHONE(String PHONE){
		this.PHONE = PHONE;
	}

	public String getPHONE(){
		return PHONE;
	}

	public void setE_Mail(String E_Mail){
		this.E_Mail = E_Mail;
	}

	public String getE_Mail(){
		return E_Mail;
	}

	@Override
 	public String toString(){
		return 
			"UpdateCustomer{" + 
			"PHONE = '" + PHONE + '\'' + 
			",e_Mail = '" + E_Mail + '\'' + 
			"}";
		}
}

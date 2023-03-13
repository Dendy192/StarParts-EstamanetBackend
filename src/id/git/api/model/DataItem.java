package id.git.api.model;

import java.util.List;

public class DataItem{
	private String COMPANY_NAME;
	private String OUTLET_ADDRESS;
	private List<ListDetailItem> listDetail;
	private String OUTLET_NAME;
	private String COMPANY_ADDRESS;
	private String TTD_FAD_MGR;
	private String OUTLET_ID;
	
	

	public String getOUTLET_ID() {
		return OUTLET_ID;
	}

	public void setOUTLET_ID(String oUTLET_ID) {
		OUTLET_ID = oUTLET_ID;
	}

	public void setCOMPANYNAME(String COMPANY_NAME){
		this.COMPANY_NAME = COMPANY_NAME;
	}

	public String getCOMPANYNAME(){
		return COMPANY_NAME;
	}

	public void setOUTLETADDRESS(String OUTLET_ADDRESS){
		this.OUTLET_ADDRESS = OUTLET_ADDRESS;
	}

	public String getOUTLETADDRESS(){
		return OUTLET_ADDRESS;
	}

	public void setListDetail(List<ListDetailItem> listDetail){
		this.listDetail = listDetail;
	}

	public List<ListDetailItem> getListDetail(){
		return listDetail;
	}

	public void setOUTLETNAME(String OUTLET_NAME){
		this.OUTLET_NAME = OUTLET_NAME;
	}

	public String getOUTLETNAME(){
		return OUTLET_NAME;
	}

	public void setCOMPANYADDRESS(String COMPANY_ADDRESS){
		this.COMPANY_ADDRESS = COMPANY_ADDRESS;
	}

	public String getCOMPANYADDRESS(){
		return COMPANY_ADDRESS;
	}

	public void setTTDFADMGR(String TTD_FAD_MGR){
		this.TTD_FAD_MGR = TTD_FAD_MGR;
	}

	public String getTTDFADMGR(){
		return TTD_FAD_MGR;
	}
}
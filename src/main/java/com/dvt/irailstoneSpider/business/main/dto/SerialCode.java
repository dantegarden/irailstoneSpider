package com.dvt.irailstoneSpider.business.main.dto;

/**
 * @author HELP
 *
 */
public class SerialCode {
	/**序列号状态
	 * 7 作废
	 * **/
	private String SN_STATUS;//记录状态
	/**补充信息**/
	private String SUPPLEMENT_INFO;
	/**生成单号**/
	private String PRODUCT_SN_ORDER;
	/**生产日期**/
	private String PRODUCE_DATE;
	/**质检日期**/
	private String QUALITY_DATE;
	private String MODIFYUSERID;
	/**售后原序列号**/
	private String AFTER_SALE_OLD_SN;
	/**装配上线日期**/
	private String ASSEMBLE_DATE;
	private String ID;
	private String AFTER_SALE_DATE_TYPE;
	/**装配上线责任人**/
	private String ASSEMBLE_USER;
	private String EXPORT_NUM;
	/**备注**/
	private String REMARK;
	private String CREATEUSERID;
	/**是否售后件**/
	private String IS_AFTER_SALE;
	/**生产批次号**/
	private String BATCH_CODE;
	/**序列号*/
	private String PRODUCT_SN;
	/**生成日期**/
	private String CREATEDATE;
	/**收货验收人**/
	private String ACCEPTANCE_USER;
	/**收货验收合格**/
	private String ACCEPTANCE_STATE;
	private String CID;
	private String PRODUCT_CODE;
	private String ORDER_ID;
	/**生成操作员**/
	private String CREATEUSERNAME;
	/**售后件生产日期**/
	private String AFTER_SALE_DATE;
	/**合格证号**/
	private String CERTIFICATE_NO;
	private String IS_BIND;
	/**生产责任人**/
	private String PRODUCE_USER;
	/**最后修改日期**/
	private String MODIFYDATE;
	/**序列号分类
	 * 0 动车 1 机车
	 * **/
	private String SN_TYPE;
	/**最后修改人**/
	private String MODIFYUSERNAME;
	private String PRINT_NUM;
	/**质检人**/
	private String QUALITY_USER;
	private String SERIAL_TYPE;
	/**收货验收说明**/
	private String ACCEPTANCE_DESC;
	/**规格数量**/
	private String SPEC_NUM;
	/**收货验收日期**/
	private String ACCEPTANCE_DATE;
	/**项目编号**/
	private String ITEMCODE;
	/**编组列编码**/
	private String TRAINCODE;
	private String CREATE_CID;
	
	
	public String getSN_STATUS() {
		return SN_STATUS;
	}
	public void setSN_STATUS(String sN_STATUS) {
		SN_STATUS = sN_STATUS;
	}
	public String getSUPPLEMENT_INFO() {
		return SUPPLEMENT_INFO;
	}
	public void setSUPPLEMENT_INFO(String sUPPLEMENT_INFO) {
		SUPPLEMENT_INFO = sUPPLEMENT_INFO;
	}
	public String getPRODUCT_SN_ORDER() {
		return PRODUCT_SN_ORDER;
	}
	public void setPRODUCT_SN_ORDER(String pRODUCT_SN_ORDER) {
		PRODUCT_SN_ORDER = pRODUCT_SN_ORDER;
	}
	public String getPRODUCE_DATE() {
		return PRODUCE_DATE;
	}
	public void setPRODUCE_DATE(String pRODUCE_DATE) {
		PRODUCE_DATE = pRODUCE_DATE;
	}
	public String getQUALITY_DATE() {
		return QUALITY_DATE;
	}
	public void setQUALITY_DATE(String qUALITY_DATE) {
		QUALITY_DATE = qUALITY_DATE;
	}
	public String getMODIFYUSERID() {
		return MODIFYUSERID;
	}
	public void setMODIFYUSERID(String mODIFYUSERID) {
		MODIFYUSERID = mODIFYUSERID;
	}
	public String getAFTER_SALE_OLD_SN() {
		return AFTER_SALE_OLD_SN;
	}
	public void setAFTER_SALE_OLD_SN(String aFTER_SALE_OLD_SN) {
		AFTER_SALE_OLD_SN = aFTER_SALE_OLD_SN;
	}
	public String getASSEMBLE_DATE() {
		return ASSEMBLE_DATE;
	}
	public void setASSEMBLE_DATE(String aSSEMBLE_DATE) {
		ASSEMBLE_DATE = aSSEMBLE_DATE;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getAFTER_SALE_DATE_TYPE() {
		return AFTER_SALE_DATE_TYPE;
	}
	public void setAFTER_SALE_DATE_TYPE(String aFTER_SALE_DATE_TYPE) {
		AFTER_SALE_DATE_TYPE = aFTER_SALE_DATE_TYPE;
	}
	public String getASSEMBLE_USER() {
		return ASSEMBLE_USER;
	}
	public void setASSEMBLE_USER(String aSSEMBLE_USER) {
		ASSEMBLE_USER = aSSEMBLE_USER;
	}
	public String getEXPORT_NUM() {
		return EXPORT_NUM;
	}
	public void setEXPORT_NUM(String eXPORT_NUM) {
		EXPORT_NUM = eXPORT_NUM;
	}
	public String getREMARK() {
		return REMARK;
	}
	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}
	public String getCREATEUSERID() {
		return CREATEUSERID;
	}
	public void setCREATEUSERID(String cREATEUSERID) {
		CREATEUSERID = cREATEUSERID;
	}
	public String getIS_AFTER_SALE() {
		return IS_AFTER_SALE;
	}
	public void setIS_AFTER_SALE(String iS_AFTER_SALE) {
		IS_AFTER_SALE = iS_AFTER_SALE;
	}
	public String getBATCH_CODE() {
		return BATCH_CODE;
	}
	public void setBATCH_CODE(String bATCH_CODE) {
		BATCH_CODE = bATCH_CODE;
	}
	public String getPRODUCT_SN() {
		return PRODUCT_SN;
	}
	public void setPRODUCT_SN(String pRODUCT_SN) {
		PRODUCT_SN = pRODUCT_SN;
	}
	public String getCREATEDATE() {
		return CREATEDATE;
	}
	public void setCREATEDATE(String cREATEDATE) {
		CREATEDATE = cREATEDATE;
	}
	public String getACCEPTANCE_USER() {
		return ACCEPTANCE_USER;
	}
	public void setACCEPTANCE_USER(String aCCEPTANCE_USER) {
		ACCEPTANCE_USER = aCCEPTANCE_USER;
	}
	public String getACCEPTANCE_STATE() {
		return ACCEPTANCE_STATE;
	}
	public void setACCEPTANCE_STATE(String aCCEPTANCE_STATE) {
		ACCEPTANCE_STATE = aCCEPTANCE_STATE;
	}
	public String getCID() {
		return CID;
	}
	public void setCID(String cID) {
		CID = cID;
	}
	public String getPRODUCT_CODE() {
		return PRODUCT_CODE;
	}
	public void setPRODUCT_CODE(String pRODUCT_CODE) {
		PRODUCT_CODE = pRODUCT_CODE;
	}
	public String getORDER_ID() {
		return ORDER_ID;
	}
	public void setORDER_ID(String oRDER_ID) {
		ORDER_ID = oRDER_ID;
	}
	public String getCREATEUSERNAME() {
		return CREATEUSERNAME;
	}
	public void setCREATEUSERNAME(String cREATEUSERNAME) {
		CREATEUSERNAME = cREATEUSERNAME;
	}
	public String getAFTER_SALE_DATE() {
		return AFTER_SALE_DATE;
	}
	public void setAFTER_SALE_DATE(String aFTER_SALE_DATE) {
		AFTER_SALE_DATE = aFTER_SALE_DATE;
	}
	public String getCERTIFICATE_NO() {
		return CERTIFICATE_NO;
	}
	public void setCERTIFICATE_NO(String cERTIFICATE_NO) {
		CERTIFICATE_NO = cERTIFICATE_NO;
	}
	public String getIS_BIND() {
		return IS_BIND;
	}
	public void setIS_BIND(String iS_BIND) {
		IS_BIND = iS_BIND;
	}
	public String getPRODUCE_USER() {
		return PRODUCE_USER;
	}
	public void setPRODUCE_USER(String pRODUCE_USER) {
		PRODUCE_USER = pRODUCE_USER;
	}
	public String getMODIFYDATE() {
		return MODIFYDATE;
	}
	public void setMODIFYDATE(String mODIFYDATE) {
		MODIFYDATE = mODIFYDATE;
	}
	public String getSN_TYPE() {
		return SN_TYPE;
	}
	public void setSN_TYPE(String sN_TYPE) {
		SN_TYPE = sN_TYPE;
	}
	public String getMODIFYUSERNAME() {
		return MODIFYUSERNAME;
	}
	public void setMODIFYUSERNAME(String mODIFYUSERNAME) {
		MODIFYUSERNAME = mODIFYUSERNAME;
	}
	public String getPRINT_NUM() {
		return PRINT_NUM;
	}
	public void setPRINT_NUM(String pRINT_NUM) {
		PRINT_NUM = pRINT_NUM;
	}
	public String getQUALITY_USER() {
		return QUALITY_USER;
	}
	public void setQUALITY_USER(String qUALITY_USER) {
		QUALITY_USER = qUALITY_USER;
	}
	public String getSERIAL_TYPE() {
		return SERIAL_TYPE;
	}
	public void setSERIAL_TYPE(String sERIAL_TYPE) {
		SERIAL_TYPE = sERIAL_TYPE;
	}
	public String getACCEPTANCE_DESC() {
		return ACCEPTANCE_DESC;
	}
	public void setACCEPTANCE_DESC(String aCCEPTANCE_DESC) {
		ACCEPTANCE_DESC = aCCEPTANCE_DESC;
	}
	public String getSPEC_NUM() {
		return SPEC_NUM;
	}
	public void setSPEC_NUM(String sPEC_NUM) {
		SPEC_NUM = sPEC_NUM;
	}
	public String getACCEPTANCE_DATE() {
		return ACCEPTANCE_DATE;
	}
	public void setACCEPTANCE_DATE(String aCCEPTANCE_DATE) {
		ACCEPTANCE_DATE = aCCEPTANCE_DATE;
	}
	public String getITEMCODE() {
		return ITEMCODE;
	}
	public void setITEMCODE(String iTEMCODE) {
		ITEMCODE = iTEMCODE;
	}
	public String getTRAINCODE() {
		return TRAINCODE;
	}
	public void setTRAINCODE(String tRAINCODE) {
		TRAINCODE = tRAINCODE;
	}
	public String getCREATE_CID() {
		return CREATE_CID;
	}
	public void setCREATE_CID(String cREATE_CID) {
		CREATE_CID = cREATE_CID;
	}
	
	public SerialCode() {
		super();
	}
	
	
}

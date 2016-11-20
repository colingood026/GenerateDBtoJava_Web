package org.colin.vo;

import org.colin.common.enumClass.DriverClassEnum;
import org.colin.common.enumClass.OrMappingEnum;

/**
 * @author Colin
 *
 */
public class ConnDeatilVo {
    
	private String url;
	private String userNm;
	private String psd;
	private String tables;
	private String dbDriverClassNm;
	private String orMappingType;
	private String saveLocation;
	
	
	public ConnDeatilVo(){}
	
	/**
	 * 
	 * @param url 
	 * @param userNm
	 * @param psd
	 * @param tables：要generate的tables(","分隔)
	 * @param dbDriverClassNm
	 * @param orMappingType：使用的ormapping類型
	 * @param saveLocation：產出資料的存檔位置
	 */
	public ConnDeatilVo(String url,
	                    String userNm,
	                    String psd,
	                    String tables,
	                    String dbDriverClassNm,
	                    String orMappingType,
	                    String saveLocation){
	    this.url = url;
	    this.userNm = userNm;
	    this.psd = psd;
	    this.tables = tables;
	    this.dbDriverClassNm = DriverClassEnum.getClassNmByDbNm(dbDriverClassNm);
	    this.orMappingType = OrMappingEnum.getOrMappingByName(orMappingType);
	    this.saveLocation = saveLocation;
	}

	public void setUrl(String url) {
        this.url = url;
    }

    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

    public void setPsd(String psd) {
        this.psd = psd;
    }

    public void setTables(String tables) {
        this.tables = tables;
    }

    public void setDbDriverClassNm(String dbDriverClassNm) {
        
        this.dbDriverClassNm = DriverClassEnum.getClassNmByDbNm(dbDriverClassNm);
    }

    public String getUrl() {
		return url;
	}

	public String getUserNm() {
		return userNm;
	}

	public String getPsd() {
		return psd;
	}

    public String getTables() {
        return tables;
    }

    public String getDbDriverClassNm() {
        return dbDriverClassNm;
    }



    @Override
    public String toString() {
        return "ConnDeatilVo [url=" + url + ", userNm=" + userNm + ", psd=" + psd + ", tables=" + tables
                + ", dbDriverClassNm=" + dbDriverClassNm + ", orMappingType=" + orMappingType + ", saveLocation="
                + saveLocation + "]";
    }

    public String getSaveLocation() {
        return saveLocation;
    }

    public void setSaveLocation(String saveLocation) {
        this.saveLocation = saveLocation;
    }

    public String getOrMappingType() {
        return orMappingType;
    }

    public void setOrMappingType(String orMappingType) {
        this.orMappingType = OrMappingEnum.getOrMappingByName(orMappingType);
    }

	


	
}

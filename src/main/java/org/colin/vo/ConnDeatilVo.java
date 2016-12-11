package org.colin.vo;

import org.colin.common.enumClass.DriverClassEnum;
import org.colin.common.enumClass.OrMappingEnum;

/**
 * @author Colin
 *
 */
public class ConnDeatilVo {
    
    /**
     * urlPrefix + host + port + schema(databasename)
     */
	private String url;
	/**
	 * 資料庫IP
	 */
//	private String host;
	/**
	 * 資料庫port
	 */
//	private Integer port;
	/**
	 * 
	 */
//	private String dbName;
	/**
	 * 
	 */
	private String userNm;
	/**
	 * 
	 */
	private String psd;
	/**
	 * 要generate的tables(","分隔)
	 */
	private String tables;
	/**
	 * 
	 */
	private String dbDriverClassNm;
	/**
	 * 使用的ormapping類型
	 */
	private String orMappingType;
	/**
	 * 產出資料的存檔位置
	 */
	private String saveLocation; 
	
	public ConnDeatilVo(){}

	/**
	 * 
	 * @param dbDriverClassNm
	 * @param host
	 * @param port
	 * @param dbName
	 */
	public void setUrl(String dbDriverClassNm,
	                   String host,
	                   Integer port,
	                   String dbName) {
	    // jdbc:sqlserver://192.168.128.159:1433;databaseName=CTBC_SM
	    // jdbc:sqlserver://192.168.128.159;databaseName=CTBC_SM
        if(port == null){
            this.url = DriverClassEnum.getClassNmByDbNm(dbDriverClassNm).getUrlPrefix()+
                       host+";databaseName="+dbName;
        }else{
            this.url = DriverClassEnum.getClassNmByDbNm(dbDriverClassNm).getUrlPrefix()+
                       host+":"+port+";databaseName="+dbName;
        }
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
        
        this.dbDriverClassNm = DriverClassEnum.getClassNmByDbNm(dbDriverClassNm).getClassNm();
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

    public void setUrl(String url) {
        this.url = url;
    }



	


	
}

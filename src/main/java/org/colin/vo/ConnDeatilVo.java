package org.colin.vo;

import org.colin.common.enumClass.DriverClassEnum;
import org.colin.common.enumClass.OrMappingEnum;

/**
 * @author Colin
 *
 */
public class ConnDeatilVo {
    
	private String url; // urlPrefix + host + port + schema(databasename)
	private String host;
	private Integer port;
	private String dbName;
	private String userNm;
	private String psd;
	private String tables;
	private String dbDriverClassNm;
	private String orMappingType;
	private String saveLocation;
	
	
	public ConnDeatilVo(){}

	/**
	 * 
	 * @param host:資料庫IP
	 * @param port:資料庫port
	 * @param dbName
	 * @param userNm
	 * @param psd
	 * @param tables：要generate的tables(","分隔)
	 * @param dbDriverClassNm
	 * @param orMappingType：使用的ormapping類型
	 * @param saveLocation：產出資料的存檔位置
	 */
	public ConnDeatilVo(String host,
	                    Integer port,
	                    String dbName,
	                    String userNm,
	                    String psd,
	                    String tables,
	                    String dbDriverClassNm,
	                    String orMappingType,
	                    String saveLocation){
	    // jdbc:sqlserver://192.168.128.159:1433;databaseName=CTBC_SM
	    // jdbc:sqlserver://192.168.128.159;databaseName=CTBC_SM
	    if(port == null){
	        this.url = DriverClassEnum.getClassNmByDbNm(dbDriverClassNm).getUrlPrefix()+
	                   host+";databaseName="+dbName;
	    }else{
	        this.url = DriverClassEnum.getClassNmByDbNm(dbDriverClassNm).getUrlPrefix()+
	                   host+":"+port+";databaseName="+dbName;
	    }

	    this.userNm = userNm;
	    this.psd = psd;
	    this.tables = tables;
	    this.dbDriverClassNm = DriverClassEnum.getClassNmByDbNm(dbDriverClassNm).getClassNm();
	    this.orMappingType = OrMappingEnum.getOrMappingByName(orMappingType);
	    this.saveLocation = saveLocation;
	}

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

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
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

	


	
}

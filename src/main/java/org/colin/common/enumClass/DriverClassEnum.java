package org.colin.common.enumClass;
/**
 * @author Colin
 *
 */
public enum DriverClassEnum {
	MSSQL("msSql","com.microsoft.sqlserver.jdbc.SQLServerDriver","jdbc:sqlserver://",1433),
	MYSQL("mySql","","",1111);
	
	private String dbNm;
	private String classNm;
	private String urlPrefix;
	private Integer defaultPort;
	
	private DriverClassEnum(String dbNm, String classNm,String urlPrefix,Integer defaultPort){
		this.dbNm = dbNm;
		this.classNm = classNm;
		this.urlPrefix = urlPrefix;
		this.defaultPort = defaultPort;
	}

	public String getDbNm() {
		return dbNm;
	}

	public String getUrlPrefix() {
        return urlPrefix;
    }

    public void setUrlPrefix(String urlPrefix) {
        this.urlPrefix = urlPrefix;
    }

    public void setDbNm(String dbNm) {
		this.dbNm = dbNm;
	}

	public Integer getDefaultPort() {
        return defaultPort;
    }

    public void setDefaultPort(Integer defaultPort) {
        this.defaultPort = defaultPort;
    }

    public String getClassNm() {
		return classNm;
	}

	public void setClassNm(String classNm) {
		this.classNm = classNm;
	}
	/**
	 * 
	 * @param dbNm
	 * @return
	 */
	public static DriverClassEnum getClassNmByDbNm(String dbNm){
		for(DriverClassEnum a:DriverClassEnum.values()){
			if(a.dbNm.equals(dbNm)){
				return MSSQL;
			}
		}
		return null;
	}
}

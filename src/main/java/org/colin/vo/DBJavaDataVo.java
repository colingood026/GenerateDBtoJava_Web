package org.colin.vo;

import java.util.List;
import java.util.Set;

/**
 * 
 */

/**
 * @author Colin
 *
 */
public class DBJavaDataVo {
	private final String classNm;
	private final String tableNm;
	private final Set<String> importJars;
	private final List<TableFieldsVo> fields;
	
	
	public String getClassNm() {
		return classNm;
	}

	public String getTableNm() {
		return tableNm;
	}

	public Set<String> getImportJars() {
		return importJars;
	}

	public List<TableFieldsVo> getFields() {
		return fields;
	}

	private DBJavaDataVo(Builder builder){
		classNm = builder.classNm;
		tableNm = builder.tableNm;
		importJars = builder.importJars;
		fields = builder.fields;
	}
	
	public static class Builder{
		private final String classNm;
		private final String tableNm;
		private final Set<String> importJars;
		private final List<TableFieldsVo> fields;
		
		/**
		 * 
		 * @param classNm
		 * @param tableNm
		 * @param importJars
		 * @param fields
		 */
		public Builder(String classNm,
					   String tableNm,
					   Set<String> importJars,
					   List<TableFieldsVo> fields){
			this.classNm = classNm;
			this.tableNm = tableNm;
			this.importJars = importJars;
			this.fields = fields;
			
		}
		
		public DBJavaDataVo build(){
			return new DBJavaDataVo(this);
		}
	}

}

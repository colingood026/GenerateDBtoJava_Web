package org.colin.common.util;

public class MethodUtils {
	
	public static final String CAPS = "    ";
	public static final String DOUBLE_CAPS = "        ";
	public static final String N = "\n";
	
	public static final String[] SPLITS = {"_"};
	/**
	 * xx_aa_bb... -> xxAaBb
	 * @param fieldNm
	 * @return
	 */
	public static String removeSplitForField(String fieldNm){		
		StringBuffer sb = new StringBuffer();
		
		for(String split:SPLITS){
			String[] spS = fieldNm.split(split);
			if(spS.length > 1){
				for(int i = 1; i < spS.length; i++){
					spS[i] = String.valueOf(spS[i].charAt(0)).toUpperCase() + spS[i].substring(1);
				}
				for(String sp:spS){
					sb.append(sp);
				}
			}else{
				sb.append(fieldNm);
			}			
		}
		return sb.toString();
	}
	/**
	 * xx_aa_bb... -> XxAaBb
	 * @param fieldNm
	 * @return
	 */
	public static String removeSplitForClassNm(String fieldNm){		
		StringBuffer sb = new StringBuffer();
		
		for(String split:SPLITS){
			String[] spS = fieldNm.split(split);
			spS[0] = String.valueOf(spS[0].charAt(0)).toUpperCase() + spS[0].substring(1);
			if(spS.length > 1){
				for(int i = 1; i < spS.length; i++){
					spS[i] = String.valueOf(spS[i].charAt(0)).toUpperCase() + spS[i].substring(1);
				}
				for(String sp:spS){
					sb.append(sp);
				}
			}else{
				sb.append(fieldNm);
			}			
		}
		return sb.toString();
	}
	
	
    /**
     * 
     * @param fieldNm
     * @param type
     * @param comment
     * @return
     */
    public static String createField(String fieldNm,String type,String comment){
//        /**
//         * comment
//         */
//        private type fieldNm;
        StringBuffer aBuffer = new StringBuffer();
        if(comment != null){
            aBuffer.append(MethodUtils.CAPS+"/**").append(MethodUtils.N);        
            aBuffer.append(MethodUtils.CAPS+" * ").append(comment).append(MethodUtils.N);
            aBuffer.append(MethodUtils.CAPS+" */").append(MethodUtils.N);
        }
        aBuffer.append(MethodUtils.CAPS+"private "+type+" "+fieldNm+";");
        return aBuffer.toString();
    }
    /**
     * 
     * @param fieldNm
     * @param type
     * @return
     */
    public static String createGet(String fieldNm,String type){
//      public String getJavaType() {
//          return javaType;
//      }
        String firstChar = fieldNm.substring(0, 1);
        String get = "get"+firstChar.toUpperCase()+fieldNm.substring(1);
        return MethodUtils.CAPS+"public "+type+" "+get+"(){ return "+fieldNm+";}";
    }
    /**
     * 
     * @param fieldNm
     * @param type
     * @return
     */
    public static String createSet(String fieldNm,String type){
//      public void setJavaType(String javaType) {
//          this.javaType = javaType;
//      }
        String firstChar = fieldNm.substring(0, 1);
        String set = "set"+firstChar.toUpperCase()+fieldNm.substring(1);
        return MethodUtils.CAPS+"public void "+set+"("+type+" "+fieldNm+"){"+MethodUtils.N+MethodUtils.DOUBLE_CAPS+
                                        "this."+fieldNm+" = "+fieldNm+";"+MethodUtils.N+MethodUtils.CAPS+"}";
        
    }
}

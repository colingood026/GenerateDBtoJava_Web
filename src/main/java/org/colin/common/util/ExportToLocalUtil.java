package org.colin.common.util;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;



/**
 * 
 */

/**
 * @author Colin
 *
 */
public class ExportToLocalUtil {
	

	/**
	 * 如果資料夾名稱重複，會再加上時間做區隔
	 * @param location
	 * @return
	 */
	public static String createDirectory(String location){
		// C:/text
		try {
			File dbDir = new File(location);
			if(dbDir.exists()){				
				Date date = new Date();
				SimpleDateFormat sf = new SimpleDateFormat("yyyymmddHHmmss");				
				location = location+"_"+sf.format(date);
				dbDir = new File(location);
			}
			dbDir.mkdir();

			File xmlDir = new File(location+"/xml");
			xmlDir.mkdir();
		} catch (Exception e) {
			e.printStackTrace();
			location = null;
		}
		return location;
	}
	
	
	/**
	 * 
	 * @param packageRoot
	 * @param location
	 * @return
	 */
	private static String createSubDirectorys(String packageRoot,String location){
        // com.colin.model.car
	    packageRoot = packageRoot.replaceAll("\\.","/");
	    location = location+"/"+packageRoot + "/";
        File root = new File(location);
        if(root.exists() == false){
            root.mkdirs();
        }
        
        return location;
	}
	/**
	 * 
	 * @param modelPackageRoot
	 * @param location
	 * @param classNm
	 * @param content
	 */
	public static void exportBean(String modelPackageRoot,String location,String classNm,String content){
		BufferedWriter bw = null;		
		FileWriter fw = null;
		try {
		    location = createSubDirectorys(modelPackageRoot,location);
			File file = new File(location + classNm + ".java");
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			bw.write(content);
			bw.flush();
		} catch (IOException e) {			
			e.printStackTrace();
		}finally{
			close(bw,fw);
		}
	}
	/**
	 * 
	 * @param daoPackageRoot
	 * @param location
	 * @param classNm
	 * @param content
	 */
	public static void exportDao(String daoPackageRoot,String location,String classNm,String content){
		BufferedWriter bw = null;	
		FileWriter fw = null;
		try {
		    location = createSubDirectorys(daoPackageRoot,location);
			File file = new File(location + classNm + "Mapper.java");
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			bw.write(content);
			bw.flush();
		} catch (IOException e) {			
			e.printStackTrace();
		}finally{
			close(bw,fw);
		}
	}
	/**
	 * 
	 * @param location
	 * @param classNm
	 * @param content
	 */
	public static void exportXml(String location,String classNm,String content){
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {		    
			File file = new File(location+"/xml/"+classNm+"Mapper"+".xml");
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			bw.write(content);
			bw.flush();
		} catch (IOException e) {			
			e.printStackTrace();
		}finally{
			close(bw,fw);
		}
	}
	/**
	 * 
	 * @param bw
	 * @param fw
	 */
	private static void close(BufferedWriter bw,FileWriter fw){
		if(bw != null){
			try {
				bw.close();
			} catch (IOException e) {					
				e.printStackTrace();
			}
		}
		if(fw != null){
			try {
				fw.close();
			} catch (IOException e) {					
				e.printStackTrace();
			}
		}

	}
}

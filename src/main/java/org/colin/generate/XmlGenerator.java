/**
 * 
 */
package org.colin.generate;

import java.util.List;

import org.colin.vo.TableFieldsVo;

/**
 * @author Colin
 *
 */
public interface XmlGenerator {

    
    /**
     * 
     * @param packageNm
     * @param fields
     * @param classNm
     * @param tableNm
     * @return
     */
    public String buildXml(String packageNm,
                           List<TableFieldsVo> fields, 
                           String classNm,
                           String tableNm);
}

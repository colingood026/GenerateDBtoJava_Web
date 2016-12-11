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
     * @param fields
     * @param classNm
     * @param tableNm
     * @return
     */
    public String buildXml(List<TableFieldsVo> fields, 
                           String classNm,
                           String tableNm);
}

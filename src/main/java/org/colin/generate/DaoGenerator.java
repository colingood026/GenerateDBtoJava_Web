/**
 * 
 */
package org.colin.generate;

import java.util.List;
import java.util.Set;

import org.colin.vo.TableFieldsVo;

/**
 * @author Colin
 *
 */
public interface DaoGenerator {

    /**
     * 
     * @param daoPackageRoot
     * @param importJars
     * @param fields
     * @param classNm
     * @return
     */
    public String buildDao(String daoPackageRoot,
                           Set<String> importJars, 
                           List<TableFieldsVo> fields, 
                           String classNm);
}

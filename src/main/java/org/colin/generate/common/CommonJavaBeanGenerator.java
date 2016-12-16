package org.colin.generate.common;

import java.util.ArrayList;
import java.util.List;

import org.colin.common.util.MethodUtils;
import org.colin.vo.CommonClassVO;
import org.colin.vo.CommonClassfield;


/**
 * 製造普通的bean
 * @author Colin
 *
 */
public class CommonJavaBeanGenerator {

    /**
     * JAVA Bean Pattern
     * @param commonClassVOs
     * @return
     */
    public List<String> beanPatternBuilder(List<CommonClassVO> commonClassVOs){

        List<String> allBeanPattern = new ArrayList<>();
        for(CommonClassVO commonClassVO:commonClassVOs){
            StringBuffer sb = new StringBuffer();

            sb.append("public class " + commonClassVO.getClassNm() + " implements Serializable{")
            .append(MethodUtils.N);
            sb.append(MethodUtils.N);
            sb.append(MethodUtils.CAPS+"private static final long serialVersionUID = 1L;")
            .append(MethodUtils.N);
            sb.append(MethodUtils.N);

            // create field
            for(CommonClassfield field:commonClassVO.getCommonClassfields()){
                sb.append(MethodUtils.createField(field.getFieldNm(),field.getType(),field.getComment()))
                .append(MethodUtils.N);
            }       
            sb.append(MethodUtils.N);
            // create get
            for(CommonClassfield field:commonClassVO.getCommonClassfields()){
                sb.append(MethodUtils.createGet(field.getFieldNm(),field.getType())).append(MethodUtils.N);
            }
            sb.append(MethodUtils.N);
            // create set
            for(CommonClassfield field:commonClassVO.getCommonClassfields()){
                sb.append(MethodUtils.createSet(field.getFieldNm(),field.getType())).append(MethodUtils.N);
            }
            sb.append(MethodUtils.N);
            sb.append("}");        
            allBeanPattern.add(sb.toString());
        }
        
        return allBeanPattern;
    }

}

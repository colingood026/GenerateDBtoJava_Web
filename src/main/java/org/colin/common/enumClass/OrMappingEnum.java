package org.colin.common.enumClass;

public enum OrMappingEnum {
    Mybatis("mybatis"),
    Hibernate("hibernate");
    
    
    private String orMappingName;
    
    private OrMappingEnum(String orMappingName){
        this.orMappingName = orMappingName;
    }

    public String getOrMappingName() {
        return orMappingName;
    }

    public void setOrMappingName(String orMappingName) {
        this.orMappingName = orMappingName;
    }
    
    public static String getOrMappingByName(String orMappingName){
        for(OrMappingEnum a:OrMappingEnum.values()){
            if(a.orMappingName.equals(orMappingName)){
                return a.orMappingName;
            }
        }
        return null;
    }
    
}

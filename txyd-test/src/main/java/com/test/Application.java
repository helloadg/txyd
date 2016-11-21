package com.test;


public enum Application {
  GWS(),  GWS1();
  private Application(){}
  
  public static String getLowercaseName(Application application){
    if(application == null){
      return "";
    }
    
    return application.name().toLowerCase();
  }
  
  public static boolean isContain(Application application){
    boolean isContain = Boolean.FALSE;
    if(application == null){
      return isContain;
    }
    
    for(Application app : Application.values()){
      if(app == application){
        isContain = Boolean.TRUE;
        break;
      }
    }
    
    return isContain;
  }
  
}
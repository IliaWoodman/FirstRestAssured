package yandex.translate;

public enum EndPointUrlYandexTranslate {

    TRANSLATE("/translate"),
    DEFINITION_LANGUAGE("/detect"),
    GET_LIST_LANGUAGES("/getLangs");
    String path;
    EndPointUrlYandexTranslate(String path){
        this.path = path;
    }
    public String getPath(){
        return path;
    }
    public String addPath(String additionalPath){
        return path + additionalPath;
    }

}

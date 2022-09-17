package ch04.n7;

public enum Colors {
    RED("#FF0000"), BLUE("#0000FF"), GREEN("#00FF00"), CYAN("#00FFFF"), MAGENTA("#FF00FF"), YELLOW("#FFFF00"), WHITE("#000000");
    private String code;
    Colors(String s) {
        code = s;
    }

    public String getRed(){
        return RED.code;
    }

    public String getGreen(){
        return GREEN.code;
    }

    public String getBlue(){
        return BLUE.code;
    }
}

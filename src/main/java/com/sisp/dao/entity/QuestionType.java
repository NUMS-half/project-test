package com.sisp.dao.entity;

public enum QuestionType {
    SINGLE("单选题", 1),      //1:单选题
    MULTIPLE("多选题", 2),    //2:多选题
    GAP_FILL("填空题", 3),    //3.填空题
    MATRIX("矩阵题", 4),      //4.矩阵题
    SCALE("量表题", 5);       //5.量表题


    private int id;
    private String name;

    QuestionType(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    public static String getNameByIndex(int id) {
        QuestionType[] types = QuestionType.values();
        for ( QuestionType i : types ) {
            if ( i.getId() == id )
                return i.name;
        }
        return null;
    }
}

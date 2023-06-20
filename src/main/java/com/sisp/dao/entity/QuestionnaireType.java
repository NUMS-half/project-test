package com.sisp.dao.entity;

public enum QuestionnaireType {
    STUDENT("学生", 0),    //0:学生
    TEACHER("老师", 1);    //1:老师

    private int id;
    private String name;

    QuestionnaireType(String name, int id) {
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
        QuestionnaireType[] types = QuestionnaireType.values();
        for ( QuestionnaireType i : types ) {
            if ( i.getId() == id )
                return i.name;
        }
        return null;
    }
}

package com.example.forum.emus;

public enum CommentTypeEmus {
    QUESTION(1),
    COMMENT(2);
    private Integer type;

    public static boolean isExist(Integer type) {
        for (CommentTypeEmus commentTypeEmus : CommentTypeEmus.values()) {
            if (commentTypeEmus.getType() == type){
                return true;
            }
        }
        return false;
    }

    public Integer getType() {
        return type;
    }

    CommentTypeEmus(Integer type) {
        this.type = type;
    }
}

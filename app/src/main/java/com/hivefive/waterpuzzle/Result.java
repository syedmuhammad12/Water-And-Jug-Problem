package com.hivefive.waterpuzzle;

import java.io.Serializable;

public class Result implements Serializable {

    String res;
    Pair main_res;

    public Result(String res, Pair main_res) {
        this.res = res;
        this.main_res = main_res;



    }
}

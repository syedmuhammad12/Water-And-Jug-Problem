package com.hivefive.waterpuzzle;
import java.io.Serializable;
import java.util.*;
class Pair implements Serializable {
    int j1, j2;
    List<Pair> path;

    Pair(int j1, int j2)
    {
        this.j1 = j1;
        this.j2 = j2;
        path = new ArrayList<>();
    }

    Pair(int j1, int j2, List<Pair> _path)
    {
        this.j1 = j1;
        this.j2 = j2;

        path = new ArrayList<>();
        path.addAll(_path);
        path.add(new Pair(this.j1, this.j2));
    }
}

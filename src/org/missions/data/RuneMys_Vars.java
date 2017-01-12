package org.missions.data;

/**
 * Created by Sphiinx on 1/11/2017.
 */
public class RuneMys_Vars {

    public static RuneMys_Vars vars;

    public static RuneMys_Vars get() {
        return vars == null ? vars = new RuneMys_Vars() : vars;
    }

    public static void reset() {
        vars = null;
    }

}


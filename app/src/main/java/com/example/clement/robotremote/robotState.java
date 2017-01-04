package com.example.clement.robotremote;

/**
 * Created by clement on 03/01/2017.
 */
public class robotState {

    static public String command;
    static public boolean changed = false;
    static public boolean ihmButtonDown = false;

    static public void setCommand(String usrCommand){
        robotState.command = usrCommand;
    }

    static public String getCommand(){
        return command;
    }

    static public void setChanged(boolean doesIt){
        robotState.changed = doesIt;
    }

    static public boolean getChanged(){
        return changed;
    }

    static public void setIhmButtonDown(boolean doesIt){
        robotState.ihmButtonDown = doesIt;
    }

    static public boolean getIhmButtonDown(){
        return ihmButtonDown;
    }

}

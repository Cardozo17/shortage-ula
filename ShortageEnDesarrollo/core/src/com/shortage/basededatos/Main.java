package com.shortage.basededatos;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.shortage.game.Shortage;


public class Main {

    public static void main(String[] args) {
        new LwjglApplication(new Shortage(), "Example", 800, 600);
    }

}

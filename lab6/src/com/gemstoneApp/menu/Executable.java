package com.gemstoneApp.menu;

interface Executable {
    Menu menu = null;
    void execute();
    String getDescription();
}
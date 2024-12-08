package com.gemstoneApp.menu;

import com.gemstoneApp.gemstone.Gemstone;

public class PrintGemstoneList implements Executable {
    private Menu menu;
    public PrintGemstoneList(Menu menu) {
        this.menu = menu;
    }
    @Override
    public void execute() {
        System.out.println("Printing Gemstone List:\n");
        for (int i = 0; i < menu.getGems().size(); i++) {
            System.out.println(i+1 + " " + menu.getGems().get(i));
        }
    }
    @Override
    public String getDescription() {
        return "Print list of all gemstones";
    }

}

package dev.zanckor.multirespawnlocation.common.util;

import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;

import static net.minecraft.client.gui.components.Button.builder;

public class MCUtilClient {
    public static Button createButton(int xPos, int yPos, int width, int height, Component component, Button.OnPress onPress) {
        Button button = builder(component, onPress).build();

        button.setPosition(xPos, yPos);
        button.setWidth(width);
        button.setHeight(height);

        return button;
    }
}

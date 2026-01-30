package io.github.jr1811.ashbornrp.screen.screen;

import io.github.jr1811.ashbornrp.AshbornMod;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class PlayerAccessoryScreen extends Screen {
    public static final Identifier TEXTURE = AshbornMod.getId("accessories");

    public PlayerAccessoryScreen(Text title) {
        super(title);
    }


}

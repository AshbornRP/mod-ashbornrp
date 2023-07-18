# AshbornRP Content Mod

Add custom models and animations using GeckoLib (3) for Fabric Minecraft 1.18.2.

## Content management

### Add or change Content

Models and animations can be created using Blockbench. Creatign textures is possible with Blockbench too, but there are
better tools for that, such as Aseprite.

---

3D Models are saved as `itemName.geo.json` files. Check the `/resources/assets/ashbornrp/geo` directory to make changes
or add new content.

---

Animations are saved as `itemName.animation.json` files. Check the `/resources/assets/ashbornrp/animations` directory to
make changes or add new content. Keep in mind that every new custom item needs at least one animation to function
properly. If there are no animations for an item, apply at least the `blank.animation.json` file

---

Textures are saved as `itemName.png` files. Item's need two different Textures by default.
One for the 2D sprite, which will be displayed in inventories `/resources/assets/ashbornrp/textures/item` and one for
the 3D model, which is located in `/resources/assets/ashbornrp/textures/models/armor`.

## Item registration

Items are registered inside the `AshbornModItems` class. For custom armor items without any custom functionality the
`GeneralArmorItem` has been created. It also acts as a parent class to the more specialized Armor items which have more
animations or other custom functionality.
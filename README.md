# AshbornRP Content Mod

Add custom models and animations using GeckoLib (3) for Fabric Minecraft 1.18.2.

## Content management

### Add or change Content

Models and animations can be created using Blockbench. Creatign textures is possible with Blockbench too, but there are
better tools for that, such as Aseprite.

---

3D Models are saved as `itemName.geo.json` files. Check the `/resources/assets/ashbornrp/geo` directory to make changes
or add new content. Their 2D sprite counterpart for the inventory view only points to the 2D sprite's texture. It can be
found in `/resources/assets/ashbornrp/models/item`.

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
`GeneralArmorItem` has been created. It also acts as a parent class for the more specialized Armor items which have more
animations or other custom functionality.

The Client sided handling of rendering and model registration starts over in the `AshbornModClient` class where each
item gets a render class and a model class.

## Naming convention

The `itemName` is defined in the `AshbornModItems` class. This item name needs to match up with the 2D sprite's texture
name. The Animation files, Geo model files and 3D model texture files are all named inside the model class of the custom
item, but it should get the same name of the registered item.

If a Purple/Black texture appears in the inventory, either the texture file or either the 2D or 3D model files had a
problem.
# Ashborn Mod

The Ashborn Mod is a Fabric Minecraft mod for the Ashborn role play projects. Its features are made for the ecosystem of currently active Ashborn projects.

## Features

### Plushies

Starting with Ashborn Season 3, new placable Plushie items have been created, which include all the mechanics which you would expect from them.

Some Plushies even have secret interactions and functionalities!

### Accessory System

For season 4, a new accessory system has been created to grant even more unique characteristics for the players.

Accessories themselves use a new system which is not coupled to anything like armor, items and other things. Players can equip (multiple) different accessories at the same time, while not wasting any inventory or equipment space.

All Accessories can at least hold one color value (aka. `tintindex`). The color value is not bound to only minecraft's dye colors so you can choose any color you like.

There are two different accessory types:
- `Accessory with Item Rendering` - Uses an Item in the backend only for rendering. This allows accessories to hold multiple parts, which all can be colored differently
- `Accessory with Entity Rendering` - Uses something similar to Entity rendering, which means that the accessory can hold and play many different Animations in many different ways. The newly created Animation System builds on top of Minecrafts own animation system *(no GeckoLib needed)*, which can be observed on newer mobs, like Warden, Camels, Frogs and Armadillos. However, in this mod, the animation system has been expanded to allow for dynamic and automatic animation toggling in many different situations.

## Compatibility and Dependencies

- [mcda](https://modrinth.com/mod/mcda) **(optional)** used in recipes
- [farmersdelight](https://modrinth.com/mod/farmers-delight-refabricated) **(optional)** used in recipes
- [Crawl](https://modrinth.com/mod/crawl) **(optional)** additional crawl pose integration for the dynamic accessory animation system
- [Cardinal Components API (cca)](https://modrinth.com/mod/cardinal-components-api) **(required)** used for streamlined processes of data persistency, networking and centralized ticking logic
- and more...

---

<div style="text-align: center;">
<br>
<a href="https://fabricmc.net/"><img
    src="https://raw.githubusercontent.com/fabricated-atelier/.github/a021bde84febcb68adc69fc7ae60114e8c0902db/assets/badges/bc25/supported_on_fabric_loader.svg"
    alt="Supported on Fabric"
    width="200"
></a>
<a href="https://github.com/JR1811/ashbornrp/issues"><img
    src="https://raw.githubusercontent.com/fabricated-atelier/.github/f026478715176aeb6a334f1c21765031d9b6c3f9/assets/badges/bc25/work_in_progress.svg"
    alt="Work in Progress"
    width="200"
></a>
</div>
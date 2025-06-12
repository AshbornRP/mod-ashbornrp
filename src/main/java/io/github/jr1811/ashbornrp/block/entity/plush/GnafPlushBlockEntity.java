package io.github.jr1811.ashbornrp.block.entity.plush;

import io.github.jr1811.ashbornrp.init.AshbornModBlockEntities;
import io.github.jr1811.ashbornrp.util.NbtKeys;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import java.util.HashMap;

public class GnafPlushBlockEntity extends GenericPlushBlockEntity {
    private final HashMap<Item, Integer> consumedItems = new HashMap<>();

    public GnafPlushBlockEntity(BlockPos pos, BlockState state) {
        super(AshbornModBlockEntities.PLUSH_GNAF, pos, state);
    }

    public HashMap<Item, Integer> getConsumedItems() {
        return consumedItems;
    }

    public int getConsumedItemCount(Item item) {
        return getConsumedItems().getOrDefault(item, 0);
    }

    public void addOrIncrementConsumedItem(Item item, int increment) {
        getConsumedItems().put(item, getConsumedItemCount(item) + increment);
        markDirty();
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        nbt.getList(NbtKeys.CONSUMED, NbtElement.COMPOUND_TYPE).stream()
                .map(nbtElement -> (NbtCompound) nbtElement)
                .forEach(entry -> {
                    Item item = Registries.ITEM.get(new Identifier(entry.getString("id")));
                    int amount = entry.getInt(NbtKeys.COUNTER);
                    this.getConsumedItems().put(item, amount);
                });
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        NbtList consumedList = new NbtList();
        this.consumedItems.forEach((item, amount) -> {
            NbtCompound itemCompound = new NbtCompound();
            Identifier identifier = Registries.ITEM.getId(item);
            itemCompound.putString("id", identifier.toString());
            itemCompound.putInt(NbtKeys.COUNTER, amount);
            consumedList.add(itemCompound);
        });
        nbt.put(NbtKeys.CONSUMED, consumedList);
    }
}

# Slipperiness For Wheelchairs

Custom Block Slipperiness values can be specified in a datapack's `<namespace>/block/slipperiness` directory with
custom JSON files. Here is an [example](src/main/resources/data/ashbornrp/block/slipperiness/defaults.json).

## Which values are possible?

For each entry you can specify either a Float JSON value

```json
{
  "example": 0.75
}
``` 

or a JSON array of Float values

```json
{
  "example": [
    0.75,
    0.1234,
    0.6969
  ]
}
```

In case of an array it will apply one of the specified entries randomly for every checked tick.

If an in-game Block can't be linked to any entry, They will use their
minecraft default slipperiness (usually <code>0.6</code>).

Technically all Float values are possible but common values are between `0.0` (not drivable at all) and `1.0` (extremely
slippery).

Values above `0.95` are already considered very slippery and may cause the wheelchair to start drifting a little bit
when turning around while driving.

## Which keys are possible?

### Block Identifier

Use a Block Identifier to target Blocks specifically.

```json
{
  "minecraft:dirt": 0.75,
  "minecraft:mud": 0.75,
  "minecraft:stone": 0.75
}
``` 

### Block Tags `#`

Use a specific Block Tag to target Blocks which are listed in Block Tags.
The first symbol needs to be a `#`.

```json
{
  "#minecraft:stone_bricks": 0.75,
  "#minecraft:ice": 0.98
}
``` 

### Block Search `+`

You can also search in a more lenient way.
The first symbol needs to be a `+`.

It will check all existing entries in the Block Registry for your input and will add
the block, if your input matched completely with the requirements.

So a search term of `sti` would include `minecraft:stick` but a search term
of `stn` would not include `minecraft:stone`.

- Use the Symbol `|` to split your search term into multiple search term entries. All entries need to exist in the block
  id. Ordering is not important
- Start a search term entry with `~` to exclude blocks, which contain this specific part

Example:

- `"+minecraft:"` everything from the minecraft namespace
- `"+~minecraft:"` nothing from the minecraft namespace
- `+red|light|~wool` everything which has "red" and "light" in the id, but excludes all entries which contain "wool"
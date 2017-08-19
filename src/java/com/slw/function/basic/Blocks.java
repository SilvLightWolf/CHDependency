package com.slw.function.basic;

import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.abstraction.MCItemStack;
import com.laytonsmith.abstraction.MCLocation;
import com.laytonsmith.abstraction.MCWorld;
import com.laytonsmith.abstraction.bukkit.BukkitMCItemStack;
import com.laytonsmith.abstraction.bukkit.BukkitMCLocation;
import com.laytonsmith.abstraction.bukkit.BukkitMCWorld;
import com.laytonsmith.abstraction.bukkit.entities.BukkitMCPlayer;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.ObjectGenerator;
import com.laytonsmith.core.Static;
import com.laytonsmith.core.constructs.*;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.exceptions.CRE.CRECastException;
import com.laytonsmith.core.exceptions.CRE.CREThrowable;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.functions.AbstractFunction;
import com.slw.DataHandling;
import org.bukkit.*;
import org.bukkit.block.*;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2017-08-19.
 */
public class Blocks {

    @api
    public static class chu_block_info extends AbstractFunction{

        @SuppressWarnings("unchecked")
        public Class<? extends CREThrowable>[] thrown() {
            return new Class[0];
        }

        public boolean isRestricted() {
            return false;
        }

        public Boolean runAsync() {
            return null;
        }

        public Construct exec(Target t, Environment env, Construct...args) throws ConfigRuntimeException {

            if (!(args[0] instanceof CArray))
                throw new CRECastException("Expecting an array, but received " + args[0].val() + " instead.", t);

            CArray ca = (CArray) args[0];
            CArray retv = new CArray(t);
            MCWorld w = Static.getWorld(args[1], t);
            MCLocation mcloc = ObjectGenerator.GetGenerator().location(ca, w, t);
            Location loc = ((BukkitMCLocation) mcloc)._Location();
            Block b = ((BukkitMCWorld)w).__World().getBlockAt(loc);
            BlockState bs = b.getState();

            retv.set("location", ObjectGenerator.GetGenerator().location(mcloc), t);
            retv.set("block_type", bs.getType().name());

            if (bs instanceof Banner) {

                Banner state = (Banner) bs;

                CArray bcolorarr = new CArray(t);
                bcolorarr.set("red", new CInt(state.getBaseColor().getColor().getRed(), t), t);
                bcolorarr.set("green", new CInt(state.getBaseColor().getColor().getGreen(), t), t);
                bcolorarr.set("blue", new CInt(state.getBaseColor().getColor().getBlue(), t), t);
                retv.set("basecolor", bcolorarr, t);

                CArray patterns = new CArray(t);
                for(Pattern pat : state.getPatterns()){
                    CArray patarr = new CArray(t);
                    patarr.set("type", pat.getPattern().name());

                    CArray patcolor = new CArray(t);
                    patcolor.set("red", new CInt(pat.getColor().getColor().getRed(), t), t);
                    patcolor.set("green", new CInt(pat.getColor().getColor().getGreen(), t), t);
                    patcolor.set("blue", new CInt(pat.getColor().getColor().getBlue(), t), t);
                    patarr.set("color", patcolor, t);

                    patterns.push(patarr, t);
                }
                retv.set("patterns", patterns, t);

            } else if (bs instanceof Beacon) {

                Beacon state = (Beacon) bs;

                Inventory binv = state.getInventory();
                CArray inv = DataHandling.inventoryToArray(state.getInventory());
                retv.set("inventory", inv, t);
                retv.set("locked", CBoolean.get(state.isLocked()), t);

                CArray effectentities = new CArray(t);
                for(LivingEntity le : state.getEntitiesInRange())
                    effectentities.push(new CString(String.valueOf(le.getUniqueId()), t), t);
                retv.set("entities", effectentities, t);

                CArray peff = new CArray(t);
                CArray seff = new CArray(t);

                if(state.getPrimaryEffect() != null) {
                    peff.set("duration", new CInt(state.getPrimaryEffect().getDuration(), t), t);
                    peff.set("amplifier", new CInt(state.getPrimaryEffect().getAmplifier(), t), t);
                    peff.set("name", state.getPrimaryEffect().getType().getName());
                    peff.set("isambient", CBoolean.get(state.getPrimaryEffect().isAmbient()), t);
                    peff.set("hasparticle", CBoolean.get(state.getPrimaryEffect().hasParticles()), t);
                }
                if(state.getSecondaryEffect() != null) {
                    seff.set("duration", new CInt(state.getSecondaryEffect().getDuration(), t), t);
                    seff.set("amplifier", new CInt(state.getSecondaryEffect().getAmplifier(), t), t);
                    seff.set("name", state.getSecondaryEffect().getType().getName());
                    seff.set("isambient", CBoolean.get(state.getSecondaryEffect().isAmbient()), t);
                    seff.set("hasparticle", CBoolean.get(state.getSecondaryEffect().hasParticles()), t);
                }

                retv.set("primaryeffect", peff, t);
                retv.set("secondaryeffect", seff, t);
                retv.set("tier", new CInt(state.getTier(), t), t);

            } else if (bs instanceof Bed) {

                Bed state = (Bed) bs;

                CArray bcolorarr = new CArray(t);

                int red = state.getColor().getColor().getRed();
                int green = state.getColor().getColor().getGreen();
                int blue = state.getColor().getColor().getBlue();

                retv.set("color", DyeColor.getByColor(Color.fromRGB(red, green, blue)).name());

            } else if (bs instanceof BrewingStand) {

                BrewingStand state = (BrewingStand) bs;

                CArray inv = DataHandling.inventoryToArray(state.getInventory());
                retv.set("inventory", inv, t);
                retv.set("locked", CBoolean.get(state.isLocked()), t);

                retv.set("brewingtime", new CInt(state.getBrewingTime(), t), t);
                retv.set("fuellevel", new CInt(state.getFuelLevel(), t), t);

            } else if (bs instanceof Chest) {

                Chest state = (Chest) bs;

                CArray inv = DataHandling.inventoryToArray(state.getBlockInventory());
                retv.set("inventory", inv, t);
                retv.set("locked", CBoolean.get(state.isLocked()), t);

            } else if (bs instanceof CommandBlock) {

                CommandBlock state = (CommandBlock) bs;

                retv.set("command", state.getCommand());
                retv.set("name", state.getName());

            } else if (bs instanceof CreatureSpawner) {

                CreatureSpawner state = (CreatureSpawner) bs;

                retv.set("delay", new CInt(state.getDelay(), t), t);
                retv.set("spawntype", state.getSpawnedType().name());

            } else if (bs instanceof Dispenser) {

                Dispenser state = (Dispenser) bs;

                CArray inv = DataHandling.inventoryToArray(state.getInventory());
                retv.set("inventory", inv, t);
                retv.set("locked", CBoolean.get(state.isLocked()), t);

            } else if (bs instanceof Dropper) {

                Dropper state = (Dropper) bs;

                CArray inv = DataHandling.inventoryToArray(state.getInventory());
                retv.set("inventory", inv, t);
                retv.set("locked", CBoolean.get(state.isLocked()), t);

            }/* else if (bs instanceof EndGateway) {

                EndGateway state = (EndGateway) bs;

                retv.set("exitlocation", ObjectGenerator.GetGenerator().location(new BukkitMCLocation(state.getExitLocation())), t);
                retv.set("exactteleport", CBoolean.get(state.isExactTeleport()), t);

            }*/ else if (bs instanceof FlowerPot) {

                FlowerPot state = (FlowerPot) bs;

                retv.set("content", state.getContents().getItemType().name());

            } else if (bs instanceof Furnace) {

                Furnace state = (Furnace) bs;

                CArray inv = DataHandling.inventoryToArray(state.getInventory());
                retv.set("inventory", inv, t);

                retv.set("cooktime", new CInt(state.getCookTime(), t), t);
                retv.set("burntime", new CInt(state.getBurnTime(), t), t);
                retv.set("locked", CBoolean.get(state.isLocked()), t);

            } else if (bs instanceof Hopper) {

                Hopper state = (Hopper) bs;

                CArray inv = DataHandling.inventoryToArray(state.getInventory());
                retv.set("inventory", inv, t);

                retv.set("locked", CBoolean.get(state.isLocked()), t);

            } else if (bs instanceof Jukebox) {

                Jukebox state = (Jukebox) bs;

                retv.set("playing", state.getPlaying().name());
                retv.set("isplaying", CBoolean.get(state.isPlaying()), t);

            } else if (bs instanceof NoteBlock) {

                NoteBlock state = (NoteBlock) bs;

                CArray note = new CArray(t);
                note.set("octave", new CInt(state.getNote().getOctave(), t), t);
                note.set("tone", new CInt(state.getNote().getTone().getId(), t), t);
                note.set("sharped", CBoolean.get(state.getNote().isSharped()), t);
                retv.set("note", note, t);

            } else if (bs instanceof ShulkerBox) {

                ShulkerBox state = (ShulkerBox) bs;

                if(!(state.getInventory() == null)){
                    CArray inv = DataHandling.inventoryToArray(state.getInventory());
                    retv.set("inventory", inv, t);
                }

                CArray color = new CArray(t);
                color.set("red", new CInt(state.getColor().getColor().getRed(), t), t);
                color.set("green", new CInt(state.getColor().getColor().getGreen(), t), t);
                color.set("blue", new CInt(state.getColor().getColor().getBlue(), t), t);
                retv.set("color", color, t);

            } else if (bs instanceof Sign) {

                Sign state = (Sign) bs;

                CArray lines = new CArray(t);
                for(String line : state.getLines())
                    lines.push(new CString(line, t), t);
                retv.set("line", lines, t);

            } else if (bs instanceof Skull) {

                Skull state = (Skull) bs;

                retv.set("owner", String.valueOf(state.getOwningPlayer().getUniqueId()));
                retv.set("type", state.getSkullType().name());
                retv.set("hasowner", CBoolean.get(state.hasOwner()), t);

                CArray rot = new CArray(t);
                rot.set("X", new CInt(state.getRotation().getModX(), t), t);
                rot.set("Y", new CInt(state.getRotation().getModY(), t), t);
                rot.set("Z", new CInt(state.getRotation().getModZ(), t), t);
                retv.set("rotation", rot, t);

            }

            return retv;
        }

        public Version since() {
            return new SimpleVersion(1, 0, 0);
        }

        public String getName() {
            return "chu_block_info";
        }

        public Integer[] numArgs() {
            return new Integer[]{ 2 };
        }

        public String docs() {
            return "Array (locationArray, world) get Block Array";
        }
    }

    @api
    public static class chu_set_block_info extends AbstractFunction{

        @SuppressWarnings("unchecked")
        public Class<? extends CREThrowable>[] thrown() {
            return new Class[0];
        }

        public boolean isRestricted() {
            return false;
        }

        public Boolean runAsync() {
            return null;
        }

        public Construct exec(Target t, Environment env, Construct... args) throws ConfigRuntimeException {
            if (!(args[0] instanceof CArray))
                throw new CRECastException("Expecting an array, but received " + args[0].val() + " instead.", t);

            CArray ca = (CArray) args[0];
            MCWorld w = Static.getWorld(args[1], t);
            MCLocation mcloc = ObjectGenerator.GetGenerator().location(ca, w, t);
            Location loc = ((BukkitMCLocation) mcloc)._Location();
            Block b = ((BukkitMCWorld)w).__World().getBlockAt(loc);
            BlockState bs = b.getState();
            String key = args[2].val();

            if (bs instanceof Banner) {

                Banner state = (Banner) bs;

                if(key.equalsIgnoreCase("basecolor")) {

                    if(!(args[3] instanceof CArray))
                        throw new CRECastException("Expecting an array, but received " + args[3].val() + " instead.", t);

                    CArray arg3 = (CArray)args[3];
                    DyeColor base = state.getBaseColor();
                    for(Construct k : arg3.keySet()){
                        if(k.val().equalsIgnoreCase("red")){
                            if(DataHandling.isNumeric(arg3.get(k, t).val()))
                                base.getColor().setRed(Integer.parseInt(arg3.get(k, t).val()));
                        }else if(k.val().equalsIgnoreCase("blue")){
                            if(DataHandling.isNumeric(arg3.get(k, t).val()))
                                base.getColor().setBlue(Integer.parseInt(arg3.get(k, t).val()));
                        }else if(k.val().equalsIgnoreCase("green")){
                            if(DataHandling.isNumeric(arg3.get(k, t).val()))
                                base.getColor().setGreen(Integer.parseInt(arg3.get(k, t).val()));
                        }
                    }
                    state.setBaseColor(base);
                    state.update();

                }else if(key.equalsIgnoreCase("patterns")){

                    if(!(args[3] instanceof CArray))
                        throw new CRECastException("Expecting an array, but received " + args[3].val() + " instead.", t);

                    CArray arg3 = (CArray)args[3];
                    List<Pattern> pats = new ArrayList<Pattern>();
                    for(Construct val : arg3.asList()){
                        if(val instanceof CArray){

                            CArray arr = (CArray) val;
                            Bukkit.broadcastMessage(arr.get("type",t).val()+" <<");
                            PatternType type = PatternType.valueOf(arr.get("type", t).val());
                            if(type == null)
                                throw new CRECastException(arr.get("type", t).val() + " is not PatternType.", t);

                            int red = 0; int green = 0; int blue = 0;

                            if(arg3.get("color", t) instanceof CArray){
                                CArray colors = (CArray) arg3.get("color", t);
                                for(Construct k : arg3.keySet()){
                                    if(k.val().equalsIgnoreCase("red")){
                                        if(DataHandling.isNumeric(arg3.get(k, t).val()))
                                            red = Integer.parseInt(arg3.get(k, t).val());
                                    }else if(k.val().equalsIgnoreCase("blue")){
                                        if(DataHandling.isNumeric(arg3.get(k, t).val()))
                                            blue = Integer.parseInt(arg3.get(k, t).val());
                                    }else if(k.val().equalsIgnoreCase("green")){
                                        if(DataHandling.isNumeric(arg3.get(k, t).val()))
                                            green = Integer.parseInt(arg3.get(k, t).val());
                                    }
                                }
                            }

                            DyeColor color = DyeColor.getByColor(Color.fromRGB(red, green, blue));
                            pats.add(new Pattern(color, type));
                        }else{
                            throw new CRECastException("Expecting an array, but received " + val.val() + " instead.", t);
                        }
                    }
                    state.setPatterns(pats);
                    state.update();
                }

            } else if (bs instanceof Beacon) {

                Beacon state = (Beacon) bs;

                if(key.equalsIgnoreCase("primaryeffect")){
                    PotionEffectType type = PotionEffectType.getByName(args[3].val());
                    if(type == null)
                        throw new CRECastException(args[3].val() + " is not PotionEffectType.", t);
                    state.setPrimaryEffect(type);
                    state.update();
                }
                if(key.equalsIgnoreCase("secondaryeffect")){
                    PotionEffectType type = PotionEffectType.getByName(args[3].val());
                    if(type == null)
                        throw new CRECastException(args[3].val() + " is not PotionEffectType.", t);
                    state.setSecondaryEffect(type);
                    state.update();
                }

            } else if (bs instanceof Bed) {

                Bed state = (Bed) bs;

                if(key.equalsIgnoreCase("color")) {

                    DyeColor color = DyeColor.valueOf(args[3].val());
                    String colors = "";
                    for(DyeColor dc : DyeColor.values())
                        colors = colors.equals("")?dc.name():(colors+", "+dc.name());

                    state.setColor(color);
                    state.update();


                }

            } else if (bs instanceof BrewingStand) {

                BrewingStand state = (BrewingStand) bs;

                if(key.equalsIgnoreCase("brewingtime")){

                    if(!(args[3] instanceof CInt))
                        throw new CRECastException("Expecting an integer, but received " + args[3].val() + " instead.", t);
                    state.setBrewingTime(Integer.parseInt(args[3].val()));
                    state.update();

                }else if(key.equalsIgnoreCase("fuellevel")){

                    if(!(args[3] instanceof CInt))
                        throw new CRECastException("Expecting an integer, but received " + args[3].val() + " instead.", t);
                    state.setFuelLevel(Integer.parseInt(args[3].val()));
                    state.update();

                }

            }else if (bs instanceof CommandBlock) {

                CommandBlock state = (CommandBlock) bs;

                if(key.equalsIgnoreCase("command")) {
                    state.setCommand(args[3].val());
                    state.update();
                }else if(key.equalsIgnoreCase("name")) {
                    state.setName(args[3].val());
                    state.update();
                }

            } else if (bs instanceof CreatureSpawner) {

                CreatureSpawner state = (CreatureSpawner) bs;

                if(key.equalsIgnoreCase("delay")){

                    if(!(args[3] instanceof CInt))
                        throw new CRECastException("Expecting an integer, but received " + args[3].val() + " instead.", t);
                    state.setDelay(Integer.parseInt(args[3].val()));
                    state.update();

                }else if(key.equalsIgnoreCase("spawntype")){

                    EntityType type = EntityType.valueOf(args[3].val());
                    if(type == null)
                        throw new CRECastException(args[3].val() + " is not EntityType.", t);
                    state.setSpawnedType(type);
                    state.update();

                }

            }/* else if (bs instanceof EndGateway) {

                EndGateway state = (EndGateway) bs;

                retv.set("exitlocation", ObjectGenerator.GetGenerator().location(new BukkitMCLocation(state.getExitLocation())), t);
                retv.set("exactteleport", CBoolean.get(state.isExactTeleport()), t);

            }*/ else if (bs instanceof FlowerPot) {

                FlowerPot state = (FlowerPot) bs;

                if(key.equalsIgnoreCase("content")){

                    Material mate = Material.valueOf(args[3].val());
                    if(mate == null)
                        throw new CRECastException(args[3].val() + " is not Material.", t);
                    state.setContents(new MaterialData(mate));
                    state.update();

                }

            } else if (bs instanceof Furnace) {

                Furnace state = (Furnace) bs;

                if(key.equalsIgnoreCase("burntime")){

                    if(!(args[3] instanceof CInt))
                        throw new CRECastException("Expecting an integer, but received " + args[3].val() + " instead.", t);
                    state.setBurnTime(Short.parseShort(args[3].val()));
                    state.update();

                }else if(key.equalsIgnoreCase("cooktime")){

                    if(!(args[3] instanceof CInt))
                        throw new CRECastException("Expecting an integer, but received " + args[3].val() + " instead.", t);
                    state.setCookTime(Short.parseShort(args[3].val()));
                    state.update();

                }

            } else if (bs instanceof Jukebox) {

                Jukebox state = (Jukebox) bs;

                if(key.equalsIgnoreCase("playing")){

                    Material mate = Material.valueOf(args[3].val());
                    if(mate == null)
                        throw new CRECastException(args[3].val() + " is not Material.", t);
                    state.setPlaying(mate);
                    state.update();

                }

            } else if (bs instanceof NoteBlock) {

                NoteBlock state = (NoteBlock) bs;
                if(key.equalsIgnoreCase("note")){
                    if(!(args[3] instanceof CArray))
                        throw new CRECastException("Expecting an array, but received " + args[3].val() + " instead.", t);
                    CArray arr = (CArray)args[3];
                    int octave = 0;
                    List<String> tones = new ArrayList<String>();
                    tones.add("A"); tones.add("B"); tones.add("C"); tones.add("D"); tones.add("E"); tones.add("F"); tones.add("G");
                    Note.Tone tone = Note.Tone.A;
                    boolean sharped = false;
                    for(Construct k : arr.keySet()){
                        if(k.val().equalsIgnoreCase("octave")){
                            if(k instanceof CInt)
                                octave = Integer.parseInt(arr.get(k, t).val());
                        }else if(k.val().equalsIgnoreCase("tone")){
                            if(tones.contains(arr.get(k, t).val()))
                                tone = Note.Tone.valueOf(arr.get(k, t).val());
                        }else if(k.val().equalsIgnoreCase("sharped")){
                            if(arr.get(k, t).val().equalsIgnoreCase("true"))
                                sharped = true;
                            else sharped = false;
                        }
                    }
                    state.setNote(new Note(octave, tone, sharped));
                    state.update();
                }

            } else if (bs instanceof Sign) {

                Sign state = (Sign) bs;

                if(key.equalsIgnoreCase("line")){

                    if(!(args[3] instanceof CArray))
                        throw new CRECastException("Expecting an array, but received " + args[3].val() + " instead.", t);
                    CArray arr = (CArray)args[3];

                    for(Construct k : arr.keySet()){
                        if(k.val().equals("0"))
                            state.setLine(0, arr.get(k, t).val());
                        if(k.val().equals("1"))
                            state.setLine(1, arr.get(k, t).val());
                        if(k.val().equals("2"))
                            state.setLine(2, arr.get(k, t).val());
                        if(k.val().equals("3"))
                            state.setLine(3, arr.get(k, t).val());
                    }
                    state.update();

                }

            } else if (bs instanceof Skull) {

                Skull state = (Skull) bs;
                if(key.equalsIgnoreCase("owner")){

                    OfflinePlayer op = ((BukkitMCPlayer)Static.GetPlayer(args[3].val(), t))._Player();
                    state.setOwningPlayer(op);
                    state.update();

                }else if(key.equalsIgnoreCase("type")){

                    SkullType type = null;
                    String types = "";
                    for(SkullType st : SkullType.values()){
                        if(st.name().equalsIgnoreCase(args[3].val()))
                            type = st; types = (types.equals(""))?st.name():(types+", "+st.name());
                    }
                    if(type == null)
                        throw new CRECastException(args[3].val() + " is not SkullType. TYPES : "+types, t);
                    state.setSkullType(type);
                    state.update();

                }

            }
            return CVoid.VOID;
        }

        public Version since() {
            return new SimpleVersion(1, 0, 0);
        }

        public String getName() {
            return "chu_set_block_info";
        }

        public Integer[] numArgs() {
            return new Integer[] { 4 };
        }

        public String docs() {
            return "Void (locationArray, world, key, value) set Block Array";
        }
    }
}


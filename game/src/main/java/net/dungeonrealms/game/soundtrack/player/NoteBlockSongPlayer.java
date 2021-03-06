package net.dungeonrealms.game.soundtrack.player;

import net.dungeonrealms.game.soundtrack.SongPlayer;
import net.dungeonrealms.game.soundtrack.Soundtrack;
import net.dungeonrealms.game.soundtrack.note.Instrument;
import net.dungeonrealms.game.soundtrack.note.Note;
import net.dungeonrealms.game.soundtrack.note.NotePitch;
import net.dungeonrealms.game.soundtrack.player.song.Layer;
import net.dungeonrealms.game.soundtrack.player.song.Song;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

/**
 * Class written by APOLLOSOFTWARE.IO on 8/2/2016
 */

public class NoteBlockSongPlayer extends SongPlayer {
    private Block noteBlock;

    public NoteBlockSongPlayer(Song song) {
        super(song);
    }

    public Block getNoteBlock() {
        return noteBlock;
    }

    public void setNoteBlock(Block noteBlock) {
        this.noteBlock = noteBlock;
    }

    @Override
    public void playTick(Player p, int tick) {
        if (noteBlock.getType() != Material.NOTE_BLOCK) {
            return;
        }
        if (!p.getWorld().getName().equals(noteBlock.getWorld().getName())) {
            // not in same world
            return;
        }
        byte playerVolume = Soundtrack.getPlayerVolume(p);

        for (Layer l : song.getLayerHashMap().values()) {
            Note note = l.getNote(tick);
            if (note == null) {
                continue;
            }
            p.playNote(noteBlock.getLocation(), Instrument.getBukkitInstrument(note.getInstrument()),
                    new org.bukkit.Note(note.getKey() - 33));
            p.playSound(noteBlock.getLocation(),
                    Instrument.getInstrument(note.getInstrument()),
                    (l.getVolume() * (int) volume * (int) playerVolume) / 1000000f,
                    NotePitch.getPitch(note.getKey() - 33));
        }
    }
}

package com.rosemods.windswept.core.data.client;

import com.rosemods.windswept.core.Windswept;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.data.SoundDefinition.Sound;
import net.minecraftforge.common.data.SoundDefinitionsProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Consumer;

import static com.rosemods.windswept.core.registry.WindsweptSounds.*;

public class WindsweptSoundProvider extends SoundDefinitionsProvider {
    public WindsweptSoundProvider(GatherDataEvent event) {
        super(event.getGenerator(), Windswept.MOD_ID, event.getExistingFileHelper());
    }

    @Override
    public void registerSounds() {
        this.register(MUSIC_DISC_RAIN, "records/rain", Sound::stream);
        this.register(MUSIC_DISC_SNOW, "records/snow", Sound::stream);
        this.register(MUSIC_DISC_BUMBLEBEE, "records/bumblebee", Sound::stream);
        this.register(PINECONE_NOTE, "pinecone_note", Sound::stream);

        this.register(CHILLED_DEATH, "entity/chilled/death/death", 4, 1.3f);
        this.register(CHILLED_HURT, "entity/chilled/hurt/hurt", 3, 1.2f);
        this.register(CHILLED_AMBIENT, "entity/chilled/ambient/idle", 3, 1f);
    }

    private void register(RegistryObject<SoundEvent> soundEvent, String location, Consumer<Sound> consumer) {
        Sound sound = sound(Windswept.location(location));
        if (consumer != null)
            consumer.accept(sound);

        this.add(soundEvent.get(), definition().with(sound));
    }

    private void register(RegistryObject<SoundEvent> soundEvent, String name, int amount, float volume) {
        Sound[] sounds = new Sound[amount];

        for (int i = 1; i <= amount; i++)
            sounds[i - 1] = sound(Windswept.location(name + i)).volume(volume);

        this.add(soundEvent.get(), definition().with(sounds));
    }

}


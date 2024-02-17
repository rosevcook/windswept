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
    }

    private void register(RegistryObject<SoundEvent> soundEvent, String location, Consumer<Sound> consumer) {
        Sound sound = sound(Windswept.location(location));
        if (consumer != null)
            consumer.accept(sound);

        this.add(soundEvent.get(), definition().with(sound));
    }

}


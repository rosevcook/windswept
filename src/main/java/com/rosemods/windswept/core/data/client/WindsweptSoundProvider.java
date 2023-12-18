package com.rosemods.windswept.core.data.client;

import com.rosemods.windswept.core.Windswept;
import com.rosemods.windswept.core.registry.WindsweptSounds;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.data.SoundDefinition.Sound;
import net.minecraftforge.common.data.SoundDefinitionsProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Consumer;

public class WindsweptSoundProvider extends SoundDefinitionsProvider {

    public WindsweptSoundProvider(GatherDataEvent event) {
        super(event.getGenerator(), Windswept.MOD_ID, event.getExistingFileHelper());
    }

    @Override
    public void registerSounds() {
        this.register(WindsweptSounds.MUSIC_DISC_RAIN, "records/rain", Sound::stream);
        this.register(WindsweptSounds.MUSIC_DISC_SNOW, "records/snow", Sound::stream);
        this.register(WindsweptSounds.MUSIC_DISC_BUMBLEBEE, "records/bumblebee", Sound::stream);
    }

    private void register(RegistryObject<SoundEvent> soundEvent, String location, Consumer<Sound> consumer) {
        Sound sound = sound(Windswept.location(location));
        if (consumer != null)
            consumer.accept(sound);

        this.add(soundEvent.get(), definition().with(sound));
    }

}


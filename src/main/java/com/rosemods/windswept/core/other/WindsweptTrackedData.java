package com.rosemods.windswept.core.other;

import com.rosemods.windswept.core.Windswept;
import com.teamabnormals.blueprint.common.world.storage.tracking.DataProcessors;
import com.teamabnormals.blueprint.common.world.storage.tracking.TrackedData;
import com.teamabnormals.blueprint.common.world.storage.tracking.TrackedDataManager;

public class WindsweptTrackedData {
    public static final TrackedData<Boolean> CANNOT_PANIC = TrackedData.Builder
            .create(DataProcessors.BOOLEAN, () -> false).build();

    public static void registerTrackedData() {
        TrackedDataManager.INSTANCE.registerData(Windswept.location("cannot_panic"), CANNOT_PANIC);
    }
}


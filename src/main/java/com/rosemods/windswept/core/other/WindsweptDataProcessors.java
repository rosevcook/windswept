package com.rosemods.windswept.core.other;

import com.rosemods.windswept.core.Windswept;
import com.teamabnormals.blueprint.common.world.storage.tracking.DataProcessors;
import com.teamabnormals.blueprint.common.world.storage.tracking.TrackedData;
import com.teamabnormals.blueprint.common.world.storage.tracking.TrackedDataManager;

public final class WindsweptDataProcessors {
    public static final TrackedData<Integer> FREEZE_CONVERT_TIME = TrackedData.Builder.create(DataProcessors.INT, () -> 0).enableSaving().build();
    public static final TrackedData<Integer> POWDER_SNOW_TIME = TrackedData.Builder.create(DataProcessors.INT, () -> 0).enableSaving().build();
    public static final TrackedData<Boolean> IS_FREEZE_CONVERTING = TrackedData.Builder.create(DataProcessors.BOOLEAN, () -> false).enableSaving().build();
    public static final TrackedData<Boolean> CANNOT_PANIC = TrackedData.Builder.create(DataProcessors.BOOLEAN, () -> false).build();

    public static void registerData() {
        register("freeze_convert_time", FREEZE_CONVERT_TIME);
        register("powder_snow_time", POWDER_SNOW_TIME);
        register("is_freeze_converting", IS_FREEZE_CONVERTING);
        register("cannot_panic", CANNOT_PANIC);
    }

    private static void register(String name, TrackedData<?> data) {
        TrackedDataManager.INSTANCE.registerData(Windswept.location(name), data);
    }

}

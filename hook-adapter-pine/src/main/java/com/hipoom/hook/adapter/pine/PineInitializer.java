package com.hipoom.hook.adapter.pine;

import com.hipoom.hook.adapter.HookStyleFactory;
import top.canyie.pine.PineConfig;

/**
 * @author ZhengHaiPeng
 * @since 2024/7/28 15:44
 */
public class PineInitializer {

    /* ======================================================= */
    /* Public Methods                                          */
    /* ======================================================= */

    public static void init() {
        HookStyleFactory.registry("pine", new PineHookStyle());
        PineConfig.debug = false;
        PineConfig.debuggable = BuildConfig.DEBUG;
    }

}

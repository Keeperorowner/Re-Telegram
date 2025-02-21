package nep.timeline.re_telegram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import nep.timeline.re_telegram.application.ApplicationLoaderHook;
import nep.timeline.re_telegram.features.NEWAntiRecall;

public class HookInit implements IXposedHookLoadPackage {
    private static final List<String> hookPackages = Arrays.asList("org.telegram.messenger", "org.telegram.messenger.web", "org.telegram.messenger.beta", "org.telegram.plus",
            "tw.nekomimi.nekogram",
            "com.cool2645.nekolite",
            "com.exteragram.messenger",
            "org.forkgram.messenger",
            "org.forkclient.messenger",
            "org.forkclient.messenger.beta",
            "uz.unnarsx.cherrygram",
            "it.octogram.android",
            "xyz.nextalone.nnngram",
            "it.belloworld.mercurygram");
    private static final List<String> hookPackagesCustomization = Arrays.asList("xyz.nextalone.nagram",
            "nekox.messenger", "com.xtaolabs.pagergram", "nekox.messenger.broken");
    public static final boolean DEBUG_MODE = false;

    public final List<String> getHookPackages()
    {
        List<String> hookPackagesLocal = new ArrayList<>(hookPackages);
        hookPackagesLocal.addAll(hookPackagesCustomization);
        return hookPackagesLocal;
    }

    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) {
        if (getHookPackages().contains(lpparam.packageName))
        {
            if (DEBUG_MODE)
                Utils.log("Trying to hook app: " + lpparam.packageName);

            Utils.pkgName = lpparam.packageName;
            ClassLoader classLoader = lpparam.classLoader;

            ApplicationLoaderHook.init(classLoader);

                NEWAntiRecall.initUI(classLoader);

                NEWAntiRecall.initProcessing(classLoader);

                NEWAntiRecall.init(classLoader);


            //AllowMoveAllChatFolder.init(classLoader);


            if (ClientChecker.check(ClientChecker.ClientType.Cherrygram)) {
                Class<?> checker = XposedHelpers.findClassIfExists("org.telegram.tgnet.ConnectionsManagerImpl", classLoader);
                XposedHelpers.findAndHookMethod(checker, "nfhpo4yruoi1je3", XC_MethodReplacement.DO_NOTHING);
                XposedHelpers.findAndHookMethod(checker, "g45ytgt513", XC_MethodReplacement.DO_NOTHING);
            }

/*
          if (!onlyNeedAR(lpparam.packageName))
            {
                if (!ClientChecker.check(ClientChecker.ClientType.MDgram))
                    UseSystemTypeface.init(classLoader);

                if (!lpparam.packageName.equals("xyz.nextalone.nnngram")) {
                    if (!ClientChecker.check(ClientChecker.ClientType.Nekogram))
                        ProhibitChannelSwitching.init(classLoader);

                    if (!notNeedHideStories.contains(lpparam.packageName))
                        HideStories.init(classLoader);

                    NoSponsoredMessages.init(classLoader);
                }

                AntiAntiForward.init(classLoader);
            }

 */
        }
    }
}

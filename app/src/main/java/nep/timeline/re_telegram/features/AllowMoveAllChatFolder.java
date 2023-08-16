package nep.timeline.re_telegram.features;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import nep.timeline.re_telegram.ClientChecker;
import nep.timeline.re_telegram.HookUtils;
import nep.timeline.re_telegram.Utils;
import nep.timeline.re_telegram.configs.Configs;
import nep.timeline.re_telegram.obfuscate.AutomationResolver;

public class AllowMoveAllChatFolder {
    public static void init(XC_LoadPackage.LoadPackageParam lpparam)
    {
        String dialogsActivity$6Name = AutomationResolver.resolve("org.telegram.ui.DialogsActivity$6");
        if (ClientChecker.check(ClientChecker.ClientType.iMoe))
            dialogsActivity$6Name = "org.telegram.ui.DialogsActivity$11";
        if (ClientChecker.check(ClientChecker.ClientType.AyuGram))
            dialogsActivity$6Name = "org.telegram.ui.DialogsActivity$5";
        Class<?> dialogsActivity$6 = XposedHelpers.findClassIfExists(dialogsActivity$6Name, lpparam.classLoader);
        if (dialogsActivity$6 != null)
        {
            String onDefaultTabMoved = AutomationResolver.resolve("DialogsActivity$6", "onDefaultTabMoved", AutomationResolver.ResolverType.Method);
            HookUtils.findAndHookAllMethod(dialogsActivity$6, onDefaultTabMoved, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    if (Configs.isAllowMoveAllChatFolder())
                        param.setResult(null);
                }
            });
        }
        else
        {
            Utils.log("Not found DialogsActivity$6, " + Utils.issue);
        }

        Class<?> filtersSetupActivity = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.Components.FilterTabsView$TouchHelperCallback"), lpparam.classLoader);
        if (filtersSetupActivity != null)
        {
            String onDefaultTabMoved = AutomationResolver.resolve("FilterTabsView$TouchHelperCallback", "lambda$new$0", AutomationResolver.ResolverType.Method);
            HookUtils.findAndHookAllMethod(filtersSetupActivity, onDefaultTabMoved, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    if (Configs.isAllowMoveAllChatFolder())
                        param.setResult(null);
                }
            });
        }
        else
        {
            Utils.log("Not found FiltersSetupActivity, " + Utils.issue);
        }
    }
}

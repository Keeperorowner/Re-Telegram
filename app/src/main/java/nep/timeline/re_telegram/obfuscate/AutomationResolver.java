package nep.timeline.re_telegram.obfuscate;

import nep.timeline.re_telegram.ClientChecker;
import nep.timeline.re_telegram.Utils;
import nep.timeline.re_telegram.obfuscate.resolves.Nekogram;

public class AutomationResolver {
    public static String resolve(String className, String pkgName)
    {
        if (ClientChecker.check(ClientChecker.ClientType.Nekogram, pkgName))
        {
            if (Nekogram.ClassResolver.has(className))
                return Nekogram.ClassResolver.resolve(className);
        }
        return className;
    }

    public static String resolve(String className, String name, ResolverType type, String pkgName)
    {
        if (ClientChecker.check(ClientChecker.ClientType.Nekogram, pkgName))
        {
            if (type == ResolverType.Field)
            {
                if (Nekogram.FieldResolver.has(className, name))
                    return Nekogram.FieldResolver.resolve(className, name);
            }
            else if (type == ResolverType.Method)
            {
                if (Nekogram.MethodResolver.has(className, name))
                    return Nekogram.MethodResolver.resolve(className, name);
            }
        }
        return name;
    }

    public static String resolve(String className)
    {
        return resolve(className, Utils.pkgName);
    }

    public static String resolve(String className, String name, ResolverType type)
    {
        return resolve(className, name, type, Utils.pkgName);
    }

    public enum ResolverType
    {
        Field,
        Method
    }
}

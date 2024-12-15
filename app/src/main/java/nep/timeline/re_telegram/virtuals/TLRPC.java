package nep.timeline.re_telegram.virtuals;

import java.lang.reflect.Field;
import java.util.ArrayList;

import de.robv.android.xposed.XposedHelpers;
import nep.timeline.re_telegram.ClientChecker;
import nep.timeline.re_telegram.Utils;
import nep.timeline.re_telegram.obfuscate.AutomationResolver;
import nep.timeline.re_telegram.utils.FieldUtils;

public class TLRPC {
    public static class Peer {
        private final Object instance;
        private final Class<?> clazz;

        public Peer(Object instance)
        {
            this.instance = instance;
            if (!instance.getClass().getName().equals(AutomationResolver.resolve("org.telegram.tgnet.TLRPC$Peer")))
            {
                Class<?> clazz = instance.getClass().getSuperclass();
                if (clazz != null && !clazz.getName().equals(AutomationResolver.resolve("org.telegram.tgnet.TLRPC$Peer")))
                    this.clazz = clazz.getSuperclass();
                else
                    this.clazz = clazz;
            }
            else
            {
                this.clazz = instance.getClass();
            }
        }

        public long getChannelID()
        {
            try
            {
                if (ClientChecker.check(ClientChecker.ClientType.Nekogram)) {
                    Field field = FieldUtils.getFieldFromMultiName(this.clazz, AutomationResolver.resolve("TLRPC$Peer", "channel_id", AutomationResolver.ResolverType.Field), long.class);
                    if (field != null)
                        return field.getLong(this.instance);
                }
                else
                    return FieldUtils.getFieldLongOfClass(this.instance, this.clazz, AutomationResolver.resolve("TLRPC$Peer", "channel_id", AutomationResolver.ResolverType.Field));
            }
            catch (IllegalAccessException e)
            {
                Utils.log(e);
            }
            return Long.MIN_VALUE;
        }
    }

    public static class Message {
        private final Object instance;
        private final Class<?> clazz;

        public Message(Object instance)
        {
            this.instance = instance;
            if (!instance.getClass().getName().equals(AutomationResolver.resolve("org.telegram.tgnet.TLRPC$Message")))
            {
                Class<?> clazz = instance.getClass().getSuperclass();
                if (clazz != null && !clazz.getName().equals(AutomationResolver.resolve("org.telegram.tgnet.TLRPC$Message")))
                    this.clazz = clazz.getSuperclass();
                else
                    this.clazz = clazz;
            }
            else
            {
                this.clazz = instance.getClass();
            }
        }

        public int getID()
        {
            try
            {
                if (ClientChecker.check(ClientChecker.ClientType.Nekogram)) {
                    Field field = FieldUtils.getFieldFromMultiName(this.clazz, AutomationResolver.resolve("TLRPC$Message", "id", AutomationResolver.ResolverType.Field), int.class);
                    if (field != null)
                        return field.getInt(this.instance);
                }
                else
                    return FieldUtils.getFieldIntOfClass(this.instance, this.clazz, "id");
            }
            catch (IllegalAccessException e)
            {
                Utils.log(e);
            }
            return Integer.MIN_VALUE;
        }

        public Peer getPeerID()
        {
            try
            {
                if (ClientChecker.check(ClientChecker.ClientType.Nekogram)){
                    Field field = FieldUtils.getFieldFromMultiName(this.clazz, AutomationResolver.resolve("TLRPC$Message", "peer_id", AutomationResolver.ResolverType.Field), AutomationResolver.resolve("org.telegram.tgnet.TLRPC$Peer"));
                    if (field != null) {
                        Object peer = field.get(this.instance);
                        if (peer != null)
                            return new Peer(peer);
                    }
                }
                else {
                    Object peer = FieldUtils.getFieldClassOfClass(this.instance, this.clazz, "peer_id");
                    if (peer != null)
                        return new Peer(peer);
                }
            }
            catch (IllegalAccessException e)
            {
                Utils.log(e);
            }
            return null;
        }

        public int getFlags()
        {
            try
            {
                if (ClientChecker.check(ClientChecker.ClientType.Nekogram)) {
                    Field field = FieldUtils.getFieldFromMultiName(this.clazz, AutomationResolver.resolve("TLRPC$Message", "flags", AutomationResolver.ResolverType.Field), int.class);
                    if (field != null)
                        return field.getInt(this.instance);
                }
                else
                    return XposedHelpers.getIntField(this.instance, "flags");
            }
            catch (IllegalAccessException e)
            {
                Utils.log(e);
            }
            return Integer.MIN_VALUE;
        }

        public void setFlags(int flags) {
            try {
                if (ClientChecker.check(ClientChecker.ClientType.Nekogram)) {
                    Field field = FieldUtils.getFieldFromMultiName(this.clazz, AutomationResolver.resolve("TLRPC$Message", "flags", AutomationResolver.ResolverType.Field), int.class);
                    if (field != null)
                        field.setInt(this.instance, flags);
                } else XposedHelpers.setIntField(this.instance, "flags", flags);
            } catch (IllegalAccessException e)
            {
                Utils.log(e);
            }
        }
    }

    /*
    public static class TL_updateDeleteScheduledMessages {
        private final Object instance;

        public TL_updateDeleteScheduledMessages(Object instance)
        {
            this.instance = instance;
        }

        public ArrayList<Integer> getMessages()
        {
            return Utils.castList(FieldUtils.getFieldClassOfClass(this.instance, "messages"), Integer.class);
        }
    }
*/
    public static class TL_updateDeleteChannelMessages {
        private final Object instance;

        public TL_updateDeleteChannelMessages(Object instance)
        {
            this.instance = instance;
        }

        public long getChannelID()
        {
            try
            {
                if (ClientChecker.check(ClientChecker.ClientType.Nekogram)) {
                    Field field = FieldUtils.getFieldFromMultiName(this.instance.getClass(), AutomationResolver.resolve("TLRPC$TL_updateDeleteChannelMessages", "channel_id", AutomationResolver.ResolverType.Field), long.class);
                    if (field != null)
                        return field.getLong(this.instance);
                }
                else
                    return FieldUtils.getFieldLongOfClass(this.instance, "channel_id");
            }
            catch (IllegalAccessException e)
            {
                Utils.log(e);
            }
            return Long.MIN_VALUE;
        }

        public ArrayList<Integer> getMessages()
        {
            try
            {
                if (ClientChecker.check(ClientChecker.ClientType.Nekogram)) {
                    Field field = FieldUtils.getFieldFromMultiName(this.instance.getClass(), AutomationResolver.resolve("TLRPC$TL_updateDeleteChannelMessages", "messages", AutomationResolver.ResolverType.Field), ArrayList.class);
                    if (field != null) {
                        Object messages = field.get(this.instance);
                        if (messages != null)
                            return Utils.castList(messages, Integer.class);
                    }
                }
                else
                    return Utils.castList(FieldUtils.getFieldClassOfClass(this.instance, "messages"), Integer.class);
            }
            catch (IllegalAccessException e)
            {
                Utils.log(e);
            }
            return null;
        }
    }

    public static class TL_updateDeleteMessages {
        private final Object instance;

        public TL_updateDeleteMessages(Object instance)
        {
            this.instance = instance;
        }

        public ArrayList<Integer> getMessages()
        {
            try
            {
                if (ClientChecker.check(ClientChecker.ClientType.Nekogram)) {
                    Field field = FieldUtils.getFieldFromMultiName(this.instance.getClass(), AutomationResolver.resolve("TLRPC$TL_updateDeleteMessages", "messages", AutomationResolver.ResolverType.Field), ArrayList.class);
                    if (field != null) {
                        Object messages = field.get(this.instance);
                        if (messages != null)
                            return Utils.castList(messages, Integer.class);
                    }
                }
                else
                    return Utils.castList(FieldUtils.getFieldClassOfClass(this.instance, "messages"), Integer.class);
            }
            catch (IllegalAccessException e)
            {
                Utils.log(e);
            }
            return null;
        }
    }
}
